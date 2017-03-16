/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Account;
import entity.Claim;
import entity.Decision;
import entity.Major;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                claim.setSendDate(rs.getString(4));
                claim.setFiledata(rs.getString(5));
                claim.setIdUser(rs.getString(6));
                claim.setIdCM(rs.getInt(7));
                s = new Decision();
                s.setIdUser(rs.getString(8));
                claim.setDecision(s);
                lClaim.add(claim);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lClaim;
    }

    public static Claim getClaimByIdClaim(int id) {
        String sql = "select c.idClaim,c.title,c.content,c.sendDate,c.filedata,c.idUser,c.idCM,d.idUser,d.content,u.fullName from tblClaim c join tblDecision d on c.idClaim=d.idClaim join tblUser u on u.idUser=c.idUser  where c.idClaim=?";
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
                claim.setSendDate(rs.getString(4));
                claim.setFiledata(rs.getString(5));
                claim.setIdUser(rs.getString(6));
                claim.setIdCM(rs.getInt(7));
                s = new Decision();
                s.setIdUser(rs.getString(8));
                s.setContent(rs.getString(9));
                claim.setUserFullName(rs.getString(10));
                claim.setDecision(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claim;
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

    public static List<Claim> getStudentUpClaimWithOutEvidenceInMajor(int id) {
        List<Claim> list = new LinkedList<>();
        String sql = "select c.idClaim, c.title, c.sendDate, u.fullName, cl.name from tblClaim c \n"
                + "inner join tblUser u on c.idUser = u.idUser \n"
                + "inner join tblClassDetail cd on u.idUser = cd.idUser\n"
                + "inner join tblClass cl on cd.idClass = cl.id\n"
                + "inner join tblMajor m on cl.idMajor = m.id\n"
                + "where c.filedata = '' and m.id = ?";
        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ps.setInt(1, id);
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

    // Get all claim unresolved after 14 days
    public static List<Claim> getAllClaimUnresolvedAfterTwoWeek() {
        String sql = "select c.idClaim, c.title,c.content, c.sendDate,c.filedata, c._status, c.idUser, u.fullName \n"
                + "from tblClaim c \n"
                + "join tblUser u on c.idUser = u.idUser \n"
                + "where c.sendDate <= DATEADD(WEEK,-2,GETDATE()) and _status = 0";
        List<Claim> list = new LinkedList<>();

        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setIdClaim(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setContent(rs.getString(3));
                claim.setSendDate(rs.getString(4));
                claim.setFiledata(rs.getString(5));
                claim.setStatus(rs.getInt(6));
                claim.setIdUser(rs.getString(7));
                claim.setUserFullName(rs.getString(8));
                list.add(claim);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<Claim> getAllClaimUnresolvedAfterTwoWeekInMajor(int id) {
        String sql = "select c.idClaim, c.title,c.content, c.sendDate,c.filedata, c._status, c.idUser, u.fullName \n"
                + "from tblClaim c \n"
                + "join tblUser u on c.idUser = u.idUser\n"
                + "join tblMajor m on u.idMajor = m.id \n"
                + "where c.sendDate <= DATEADD(WEEK,-2,GETDATE()) and _status = 0 and m.id = ?";
        List<Claim> list = new LinkedList<>();

        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setIdClaim(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setContent(rs.getString(3));
                claim.setSendDate(rs.getString(4));
                claim.setFiledata(rs.getString(5));
                claim.setStatus(rs.getInt(6));
                claim.setIdUser(rs.getString(7));
                claim.setUserFullName(rs.getString(8));
                list.add(claim);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<Major> getListMajor() {
        List<Major> list = new LinkedList<>();
        String sql = "select * from tblMajor";
        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Major m = new Major();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                list.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static int getCountClaimByMajor(String year, int idMajor) {
        int result = 0;
        String sql = "select count(*) from tblClaim tc inner join tblUser tu on tc.idUser = tu.idUser \n"
                + "inner join tblMajor tm on tu.idMajor = tm.id\n"
                + "inner join tblClaimManage tcm on tc.idCM = tcm.idCM \n"
                + "where \n"
                + "tcm.createDate between '01/01/" + year + "' and '12/31/" + year + "'\n"
                + "and tm.id = " + idMajor;
        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int getCountClaimByMonth(String year, String month, int idMajor) {
        int result = 0;
        String sql = "select count(*) from tblClaim tc inner join tblUser tu on tc.idUser = tu.idUser \n"
                + "inner join tblMajor tm on tu.idMajor = tm.id\n"
                + "inner join tblClaimManage tcm on tc.idCM = tcm.idCM \n"
                + "where \n"
                + "tc.sendDate like '%" + year + "-" + month + "-%'"
                + "and tm.id = " + idMajor;
        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int getCountStudentUpClaim(String year, String month, int idMajor) {
        int result = 0;
        String sql = "select count(tc.idUser), tc.idUser from tblClaim tc inner join tblUser tu on tc.idUser = tu.idUser \n"
                + "inner join tblMajor tm on tu.idMajor = tm.id\n"
                + "inner join tblClaimManage tcm on tc.idCM = tcm.idCM \n"
                + "where \n"
                + "tc.sendDate like '%" + year + "-" + month + "-%'"
                + "and tm.id = " + idMajor + " group by tc.idUser";
        try {
            PreparedStatement ps = connectdatabase().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean createClaim(Claim claim){
         try {
            String sql="insert into tblClaim values(?,?,?,?,?,?,?)";
            PreparedStatement st = connectdatabase().prepareStatement(sql);
            st.setString(1, claim.getTitle());
            st.setString(2, claim.getContent());
            st.setString(3, claim.getSendDate());
            st.setString(4, claim.getFiledata());
            st.setInt(5, claim.getStatus());
            st.setString(6, claim.getIdUser());
            st.setInt(7, claim.getIdCM());
            if(st.executeUpdate()>0){
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
    }

    public static void main(String[] args) {
        connectdatabase();
        Claim c= new Claim("1", "1", "2017-02-12", "1", "taincgc",1, 0);
        boolean a= createClaim(c);
        System.out.println(a+"");
    }
}
