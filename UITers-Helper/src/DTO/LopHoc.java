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
public class LopHoc implements Comparable<LopHoc>{
    private String maLop;
    private String maMonHoc;
    private String tenGiangVien;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String tiet;
    private String thu;
    private String phong;
    private String heDaoTao;
    private String hinhthucDay;
    
    //The property will be created automatically
    int tietBatDau;
    int tietKetThuc;
    

    //---------------------------------------------------
    public LopHoc() {
    }
    
    public LopHoc(String maLop, String maMonHoc, String tenGiangVien,
            String ngayBatDau, String ngayKetThuc, String tiet, 
            String Thu, String phong, String HDT,String hinhThuc) {
        this.maLop = maLop;
        this.maMonHoc = maMonHoc;
        this.tenGiangVien = tenGiangVien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tiet = tiet;
        this.thu = Thu;
        this.phong = phong;
        this.heDaoTao = HDT;
        this.hinhthucDay=hinhThuc;
        
        if("*".equals(this.tiet)||"NULL".equals(this.tiet)||this.tiet.isEmpty())
        {
            this.tietBatDau = -1;
            this.tietKetThuc = -1;
            return;
        }
        this.tietBatDau = Integer.parseInt(String.valueOf(this.tiet.charAt(0)) ) ;
        this.tietKetThuc = Integer.parseInt(String.valueOf(this.tiet.charAt(this.tiet.length()-1))) ;
    }

    
    //---------------------------------------------------
    public String getHinhthucDay() {
        return hinhthucDay;
    }

    public void setHinhthucDay(String hinhthucDay) {
        this.hinhthucDay = hinhthucDay;
    }
    
    
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getmaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTiet() {
        return tiet;
    }

    public void setTiet(String tiet) {
        this.tiet = tiet;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String Thu) {
        this.thu = Thu;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getHeDaoTao() {
        return heDaoTao;
    }

    public void setHeDaoTao(String heDaoTao) {
        this.heDaoTao = heDaoTao;
    }

    public int getTietBatDau() {
        return tietBatDau;
    }

    public void setTietBatDau(int tietBatDau) {
        this.tietBatDau = tietBatDau;
    }

    public int getTietKetThuc() {
        return tietKetThuc;
    }

    public void setTietKetThuc(int tietKetThuc) {
        this.tietKetThuc = tietKetThuc;
    }
    
    
    //---------------------------------------------------
    // check 2 course is intersect
    // input: course need to check with current course
    // output: true if it intersect and else
    public  boolean isOverlap(LopHoc lop){
        //the day different of course it not overlap.
        if(!this.thu.equals(lop.getThu())||this.thu.equals("*")||lop.getThu().equals("*"))
            return false;
        
        else
        {
            
            if(this.tietKetThuc>=lop.getTietBatDau()&&this.tietBatDau<=lop.getTietKetThuc())
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(LopHoc o) {
        return this.getMaLop().compareTo(o.getMaLop());
    }
    
}
