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
    String courseName;
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
    
}