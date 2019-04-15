/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.LopHocAccess;
import DAL.MonHocAccess;
import DTO.MonHoc;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class MonHocBLL {
    
    //khóa học vd: 2018, 2019,2020
     public List<MonHoc> GetAllMonHoc(String MSSV,String nganhhoc,int hocky) throws SQLException
     {
         if(MSSV.isEmpty()||nganhhoc.isEmpty()||hocky<1)
             return null;
         
         
         //convert mssv to khóa học
         // khoa 15-20
         String key=MSSV.substring(0, 2);
         int khoa=2018;
         if(key.equals("15"))
             khoa=2015;
         else
             if(key.equals("16"))
             khoa=2016;
         else
                 if(key.equals("17"))
             khoa=2017;
         else
                     if(key.equals("18"))
             khoa=2018;
         else
                         if(key.equals("19"))
             khoa=2019;
         else
                             if(key.equals("20"))
             khoa=2020;
         

         MonHocAccess lhAccess=new MonHocAccess();
         return lhAccess.GetAllMonHoc(khoa, nganhhoc, hocky);
     }


     public MonHoc GetMonHoc(String MaMH) throws SQLException{
         MonHocAccess mha=new MonHocAccess();
         return mha.GetMonHoc(MaMH);
     }
}
