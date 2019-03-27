/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class TimeTable {
    String key;
    String name;
    String studentID;

    public TimeTable() {
    }

    public TimeTable(String key, String name, String studentID) {
        this.key = key;
        this.name = name;
        this.studentID = studentID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    
}
