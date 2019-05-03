/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.MonHoc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class MonHocAccess extends DatabaseAccess{
    
    //khóa học vd: 2018, 2019,2020
     public List<MonHoc> GetAllMonHoc(int khoahoc,String nganhhoc,int hocky) throws SQLException{
           
        this.ConnectToDatabase();
        List<MonHoc> list=new ArrayList<MonHoc>();
        String query="SELECT MONHOC.* " +
            " FROM MONHOC , MONHOC_HOCKY , HOCKY , MONHOC_NGHANHHOC " +
            " WHERE MONHOC.MAMH = MONHOC_HOCKY.MAMH " +
                    " AND MONHOC_HOCKY.MAHK = HOCKY.MAHK " +
                    " AND MONHOC.MAMH = MONHOC_NGHANHHOC.MAMH " +
                    " AND HOCKY.MAHK = "+hocky+
                    " AND MONHOC_NGHANHHOC.KHOAHOC = "+khoahoc+
                    " AND MONHOC_NGHANHHOC.MANGANH = '"+nganhhoc+"'";

      //  String query="select * from MONHOC";
        
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("Err in get all MON Hoc - DAL package");
            return null;
        }
        
        while (resultSet.next()) {            
            MonHoc m =new MonHoc();
            
            m.setMaMonHoc(resultSet.getString(1));
            m.setTenMonHoc(resultSet.getString(2));
            m.setDonViQL(resultSet.getString(3));
            m.setSoChiLyThuyet(resultSet.getInt(4));
            m.setSoChiThucHanh(resultSet.getInt(5));
           
            
            list.add(m);
        }
            
        statement.close();
        this.CloseConnection();
        return list;
    }
     
    public MonHoc GetMonHoc(String MaMH) throws SQLException{
           
        this.ConnectToDatabase();
         MonHoc m =new MonHoc();
        
        String query="SELECT * from MONHOC where MAMH = '"+MaMH+"'";

        
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("Err in get  MON Hoc - DAL package");
            return null;
        }
        
        while (resultSet.next()) {                 
            m.setMaMonHoc(resultSet.getString(1));
            m.setTenMonHoc(resultSet.getString(2));
            m.setDonViQL(resultSet.getString(3));
            m.setSoChiLyThuyet(resultSet.getInt(4));
            m.setSoChiThucHanh(resultSet.getInt(5));
        }
            
        statement.close();
        this.CloseConnection();
        return m;
    }
}
