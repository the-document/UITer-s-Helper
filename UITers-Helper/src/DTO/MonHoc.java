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
public class MonHoc {
    String maMonHoc;
    String tenMonHoc;
    String donViQL;
    int soChiLyThuyet;
    int soChiThucHanh;

    //---------------------------------------------------
    public MonHoc() {
    }

    public MonHoc(String maMonHoc, String tenMonHoc, String donViQL, int soChiLyThuyet, int soChiThucHanh) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.donViQL = donViQL;
        this.soChiLyThuyet = soChiLyThuyet;
        this.soChiThucHanh = soChiThucHanh;
    }

    
    //---------------------------------------------------
    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getDonViQL() {
        return donViQL;
    }

    public void setDonViQL(String donViQL) {
        this.donViQL = donViQL;
    }

    public int getSoChiLyThuyet() {
        return soChiLyThuyet;
    }

    public void setSoChiLyThuyet(int soChiLyThuyet) {
        this.soChiLyThuyet = soChiLyThuyet;
    }

    public int getSoChiThucHanh() {
        return soChiThucHanh;
    }

    public void setSoChiThucHanh(int soChiThucHanh) {
        this.soChiThucHanh = soChiThucHanh;
    }
    
    //---------------------------------------------------
    @Override public String toString()
    {
        return this.maMonHoc+" - "+this.tenMonHoc+" - "+this.donViQL+" - "+this.soChiLyThuyet+" - "+this.soChiThucHanh;
    }
}
