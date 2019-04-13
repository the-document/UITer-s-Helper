/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class TimeTable {
    String key;
    String name;
    String studentID;
    
    ArrayList<LopHoc> listLopHocs=new ArrayList<>();

    
    //---------------------------------------------------
    public TimeTable() {
    }

    public TimeTable(String key) {
        this.key = key;
        
    }
    
    public TimeTable(String key, String name, String studentID) {
        this.key = key;
        this.name = name;
        this.studentID = studentID;
    }

    
    //---------------------------------------------------
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
    
    public List<LopHoc> getListLopHocs() {
        return listLopHocs;
    }

    public void setListLopHocs(ArrayList<LopHoc> listLopHocs) {
        this.listLopHocs = listLopHocs;
    }
    
    
    //---------------------------------------------------
    public void AddCourse(LopHoc l){
        listLopHocs.add(l);
    }
    
    public void Sort(){
        Collections.sort(listLopHocs);
    }
    
    public void Export(){
        for (LopHoc lopHoc : listLopHocs) {
                System.out.println(lopHoc.getMaLop()+"\n");
            }
           
    }
    
    public boolean Equals(TimeTable timeTable){
        for (int i = 0; i < listLopHocs.size(); i++) {
            if(!listLopHocs.get(i).equals(timeTable.getListLopHocs().get(i)))
                return false;
        }
        return true;
    }
}
