/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import Exception.CannotBrowseCourseException;
import Exception.CannotLoginException;
import Exception.NotLoggedInException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.apache.commons.lang3.NotImplementedException;
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
    String mssv = "";
    String password = "";
    String userfullname = "";
    
    //Lưu lại danh sách các courses.
    
    ArrayList<Course> currentCourses;
    
    //Lưu lại danh sách các deadline.
    HashMap <String, ArrayList<Deadline>> deadlineOfCourses;
    
    //Lưu lại danh sách các thông báo của courses.
    HashMap <String, ArrayList<Advertise>> adverties;
    
    //Lưu lại danh sách tất cả các deadline (truy xuất theo ngày).
    HashMap <LocalDate,ArrayList<Deadline>> allDeadlineOfDates;
    
    //Lưu lại danh sách các advertise (thông báo) theo ngày.
    HashMap <LocalDate,ArrayList<Advertise>> advertiesOfDates;
    
    //Lưu lại danh sách các thông báo đã được tải về trước đó.
    HashMap <Integer,ArrayList<ThongBao>> thongBaoOfPageNo;
    
    //Lưu lại danh sách các ngày có deadline theo tháng.
    HashMap <LocalDate,ArrayList<LocalDate>> monthToDeadLines;
    
    //Cờ để nhận biết đã update chưa.
    private boolean DeadlineByDateUpdated = false;
    private boolean AdvertiseByDateUpdated = false;
    
    //HashMap để kiểm tra xem ngày tháng đã được buffer deadline trước đó chưa.
    
    //Danh sách các khoa, để phục vụ cho việc lọc ra xem đâu là courses môn học, đâu là courses khác.
    ArrayList<String> facultiesList;
    
    public static void SetProperty()
    {
        //Lấy path trực tiếp từ class Global.
       System.setProperty("webdriver.ie.driver", Global.IEDriverPath);
       System.setProperty("webdriver.gecko.driver",Global.FirefoxDriverPath);
    }
    
    public ArrayList<LocalDate> getDatesHaveAdvertise(Integer month, Integer year, boolean wantUpdate)
    {
        try
        {
           BufferAdvertiesListByDate(wantUpdate); 
        }
        catch (NotLoggedInException notLoggedIn)
        {
            try {
                ExecuteLogin();
            } catch (CannotBrowseCourseException ex) {
                Logger.getLogger(WebCommunicate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CannotLoginException ex) {
                Logger.getLogger(WebCommunicate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        ArrayList<LocalDate> res = new ArrayList<>();
        
        for (LocalDate date : advertiesOfDates.keySet())
        {
            if (date.getMonthValue() == month && date.getYear() == year)
                res.add(date);
        }
        
        return res;
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
        
        //Tạo 1 danh sách các deadline rỗng cho các ngày.
        allDeadlineOfDates = new HashMap<LocalDate,ArrayList<Deadline>>();
        //Tạo 1 danh sách các advertises rỗng cho các ngày.
        advertiesOfDates = new HashMap<LocalDate,ArrayList<Advertise>>();
        
        //Tạo 1 danh sách các thông báo rỗng.
        adverties = new HashMap<String, ArrayList<Advertise>>();
        
        //Tạo 1 danh sách các thông báo chung rỗng.
        thongBaoOfPageNo = new HashMap<Integer, ArrayList<ThongBao>>();
        
        //Tạo 1 danh sách các deadline rỗng.
        monthToDeadLines = new HashMap<LocalDate, ArrayList<LocalDate>>();
        
        //Tạo danh sách các khoa.
        InitializeFalcultyList();
    }
    
    public ArrayList<ThongBao> GetThongBao(Integer pageNo, boolean wantUpdate)
    {
        ArrayList<ThongBao> dsThongBao;
        
        //Nếu không cần cập nhập mới danh sách thông báo và trước đó đã từng load thông báo.
        if (!wantUpdate && (dsThongBao = thongBaoOfPageNo.get(pageNo)) != null)
            return dsThongBao;
        
        dsThongBao = new ArrayList<>();
        List<WebElement> thongBaoElements = GetThongBaoElement(pageNo);
        
        for (WebElement e : thongBaoElements)
        {
            ThongBao tb = new ThongBao();
            tb.setName(e.getText());
            
            //Do URL khi lấy thuộc tính href không có tên miền, vì vậy ta cần thêm tên miền vào để thành URL hoàn chỉnh.
            tb.setUrl("https://daa.uit.edu.vn" + e.getAttribute("href"));
            
            dsThongBao.add(tb);
        }
        
        thongBaoOfPageNo.put(pageNo, dsThongBao);
        
        return dsThongBao;
        
    }
    
    private List<WebElement> GetThongBaoElement(Integer pageNo)
    {
        String urlToVisit = "https://daa.uit.edu.vn/thong-bao-chung?page=" + pageNo.toString();
        driver.get(urlToVisit);
        
        List<WebElement> thongBaoElements = new ArrayList<>();
        
        try
        {
            thongBaoElements = driver.findElements(By.xpath("//div[@class=\"view-content\"]/div/article/h2/a"));
        }
        catch (NoSuchElementException elementNotFound)
        {
            System.out.println("Không thể tìm thấy các thông báo !");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return thongBaoElements;
        
    }
    
    //Nếu bật cờ allDeadlinesBuffer, toàn bộ deadline của tất cả các môn học
    public ArrayList<Deadline> GetDeadlinesByLocalDate(LocalDate date, DeadLineDateBufferMode bufferMode, boolean wantUpdate) throws CannotBrowseCourseException, CannotLoginException
    {
        switch (bufferMode) {
            case ALL:
                try {
                    BufferAllDeadlineListByDate(wantUpdate);
                } catch (NotLoggedInException notLoggedIn) {
                    System.out.println("Chưa đăng nhập.");
                }
                return (allDeadlineOfDates.get(date));
            case MONTHLY:
                BufferDeadlinesListAtMonthByDate(date, wantUpdate);
                return (allDeadlineOfDates.get(date));
            default:
                throw new NotImplementedException("Buffer Mode not available.");
        }
    }
    
    
    
    public ArrayList<Advertise> GetAdvertisesByDate(LocalDate date, boolean wantUpdate) throws CannotBrowseCourseException, CannotLoginException
    {
        try
        {
            BufferAdvertiesListByDate(wantUpdate);
        }
        catch (NotLoggedInException notLoggedInException)
        {
            ExecuteLogin();
        }
        
        return (advertiesOfDates.get(date));
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
            driver.navigate().to("https://courses.uit.edu.vn/?lang=en");
            updateUserName();
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
    
    private Deadline getDeadLineFromURL (Course course, String url)
    {
        
        //Course==null khi không truyền tham số course vào. Khi đó, ta phải làm thủ công để lấy thông tin course.
        if (course == null)
        {
            try {
                //Tìm trong các course đã được lấy trước đó xem course nào phù hợp với hàm.
                ArrayList<Course> courseList = GetCoursesList(false);
                String query = "//a[contains(@href,\"course/view.php\")]";
                driver.get(url);
                WebElement courseName = driver.findElement(By.xpath(query));
                for (Course c : courseList)
                {
                    //Nếu mã môn học trùng với mã môn học trên deadline thì ta xác định chính là course cần tìm.
                    if (c.getCourseCode().equals(courseName.getText()))
                    {
                        course = new Course(c);
                        break;
                    }
                }
                System.out.println("Course is null.");
            } catch (NotLoggedInException ex) {
                Logger.getLogger(WebCommunicate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Deadline res;
        
        String deadlineID;
        String deadlineName;
        //Get text deadlineElement sẽ trả ra tên deadline. Get Attribute href sẽ trả ra URL của deadline.
        driver.get(url);
        res = new Deadline();
        
        WebElement e = driver.findElement(By.xpath("//div[@role=\"main\"]/h2")); //Tìm đến tên của deadline.
        deadlineID = GetIDByURL(url);
        deadlineName = e.getText();

        String deadlineDate;
        try {
            deadlineDate = driver.findElement(By.xpath("//div[@class='submissionstatustable']/div/table/tbody/tr[3]/td[@class='cell c1 lastcol']")).getText();
        } catch (NoSuchElementException nse) {
            deadlineDate = "TODAY";
        }

        res.setDeadlineID(deadlineID);
        res.setDeadLineName(deadlineName);
        res.setDeadLineDate(GetDateTimeFromString(deadlineDate));
        res.setCourseOfDeadlines(course);

        return res;
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
        
        ArrayList<String> deadlinesURL = new ArrayList<>();

        for (WebElement dlElement : deadlineElements)
        {
            deadlinesURL.add(dlElement.getAttribute("href"));
        }
        
        for (String url : deadlinesURL)
        {
            deadlines.add(getDeadLineFromURL(course,url));
        }
            
        //Thêm deadlines vào danh sách.
        deadlineOfCourses.put(course.getCourseID(), deadlines);
        
        return deadlines;
    }
    
    public String getUserName()
    {
        if (userfullname.length() <= 0)
            updateUserName();
        return userfullname;
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
            //Nếu không phải là một thông báo nghỉ học hoặc học bù thì chúng ta bỏ qua luôn, không thêm vào kết quả.
            if (!CheckRealAdvertiseByName(e.getText()))
                continue;
            tmp = new Advertise(_Course, e.getText() , GetNodeIDByURL(e.getAttribute("href")),"", GetAdvertiseDateByName(e.getText()));
            System.out.println(e.getText());
            adv.add(tmp);
        }
        
        //Add newly created advertise into hashmap.
        adverties.put(_Course.getCourseCode(), adv);
        
        return adv;
    }
    
    private boolean CheckRealAdvertiseByName(String advertiseName)
    {
        return advertiseName.contains("Thông báo học bù") || advertiseName.contains("Thông báo nghỉ lớp");
    }
    
    private LocalDate GetAdvertiseDateByName(String advertiseName)
    {
        LocalDate res;
        String []split = advertiseName.split(" ngày ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.US);
        res = LocalDate.parse(split[split.length - 1],dtf);
        return res;
    }
    
    private String GetNodeIDByURL(String input)
    {
        String []res = input.split("/");
        return res[res.length - 1];
    }
    
    private void BufferDeadlinesListAtMonthByDate(LocalDate monthOfYear, boolean wantUpdate)
    {
        //Đầu tiên là ta phải lấy tất cả những ngày có deadline.
        //Sau đó kiểm tra nếu đã tồn tại và không cần update thì return luôn.
        
        //Ta lấy danh sách những ngày có deadline dựa trên tháng và năm của ngày đưa vào.
        ArrayList<LocalDate> dateHaveDeadlines = getDateHaveDeadlines(monthOfYear.getMonthValue(), monthOfYear.getYear(), false);
        
        if (!wantUpdate && allDeadlineOfDates.get(dateHaveDeadlines.get(0)) != null)
            return;
        
        //Đi đến những ngày có deadline để xem deadline.
        for (LocalDate d : dateHaveDeadlines)
        {
            ArrayList<Deadline> deadlinesOfThisDay = getDeadLinesListFromDate(d);
            allDeadlineOfDates.put(d, deadlinesOfThisDay);
        }
        
    }
    
    //Hàm này sẽ trả về các deadline trong ngày được chọn từ epoch.
    //Get text để lấy tên deadline. Get href để lấy liên kết đến deadline.
    private List<WebElement> getDeadLineElementFromEpochDate(String epoch)
    {
        driver.get("https://courses.uit.edu.vn/calendar/view.php?view=day&time=" + epoch);
        List<WebElement> deadlines = driver.findElements(By.xpath("//div[@class=\"referer\"]/a"));
        
        return deadlines;
    }
    
    private ArrayList<Deadline> getDeadLinesListFromDate (LocalDate date)
    {
        ArrayList<Deadline> res = new ArrayList<>();
        
        String epoch = convertLocalDateToEpoch(date);
        List<WebElement> deadlinesElement = getDeadLineElementFromEpochDate(epoch);
        
        ArrayList<String> dsURLDeadline = new ArrayList<>();
        
        //Duyệt qua tất cả các deadline element tìm được và thêm URL của chúng vào ds.
        for (WebElement e : deadlinesElement)
        {
            dsURLDeadline.add(e.getAttribute("href"));
        }
        
        //Từ các URL, ta tạo ra danh sách các deadline.
        Deadline tmp;
        for (String url : dsURLDeadline)
        {
            tmp = getDeadLineFromURL(null, url);
            res.add(tmp);
        }
        
        return res; 
    }
    
    private String convertLocalDateToEpoch(LocalDate date)
    {
        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
        Long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        return epoch.toString();
    }
    
    private void navigateTillRightMonth(LocalDate destinationMonth)
    {
        String url = "https://courses.uit.edu.vn/calendar/view.php?view=month&time=" + convertLocalDateToEpoch(destinationMonth);
        
        driver.navigate().to(url);
        //Old code (manually navigation - not recommended).
        /*
        WebElement WEcurrentMonthAndYear = driver.findElement(By.xpath("//h2[@class='current']"));

        String destinationMonthOfYear = destinationMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " + destinationMonth.getYear();
        String currentMonthOfYear = WEcurrentMonthAndYear.getText();

        //Nếu tháng hiện tại chưa đạt được như yêu cầu, ta tăng giảm tháng tuỳ theo độ chênh lệch.
        DateTimeFormatter monthAndYearCoursesFormat = DateTimeFormatter.ofPattern("d MMMM uuuu",Locale.US);
        LocalDate LDcurrentMonth = LocalDate.parse("1 " + currentMonthOfYear,monthAndYearCoursesFormat);
        
        WebElement nextMonthButton;
        WebElement prevMonthButton;
        
        WEcurrentMonthAndYear  = driver.findElement(By.xpath("//h2[@class='current']"));
        
        while (!currentMonthOfYear.equals(destinationMonthOfYear))
        {
            //Ta chọn XPath đến nút prev và nút forward.
            nextMonthButton = driver.findElement(By.xpath("//a[@class=\"arrow_link next\"]"));
            prevMonthButton = driver.findElement(By.xpath("//a[@class=\"arrow_link previous\"]"));
            //Nếu ngày hiện tại nhỏ hơn ngày cần đến, ta bấm nút đi tiếp.
            if (LDcurrentMonth.isBefore(destinationMonth))
                nextMonthButton.click();
            else if (LDcurrentMonth.isAfter(destinationMonth))
                prevMonthButton.click();
            
            //Cập nhập lại ngày hiện tại.
            WEcurrentMonthAndYear = driver.findElement(By.xpath("//h2[@class='current']"));
            currentMonthOfYear = WEcurrentMonthAndYear.getText();
            LDcurrentMonth = LocalDate.parse("1 " + currentMonthOfYear,monthAndYearCoursesFormat);
        }
        */
    }
    
    //Hàm dùng để tìm những ngày có deadline.
    public ArrayList<LocalDate> getDateHaveDeadlines(Integer month, Integer year, boolean wantUpdate)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d M uuuu", Locale.US);
        LocalDate destinationDate = LocalDate.parse("1 " + month.toString() + " " + year.toString(), dtf);
        
        ArrayList<LocalDate> res;
        
        if (!wantUpdate && (res = monthToDeadLines.get(destinationDate) )!= null)
            return res;
        
        res = new ArrayList<LocalDate>();
            
        String monthName = destinationDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US);

        navigateTillRightMonth(destinationDate);
        
        //Khi đã thực thi đến được đây, nghĩa là tháng đang chọn đã chính xác.
        
        String XPathToEventDates = "//td[./div/a[contains(@title,\"event\")]]/div/a";
        List<WebElement> datesHaveDeadlines = driver.findElements(By.xpath(XPathToEventDates));
        
        DateTimeFormatter formatWithOnlyDate = DateTimeFormatter.ofPattern("d M uuuu", Locale.US);
        
        for(WebElement e : datesHaveDeadlines)
        {
            //Khi getText WebElement được tìm bởi XPathToEventDates, sẽ trả về 1 con số là ngày có deadline.
            LocalDate tmp = LocalDate.parse(e.getText() + " " + month + " " + year,formatWithOnlyDate);
            res.add(tmp);
        }
        
        //Buffer to HashMap for faster retrieval later.
        monthToDeadLines.put(destinationDate, res);
        
        return res;
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
        
        if (dateTime.equals("TODAY"))
        {
            res = LocalDateTime.now();
            return res;
        }
        
        //String gốc của chúng ta có kèm cả thứ (Monday, Tuesday, Wednesday,...).
        //Ta cần lược bỏ nó đi.
        String[]split = dateTime.split(", ");
        
        if (split.length < 3)
            return null;
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM uuuu h:m a", Locale.US);
        try
        {
           res = LocalDateTime.parse(split[1] + " " + split[2], formatter); 
        }
        catch (DateTimeParseException parseException)
        {
            res = LocalDateTime.now();
        }
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
        
        //driver.navigate().back();
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
    
    private void updateUserName()
    {
        driver.navigate().to("https://courses.uit.edu.vn/?lang=en");
        driver.findElement(By.xpath("//a[@title=\"View profile\"]")).click();

        userfullname = driver.findElement(By.xpath("//h2")).getText();
    }
    
    private boolean isLoggedIn()
    {
        driver.navigate().to("https://courses.uit.edu.vn/my/");
        String curURL = driver.getCurrentUrl();
        
        if (curURL.equals("https://courses.uit.edu.vn/my/"))
            return true;
        return false;
    }
    
    private void BufferAllDeadlineListByDate(boolean wantUpdate) throws NotLoggedInException
    {
        if (!wantUpdate && DeadlineByDateUpdated)
            return;
        if (currentCourses.size() <= 0)
            GetCoursesList(false);
        System.out.println("Course list : " + currentCourses.size());
        for (Course c : currentCourses)
        {
            if (!c.isIsRealCourse())
                continue;
            //Khi gọi hàm GetDeadlinesByCourse, deadline sẽ được tự động buffer vào deadlinesOfCourse. 
            ArrayList<Deadline> courseDeadlines = GetDeadlinesByCourse(c, false);
            if (courseDeadlines == null)
                continue;
            for (Deadline d : courseDeadlines)
            {
                try
                {
                    ArrayList<Deadline> currentDeadline = allDeadlineOfDates.get(d.getDeadLineDate().toLocalDate());
                    if (currentDeadline == null)
                        currentDeadline = new ArrayList<Deadline>();
                    currentDeadline.add(d);
                    
                    allDeadlineOfDates.put(d.getDeadLineDate().toLocalDate(), currentDeadline);
                    
                }
                catch (Exception e)
                {
                    System.out.println("Không thể thêm vào buffer deadline theo ngày !");
                    e.printStackTrace();
                }
            }
        }
        DeadlineByDateUpdated = true;
    }
    
    private void BufferAdvertiesListByDate(boolean wantUpdate) throws NotLoggedInException
    {
        if (!wantUpdate && AdvertiseByDateUpdated)
            return;
        if (currentCourses.size() <= 0)
            GetCoursesList(false);
        System.out.println("Course list : " + currentCourses.size());
        for (Course c : currentCourses)
        {
            if (!c.isIsRealCourse())
                continue;
            ArrayList<Advertise> courseAdvertises = GetCourseAdvertisesByCourse(c, false);
            if (courseAdvertises == null)
                continue;
            for (Advertise a : courseAdvertises)
            {
                try
                {
                    ArrayList<Advertise> currentAdvertises = advertiesOfDates.get(a.getAdvertiseTime());
                    if (currentAdvertises == null)
                        currentAdvertises = new ArrayList<Advertise>();
                    currentAdvertises.add(a);
                    
                    advertiesOfDates.put(a.getAdvertiseTime(), currentAdvertises);
                    
                }
                catch (Exception e)
                {
                    System.out.println("Không thể thêm vào buffer advertise theo ngày !");
                    e.printStackTrace();
                }
            }
        }
        AdvertiseByDateUpdated = true;
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
