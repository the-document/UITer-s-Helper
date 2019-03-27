/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.LopHoc;
import DTO.TimeTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class ThuatToanTaoTKB {
    
    static List<LopHoc> fullDsLop=new ArrayList<>(); //dung tam de load all ds th va lt
    static List<LopHoc> dsFilterLT=new ArrayList<>(); //ds can get to choose LT
    static List<LopHoc> dsFilterTH=new ArrayList<>(); //ds can get to choose
    static List<String> dsMaLop=new ArrayList<>(); //ds ma mon hoc can hoc trong ky nay
    static List<LopHoc>dstkbFull=new ArrayList<>();
    
    static String CTDT="CQUI";
   
    
   public static void NapDanhSachMaMonHoc(List<String> danhSachMaMon)
   {
       danhSachMaMon.forEach((malop) -> {
           dsMaLop.add(malop);
        });
   }
   
   public static void SetHeDaoTao(String HeDaoTao)
   {
       CTDT=HeDaoTao;
   }
    
   //lay ra danh sach cac lop LT va TH cu the hoc theo yeu cau
    public static void init() throws SQLException {
        
        //lay ra tat ca cac lop ly thuyet theo chuong trinh dao tao
        //===============================================================
        LopHocBLL lhbll =new LopHocBLL();
        fullDsLop = lhbll.GetListCourseTheoryOfEducationProgram(CTDT);
        
        //loc ra nhung lop trong hoc ky nay (ds mon hoc da duoc nap truoc do)
        for (LopHoc nextLopHoc : fullDsLop) {
            for (String next1 : dsMaLop) {
                if(nextLopHoc.getmaMonHoc().equals(next1))
                    dsFilterLT.add(nextLopHoc);
            }
        }
        
        //==============================================================
        //lay ra tat ca cac lop Thuc hanh theo chuong trinh dao tao
        fullDsLop = lhbll.GetListCoursePracticeOfEducationProgram(CTDT);
         
        for (LopHoc nextLopHoc : fullDsLop) {
            //lay ra ds lop TH tuong ung voi lop LT
            for (LopHoc next1 : dsFilterLT) {
                if(nextLopHoc.getmaMonHoc().equals(next1.getmaMonHoc()))
                {
                    String malTH=nextLopHoc.getMaLop(); //TH
                    String malLT=next1.getMaLop(); //LT
                    
                    // neu lop TH .1 or .2 cua cac ma lop ly thuyet da chon
                    if((malTH.equals((malLT+".1")))
                            ||(malTH.equals((malLT+".2"))))
                        
                        dsFilterTH.add(nextLopHoc);
                }
            }
        }
    }
    
//    public static void ShowDS(){
//        System.out.println("SL all:"+fullDsLop.size()+"\n");
//        System.out.println("SL filter:"+dsFilter.size()+"\n");
//        for (LopHoc lopHoc : dsFilter) {
//           
//           System.out.print("Mã Mon: "+lopHoc.getmaMonHoc()+"\n");
//           System.out.print("Mã Lớp: "+lopHoc.getMaLop()+"\n");
//           System.out.print("Ngày BĐ: "+lopHoc.getNgayBatDau()+"\n");
//           System.out.print("Ngày KT: "+lopHoc.getNgayKetThuc()+"\n");
//           System.out.print("Thứ: "+String.valueOf(lopHoc.getThu())+"\n");
//           System.out.print("Tiết: "+lopHoc.getTiet()+"\n");
//           System.out.print("Tiết BĐ: "+String.valueOf(lopHoc.getTietBatDau())+"\n");
//           System.out.print("Tiết KT: "+String.valueOf(lopHoc.getTietKetThuc())+"\n");
//           System.out.println("===============================================\n");
//        }
//        
//        //=========list TH==================
//        System.out.println("======================================================================\n");
//        System.out.println("SL filter:"+dsFilterTH.size()+"\n");
//        for (LopHoc lopHoc : dsFilterTH) {
//           
//           System.out.print("Mã Mon: "+lopHoc.getmaMonHoc()+"\n");
//           System.out.print("Mã Lớp: "+lopHoc.getMaLop()+"\n");
//           System.out.print("Ngày BĐ: "+lopHoc.getNgayBatDau()+"\n");
//           System.out.print("Ngày KT: "+lopHoc.getNgayKetThuc()+"\n");
//           System.out.print("Thứ: "+String.valueOf(lopHoc.getThu())+"\n");
//           System.out.print("Tiết: "+lopHoc.getTiet()+"\n");
//           System.out.print("Tiết BĐ: "+String.valueOf(lopHoc.getTietBatDau())+"\n");
//           System.out.print("Tiết KT: "+String.valueOf(lopHoc.getTietKetThuc())+"\n");
//           System.out.println("===============================================\n");
//        }
//    }
    

    
    //kiem tra 2 lop co bi trung lich hay khong
    //check couse Lt and TH.
    public static boolean CheckOverLap(LopHoc l)
    {
        if(dstkbFull.size()<=0)
            return false;
        
        //check LT with tkb FULL
        for (int t=0;t<dstkbFull.size();t++) {
            LopHoc lopHoc=dstkbFull.get(t);
            if(lopHoc!=null)
            {
                //lop tieng anh co the trung ten lop
                if (l.getmaMonHoc().substring(0, 1).equals("EN") && !lopHoc.isOverlap(l)) {
                    return true;
                }
                if (lopHoc.getmaMonHoc().equals(l.getmaMonHoc()) || lopHoc.isOverlap(l)) {
                    return true;
                }
            }
                
        }
        
        //neu day la mon tieng anh thi can check ca 1 buoi con lai cua lop nay
        if(l.getmaMonHoc().substring(0, 1).equals("EN"))
        {
            for (LopHoc lopHoc :dsFilterLT) {
                if(lopHoc==l)
                    continue;
                if(lopHoc.getMaLop().equals(l))
                {
                    for (LopHoc lopHoc2 : dstkbFull) {
                        if(lopHoc2.isOverlap(lopHoc))
                            return true;
                    }
                }
            }
            return false;
        }
        
        //check TH with ful tkb==========================
        //lay lop TH tuong ung lop LT
        LopHoc lopTH=new LopHoc();
        lopTH.setMaLop("*");
        for (LopHoc lopHoc : dsFilterTH) {
            String tenLopTHcuaLT=l.getMaLop()+".1";
            if(tenLopTHcuaLT.equals(lopHoc.getMaLop()))
            {
                lopTH=lopHoc;
                break;
            }
        }
        
        //if esxist course TH
        if(!lopTH.getMaLop().equals("*" ))
        {
            for (int t=0;t<dstkbFull.size();t++) {
                if(dstkbFull.get(t)!=null)
                if(dstkbFull.get(t).isOverlap(lopTH))
                    return true;
            }
        }
        
        return false;
    }
    
    //add course to full tkb include TH, and name course to tkb.
    //true if have TH
    public  static boolean AddCourse(LopHoc l,int index){
        dstkbFull.add(l);
        
        //kiem tra coi day co phai la mon anh van
        if(l.getmaMonHoc().substring(0, 1).equals("EN"))
        {
            for (LopHoc lopHoc : dsFilterLT) {
                if(lopHoc==l)
                    continue;
                if(lopHoc.getMaLop().equals(l.getMaLop()))
                    dstkbFull.add(lopHoc);
            }
            return true;
        }
        
        //check, if have TH => add TH.
        for (LopHoc lopHoc : dsFilterTH) {
            
            String tenLopTHcuaLT=l.getMaLop()+".1";
          
            if(tenLopTHcuaLT.equals(lopHoc.getMaLop()))
            {
                dstkbFull.add(lopHoc);
                return true;
            }
        }
        return false;
    }
    

    public static int countCase=0;
    public static List<TimeTable> listTimeTables=new ArrayList<>();
    public static void Try(int i){
       if(i==dsMaLop.size())
        {
           
            TimeTable table=new TimeTable(i+"");
             for (LopHoc lopHoc : dstkbFull)
                 table.AddCourse(lopHoc);
             
             table.Sort();
             
             //check exits in list table
             for (TimeTable  timeTable : listTimeTables) {
                if(timeTable.Equals(table))
                    return;
            }
            countCase++;
            listTimeTables.add(table);
            return;
        }
            
       //check all course LT
        for(int t=0;t<dsFilterLT.size();t++)
        {
            //System.out.println(dsFilter.get(t).getMaLop()+"\n");
             if(!CheckOverLap(dsFilterLT.get(t)))
             {
                //add LT+TH
                boolean havaTH=AddCourse(dsFilterLT.get(t), i);
                Try(i+1);
                dstkbFull.remove(dstkbFull.size()-1);
                if(havaTH)
                    //if have TH, TH course be near Lt and after LT. so it latest
                    dstkbFull.remove(dstkbFull.size()-1);
             }
        }
    }
}