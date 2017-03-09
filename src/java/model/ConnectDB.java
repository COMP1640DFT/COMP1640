/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Account;
import entity.Claim;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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
                System.out.println(ex.getMessage());
            }
            String user = "sa";
            String passwd = "123123";
            String url = "jdbc:sqlserver://10.211.55.3:1433;databaseName=COMP1640";
            con = DriverManager.getConnection(url, user, passwd);
            if (con != null) {
                System.out.println("thanh cong");

            } else {
                System.out.println("that bai");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            con = null;
        }
        return con;
    }

    public static Account checkLogin(String idUser, String pass) {
        Account acc = null;
        String sql = "select * from tblUser where idUser=? and _passWord=? ";
        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ps.setString(1, idUser);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc = new Account();
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

    //Get all student send claim without evidence

    public static List<Claim> getStudentUpClaimWithOutEvidence() {
        List<Claim> list = new LinkedList<>();
        String sql = "select c.idClaim, c.title, c.sendDate, u.fullName, cl.name from tblClaim c \n"
                + "inner join tblUser u on c.idUser = u.idUser \n"
                + "inner join tblClassDetail cd on u.idUser = cd.idUser\n"
                + "inner join tblClass cl on cd.idClass = cl.id\n"
                + "where c.filedata = ''";
        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim c = new Claim();
                c.setIdClaim(rs.getInt(1));
                c.setTitle(rs.getString(2));
                c.setSendDate(rs.getDate(3).toString());
                c.setUserFullName(rs.getString(4));
                c.setClassName(rs.getString(5));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        connectdatabase();
       
    }
}
