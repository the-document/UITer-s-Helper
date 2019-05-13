/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.time.LocalDateTime;

/**
 *
 * @author mirushi
 */
public class Advertise {
    Course associatedCourse;
    String name;
    String room;
    LocalDateTime dateTimeStart;
    LocalDateTime dateTimeEnd;
    public Advertise()
    {
    
    }
    public Advertise(Course _associatedCourse, String _name, String _room, LocalDateTime _dateTimeStart, LocalDateTime _dateTimeEnd)
    {
        associatedCourse = _associatedCourse;
        name = _name;
        room = _room;
        dateTimeStart = _dateTimeStart;
        dateTimeEnd = _dateTimeEnd;
    }

    public Course getAssociatedCourse() {
        return associatedCourse;
    }

    public void setAssociatedCourse(Course associatedCourse) {
        this.associatedCourse = associatedCourse;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
