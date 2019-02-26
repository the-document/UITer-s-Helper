/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class Calender {
    Date date;
    Time time;
    String location;
    String Describe;

    public Calender() {
    }

    public Calender(Date date, Time time, String location, String Describe) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.Describe = Describe;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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
    
    
}
