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
    String time;
    String location;
    String Describe;

    //---------------------------------------------------
    public Calender() {
    }

    public Calender(String time, String location, String Describe) {
        this.time = time;
        this.location = location;
        this.Describe = Describe;
    }

    //---------------------------------------------------

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

  
 
}
