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
import entity.Statistic;
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

    private Connection con;

    public ConnectDB() {
    }

    public void connectdatabase() {
        this.con = MySQLConnection.getConnection();
//        try {
//
//            try {
//                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println(ex.getMessage());
//            }
//            String user = "sa";
//            String passwd = "123123";
//            String url = "jdbc:sqlserver://10.211.55.3:1433;databaseName=COMP1640";
//            con = DriverManager.getConnection(url, user, passwd);
//            if (con != null) {
//                System.out.println("thanh cong");
//
//            } else {
//                System.out.println("that bai");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            con = null;
//        }
    }

    public Account checkLogin(String idUser, String pass) {
        Account acc = null;
        String sql = "select * from tblUser where idUser=? and _passWord=? ";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;

    }

    public Account getAccoutnByid(String idUser) {
        Account acc = null;
        String sql = "select * from tblUser where idUser=? ";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idUser);
         
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;

    }
    
    public List<Claim> getAllClaim() {
        String sql = "select c.idClaim,c.title,c.content,c.sendDate,c.filedata,c.idUser,c.idCM from tblClaim c";
        Claim claim = null;
        Decision s = null;
        List<Claim> lClaim = new ArrayList<>();
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
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
                lClaim.add(claim);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lClaim;
    }

    public Claim getClaimByIdClaim(int id) {
        String sql = "select c.idClaim,c.title,c.content,c.sendDate,c.filedata,c.idUser,c.idCM,u.fullName from tblClaim c join tblUser u on u.idUser=c.idUser  where c.idClaim=?";
        Claim claim = null;

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
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
                claim.setUserFullName(rs.getString(8));

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claim;
    }
    
    public Decision getDecissionById(int id){
        String sql = "select d.idUser,d.content,u.fullName from tblDecision d join tblUser u on u.idUser=d.idUser  where d.idClaim=?";
        Decision s = null;

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s = new Decision();
                s.setIdUser(rs.getString(1));
                s.setContent(rs.getString(2));
                s.setFullNameEC(rs.getString(3));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public Claim getClaimById(int id) {
        String sql = "select idClaim,title,content,sendDate,filedata,idUser from tblClaim where idClaim = ?";
        Claim claim = null;

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
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
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claim;
    }

    //Get all student send claim without evidence
    public List<Claim> getStudentUpClaimWithOutEvidence() {
        List<Claim> list = new LinkedList<>();
        String sql = "select c.idClaim, c.title, c.sendDate, u.fullName from tblClaim c \n"
                + "inner join tblUser u on c.idUser = u.idUser \n"
                + "where c.filedata = ''";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim c = new Claim();
                c.setIdClaim(rs.getInt(1));
                c.setTitle(rs.getString(2));
                c.setSendDate(rs.getDate(3).toString());
                c.setUserFullName(rs.getString(4));
                list.add(c);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getStudentUpClaimWithOutEvidenceInMajor(int id) {
        List<Claim> list = new LinkedList<>();
        String sql = "select c.idClaim, c.title, c.sendDate, u.fullName from tblClaim c \n"
                + "inner join tblUser u on c.idUser = u.idUser \n"
                + "inner join tblMajor m on u.idMajor = m.id\n"
                + "where c.filedata = '' and m.id = ?";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim c = new Claim();
                c.setIdClaim(rs.getInt(1));
                c.setTitle(rs.getString(2));
                c.setSendDate(rs.getDate(3).toString());
                c.setUserFullName(rs.getString(4));
                list.add(c);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Get all claim unresolved after 14 days
    public List<Claim> getAllClaimUnresolvedAfterTwoWeek() {
        String sql = "select c.idClaim, c.title,c.content, c.sendDate,c.filedata, c._status, c.idUser, u.fullName \n"
                + "from tblClaim c \n"
                + "join tblUser u on c.idUser = u.idUser \n"
                + "where c.sendDate <= ADDDATE(NOW(),-14) and _status = 0";
        List<Claim> list = new LinkedList<>();

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getAllClaimUnresolvedAfterTwoWeekInMajor(int id) {
        String sql = "select c.idClaim, c.title,c.content, c.sendDate,c.filedata, c._status, c.idUser, u.fullName \n"
                + "from tblClaim c \n"
                + "join tblUser u on c.idUser = u.idUser\n"
                + "join tblMajor m on u.idMajor = m.id \n"
                + "where c.sendDate <= ADDDATE(NOW(),-14) and _status = 0 and m.id = ?";
        List<Claim> list = new LinkedList<>();

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
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
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getAllClaimManage(int idMajor) {
        String sql = "SELECT * \n"
                + "FROM tblClaimManage tcm\n"
                + "INNER JOIN tblSubject ts ON tcm.idSubject = ts.id\n"
                + "INNER JOIN tblMajor tm ON tm.id = ts.idMajor WHERE idMajor = ?";
        List<Claim> list = new LinkedList<>();

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMajor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setIdClaim(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setCreateDate(rs.getString(3));
                claim.setEndDate(rs.getString(4));
                claim.setIdSubject(rs.getInt(5));
                claim.setStatus(rs.getInt(6));
                list.add(claim);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getAllClaimOfStudent(String user, int idCM) {
        String sql = "select c.idClaim, c.title,c.content, c.sendDate,c.filedata,c._status ,c.idUser\n"
                + "from tblClaim c \n"
                + " where c.idUser = ? and c.idCM = ?";
        List<Claim> list = new LinkedList<>();

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, idCM);
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
                list.add(claim);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getAllClaimOfStudentInAFaculty(int majorID) {
        String sql = " select  c.idClaim, c.title,c.content, c.sendDate,c.filedata,c._status ,c.idUser "
                + "from tblClaim c join tblUser u "
                + "on c.idUser = u.idUser where u.idMajor = ?";
        List<Claim> list = new LinkedList<>();
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, majorID);
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
                list.add(claim);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Claim getClaimOfStudentInAFacultyByIdClaim(int majorID, int idclaim) {
        String sql = " select  c.idClaim, c.title,c.content, c.sendDate,c.filedata,c._status ,c.idUser,u.fullName "
                + "from tblClaim c join tblUser u "
                + "on c.idUser = u.idUser where u.idMajor =? and c.idClaim = ? ";
        Claim claim = new Claim();
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, majorID);
            ps.setInt(2, idclaim);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                claim.setIdClaim(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setContent(rs.getString(3));
                claim.setSendDate(rs.getString(4));
                claim.setFiledata(rs.getString(5));
                claim.setStatus(rs.getInt(6));
                claim.setIdUser(rs.getString(7));
                claim.setUserFullName(rs.getString(8));

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claim;
    }

    public Decision getDecisionOfAClaim(int claimID) {
        String sql = " select c.title,d.content,d.sendDate,d._status,u.fullName,d.idUser from tblDecision d\n"
                + " join tblUser u on d.idUser = u.idUser \n"
                + " join tblClaim c on d.idClaim = c.idClaim\n"
                + " where d.idClaim = ?";
        Decision d = null;

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, claimID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                d = new Decision();
                d.setClaimTitle(rs.getString(1));
                d.setContent(rs.getString(2));
                d.setSendDate(rs.getString(3));
                d.setStatus(rs.getInt(4));
                d.setFullNameEC(rs.getString(5));
                d.setIdUserEC(rs.getString(6));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public List<Major> getListMajor() {
        List<Major> list = new LinkedList<>();
        String sql = "select * from tblMajor";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Major m = new Major();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                list.add(m);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Major getMajor(int id) {
        String sql = " select * from tblMajor where id = ?";
        Major m = null;
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Major();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public List<Major> getCountClaimByMajor(String year) {
        List<Major> list = new ArrayList<>();
        String sql = "select tm.id, tm.name from tblClaim tc inner join tblUser tu on tc.idUser = tu.idUser \n"
                + "inner join tblMajor tm on tu.idMajor = tm.id\n"
                + "inner join tblClaimManage tcm on tc.idCM = tcm.idCM \n"
                + "where \n"
                + "tcm.createDate between '" + year + "-01-01' and '" + year + "-12-31'\n";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Major m = new Major();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
                list.add(m);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Statistic> getAllClaim(String year, int idMajor) {
        List<Statistic> list = new ArrayList<>();
        String sql = "select tm.id, tm.name, tc.sendDate, tc.idUser,tc.title, ts._name from tblClaim tc inner join tblUser tu on tc.idUser = tu.idUser \n"
                + "inner join tblMajor tm on tu.idMajor = tm.id\n"
                + "inner join tblClaimManage tcm on tc.idCM = tcm.idCM \n"
                + "inner join tblSubject ts on ts.id = tc.idSubject\n"
                + "where \n"
                + "tc.sendDate between '" + year + "-01-01' and '" + year + "-12-31'\n"
                + "and tm.id = " + idMajor;
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Statistic s = new Statistic();
                s.setData(rs.getInt(1));
                s.setTitle(rs.getString(2));
                s.setYear(rs.getString(3));
                s.setUser(rs.getString(4));
                s.setTitleClaim(rs.getString(5));
                s.setNameSubject(rs.getString(6));
                list.add(s);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    public List<Statistic> getCountStudentUpClaim(String year, String month, int idMajor) {
//        List<Statistic> list = new ArrayList<>();
//        String sql = "select count(tc.idUser), tc.idUser from tblClaim tc inner join tblUser tu on tc.idUser = tu.idUser \n"
//                + "inner join tblMajor tm on tu.idMajor = tm.id\n"
//                + "inner join tblClaimManage tcm on tc.idCM = tcm.idCM \n"
//                + "where \n"
//                + "tc.sendDate between '" + year + "-01-01' and '" + year + "-12-31'\n"
//                + "and tm.id = " + idMajor + " group by tc.idUser";
//        try {
//            connectdatabase();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                result++;
//            }
//            con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    public boolean createClaim(Claim claim) {
        try {
            connectdatabase();
            String sql = "INSERT INTO tblClaim ( title, content,sendDate, filedata, _status, idUser, idSubject, idCM) VALUES"
                    + "(?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, claim.getTitle());
            st.setString(2, claim.getContent());
            st.setString(3, claim.getSendDate());
            st.setString(4, claim.getFiledata());
            st.setInt(5, claim.getStatus());
            st.setString(6, claim.getIdUser());
            st.setInt(7, claim.getIdSubject());
            st.setInt(8, claim.getIdCM());
            if (st.executeUpdate() > 0) {
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean createDecision(Decision d){
        try {
        connectdatabase();
        String sql="Insert into tblDecision (idClaim,content,sendDate, _status,idUser) values(?,?,?,?,?)";
         PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, d.getIdClaim());
            st.setString(2, d.getContent());
            st.setString(3, d.getSendDate());
            st.setInt(4, d.getStatus());
            st.setString(5, d.getIdUser());
           
            if (st.executeUpdate() > 0) {
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean updateDecision(int status, String content, int idClaim){
        try {
        connectdatabase();
        String sql="Update tblDecision set content=?, _status=? where idClaim=?";
         PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, content);
            st.setInt(2, status);
            st.setInt(3, idClaim);
            if (st.executeUpdate() > 0) {
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public boolean updateClaim(int status, int idClaim){
        try {
        connectdatabase();
        String sql="Update tblClaim set  _status=? where idClaim=?";
         PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, idClaim);
            if (st.executeUpdate() > 0) {
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
       public boolean updateFileofClaim(String file, int idClaim){
        try {
        connectdatabase();
        String sql="Update tblClaim set  filedata=? where idClaim=?";
         PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, file);
            st.setInt(2, idClaim);
            if (st.executeUpdate() > 0) {
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
    public List<Account> getListEccoor(int idMajor){
        List<Account> list = new ArrayList<>();
        String sql = "select * from tblUser where idMajor = ? and lever = 4 ";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMajor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setIdUser(rs.getString(1));
                acc.setFullName(rs.getString(3));
                acc.setDob(rs.getString(4));
                acc.setEmail(rs.getString(5));
                acc.setPhoneNumber(rs.getString(6));
                acc.setIdAcademy(rs.getInt(7));
                acc.setIdMajor(rs.getInt(8));
                acc.setLever(rs.getInt(9));
                list.add(acc);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    

    public void main(String[] args) {
//        con;
//        Claim c = new Claim("1", "1", "2017-02-12", "1", "taincgc", 1, 0);
//        boolean a = createClaim(c);
//        System.out.println(a + "");
//        for (Claim c : ConnectDB.getAllClaimOfStudent("dungkv")) {
//            System.out.println(c.getTitle());
//        }

//        System.out.println(getCountClaimByMajor("2017",1));
//        getCountClaimByMonth("2017",1);
    }
}
