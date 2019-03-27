/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.LopHoc;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class LopHocAccess extends DatabaseAccess{
    
    public boolean InsertLopHoc(LopHoc lopHoc) throws SQLException{
        super.ConnectToDatabase();

        String query ="insert into LopHoc (maLop,maMon,giangVien,ngayBD,ngayKT,tiet,thu,phong,heDaoTao,tietBatDau,tietKetThuc,HinhThuc)"
                + " values (?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
        
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1, lopHoc.getMaLop());
        preparedStmt.setString (2, lopHoc.getmaMonHoc());
        preparedStmt.setString   (3, lopHoc.getTenGiangVien());
        preparedStmt.setString(4, lopHoc.getNgayBatDau());
        preparedStmt.setString  (5, lopHoc.getNgayKetThuc());
        preparedStmt.setString  (6, lopHoc.getTiet());
        preparedStmt.setString  (7, lopHoc.getThu());
        preparedStmt.setString  (8, lopHoc.getPhong());
        preparedStmt.setString  (9, lopHoc.getHeDaoTao());
        preparedStmt.setInt(10, lopHoc.getTietBatDau());
        preparedStmt.setInt(11, lopHoc.getTietKetThuc());
        preparedStmt.setString  (12, lopHoc.getHinhthucDay());

        // execute the preparedstatement
        preparedStmt.execute();
      
        connection.close();
        
        return true;
    }
    
    public boolean SaveAllLopHoc(List<LopHoc> listLopHoc) throws SQLException{
        
        super.ConnectToDatabase();
        String query ="insert into LopHoc (maLop,maMon,giangVien,ngayBD,ngayKT,tiet,thu,phong,heDaoTao,tietBatDau,tietKetThuc,HinhThuc)"
                + " values (?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        
        for (LopHoc lopHoc : listLopHoc) {
            
            preparedStmt.setString (1, lopHoc.getMaLop());
            preparedStmt.setString (2, lopHoc.getmaMonHoc());
            preparedStmt.setString   (3, lopHoc.getTenGiangVien());
            preparedStmt.setString(4, lopHoc.getNgayBatDau());
            preparedStmt.setString  (5, lopHoc.getNgayKetThuc());
            preparedStmt.setString  (6, lopHoc.getTiet());
            preparedStmt.setString  (7, lopHoc.getThu());
            preparedStmt.setString  (8, lopHoc.getPhong());
            preparedStmt.setString  (9, lopHoc.getHeDaoTao());
            preparedStmt.setInt(10, lopHoc.getTietBatDau());
            preparedStmt.setInt(11, lopHoc.getTietKetThuc());
            preparedStmt.setString  (12, lopHoc.getHinhthucDay());

            // execute the preparedstatement
            preparedStmt.execute();
        }

        connection.close();        
        return true;
    }
    
    public List<LopHoc> GetAllLopHoc() throws SQLException{
        List<LopHoc> list=new ArrayList<LopHoc>();
        String query="select * from LopHoc";
        
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("Err in get all Lop Hoc - DAL package");
            return null;
        }
        
        while (resultSet.next()) {            
            LopHoc l =new LopHoc();
            
            l.setMaLop(resultSet.getString(1));
            l.setMaMonHoc(resultSet.getString(2));
            l.setTenGiangVien(resultSet.getString(3));
            l.setNgayBatDau(resultSet.getString(4));
            l.setNgayKetThuc(resultSet.getString(5));
            l.setTiet(resultSet.getString(6));
            l.setThu(resultSet.getString(7));
            l.setPhong(resultSet.getString(8));
            l.setHeDaoTao(resultSet.getString(9));
            l.setTietBatDau(resultSet.getInt(10));
            l.setTietKetThuc(resultSet.getInt(11));
            l.setHinhthucDay(resultSet.getString(12));
            
            list.add(l);
        }
            
        statement.close();
        return list;
    }
    
    //type: CQUI, CLC,...
    public List<LopHoc> GetListCourseTheoryOfEducationProgram(String type) throws SQLException{
        List<LopHoc> list=new ArrayList<LopHoc>();
        
        String query="SELECT * FROM `LopHoc` WHERE heDaoTao='"+type+"' AND HinhThuc='LT' ";
        
        super.ConnectToDatabase();
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("GetListCourseTheoryOfEducationProgram - DAL package");
            return null;
        }
        
        while (resultSet.next()) {            
            LopHoc l =new LopHoc();
            
            l.setMaLop(resultSet.getString(1));
            l.setMaMonHoc(resultSet.getString(2));
            l.setTenGiangVien(resultSet.getString(3));
            l.setNgayBatDau(resultSet.getString(4));
            l.setNgayKetThuc(resultSet.getString(5));
            l.setTiet(resultSet.getString(6));
            l.setThu(resultSet.getString(7));
            l.setPhong(resultSet.getString(8));
            l.setHeDaoTao(resultSet.getString(9));
            l.setTietBatDau(resultSet.getInt(10));
            l.setTietKetThuc(resultSet.getInt(11));
            l.setHinhthucDay(resultSet.getString(12));
            
            list.add(l);
        }
            
        statement.close();
        
        return list;
    }
    
    public List<LopHoc> GetListCoursePracticeOfEducationProgram(String type) throws SQLException{
        List<LopHoc> list=new ArrayList<LopHoc>();
        String query="SELECT * FROM `LopHoc` WHERE heDaoTao='"+type+"' AND HinhThuc='TH' ";
        
        super.ConnectToDatabase();
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("GetListCoursePracticeOfEducationProgram - DAL package");
            return null;
        }
        
        while (resultSet.next()) {            
            LopHoc l =new LopHoc();
            
            l.setMaLop(resultSet.getString(1));
            l.setMaMonHoc(resultSet.getString(2));
            l.setTenGiangVien(resultSet.getString(3));
            l.setNgayBatDau(resultSet.getString(4));
            l.setNgayKetThuc(resultSet.getString(5));
            l.setTiet(resultSet.getString(6));
            l.setThu(resultSet.getString(7));
            l.setPhong(resultSet.getString(8));
            l.setHeDaoTao(resultSet.getString(9));
            l.setTietBatDau(resultSet.getInt(10));
            l.setTietKetThuc(resultSet.getInt(11));
            l.setHinhthucDay(resultSet.getString(12));
            
            list.add(l);
        }
            
        statement.close();
        
        return list;
    }
    
    public boolean DeleteAllData() throws SQLException{
        super.ConnectToDatabase();
        String query="delete from LopHoc";
        
        statement=connection.createStatement();
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();
         
        statement.close();
        return true;
    }
}