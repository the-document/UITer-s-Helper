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
public class HocKy {
    
    int maHK;
    String tenHK;

    public HocKy() {
    }

    
    
    public HocKy(int maHK, String tenHK) {
        this.maHK = maHK;
        this.tenHK = tenHK;
    }

    public int getMaHK() {
        return maHK;
    }

    public void setMaHK(int maHK) {
        this.maHK = maHK;
    }

    public String getTenHK() {
        return tenHK;
    }

    public void setTenHK(String tenHK) {
        this.tenHK = tenHK;
    }
    
    
}
