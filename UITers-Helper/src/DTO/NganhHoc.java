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
public class NganhHoc {
    String maNganh;
    String tenNganh;

    //---------------------------------------------------
    public NganhHoc() {
    }

    public NganhHoc(String maNganh, String tenNganh) {
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
    }

    //---------------------------------------------------
    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }
    
    
}
