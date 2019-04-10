package GUI.controller;

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
