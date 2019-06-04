/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Global;
import DTO.Calender;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class CalenderAccess extends DatabaseAccess{
    
    public boolean InsertCalender(Calender calender) throws SQLException{
        
        super.ConnectToDatabase();
        String query ="insert into CALENDAR (ThoiGian,DiaDiem,MoTa,MaSinhVien,NgayNhacNho)"
                + " values (?, ?, ?, ?,?)";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date date; 
        
        try {
            date = formatter.parse(calender.getTime());
           // java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            java.sql.Timestamp d = new java.sql.Timestamp (date.getTime());
//            System.out.println(date.toString());
//            System.out.println(sqlDate.toString());
//            System.out.println(d.toString());
            preparedStmt.setTimestamp(1, d);
            preparedStmt.setString(2, calender.getLocation());
            preparedStmt.setString(3, calender.getDescribe());
            preparedStmt.setString(4, Global.username);//MSSV
            preparedStmt.setInt(5, calender.getRemindDay());
            System.out.println(Global.username);

        // execute the preparedstatement
        preparedStmt.execute();
        } catch (ParseException ex) {
            Logger.getLogger(CalenderAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
       


        preparedStmt.close();
        this.CloseConnection();
        return true;
    }
 
    public List<Calender> GetPersonalCalenderInfuture() throws SQLException{
        List<Calender> result=new ArrayList<>();
        
        this.ConnectToDatabase();
        //need add constrain time after curren day
        LocalDateTime currentDateTime=LocalDateTime.of(Global.CurrentYear, Global.CurrentMonth, 1, 0, 0);
        Timestamp currentTime =Timestamp.valueOf(currentDateTime);
        System.out.println(currentTime.toString());
        String query="SELECT * FROM CALENDAR where MaSinhVien = "+Global.username+" and ThoiGian >= '"+currentTime+"'";
        
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("Err in get Calendar - DAL package");
            return null;
        }
        
        while (resultSet.next()) {            
            Calender c =new Calender();
            
            c.setKey(String.valueOf(resultSet.getInt(1)));
            c.setTime(resultSet.getTimestamp(2).toString());
            c.setLocation(resultSet.getString(3));
            c.setDescribe(resultSet.getString(4));
           
            result.add(c);
        }
            
        resultSet.close();
        statement.close();
        this.CloseConnection();
        return result;
    }
    
    public List<Calender> GetPersonalCalenderCurrentMonth() throws SQLException{
        List<Calender> result=new ArrayList<>();
        
        this.ConnectToDatabase();
       
        LocalDateTime currentDateTime=LocalDateTime.of(Global.CurrentYear, Global.CurrentMonth, 1, 0, 0);
        Timestamp currentTime =Timestamp.valueOf(currentDateTime);
        //int timeHasPassed=currentDateTime.getDayOfMonth();
        LocalDateTime nexTime= currentDateTime.plusMonths(1);
        Timestamp nextMonth=Timestamp.valueOf(nexTime);
        System.out.println(nextMonth.toString());
        
        String query="SELECT * FROM CALENDAR where MaSinhVien = "+Global.username+" and ThoiGian >= '"+currentTime+"' and ThoiGian < '"+nextMonth+"' ORDER BY ThoiGian ASC";
        
        statement=connection.createStatement();
        resultSet =statement.executeQuery(query);
        
        if(resultSet.isBeforeFirst()==false)
        {
            System.err.println("Err in get Calendar this month- DAL package");
            return null;
        }
        
        while (resultSet.next()) {            
            Calender c =new Calender();
            
            c.setKey(String.valueOf(resultSet.getInt(1)));
            c.setTime(resultSet.getTimestamp(2).toString());
            c.setLocation(resultSet.getString(3));
            c.setDescribe(resultSet.getString(4));
            c.setRemindDay(resultSet.getInt(5));
           
            result.add(c);
        }
            
        resultSet.close();
        statement.close();
        this.CloseConnection();
        return result;
    }
    
    public boolean DeleteCalender(String id) throws SQLException{
        super.ConnectToDatabase();
        String query="DELETE from CALENDAR WHERE MaCalendar = "+id;
        
        statement=connection.createStatement();
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();
         
        statement.close();
        this.CloseConnection();
        return true;
    }
}
