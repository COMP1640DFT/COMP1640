/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Claim;
import entity.Decision;
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
import model.Mail;

/**
 *
 * @author user
 */
public class CondinatiorController extends HttpServlet {

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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action.equals("viewClaimDetail")) {
            String idclaim = request.getParameter("idclaim");
            String idUser = request.getParameter("idUser");
            Account account = connectDB.getAccoutnByid(idUser);
            Claim claim = connectDB.getClaimOfStudentInAFacultyByIdClaim(account.getIdMajor(), Integer.parseInt(idclaim));
            Decision decision = connectDB.getDecisionOfAClaim(Integer.parseInt(idclaim));
            if (decision != null) {

                session.setAttribute("textbtn", "Update");
            } else {
                session.setAttribute("textbtn", "Send");
            }
            session.setAttribute("decision", decision);
            session.setAttribute("claimD", claim);
            session.setAttribute("acc", account);

            response.sendRedirect("../coordinator/detailclaim.jsp");
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
                        sendMessage(response, "Add claim complete successfull!", "../student/createclaim.jsp");
                        response.sendRedirect("../coordinator/detailclaim.jsp");
                    } else {

                    }
                } else {
                    sendMessage(response, "Please enter content before add comment!", "../coordinator/detailclaim.jsp");
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
                        Account stdAcc = connectDB.getAccoutnByid(claim.getIdUser());
                        String mailtext = "Hello " + stdAcc.getFullName() + "(" + stdAcc.getIdUser() + ")"
                                + "\n The decision of your claim  (" + claim.getTitle() + ") update finished.";
                        mail(stdAcc.getEmail(), mailtext);
                        response.sendRedirect("../coordinator/detailclaim.jsp");
                    } else {

                    }
                } else {
                    sendMessage(response, "Please enter content before update!", "../coordinator/detailclaim.jsp");
                }

            }

        }
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
