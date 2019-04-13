/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NganhHoc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class NganhHocAccess extends DatabaseAccess{
    
    public List<NganhHoc> GetAllNganhHoc() throws SQLException{
           
        this.ConnectToDatabase();
        List<NganhHoc> list=new ArrayList<NganhHoc>();
        String query="SELECT * from NGANHHOC";
        
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("Err in get all Nganh Hoc - DAL package");
            return null;
        }
        
        while (resultSet.next()) {            
            NganhHoc m =new NganhHoc();
            
            m.setMaNganh(resultSet.getString(1));
            m.setTenNganh(resultSet.getString(2));
              
            list.add(m);
        }
            
        statement.close();
        return list;
    }
}
