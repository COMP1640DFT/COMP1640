/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Account;
import entity.Claim;
import entity.Decision;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Connect_DB {

    private static Connection con;

    public static Connection connectdatabase() {

        try {

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connect_DB.class.getName()).log(Level.SEVERE, null, ex);
            }
            String user = "sa";
            String passwd = "123456";
            String url = "jdbc:sqlserver://10.211.55.3:1433;databaseName=COMP1640";
            con = DriverManager.getConnection(url, user, passwd);
            if (con != null) {
                System.out.println("thanh cong");
            } else {
                System.out.println("that bai");
            }
        } catch (SQLException e) {

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
            Logger.getLogger(Connect_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;

    }

    public static List<Claim> getAllClaim() {
        String sql = "select c.idClaim,c.title,c.content,c.sendDate,c.filedata,c.idUser,c.idCM,d.idUser from tblClaim c join tblDecision d on c.idClaim=d.idClaim";
        Claim claim = null;
        Decision s = null;
        List<Claim> lClaim = new ArrayList<>();
        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                claim = new Claim();
                claim.setIdClaim(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setContent(rs.getString(3));
                claim.setDate(rs.getString(4));
                claim.setFiledata(rs.getString(5));
                claim.setIdUser(rs.getString(6));
                claim.setIdCM(rs.getInt(7));
                s = new Decision();
                s.setIdUser(rs.getString(8));
                claim.setDecision(s);
                lClaim.add(claim);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lClaim;
    }

    public static Claim getClaimByIdClaim(int id) {
        String sql ="select c.idClaim,c.title,c.content,c.sendDate,c.filedata,c.idUser,c.idCM,d.idUser,d.content,u.fullName from tblClaim c join tblDecision d on c.idClaim=d.idClaim join tblUser u on u.idUser=c.idUser  where c.idClaim=?";
        Claim claim = null;
        Decision s = null;

        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                claim = new Claim();
                claim.setIdClaim(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setContent(rs.getString(3));
                claim.setDate(rs.getString(4));
                claim.setFiledata(rs.getString(5));
                claim.setIdUser(rs.getString(6));
                claim.setIdCM(rs.getInt(7));
                s = new Decision();
                s.setIdUser(rs.getString(8));
                s.setContent(rs.getString(9));
                claim.setFullnameS(rs.getString(10));
                claim.setDecision(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claim;
    }

    public static void main(String[] args) {
        connectdatabase();
        Claim a = getClaimByIdClaim(1);
        System.out.println("---" + a.getFullnameS());
    }
}
