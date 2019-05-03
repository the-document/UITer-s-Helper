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

        String query ="insert into LOPHOC (maLop,MAMH,giangVien,ngayBD,ngayKT,tiet,thu,phong,heDaoTao,tietBatDau,tietKetThuc,HinhThuc)"
                + " values (?, ?, "+"N'"+lopHoc.getTenGiangVien()+"', ?, ?,?,?,?,?,?,?,?)";
        
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1, lopHoc.getMaLop());
        preparedStmt.setString (2, lopHoc.getmaMonHoc());
        //preparedStmt.setString   (3, lopHoc.getTenGiangVien());
        preparedStmt.setString(3, lopHoc.getNgayBatDau());
        preparedStmt.setString  (4, lopHoc.getNgayKetThuc());
        preparedStmt.setString  (5, lopHoc.getTiet());
        preparedStmt.setString  (6, lopHoc.getThu());
        preparedStmt.setString  (7, lopHoc.getPhong());
        preparedStmt.setString  (8, lopHoc.getHeDaoTao());
        preparedStmt.setInt(9, lopHoc.getTietBatDau());
        preparedStmt.setInt(10, lopHoc.getTietKetThuc());
        preparedStmt.setString  (11, lopHoc.getHinhthucDay());

        // execute the preparedstatement
        preparedStmt.execute();
      
        preparedStmt.close();
        this.CloseConnection();
        
        return true;
    }
    
    public boolean SaveAllLopHoc(List<LopHoc> listLopHoc) throws SQLException{
        
        super.ConnectToDatabase();
        String query ="insert into LOPHOC (maLop,MAMH,giangVien,ngayBD,ngayKT,tiet,thu,phong,heDaoTao,tietBatDau,tietKetThuc,HinhThuc)"
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

        preparedStmt.close();
        this.CloseConnection();
        return true;
    }
    
    public List<LopHoc> GetAllLopHoc() throws SQLException{
        List<LopHoc> list=new ArrayList<LopHoc>();
        String query="select * from LOPHOC";
        
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
        this.CloseConnection();
        return list;
    }
    
    //type: CQUI, CLC,...
    public List<LopHoc> GetListCourseTheoryOfEducationProgram(String type) throws SQLException{
        List<LopHoc> list=new ArrayList<>();
        
        String query="SELECT * FROM `LOPHOC` WHERE heDaoTao='"+type+"' AND HinhThuc='LT' ";
        
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
        this.CloseConnection();
        return list;
    }
    
    public List<LopHoc> GetListCourseTheory(String type,String MaMH) throws SQLException{
        List<LopHoc> list=new ArrayList<>();
        
        String query="SELECT * FROM `LOPHOC` WHERE heDaoTao='"+type+"' AND HinhThuc='LT' and MAMH = '"+MaMH+"'";
        
        super.ConnectToDatabase();
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("GetListCourseTheory - DAL package");
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
        this.CloseConnection();
        return list;
    }
    
    public List<LopHoc> GetListCourseTheory(String type,List<String> lsMaMH) throws SQLException{
        List<LopHoc> list=new ArrayList<>();
        
        String query="SELECT * FROM `LOPHOC` WHERE heDaoTao='"+type+"' AND HinhThuc='LT' and ( MAMH = '";
        for (int i=0;i<lsMaMH.size()-1;i++){
            query=query+lsMaMH.get(i)+"' or MAMH = '";
        }
        query=query+lsMaMH.get(lsMaMH.size()-1)+"' )";
        
        System.out.println("Query: "+query);
        
        super.ConnectToDatabase();
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("GetListCourseTheory - DAL package");
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
        this.CloseConnection();
        return list;
    }
    
    public List<LopHoc> GetListCoursePractice(String type,String MaMH) throws SQLException{
        List<LopHoc> list=new ArrayList<>();
        
        String query="SELECT * FROM `LOPHOC` WHERE heDaoTao='"+type+"' AND HinhThuc='TH' and MAMH = '"+MaMH+"'";
        
        super.ConnectToDatabase();
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("GetListCourseTheory - DAL package");
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
        this.CloseConnection();
        return list;
    }
    
    public List<LopHoc> GetListCoursePractice(String type,List<String> lsMaMH) throws SQLException{
        List<LopHoc> list=new ArrayList<>();
        
        String query="SELECT * FROM `LOPHOC` WHERE heDaoTao='"+type+"' AND HinhThuc='TH' and ( MAMH = '";
        for (int i=0;i<lsMaMH.size()-1;i++){
            query=query+lsMaMH.get(i)+"' or MAMH = '";
        }
        query=query+lsMaMH.get(lsMaMH.size()-1)+"' )";
        
        System.out.println("Query: "+query);
        
        super.ConnectToDatabase();
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("GetListCourseTheory - DAL package");
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
        this.CloseConnection();
        return list;
    }
    
    public List<LopHoc> GetListCoursePracticeOfEducationProgram(String type) throws SQLException{
        List<LopHoc> list=new ArrayList<LopHoc>();
        String query="SELECT * FROM `LOPHOC` WHERE heDaoTao='"+type+"' AND HinhThuc='TH' ";
        
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
        this.CloseConnection();
        return list;
    }
    
    public boolean DeleteAllData() throws SQLException{
        super.ConnectToDatabase();
        String query="delete from LOPHOC";
        
        statement=connection.createStatement();
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();
         
        statement.close();
        this.CloseConnection();
        return true;
    }
}
