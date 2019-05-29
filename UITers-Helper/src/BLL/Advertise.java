/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author mirushi
 */
public class Advertise {
    Course associatedCourse;
    String name;
    String room;
    String nodeID;
    LocalDate advertiseTime;

    public LocalDate getAdvertiseTime() {
        return advertiseTime;
    }

    public void setAdvertiseTime(LocalDate advertiseTime) {
        this.advertiseTime = advertiseTime;
    }
    public Advertise()
    {
    
    }
    public Advertise(Course _associatedCourse, String _name, String _nodeID, String _room, LocalDate _date)
    {
        associatedCourse = _associatedCourse;
        name = _name;
        room = _room;
        advertiseTime = _date;
        nodeID = _nodeID;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
