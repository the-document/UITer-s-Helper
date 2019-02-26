/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.LopHoc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Nguyen Hong Phuc
 */

//============================================|
//===========PROCESS FILE EXCEL INPUT=========|
//============================================|
public class MyFile {

    File exelFile;
    FileInputStream fis;
    XSSFWorkbook worbook;
    
    //data we get from sheet
    List<LopHoc> listCourseTheory =new ArrayList<>();
    List<LopHoc> listCoursePractice =new ArrayList<>();    
    
    //==CREATE FILE INPUT AND TRY READ AS EXCEL============
    public MyFile(String path) throws IOException {
        exelFile= new File(path);
        
        try {
            fis= new FileInputStream(exelFile); 
            worbook= new XSSFWorkbook(fis);
            
        } catch (FileNotFoundException e) {
            System.err.println("Err in MyFile "+ e.toString());
        } 
    }
    
    
    //READ ALL ROW IN SHEET i-st
    //input: index of sheet need to read
    //output: true if can read all data and push to array of MyFile
    public boolean ReadAllDataFromSheet(int index ){
        
        XSSFSheet sheet=worbook.getSheetAt(index);
            
            int row =sheet.getPhysicalNumberOfRows();
            System.out.println(String.valueOf(row)+"\n");
            
            String maMon="*"; //1
            String maLop="*";//2
            String giangVien="*";//5
            String thu="*";//10
            String tiet="*";//11
            String phong="*";//13
            String heDaoTao="*";//17
           // String khoaQL="*";//18
            String ngayBD="*";//19
            String ngayKT="*";//20
            
            //data start at row 8 (9 in sheet - but start from 0)
            for (int i = 8; i < row; i++) {
                XSSFRow r=sheet.getRow(i);
                
                try {maMon=r.getCell(1).toString();} 
                catch (Exception e) {}
                
                try {maLop=r.getCell(2).toString();} 
                catch (Exception e) {}
                
                try {giangVien= r.getCell(5).toString();} 
                catch (Exception e) {}
                
                try { thu=r.getCell(10).toString();} 
                catch (Exception e) {}
                
                try {tiet=r.getCell(11).toString();} 
                catch (Exception e) {}
                
                try {phong= r.getCell(13).toString();} 
                catch (Exception e) {}
                
                try {heDaoTao=r.getCell(17).toString();} 
                catch (Exception e) {}
                
                try {heDaoTao=r.getCell(17).toString();} 
                catch (Exception e) {}
                
                try {ngayBD=r.getCell(19).toString();} 
                catch (Exception e) {}
                
                try {ngayKT=r.getCell(20).toString();} 
                catch (Exception e) {}
            
                
                LopHoc lopHoc=new LopHoc(maLop,maMon,giangVien,ngayBD,ngayKT,tiet,thu,phong,heDaoTao);
//                lopHoc.setMaLop(malopString);
//                lopHoc.setTenMH(tenmonString);
//                lopHoc.setNgayBD(ngbdString);
//                lopHoc.setNgayKT(ngktString);
//                lopHoc.setTiet(tietString);
//                lopHoc.setThu(thu);
                
                
                if(lopHoc.getMaLop().equals("*")||lopHoc.getMaLop().isEmpty())
                    continue;
                
                if(index==0)
                listCourseTheory.add(lopHoc); 
                else
                    listCoursePractice.add(lopHoc);
    }
        return true;
    }
    
    
    // fiter all course to get education program of SV
    // * it mean get all course
    public List<LopHoc> GetListCourseTheoryOfEducationProgram(String type)
    {
       
        List<LopHoc> list=new ArrayList<LopHoc>();
        
        if (type.equals("*")) {
            listCourseTheory.forEach((l) -> {
                list.add(l);
            });
        } else {
            listCourseTheory.stream().filter((l) -> (l.getHeDaoTao().equals(type))).forEachOrdered((l) -> {
                list.add(l);
            });
        }
        
        return list;
    }
    
    
    // fiter all course to get education program of SV
    // * it mean get all course
    public List<LopHoc> GetListCoursePracticeOfEducationProgram(String type)
    {
        List<LopHoc> list=new ArrayList<LopHoc>();
        
        if (type.equals("*")) {
            listCoursePractice.forEach((l) -> {
                list.add(l);
            });
        } else {
            listCoursePractice.stream().filter((l) -> (l.getHeDaoTao().equals(type))).forEachOrdered((l) -> {
                list.add(l);
            });
        }
        
        return list;
    }
    
    
    public void ExportConsole(List<LopHoc> l)
    {
      //  System.out.println("SL all:"+fullDsLop.size()+"\n");
        System.out.println("SL filter:"+l.size()+"\n");
        for (LopHoc lopHoc : l) {
           
           System.out.print("Mã Mon: "+lopHoc.getmaMonHoc()+"\n");
           System.out.print("Mã Lớp: "+lopHoc.getMaLop()+"\n");
           System.out.print("Giảng viên: "+lopHoc.getTenGiangVien()+"\n");
           System.out.print("Hệ DT: "+lopHoc.getHeDaoTao()+"\n");
           System.out.print("Phòng: "+lopHoc.getPhong()+"\n");
           System.out.print("Ngày BD: "+lopHoc.getNgayBatDau()+"\n");
           System.out.print("Ngày KT: "+lopHoc.getNgayKetThuc()+"\n");
           System.out.print("Thứ: "+lopHoc.getThu()+"\n");
           System.out.print("Tiết: "+lopHoc.getTiet()+"\n");
           System.out.print("Tiết BĐ: "+String.valueOf(lopHoc.getTietBatDau())+"\n");
           System.out.print("Tiết KT: "+String.valueOf(lopHoc.getTietKetThuc())+"\n");
           System.out.println("===============================================\n");
        }
   }
   
}
