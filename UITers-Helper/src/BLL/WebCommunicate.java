/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import Exception.CannotBrowseCourseException;
import Exception.CannotLoginException;
import Exception.NotLoggedInException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 *
 * @author mirushi
 */



public class WebCommunicate {
    
    boolean isLoggedIn = false;
    WebDriver driver;
    String mssv;
    String password;
    
    //Lưu lại danh sách các courses.
    ArrayList<Course> currentCourses;
    
    //Lưu lại danh sách các deadline.
    HashMap <String, ArrayList<Deadline>> deadlineOfCourses;
    
    //Lưu lại danh sách các thông báo của courses.
    HashMap <String, ArrayList<Advertise>> adverties;
    
    //Danh sách các khoa, để phục vụ cho việc lọc ra xem đâu là courses môn học, đâu là courses khác.
    ArrayList<String> facultiesList;
    
    public static void SetProperty()
    {
        //Lấy path trực tiếp từ class Global.
       System.setProperty("webdriver.ie.driver", Global.IEDriverPath);
       System.setProperty("webdriver.gecko.driver",Global.FirefoxDriverPath);
    }
    
    public WebCommunicate(WebDriverMode driverMode, String _mssv, String _password)
    {
        SetProperty();
        switch (driverMode) {
            case Firefox:
                driver = new FirefoxDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            case HtmlUnitDriver:
                driver = new HtmlUnitDriver();
                break;
            default:
                System.out.println("You selected an invalid driver mode. Won't create any driver and web communicate will not function.");
                break;
        }
        
        mssv = _mssv;
        password = _password;
        
        //Khởi tạo 1 danh sách courses rỗng.
        currentCourses = new ArrayList<Course>();
        
        //Tạo 1 danh sách các deadlines rỗng cho các courses.
        deadlineOfCourses = new HashMap<String, ArrayList<Deadline>>();
        
        //Tạo 1 danh sách các thông báo rỗng.
        adverties = new HashMap<String, ArrayList<Advertise>>();
        
        //Tạo danh sách các khoa.
        InitializeFalcultyList();
    }
    
    public void ExecuteLogin() throws CannotBrowseCourseException, CannotLoginException
    {
        //Mở trang chủ của courses.
        driver.get("https://courses.uit.edu.vn");
        
        //Kiểm tra xem trang đã được load đúng chưa.
        String url = driver.getCurrentUrl();
        if (url.equalsIgnoreCase("https://courses.uit.edu.vn/login/index.php"))
            System.out.println("Courses loaded successfully !");
        else
            throw new CannotBrowseCourseException("Can't open courses.uit.edu.vn. Please check your internet connection.");
        
        //Input login info.
        WebElement loginMSSV = driver.findElement(By.id("username"));
        loginMSSV.sendKeys(mssv);

        WebElement loginPasswd = driver.findElement(By.id("password"));
        loginPasswd.sendKeys(password);
        
        //Click login button.
        driver.findElement(By.id("loginbtn")).click();
        //Get page content.
        String pageSource = driver.getPageSource();

        //Set login success signal.
        if (isLoggedIn())
        {
            isLoggedIn = true;
            System.out.println("Logged in successfully !");
        }
            
        else
        {
            isLoggedIn = false;
            throw new CannotLoginException("Cannot login. Please check username and password !");
        }
            
    }
    
    //Cờ wantUpdate dùng để đưa tín hiệu cho hàm xem có muốn lấy lại danh sách môn học trước đó không.
    //Việc để cờ wantUpdate nên được cân nhắc kỹ, vì việc lấy danh sách môn học trên courses rất lâu. 
    //Nếu không cần thiết, nên để wantUpdate = false. 
    public ArrayList<Course> GetCoursesList(boolean wantUpdate) throws NotLoggedInException
    {
        if (!isLoggedIn)
            throw new NotLoggedInException("Please login before get courses list.");
        
        //Nếu WebCommunicate đã lưu danh sách courses trước đó và không cần update lại thì trả về ds cũ.
        if (!wantUpdate && currentCourses != null && currentCourses.size() > 0)
            return currentCourses;
        
        List<WebElement> coursesElement = GetCourses();
        System.out.println("Old course not found.");
        currentCourses = new ArrayList<Course>();
        Course tmp;
        
        //Đầu tiên là ta thêm 2 thứ : CourseID và CourseName vào trước.
        for (WebElement c : coursesElement)
        {
            tmp = new Course(GetIDByURL(c.getAttribute("href")),c.getText());
            currentCourses.add(tmp);
        }
        boolean tmpCourseCheck;
        //Tiếp theo ta thêm vào mã lớp.
        for (Course c : currentCourses)
        {
            //Kiểm tra xem courses này có phải là một môn học không.
            if ((tmpCourseCheck = IsGoodCourse(c)) == true)
            {
                c.setCourseCode(GetCodeByCourseID(c.getCourseID()));
                c.setIsRealCourse(tmpCourseCheck);
            }
            else
                c.setCourseCode("NOT A COURSE");
            
        }
        
        //Cuối cùng thì ta trả về danh sách các courses đã lấy được.
        return currentCourses;
    }
    
    /*Nếu bật cờ wantUpdate, hàm GetDeadlines sẽ luôn luôn lấy deadline trực tiếp từ server.
    Nếu không bật cờ wantUpdate, hàm GetDeadlines sẽ trả về giá trị trước đó lấy được đã lưu trong đối tượng WebCommunicate.
    Và nếu trước đó không lưu, hàm GetDeadlines sẽ tự động lấy dữ liệu từ server.
    Để wantUpdate = false, thì hàm sẽ chạy nhanh hơn (do có lưu lại trạng thái trước).*/
    public ArrayList<Deadline> GetDeadlinesByCourse(Course course, boolean wantUpdate) throws NotLoggedInException
    {
        if (!isLoggedIn)
            throw new NotLoggedInException("Please login before get deadlines !");
        
        //Kiểm tra xem deadline của courses đã được lấy về trước đó chưa.
        
        ArrayList<Deadline> deadlines;
        
        if (!wantUpdate && (deadlines = deadlineOfCourses.get(course.getCourseID())) != null )
        {
            return deadlines;
        }
        
        deadlines = new ArrayList<Deadline>();
        
        String courseID = course.getCourseID();
        List<WebElement> deadlineElements = new ArrayList<>();
        String deadlineID = "";
        String deadlineDate = "";
        String deadlineName = "";
        String destURL = "https://courses.uit.edu.vn/course/recent.php?id=" + courseID;
        driver.navigate().to(destURL);
        
        
        //Get all deadlines WebElement.
        try
        {
            deadlineElements = driver.findElements(By.xpath("//*[@class='box generalbox']//a[contains(@href,'assign')]"));        
        }
        catch (NoSuchElementException ex)
        {
            System.out.println("Không tìm thấy deadlines nào !");
        }
        Deadline tmp;
        
        //Get text deadlineElement sẽ trả ra tên deadline. Get Attribute href sẽ trả ra URL của deadline.
        for (int i=0;i<deadlineElements.size();++i)
        {
            tmp = new Deadline();
            deadlineElements = driver.findElements(By.xpath("//*[@class='box generalbox']//a[contains(@href,'assign')]"));
            
            WebElement e = deadlineElements.get(i);
            deadlineID = GetIDByURL(e.getAttribute("href"));
            deadlineName = e.getText();
            driver.get("https://courses.uit.edu.vn/mod/assign/view.php?id=" + deadlineID);
            deadlineDate = driver.findElement(By.xpath("//div[@class='submissionstatustable']/div/table/tbody/tr[3]/td[@class='cell c1 lastcol']")).getText();
            
            tmp.setDeadlineID(deadlineID);
            tmp.setDeadLineName(deadlineName);
            tmp.setDeadLineDate(GetDateTimeFromString(deadlineDate));
            
            deadlines.add(tmp);
            driver.navigate().back();
        }
        
        //Thêm deadlines vào danh sách.
        deadlineOfCourses.put(course.getCourseID(), deadlines);
        
        return deadlines;
    }
    
    //Hàm dùng để dọn dẹp và tắt các WebDriver đang chạy.
    public void Dispose()
    {
        driver.close();
        
    }
    
    public ArrayList<Advertise> GetCourseAdvertisesByCourse(Course _Course, boolean wantUpdate)
    {
        if (!_Course.isRealCourse)
            return null;
        
        ArrayList<Advertise> adv;
        
        String courseCode = _Course.getCourseCode();
        
        if (!wantUpdate && (adv = adverties.get(courseCode)) != null)
        {
            return adv;
        }
        
        List<WebElement> advElement = GetAdvertiseElementByCourseCode(courseCode);
        
        adv = new ArrayList<>();
        Advertise tmp;
        
        for (WebElement e : advElement)
        {
            tmp = new Advertise(_Course, e.getText() , "", LocalDateTime.MAX, LocalDateTime.MIN);
            System.out.println(e.getText());
            adv.add(tmp);
        }
        //Add newly created advertise into hashmap.
        adverties.put(_Course.getCourseCode(), adv);
        
        return adv;
    }
    
    //Bên dưới là các phương thức private. Chỉ nên được sử dụng bên trong class.
    private List<WebElement> GetCourses() {
        if (!isLoggedIn)
        {
            ErrorOccured("Please login first.");
            return null;
        }
        List<WebElement> courses = new ArrayList<>();
        driver.navigate().to("https://courses.uit.edu.vn/my/index.php?mynumber=-2");
        try
        {
            courses = driver.findElements(By.xpath("//*[@class='course_list']//*[@class='box coursebox']//*[@class='course_title']//*[@class='title']//*[@title]"));
        }
        catch (NoSuchElementException ex)
        {
            System.out.println("Không tìm thấy course nào !");
        }
        return courses;
    }
    
    private static LocalDateTime GetDateTimeFromString(String dateTime)
    {
        LocalDateTime res;
        
        //String gốc của chúng ta có kèm cả thứ (Monday, Tuesday, Wednesday,...).
        //Ta cần lược bỏ nó đi.
        String[]split = dateTime.split(", ");
        
        if (split.length < 3)
            return null;
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM uuuu h:m a", Locale.US);
        
        res = LocalDateTime.parse(split[1] + " " + split[2], formatter);
        return res;
    }
    
    private String GetIDByURL(String attr) {
        String[]split = attr.split("id=");
        if (split.length <= 1)
            return "";
        return split[1];
    }
    
    //Hàm này trả về mã môn học (VD IT001.J21). Nhằm mục đích tìm kiếm thông báo trên daa.
    private String GetCodeByCourseID(String courseID)
    {
        driver.get("https://courses.uit.edu.vn/course/view.php?id=" + courseID);
        WebElement titleSection = driver.findElement(By.xpath("//div[@class='navbar clearfix']/div[@class='breadcrumb']/nav/ul/li[last()]/a"));
        return titleSection.getText();
    }
    
    private List<WebElement> GetAdvertiseElementByCourseCode(String courseCode)
    {
        driver.get("https://daa.uit.edu.vn/search/node/" + courseCode);
        List<WebElement> advertise = driver.findElements(By.xpath("//li[@class='search-result']/h3/a"));
        return advertise;
    }
    
    public boolean IsGoodCourse(Course _course)
    {
        String courseID = _course.getCourseID();
        driver.get("https://courses.uit.edu.vn/course/view.php?id=" + courseID);
        List <WebElement> titleSection = driver.findElements(By.xpath("//div[@class='navbar clearfix']/div[@class='breadcrumb']/nav/ul/li[*]/a"));
        
        boolean flag = false;
        for (WebElement e : titleSection)
        {
            if (IsValidName(e.getText()))
            {
                flag = true;
                break;
            }   
        }
        
        driver.navigate().back();
        return flag;
    }
    
    private boolean IsValidName(String name)
    {
        for (String faculty : facultiesList)
        {
            if (name.equalsIgnoreCase(faculty))
                return true;
        }
        return false;
    }
    
    private boolean isLoggedIn()
    {
        driver.navigate().to("https://courses.uit.edu.vn/my/");
        String curURL = driver.getCurrentUrl();
        
        if (curURL.equals("https://courses.uit.edu.vn/my/"))
            return true;
        return false;
    }
    
    //Phương 
    private void ErrorOccured(String errorDetails)
    {
        System.out.println("Error : " + errorDetails);
    }

    private void InitializeFalcultyList() {
        facultiesList = new ArrayList<>();
        facultiesList.add("Môn chung");
        facultiesList.add("Trung tâm ngoại ngữ");
        facultiesList.add("Bộ môn anh văn");
        facultiesList.add("Khoa MMT & Truyền Thông");
        facultiesList.add("Khoa Kỹ Thuật Máy Tính");
        facultiesList.add("Khoa Khoa Học Máy Tính");
        facultiesList.add("Khoa Hệ Thống Thông Tin");
        facultiesList.add("Khoa Công Nghệ Phần Mềm");
        facultiesList.add("Bộ Môn KH&KTTT");
        facultiesList.add("Bộ Môn Toán Lý");
    }
}
