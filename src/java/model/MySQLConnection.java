/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DaoMinhThien
 */
public class MySQLConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(
                    "jdbc:mysql://sql12.freemysqlhosting.net/sql12164947", "sql12164947", "WWjZBrTXmg");
//here sonoo is database name, root is username and password  
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    public static void show (){
        
        try{
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from tblCourse c inner join tblMajor m on c.idMajor = m.id ");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) +"-" +rs.getString(5));
            }
            getConnection().close();
        }catch(Exception ex){
            
        }
        
    }
    
    public static void main(String[] args) {
//        createDatabase("TOPUP");
        show();
    }
}
