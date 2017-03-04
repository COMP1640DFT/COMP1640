/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConnectDB {
    private static Connection con;
     public static Connection connectdatabase() {

        try {

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            String user = "sa";
            String passwd = "123456";
            String url = "jdbc:sqlserver://10.211.55.3:1433;databaseName=COMP1640";
            con = DriverManager.getConnection(url, user, passwd);
            if(con!=null){
                    System.out.println("thanh cong");
                }
                else{
                    System.out.println("that bai");
                }
        } catch (SQLException e) {

            con = null;
        }
        return con;
    }
      public static Account checkLogin(String idUser, String pass){
       Account acc= null;
       String sql="select * from tblUser where idUser=? and _passWord=? ";
        try {
            PreparedStatement ps=connectdatabase().prepareStatement(sql);
            ps.setString(1, idUser);
            ps.setString(2, pass);
            ResultSet  rs=ps.executeQuery();
            while(rs.next()){
               acc= new Account();
               acc.setIdUser(rs.getString(1));
               acc.setFullName(rs.getString(3));
               acc.setDob(rs.getString(4));
               acc.setEmail(rs.getString(5));
               acc.setPhoneNumber(rs.getString(6));
               acc.setIdAcademy(rs.getInt(7));
               acc.setIdMajor(rs.getInt(8));
               acc.setLever(rs.getInt(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
       
   }
      
      public static void main(String[] args) {
        connectdatabase();
    }
}
