/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.LopHocAccess;
import DTO.LopHoc;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class LopHocBLL {
    
    public boolean InsertLopHoc(LopHoc lopHoc) throws SQLException{
        
        LopHocAccess lhac=new LopHocAccess();
        
        return lhac.InsertLopHoc(lopHoc);
    }
    
    public boolean SaveAllLopHoc(List<LopHoc> listLopHoc) throws SQLException{
        LopHocAccess lhac=new LopHocAccess();
        
        return lhac.SaveAllLopHoc(listLopHoc);
    }
    
    //type: CQUI, CLC,...
    public List<LopHoc> GetListCourseTheoryOfEducationProgram(String type) throws SQLException{
        
        LopHocAccess lhac=new LopHocAccess();
        return lhac.GetListCourseTheoryOfEducationProgram(type);
    }
    
    public List<LopHoc> GetListCourseTheory(String CTDT,String MaMH) throws SQLException
    {
       
        LopHocAccess lhac=new LopHocAccess();
        return lhac.GetListCourseTheory(CTDT,MaMH);
    }
    
    public List<LopHoc> GetListCoursePracticeOfEducationProgram(String type) throws SQLException{
        
        LopHocAccess lhac=new LopHocAccess();
        return lhac.GetListCoursePracticeOfEducationProgram(type);
    }
    
    public List<LopHoc> GetListCoursePractice(String CTDT,String MaMH) throws SQLException
     {
       
        LopHocAccess lhac=new LopHocAccess();
        return lhac.GetListCoursePractice(CTDT,MaMH);
     }
    
    public List<LopHoc> GetListCourseTheory(String CTDT,List<String> lsMaMH) throws SQLException{
        LopHocAccess lhac=new LopHocAccess();
        return lhac.GetListCourseTheory(CTDT,lsMaMH);
    }
    
    public List<LopHoc> GetListCoursePractice(String CTDT,List<String> lsMaMH) throws SQLException{
        LopHocAccess lhac=new LopHocAccess();
        return lhac.GetListCoursePractice(CTDT,lsMaMH);
    }
    
    public boolean DeleteAllData() throws SQLException{
       
        LopHocAccess lhac=new LopHocAccess();
        return lhac.DeleteAllData();
    }
}
