/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HocKy;
import DTO.MonHoc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class HocKyAccess extends DatabaseAccess{
    
    public List<HocKy> GetAllHocKy() throws SQLException{
           
        this.ConnectToDatabase();
        List<HocKy> list=new ArrayList<HocKy>();
        String query="SELECT * FROM HOCKY";
        
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("Err in get all HocKy - DAL package");
            return null;
        }
        
        while (resultSet.next()) {            
            HocKy h =new HocKy();
            
            h.setMaHK(resultSet.getInt(1));
            h.setTenHK(resultSet.getString(2));
            list.add(h);
        }
            
        statement.close();
        this.CloseConnection();
        return list;
    }
}
