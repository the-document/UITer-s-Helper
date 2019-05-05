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
    
    boolean isLoggedIn =false;
    WebDriver driver;
    
    public static void SetProperty()
    {
        //Lấy path trực tiếp từ class Global.
       System.setProperty("webdriver.ie.driver", Global.IEDriverPath);
       System.setProperty("webdriver.gecko.driver",Global.FirefoxDriverPath);
    }
    
    public WebCommunicate(WebDriverMode driverMode)
    {
        SetProperty();
        if (driverMode.equals(WebDriverMode.Firefox))
            driver = new FirefoxDriver();
        else if (driverMode.equals(WebDriverMode.IE))
            driver = new InternetExplorerDriver();
    }
    
    public void ExecuteLogin(String mssv, String password) throws CannotBrowseCourseException, CannotLoginException
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
        if (driver.getCurrentUrl().compareToIgnoreCase("https://courses.uit.edu.vn/") == 0 && pageSource.contains("Log out"))
            isLoggedIn = true;
        else if (driver.getCurrentUrl().compareToIgnoreCase("https://courses.uit.edu.vn/") == 0 && pageSource.contains("Thoát"))
            isLoggedIn = true;
        else
        {
            isLoggedIn = false;
            throw new CannotLoginException("Cannot login. Please check username and password !");
        }
            
    }
    
    public ArrayList<Course> GetCoursesList() throws NotLoggedInException
    {
        if (!isLoggedIn)
            throw new NotLoggedInException("Please login before get courses list.");
        
        ArrayList<Course> courses = new ArrayList<>();
        List<WebElement> coursesElement = GetCourses();
        
        Course tmp;
        
        for (WebElement c : coursesElement)
        {
            tmp = new Course(c.getText(), GetIDByURL(c.getAttribute("href")));
            courses.add(tmp);
        }
        
        return courses;
    }
    
    public ArrayList<Deadline> GetDeadlinesByCourseID(String courseID) throws NotLoggedInException
    {
        if (!isLoggedIn)
            throw new NotLoggedInException("Please login before get deadlines !");
        
        List<WebElement> deadlineElements = new ArrayList<>();
        String deadlineID = "";
        String deadlineDate = "";
        String deadlineName = "";
        String destURL = "https://courses.uit.edu.vn/course/recent.php?id=" + courseID;
        driver.navigate().to(destURL);

        ArrayList<Deadline> deadlines = new ArrayList<>();
        
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
            
            tmp.DeadlineID = deadlineID;
            tmp.DeadLineName = deadlineName;
            tmp.DeadLineDate = GetDateTimeFromString(deadlineDate);
            
            deadlines.add(tmp);
            driver.navigate().back();
        }
        
        return deadlines;
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
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM uuuu hh:mm a", Locale.US);
        
        res = LocalDateTime.parse(split[1] + " " + split[2], formatter);
        return res;
    }
    
    private String GetIDByURL(String attr) {
        String[]split = attr.split("id=");
        if (split.length <= 1)
            return "";
        return split[1];
    }
    
    //Phương 
    private void ErrorOccured(String errorDetails)
    {
        System.out.println("Error : " + errorDetails);
    }
}
