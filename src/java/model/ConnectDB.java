/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Academy;
import entity.Account;
import entity.Claim;
import entity.Decision;
import entity.Faculty;
import entity.ItemSelected;
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
                acc.setIdFaculty(rs.getInt(8));
                acc.setLever(rs.getInt(9));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    public boolean deleteCM(int idCM) {
        boolean rs = false;
        String sql = "DELETE FROM tblClaimManage WHERE idCM = "+idCM;
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeUpdate()>0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return rs;
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
                acc.setIdFaculty(rs.getInt(8));
                acc.setLever(rs.getInt(9));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;

    }

    public List<Claim> getAllClaim() {
        String sql = "select c.idClaim,c.idUser,c.title,f.name ,asm._name,im.name,c.sendDate  from tblClaim c join tblClaimManage cm on c.idCM=cm.idCM \n"
                + "join tblADetail ad on cm.idItemAssessment=ad.id \n"
                + "join  tblItemA im on ad.idItem= im.id\n"
                + "join  tblAssessment  asm on asm.id= ad.idAssesment\n"
                + "join tblFaculty f on f.id=asm.idFaculty";
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
                claim.setIdUser(rs.getString(2));
                claim.setTitle(rs.getString(3));
                claim.setFacultyName(rs.getString(4));
                claim.setAssessmentName(rs.getString(5));
                claim.setItemAssessmentName(rs.getString(6));
                claim.setSendDate(rs.getString(7));
                lClaim.add(claim);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lClaim;
    }

    public Claim getClaimByIdClaim(int id) {
        String sql = "select c.idClaim,c.title,c.content,c.sendDate,c.evidence,c.idUser,c.idCM,u.fullName from tblClaim c join tblUser u on u.idUser=c.idUser  where c.idClaim=?";
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

    public Decision getDecissionById(int id) {
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
        String sql = "select idClaim,title,content,sendDate,evidence,idUser, _status from tblClaim where idClaim = ?";
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
                claim.setStatus(rs.getInt(7));
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
                + "where c.evidence = ''";
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
                + "inner join tblFaculty m on u.idFaculty = m.id\n"
                + "where c.evidence = '' and m.id = ?";
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
        String sql = "select c.idClaim, c.title,c.content, c.sendDate,c.evidence, c._status, c.idUser, u.fullName \n"
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
        String sql = "select c.idClaim, c.title,c.content, c.sendDate,c.evidence, c._status, c.idUser, u.fullName \n"
                + "from tblClaim c \n"
                + "join tblUser u on c.idUser = u.idUser\n"
                + "join tblFaculty m on u.idFaculty = m.id \n"
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

    public List<Claim> getAllClaimManage(int idFaculty) {
        String sql = "select tcm.idCM, tcm.title, tcm.createdate, tcm.enddate, tcm._status, ta._name, tia.name \n"
                + "from tblClaimManage tcm\n"
                + "join tblADetail tad on tcm.idItemAssessment = tad.id\n"
                + "join tblAssessment ta on ta.id = tad.idAssesment\n"
                + "join tblFaculty tf on tf.id = ta.idFaculty\n"
                + "join tblItemA tia on tad.idItem = tia.id  where tf.id = ?";
        List<Claim> list = new LinkedList<>();

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idFaculty);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                System.out.println("---" + rs.getInt(1));
                claim.setIdCM(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setCreateDate(rs.getString(3));
                claim.setEndDate(rs.getString(4));
                claim.setStatus(rs.getInt(5));
                claim.setAssessmentName(rs.getString(6));
                claim.setItemAssessmentName(rs.getString(7));
                list.add(claim);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getAllClaimManageFilterByStatus(int idFaculty, int stt) {
        String sql = "select tcm.idCM, tcm.title, tcm.createdate, tcm.enddate, tcm._status, ta._name, tia.name \n"
                + "from tblClaimManage tcm\n"
                + "join tblADetail tad on tcm.idItemAssessment = tad.id\n"
                + "join tblAssessment ta on ta.id = tad.idAssesment\n"
                + "join tblFaculty tf on tf.id = ta.idFaculty\n"
                + "join tblItemA tia on tad.idItem = tia.id  where ta.idFaculty = ? and tcm._status = ?";
        List<Claim> list = new LinkedList<>();

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idFaculty);
            ps.setInt(2, stt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setIdCM(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setCreateDate(rs.getString(3));
                claim.setEndDate(rs.getString(4));
                claim.setStatus(rs.getInt(5));
                claim.setAssessmentName(rs.getString(6));
                claim.setItemAssessmentName(rs.getString(7));
                list.add(claim);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getAllClaimManage(String y) {
        String sql = "select tcm.idCM, tcm.title, tcm.createdate, tcm.enddate, tcm._status, tf.name, ta._name, tia.name from tblClaimManage tcm join tblADetail tad on tcm.idItemAssessment = tad.id \n"
                + "                            join tblAssessment ta on ta.id = tad.idAssesment\n"
                + "                            join tblFaculty tf on tf.id = ta.idFaculty\n"
                + "                            join tblItemA tia on tad.idItem = tia.id WHERE createDate LIKE  '%"+y+"%' ORDER BY tcm.createDate DESC ";
        List<Claim> list = new LinkedList<>();

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setIdClaim(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setCreateDate(rs.getString(3));
                claim.setEndDate(rs.getString(4));
                claim.setStatus(rs.getInt(5));
                claim.setFacultyName(rs.getString(6));
                claim.setAssessmentName(rs.getString(7));
                claim.setItemAssessmentName(rs.getString(8));
                list.add(claim);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getAllClaimManageByStt(int stt, String date) {
        String q[] = {"tcm.createdate", "tcm.enddate"};
        String sql = "select tcm.idCM, tcm.title, tcm.createdate, tcm.enddate, tcm._status, tf.name, ta._name, tia.name from tblClaimManage tcm join tblADetail tad on tcm.idItemAssessment = tad.id \n"
                + "                            join tblAssessment ta on ta.id = tad.idAssesment\n"
                + "                            join tblFaculty tf on tf.id = ta.idFaculty\n"
                + "                            join tblItemA tia on tad.idItem = tia.id where " + q[stt] + "='" + date + "' and tcm._status = " + stt;
        List<Claim> list = new LinkedList<>();

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setIdClaim(rs.getInt(1));
                claim.setTitle(rs.getString(2));
                claim.setCreateDate(rs.getString(3));
                claim.setEndDate(rs.getString(4));
                claim.setStatus(rs.getInt(5));
                claim.setFacultyName(rs.getString(6));
                claim.setAssessmentName(rs.getString(7));
                claim.setItemAssessmentName(rs.getString(8));
                list.add(claim);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int checkSttClaimManage(int idCM) {
        String sql = "select _status from tblClaimManage where idCM = " + idCM;
        int stt = -1;

        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stt = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stt;
    }

    public List<ItemSelected> getAllAssessmentDetail() {
        List<ItemSelected> list = new ArrayList<>();
        String sql = "select tad.id, ta._name, tia.name, tf.name from tblADetail tad join tblAssessment ta on ta.id = tad.idAssesment\n"
                + "                            join tblFaculty tf on tf.id = ta.idFaculty\n"
                + "                            join tblItemA tia on tad.idItem = tia.id ";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemSelected item = new ItemSelected();
                item.setValue(rs.getInt(1) + "");
                item.setData(rs.getString(3) + " -- " + rs.getString(2));
                list.add(item);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertClaimM(Claim c) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "INSERT INTO tblClaimManage ( title, createDate, endDate, idItemAssessment, _status) VALUES"
                    + "(?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, c.getTitle());
            st.setString(2, c.getSendDate());
            st.setString(3, c.getEndDate());
            st.setInt(4, c.getIdItemAssessment());
            st.setInt(5, c.getStatus());
            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public List<Claim> getAllClaimOfStudent(String user, int idCM) {
        String sql = "select c.idClaim, c.title,c.content, c.sendDate,c.evidence,c._status ,c.idUser\n"
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
        String sql = " select  c.idClaim, c.title,c.content, c.sendDate,c.evidence,c._status ,u.idUser, f.name, a._name,ia.name \n"
                + "from tblClaim c \n"
                + "join tblUser u on c.idUser = u.idUser \n"
                + "join tblFaculty f on u.idFaculty = f.id\n"
                + "join tblAssessment a on f.id = a.id\n"
                + "join tblADetail ad on a.id = ad.id\n"
                + "join tblItemA ia on ad.idItem = ia.id\n"
                + "where u.idFaculty = ?";
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
                claim.setFacultyName(rs.getString(8));
                claim.setAssessmentName(rs.getString(9));
                claim.setItemAssessmentName(rs.getString(10));
                list.add(claim);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Claim> getAllClaimOfStudentInAFacultyFilterByStatus(int majorID, int stt) {
        String sql = " select  c.idClaim, c.title,c.content, c.sendDate,c.evidence,c._status ,u.idUser, f.name, a._name,ia.name \n"
                + "from tblClaim c \n"
                + "join tblUser u on c.idUser = u.idUser \n"
                + "join tblFaculty f on u.idFaculty = f.id\n"
                + "join tblAssessment a on f.id = a.id\n"
                + "join tblADetail ad on a.id = ad.id\n"
                + "join tblItemA ia on ad.idItem = ia.id\n"
                + "where u.idFaculty = ? and c._status = ?";
        List<Claim> list = new LinkedList<>();
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, majorID);
            ps.setInt(2, stt);
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
                claim.setFacultyName(rs.getString(8));
                claim.setAssessmentName(rs.getString(9));
                claim.setItemAssessmentName(rs.getString(10));
                list.add(claim);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Claim getClaimOfStudentInAFacultyByIdClaim(int majorID, int idclaim) {
        String sql = " select  c.idClaim, c.title,c.content, c.sendDate,c.evidence,c._status ,c.idUser,u.fullName "
                + "from tblClaim c join tblUser u "
                + "on c.idUser = u.idUser where u.idFaculty =? and c.idClaim = ? ";
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

    public List<Faculty> getListMajor() {
        List<Faculty> list = new LinkedList<>();
        String sql = "select * from tblFaculty";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Faculty m = new Faculty();
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

    public Faculty getMajor(int id) {
        String sql = " select * from tblFaculty where id = ?";
        Faculty m = null;
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Faculty();
                m.setId(rs.getInt(1));
                m.setName(rs.getString(2));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public List<Faculty> getCountClaimByMajor(String year) {
        List<Faculty> list = new ArrayList<>();
        String sql = "select tm.id, tm.name from tblClaim tc inner join tblUser tu on tc.idUser = tu.idUser \n"
                + "inner join tblFaculty tm on tu.idFaculty = tm.id\n"
                + "inner join tblClaimManage tcm on tc.idCM = tcm.idCM \n"
                + "where \n"
                + "tcm.createDate between '" + year + "-01-01' and '" + year + "-12-31'\n";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Faculty m = new Faculty();
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

    public List<Statistic> getAllClaim(String year, int idFaculty) {
        List<Statistic> list = new ArrayList<>();
        String sql = "select tm.id, tm.name, tc.sendDate, tc.idUser,tc.title, ta._name from tblClaim tc inner join tblUser tu on tc.idUser = tu.idUser \n"
                + "                inner join tblFaculty tm on tu.idFaculty = tm.id\n"
                + "                inner join tblClaimManage tcm on tc.idCM = tcm.idCM\n"
                + "                inner join tblADetail tad on tcm.idItemAssessment = tad.id \n"
                + "				join tblAssessment ta on ta.id = tad.idAssesment\n"
                + "				join tblFaculty tf on tf.id = ta.idFaculty\n"
                + "				join tblItemA tia on tad.idItem = tia.id\n"
                + "                where\n"
                + "                tc.sendDate between '" + year + "-01-01' and '" + year + "-12-31'\n"
                + "                and tf.id = " + idFaculty;
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

    public boolean createClaim(Claim claim) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "INSERT INTO tblClaim ( title, content,sendDate, evidence, _status, idUser, idCM) VALUES"
                    + "(?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, claim.getTitle());
            st.setString(2, claim.getContent());
            st.setString(3, claim.getSendDate());
            st.setString(4, claim.getFiledata());
            st.setInt(5, claim.getStatus());
            st.setString(6, claim.getIdUser());
            st.setInt(7, claim.getIdCM());
            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean createDecision(Decision d) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "Insert into tblDecision (idClaim,content,sendDate, _status,idUser) values(?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, d.getIdClaim());
            st.setString(2, d.getContent());
            st.setString(3, d.getSendDate());
            st.setInt(4, d.getStatus());
            st.setString(5, d.getIdUser());

            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean updateDecision(int status, String content, int idClaim) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "Update tblDecision set content=?, _status=? where idClaim=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, content);
            st.setInt(2, status);
            st.setInt(3, idClaim);
            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateClaim(int status, int idClaim) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "Update tblClaim set  _status=? where idClaim=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, idClaim);
            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean updateFileofClaim(String file, int idClaim) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "Update tblClaim set  evidence=? where idClaim=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, file);
            st.setInt(2, idClaim);
            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean updateSttCM(int id, int stt) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "Update tblClaimManage set _status=? where idCM=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, stt);
            st.setInt(2, id);
            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public List<Account> getListEccoor(int idFaculty) {
        List<Account> list = new ArrayList<>();
        String sql = "select * from tblUser where idFaculty = ? and lever = 4 ";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idFaculty);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setIdUser(rs.getString(1));
                acc.setFullName(rs.getString(3));
                acc.setDob(rs.getString(4));
                acc.setEmail(rs.getString(5));
                acc.setPhoneNumber(rs.getString(6));
                acc.setIdAcademy(rs.getInt(7));
                acc.setIdFaculty(rs.getInt(8));
                acc.setLever(rs.getInt(9));
                list.add(acc);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Account> getAllAccount(String idAdminUser) {
        List<Account> list = new ArrayList<>();
        String sql = "select  distinct  u.idUser, u.fullName, u.email, f.name, u.lever\n"
                + " from tblUser u \n"
                + " left join tblFaculty f on u.idFaculty = f.id\n"
                + "where u.idUser not like ?";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idAdminUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setIdUser(rs.getString(1));
                acc.setFullName(rs.getString(2));
                acc.setEmail(rs.getString(3));
                acc.setFacultyName(rs.getString(4));
                acc.setLever(rs.getInt(5));
                list.add(acc);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Account getAccountInfor(String idUser) {
        Account acc = null;
        String sql = "select * from tblUser where idUser like ? ";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setIdUser(rs.getString(1));
                acc.setPassWord(rs.getString(2));
                acc.setFullName(rs.getString(3));
                acc.setDob(rs.getString(4));
                acc.setEmail(rs.getString(5));
                acc.setPhoneNumber(rs.getString(6));
                acc.setIdAcademy(rs.getInt(7));
                acc.setIdFaculty(rs.getInt(8));
                acc.setLever(rs.getInt(9));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    public boolean updateAccountWithAllInfor(Account acc) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "Update tblUser set  _passWord = ?, fullName = ?, dob = ?, email = ?, phoneNumber = ? where idUser = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, acc.getPassWord());
            st.setString(2, acc.getFullName());
            st.setString(3, acc.getDob());
            st.setString(4, acc.getEmail());
            st.setString(5, acc.getPhoneNumber());
            st.setString(6, acc.getIdUser());
            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean updateAccountMissingPwd(Account acc) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "Update tblUser set fullName = ?, dob = ?, email = ?, phoneNumber = ? where idUser = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, acc.getFullName());
            st.setString(2, acc.getDob());
            st.setString(3, acc.getEmail());
            st.setString(4, acc.getPhoneNumber());
            st.setString(5, acc.getIdUser());
            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean addAccount(Account account) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "INSERT INTO tblUser ( idUser, _passWord,fullName, dob, email, phoneNumber, idAcademy,idFaculty,lever) VALUES"
                    + "(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, account.getIdUser());
            st.setString(2, account.getPassWord());
            st.setString(3, account.getFullName());
            st.setString(4, account.getDob());
            st.setString(5, account.getEmail());
            st.setString(6, account.getPhoneNumber());
            st.setInt(7, account.getIdAcademy());
            st.setInt(8, account.getIdFaculty());
            st.setInt(9, account.getLever());

            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public boolean addItemAssessment(int idAssessment) {
        boolean rs = false;
        try {
            connectdatabase();
            String sql = "insert into tblADetail(idAssesment,idItem) value (?,'1'),(?,'2'),(?,'3')";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idAssessment);
            st.setInt(2, idAssessment);
            st.setInt(3, idAssessment);

            rs  = st.executeUpdate() > 0;
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public List<Academy> getAllAcademy() {
        List<Academy> list = new ArrayList<>();
        String sql = "select * from tblAcademyYear";
        try {
            connectdatabase();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Academy academy = new Academy();
                academy.setId(rs.getInt(1));
                academy.setName(rs.getString(2));
                list.add(academy);
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
