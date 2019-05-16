/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.LopHoc;
import DTO.MonHoc;
import DTO.TimeTable;
import GUI.controller.CreateTimetableNowController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class ThuatToanTaoTKB {
    
    static List<LopHoc> fullDsLop=new ArrayList<>(); //dung tam de load all ds th va lt
    static List<LopHoc> dsFilterLT=new ArrayList<>(); //ds can get to choose LT
    static List<LopHoc> dsFilterTH=new ArrayList<>(); //ds can get to choose
    static List<LopHoc> dstkbFull=new ArrayList<>();
    
    static List<String> dsMaMonNeedCreate=new ArrayList<>(); //ds ma mon hoc can hoc trong ky nay
    public static List<String> dsMaMonNotFound=new ArrayList<>(); //ds ma mon hoc khong tim thay
    
    static String CTDT="CQUI";
    public static boolean Has_Found=false;
    public static int NumberOfCouseFound=0; //so luong mon hoc can tao co trong danh sach lop mo vao ky nay
   
    
   public static void NapDanhSachMaMonHoc(List<String> danhSachMaMon)
   {
       dsMaMonNeedCreate.clear();
       danhSachMaMon.forEach((mamon) -> {
           dsMaMonNeedCreate.add(mamon);
        });
   }
   
   public static void SetHeDaoTao(String HeDaoTao)
   {
       CTDT=HeDaoTao;
   }
    
   //lay ra danh sach cac lop LT va TH cu the hoc theo yeu cau------------------
    public static void init() throws SQLException {
        
        //lay ra tat ca cac lop ly thuyet theo chuong trinh dao tao
        //===============================================================
        LopHocBLL lhbll =new LopHocBLL();
        List<LopHoc> lsLopHocTemp=new ArrayList<>();
        dsMaMonNotFound.clear();
        dsFilterLT.clear();
        dsFilterTH.clear();
        listTimeTables.clear();
        Has_Found=false;
        countCase=0;
        

//        for (String monHoc : dsMaMonNeedCreate) 
//        {
//            
//            lsLopHocTemp=lhbll.GetListCourseTheory(CTDT, monHoc);
//            if(lsLopHocTemp!=null)
//            {
//                System.out.println("found: "+monHoc);
//                dsFilterLT.addAll(lsLopHocTemp);
//            }
//            else
//            {
//                System.out.println("not found: "+monHoc);
//                dsMaMonNotFound.add(monHoc);
//            }
//                
//        }

        dsFilterLT=lhbll.GetListCourseTheory(CTDT, dsMaMonNeedCreate);
        System.out.println("Size LT:"+dsFilterLT.size());
        
        Boolean check;
        for (String maMH : dsMaMonNeedCreate){
            check=false;


            for (LopHoc lop : dsFilterLT) {
                if (lop.getmaMonHoc().equals(maMH)) {
                    check = true;
                    break;
                }
            }                              

            
            if(!check){
                dsMaMonNotFound.add(maMH);
               // System.out.println("Mon khong co: " + maMH);
            } 
        }

        //fullDsLop = lhbll.GetListCourseTheoryOfEducationProgram(CTDT);
        
        //loc ra nhung lop trong hoc ky nay (ds mon hoc da duoc nap truoc do)
//        for (LopHoc nextLopHoc : fullDsLop) {
//            
//            for (String maMon : dsMaMonNeedCreate) {
//               // System.out.println("--"+ nextLopHoc.getmaMonHoc()+"--"+maMon);
//                if(nextLopHoc.getmaMonHoc().equals(maMon))
//                {
//                    dsFilterLT.add(nextLopHoc);
//                   // System.out.println("--"+ nextLopHoc.getMaLop());
//                }
//                    
//            }
//        }
        
        //==============================================================
        //lay ra tat ca cac lop Thuc hanh theo chuong trinh dao tao
        
//        for (String monHoc : dsMaMonNeedCreate) 
//        {
//            lsLopHocTemp=lhbll.GetListCoursePractice(CTDT, monHoc);
//            if(lsLopHocTemp!=null)
//            {
//                dsFilterTH.addAll(lsLopHocTemp);
//            }
////            else
////                dsMaMonNotFound.add(monHoc);
//        }

        dsFilterTH=lhbll.GetListCoursePractice(CTDT, dsMaMonNeedCreate);
        
        NumberOfCouseFound=dsMaMonNeedCreate.size()-dsMaMonNotFound.size();
//        fullDsLop = lhbll.GetListCoursePracticeOfEducationProgram(CTDT);
//         
//        for (LopHoc nextLopHoc : fullDsLop) {
//            //lay ra ds lop TH tuong ung voi lop LT
//            for (LopHoc next1 : dsFilterLT) {
//                if(nextLopHoc.getmaMonHoc().equals(next1.getmaMonHoc()))
//                {
//                    String malTH=nextLopHoc.getMaLop(); //TH
//                    String malLT=next1.getMaLop(); //LT
//                    
//                    // neu lop TH .1 or .2 cua cac ma lop ly thuyet da chon
//                    if((malTH.equals((malLT+".1")))
//                            ||(malTH.equals((malLT+".2"))))
//                        
//                        dsFilterTH.add(nextLopHoc);
//                }
//            }
//        }
        
        //System.out.println("CTDT: "+CTDT);
        //System.out.println("List Lop: "+dsFilterLT.toString()+dsFilterTH.toString());
    }
    
    public static void ShowDS(){
       // System.out.println("SL all:"+fullDsLop.size()+"\n");
        System.out.println("SL filter:"+dsFilterLT.size()+"\n");
        for (LopHoc lopHoc : dsFilterLT) {
           
           System.out.print("Mã Mon: "+lopHoc.getmaMonHoc()+"\n");
           System.out.print("Mã Lớp: "+lopHoc.getMaLop()+"\n");
           System.out.print("Ngày BĐ: "+lopHoc.getNgayBatDau()+"\n");
           System.out.print("Ngày KT: "+lopHoc.getNgayKetThuc()+"\n");
           System.out.print("Thứ: "+String.valueOf(lopHoc.getThu())+"\n");
           System.out.print("Tiết: "+lopHoc.getTiet()+"\n");
           System.out.print("Tiết BĐ: "+String.valueOf(lopHoc.getTietBatDau())+"\n");
           System.out.print("Tiết KT: "+String.valueOf(lopHoc.getTietKetThuc())+"\n");
           System.out.println("===============================================\n");
        }
        
        //=========list TH==================
        System.out.println("======================================================================\n");
        System.out.println("SL filter:"+dsFilterTH.size()+"\n");
        for (LopHoc lopHoc : dsFilterTH) {
           
           System.out.print("Mã Mon: "+lopHoc.getmaMonHoc()+"\n");
           System.out.print("Mã Lớp: "+lopHoc.getMaLop()+"\n");
           System.out.print("Ngày BĐ: "+lopHoc.getNgayBatDau()+"\n");
           System.out.print("Ngày KT: "+lopHoc.getNgayKetThuc()+"\n");
           System.out.print("Thứ: "+String.valueOf(lopHoc.getThu())+"\n");
           System.out.print("Tiết: "+lopHoc.getTiet()+"\n");
           System.out.print("Tiết BĐ: "+String.valueOf(lopHoc.getTietBatDau())+"\n");
           System.out.print("Tiết KT: "+String.valueOf(lopHoc.getTietKetThuc())+"\n");
           System.out.println("===============================================\n");
        }
    }
    

    //kiem tra 2 lop co bi trung lich hay khong----------------------------
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
    //true if have TH-----------------------------------------------------
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
            //System.out.println("LT-TH: "+tenLopTHcuaLT+"-"+lopHoc.getMaLop());
            if(tenLopTHcuaLT.equals(lopHoc.getMaLop()))
            {
                dstkbFull.add(lopHoc);
                //System.out.println("List TH result: "+lopHoc.getMaLop());
                return true;
            }
        }
        return false;
    }
    

    public static int countCase=0;
    public static List<TimeTable> listTimeTables=new ArrayList<>();
    public static void Try(int i){
        
        if(Has_Found)
            return;
        
        if(i==NumberOfCouseFound)
        {
            
            TimeTable table=new TimeTable(i+"");
            for (LopHoc lopHoc : dstkbFull)
            {
                //System.out.println("List time table result: "+lopHoc.getMaLop());
                table.AddCourse(lopHoc);
            }
                
             
            table.Sort();
             
            //check exits in list table
            for (TimeTable  timeTable : listTimeTables) {
                if(timeTable.Equals(table))
                    return;
            }
            countCase++;
            listTimeTables.add(table);
            
            Has_Found=true;
            //table.Export();
            return;
        }
            
       //check all course LT
        for(int t=0;t<dsFilterLT.size();t++)
        {
            //System.out.println(dsFilter.get(t).getMaLop()+"\n");
             if(!CheckOverLap(dsFilterLT.get(t)))
             {
                //add LT+TH
                boolean haveTH=AddCourse(dsFilterLT.get(t), i);
                Try(i+1);
                
                //remove and come back try before
                dstkbFull.remove(dstkbFull.size()-1);
                if(haveTH)
                    //if have TH, TH course be near Lt and after LT. so it latest
                    dstkbFull.remove(dstkbFull.size()-1);
             }
        }
    }
    
}
