/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Academy;
import entity.Account;
import entity.Assessment;
import entity.AsssessmentDetail;
import entity.Claim;
import entity.Faculty;
import entity.ItemSelected;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ConnectDB;
import model.Encode;
import org.jboss.weld.context.http.Http;

/**
 *
 * @author DaoMinhThien
 */
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ConnectDB connectDB = new ConnectDB();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getLever() == 2) {
            action(request, response, session);
        } else {
            response.sendRedirect("logout.jsp");
        }
    }

    private void action(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("adminViewAll")) {
            viewAllClaim(request, response, session);
        }
        if (action.equals("openShedule")) {
            openShedule(request, response, session);
        }
        if (action.equals("addnewCM")) {
            addNewCM(request, response, session);
        }
        if (action.equals("viewAllUser")) {
            viewAllUser(request, response, session);
        }
        if (action.equals("viewUserDetail")) {
            viewUserDetail(request, response, session);
        }
        if (action.equals("updateUser")) {
            updateUser(request, response, session);
        }
        if (action.equals("updateSttCM")) {
            updateSttClaim(request, response, session);
        }
        if (action.equals("openCreateUser")) {
            openCreateUser(request, response, session);
        }
        if (action.equals("createUser")) {
            createAccount(request, response, session);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void viewAllClaim(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<Claim> list = connectDB.getAllClaimManage();
        Claim c = new Claim();
        c.setListClaim(list);
        session.setAttribute("beanAdminCM", c);
        response.sendRedirect("indexA.jsp");
    }

    private void openShedule(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<ItemSelected> list = connectDB.getAllAssessmentDetail();
        AsssessmentDetail at = new AsssessmentDetail();
        at.setList(list);
        session.setAttribute("beanASD", at);
        response.sendRedirect("schedule.jsp");
    }

    private void addNewCM(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("iditem"));
        String title = request.getParameter("title");
        String senddate = request.getParameter("from");
        String enddate = request.getParameter("to");
        int st = 0;
        Claim c = new Claim();
        c.setTitle(title);
        c.setSendDate(senddate);
        c.setEndDate(enddate);
        c.setIdItemAssessment(id);
        c.setStatus(st);

        if (connectDB.insertClaimM(c)) {
            response.sendRedirect("AdminController?action=adminViewAll");
        } else {
            String mes = "Create new failed!";
            sendMessage(response, mes, "AdminController?action=adminViewAll");
        }
    }

    private void viewAllUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        Account acc = (Account) session.getAttribute("account");
        Account aL = new Account();
        aL.setListAccount(connectDB.getAllAccount(acc.getIdUser()));
        session.setAttribute("idUser", acc.getIdUser());
        session.setAttribute("fullName", acc.getFullName());
        session.setAttribute("beanAllUser", aL);
        response.sendRedirect("account-view-all.jsp");
    }

    private void viewUserDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        Account accDetail = connectDB.getAccountInfor(id);
        session.setAttribute("beanUserDetail", accDetail);
        response.sendRedirect("account-edit.jsp");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String idUser = request.getParameter("idUser");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("birthdate");
        Account acc = new Account();
        acc.setIdUser(idUser);
        acc.setPassWord(Encode.encryptPass(password));
        acc.setFullName(name);
        acc.setEmail(email);
        acc.setPhoneNumber(phone);
        acc.setDob(dob);
        if (connectDB.updateAccount(acc)) {
            response.sendRedirect("AdminController?action=viewAllUser");
        }
    }

    private void updateSttClaim(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        int stt = Integer.parseInt(request.getParameter("stt"));
        int idCM = Integer.parseInt(request.getParameter("idCM"));
        if (connectDB.updateSttCM(idCM, stt)) {
            response.sendRedirect("AdminController?action=adminViewAll");
        }
    }

    private void openCreateUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
//        Account account = new Account();
//        session.setAttribute("account", account);
        List<Academy> listAcademy = connectDB.getAllAcademy();
        List<Faculty> listFaculty = connectDB.getListMajor();
        session.setAttribute("listAcademy", listAcademy);
        session.setAttribute("listFaculty", listFaculty);
        response.sendRedirect("account-create.jsp");
    }

    public void createAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        String userid = request.getParameter("id");
        String password = request.getParameter("password");
        String fullname = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String dob =request.getParameter("birthdate");
        String role = request.getParameter("role");
        String faculty = request.getParameter("faculty");
        String academy = request.getParameter("academy");
        if (!userid.equals("") && !password.equals("") && !email.equals("")) {
            Account account = connectDB.getAccountInfor(userid);
            if (account == null) {
              
                Account acc= new Account(userid, password, fullname, dob, email, phone, Integer.parseInt(academy), Integer.parseInt(faculty), Integer.parseInt(role));
                if(connectDB.addAccount(acc)){
                    response.sendRedirect("account-create.jsp");
                }
                else{
                     String mes="Add failed!";
                        sendMessage(response,mes,"account-create.jsp");
                }
            } else {
                String mes = "This Account is exist, Please try again!";
                sendMessage(response, mes, "account-create.jsp");
            }
        } else {
            String mes = "Please fill fields as Account ID, Password and Email";
            sendMessage(response, mes, "account-create.jsp");
        }

    }

    private void sendMessage(HttpServletResponse response, String sms, String path) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + sms + "');");
        out.println("location='" + path + "';");
        out.println("</script>");
    }

}
