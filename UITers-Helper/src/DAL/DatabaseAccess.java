/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class DatabaseAccess {
    ahihihihihi-nghi
    
    private String dbURL;
    private String username;
    private String passWord;
    
    protected Connection connection=null;
    protected Statement statement=null;
    protected ResultSet resultSet=null;
       
    public  DatabaseAccess(){
        //set default private part;
        //dbURL="jdbc:mysql://localhost/uiterhelper";
        dbURL="jdbc:mysql://65.52.172.214/uiterhelper?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8";
        username="uiterhelper";
        passWord="uiterhelper";
        
    }
    
    public  DatabaseAccess(String _dbURL,String _username,String _passWord){
        this.dbURL=_dbURL;
        this.username=_username;
        this.passWord=_passWord;
    }
    
    public boolean ConnectToDatabase(){
        try {           
            connection =DriverManager.getConnection(dbURL, username, passWord);
            
            if(connection!=null)
            {
                return true;
            } 
        } catch (Exception e) {
            System.err.println("error conect to database");
        }
        return false;
    }
    
    public void CloseConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
