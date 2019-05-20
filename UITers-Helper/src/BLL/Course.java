/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.ArrayList;

/**
 *
 * @author mirushi
 */

//Đây là một lớp dùng để lưu trữ thông tin của 1 course.
//Thông tin của 1 course bao gồm : courseID (được set cố định trên website môn học) và courseName (tên môn học).
public class Course
{
    String courseID;
    String courseCode;
    String courseName;
    boolean isRealCourse;
    
    public Course()
    {
        courseID = "";
        courseName = "";
    }
    public Course(String _courseID, String _courseName)
    {
        courseID = _courseID;
        courseName = _courseName;
    }
    
    public Course(String _courseID, String _courseCode, String _courseName)
    {
        courseID = _courseID;
        courseCode = _courseCode;
        courseName = _courseName;
    }
    
    public String getCourseID()
    {
        return courseID;
    }
    
    public void setCourseID(String _courseID)
    {
        courseID = _courseID;
    }
    
    public String getCourseName()
    {
        return courseName;
    }
    
    public void setCourseName(String _courseName)
    {
        courseName = _courseName;
    }
    
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public boolean isIsRealCourse() {
        return isRealCourse;
    }

    public void setIsRealCourse(boolean isRealCourse) {
        this.isRealCourse = isRealCourse;
    }
}
