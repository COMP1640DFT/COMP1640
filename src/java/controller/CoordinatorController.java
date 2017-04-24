/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Claim;
import entity.Decision;
import entity.Faculty;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ConnectDB;
import model.Encode;
import model.Mail;

/**
 *
 * @author user
 */
public class CoordinatorController extends HttpServlet {

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
        if (account != null && account.getLever() == 4) {
            action(request, response, session);
        } else {
            response.sendRedirect("logout.jsp");
        }

    }

    private void action(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action.equals("viewClaimDetail")) {
            String idclaim = request.getParameter("idclaim");
            String idUser = request.getParameter("idUser");
            Account account = connectDB.getAccoutnByid(idUser);
            Claim claim = connectDB.getClaimOfStudentInAFacultyByIdClaim(account.getIdFaculty(), Integer.parseInt(idclaim));
            Decision decision = connectDB.getDecisionOfAClaim(Integer.parseInt(idclaim));
            if (decision != null) {

                session.setAttribute("textbtn", "Update");
            } else {
                session.setAttribute("textbtn", "Send");
            }
            session.setAttribute("decision", decision);
            session.setAttribute("claimD", claim);
            session.setAttribute("acc", account);

            response.sendRedirect("detailclaimC.jsp");
        }
        if (action.equals("viewAllClaim")) {
            viewAllClaim(request, response, session);
        }
        if (action.equals("changePass")) {
            changePass(request, response, session);
        }
        if (action.equals("viewAllClaimFilterByStatus")) {
            viewAllClaimFilterByStatus(request, response, session);
        }
        if (action.equals("AddMessage")) {
            String btntext = session.getAttribute("textbtn").toString();
            if (btntext.equals("Send")) {
                String message = request.getParameter("message");
                String selectedStatus = request.getParameter("selectStatus");

                Account acc = (Account) session.getAttribute("acc");
                Claim claim = (Claim) session.getAttribute("claimD");

                if (!message.equals("")) {
                    String date_send = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
                    Decision decision = new Decision(claim.getIdClaim(), message, date_send, Integer.parseInt(selectedStatus), acc.getIdUser());
                    decision.setFullNameEC(acc.getFullName());
                    if (connectDB.createDecision(decision)) {
                        session.setAttribute("decision", decision);
                        connectDB.updateClaim(1, claim.getIdClaim());

                        String mailtext = "Hello " + acc.getFullName() + "(" + acc.getIdUser() + ")"
                                + "\n The decision of your claim (" + claim.getTitle() + ") finished.";
                        Account stdAcc = connectDB.getAccoutnByid(claim.getIdUser());
                        mail(stdAcc.getEmail(), mailtext);
//                        sendMessage(response, "Add claim complete successfull!", "../student/createclaim.jsp");
                        response.sendRedirect("detailclaimC.jsp");
                    } else {

                    }
                } else {
                    sendMessage(response, "Please enter content before add comment!", "detailclaimC.jsp");
                }
            }
            if (btntext.equals("Update")) {

                String message = request.getParameter("message");
                String selectedStatus = request.getParameter("selectStatus");

                Claim claim = (Claim) session.getAttribute("claimD");
                Account acc = (Account) session.getAttribute("acc");
                if (!message.equals("")) {

                    if (connectDB.updateDecision(Integer.parseInt(selectedStatus), message, claim.getIdClaim())) {
                        String date_send = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
                        Decision decision = new Decision(claim.getIdClaim(), message, date_send, Integer.parseInt(selectedStatus), acc.getIdUser());
                        decision.setFullNameEC(acc.getFullName());
                        session.setAttribute("decision", decision);
                        connectDB.updateClaim(Integer.parseInt(selectedStatus), claim.getIdClaim());
                        Account stdAcc = connectDB.getAccoutnByid(claim.getIdUser());
                        String mailtext = "Hello " + stdAcc.getFullName() + "(" + stdAcc.getIdUser() + ")"
                                + "\n The decision of your claim  (" + claim.getTitle() + ") update finished.";
                        mail(stdAcc.getEmail(), mailtext);
                        System.out.println("123");
//                        response.sendRedirect("../coordinator/detailclaim.jsp");
                        response.sendRedirect("CoordinatorController?action=viewAllClaim");
                    } else {
                        System.out.println("fialse");
                    }
                } else {
                    sendMessage(response, "Please enter content before update!", "detailclaimC.jsp");
                }

            }

        }
    }

    private void viewAllClaim(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        Claim c = new Claim();
        Account acc = (Account) session.getAttribute("account");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(year);
        c.setListClaim(connectDB.getAllClaimOfStudentInAFaculty(acc.getIdFaculty(), year));
        Faculty m = connectDB.getMajor(acc.getIdFaculty());
        session.setAttribute("idUser", acc.getIdUser());
        session.setAttribute("fullName", acc.getFullName());
        session.setAttribute("beanClaimInFaculty", c);
        session.setAttribute("majorName", m);
        response.sendRedirect("allclaimC.jsp");
    }

    private void changePass(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        String oldPass = request.getParameter("oldpass");
        String idUser = request.getParameter("idUser");
        String newpass = request.getParameter("password");
        Account acc = connectDB.checkLogin(idUser, Encode.encryptPass(oldPass));
        if (acc != null) {
            if (connectDB.changePassword(idUser, Encode.encryptPass(newpass))) {
                String message = "Chang password successful! Please login again with new password!";
                sendMessage(response, message, "logout.jsp");
            } else {
                String message = "Chang password failed! Please try again!";
                sendMessage(response, message, "eccoorChangePwd.jsp");
            }
        } else {
            String message = "Old password is incorrect!";
            sendMessage(response, message, "eccoorChangePwd.jsp");
        }
    }

    private void viewAllClaimFilterByStatus(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        Claim c = new Claim();
        int idStt = Integer.parseInt(request.getParameter("status"));

        Account acc = (Account) session.getAttribute("account");
        c.setListClaim(connectDB.getAllClaimOfStudentInAFacultyFilterByStatus(acc.getIdFaculty(), idStt));
        Faculty m = connectDB.getMajor(acc.getIdFaculty());
        session.setAttribute("idUser", acc.getIdUser());
        session.setAttribute("fullName", acc.getFullName());
        session.setAttribute("beanClaimInFaculty", c);
        session.setAttribute("majorName", m);
        response.sendRedirect("allclaimC.jsp");
    }

    private void mail(String email, String mailtext) {
        Mail mail = new Mail();
        try {
            mail.sendMail(email, mailtext);
        } catch (MessagingException ex) {
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
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

}
