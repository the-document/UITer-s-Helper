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
public class Calender {

    String Key;
    String time;
    String location;
    String Describe;
    int RemindDay;

    //---------------------------------------------------
    public Calender() {
    }
    
    public Calender(String time, String location, String Describe,int remindDay) {
        this.time = time;
        this.location = location;
        this.Describe = Describe;
        this.RemindDay=remindDay;
    }
    

    public Calender(String time, String location, String Describe) {
        this.time = time;
        this.location = location;
        this.Describe = Describe;
    }

    public Calender(String Key, String time, String location, String Describe) {
        this.Key = Key;
        this.time = time;
        this.location = location;
        this.Describe = Describe;
    }

    //---------------------------------------------------
    public String getKey() {
        return Key;
    }

    public void setKey(String MSSV) {
        this.Key = MSSV;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String Describe) {
        this.Describe = Describe;
    }

    public int getRemindDay() {
        return RemindDay;
    }

    public void setRemindDay(int RemindDay) {
        this.RemindDay = RemindDay;
    }
    
    
}
