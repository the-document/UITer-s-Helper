/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.controller;

/**
 *
 * @author Admin
 */
public class Schedule {
    String time;
    String location;
    String description;
    public Schedule()
    {
        this.time = "";
        this.location = "";
        this.description = "";
    }
    public Schedule(String _time, String _location, String _description)
    {
        this.time = _time;
        this.location = _location;
        this.description = _description;
        
    }
    
    public String getTime()
    {
        return this.time;
    }
    public String getLocation()
    {
        return this.location;
    }
    public String getDescription()
    {
        return this.description;
    }
    
    
}
