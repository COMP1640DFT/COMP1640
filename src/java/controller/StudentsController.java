/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Claim;
import entity.ClaimManage;
import entity.Assessment;
import entity.Decision;
import entity.Faculty;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class StudentsController extends HttpServlet {

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
    public String file_name = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getLever()==1) {
            action(request, response, session);
        }else{
            response.sendRedirect("logout.jsp");
        }
    }
    
    private void action(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action.equals("AddClaimPage")) {
           // String idSubject = request.getParameter("idC");
            String idCM = request.getParameter("idCM");
         
        //    String idMajor = request.getParameter("idM");
            Assessment subject = new Assessment();
            ClaimManage cm = new ClaimManage();
            cm.setId(Integer.parseInt(idCM));
         //   subject.setId(Integer.parseInt(idSubject));
         //   session.setAttribute("beanSubject", subject);
            session.setAttribute("beanCM", cm);
          //  session.setAttribute("idMajor", idMajor);
            response.sendRedirect("createclaimST.jsp");

        }
        if (action.equals("viewAllCM")){
            viewAllClaimManage(request, response, session);
        }
        if (action.equals("viewAllClaimManageFilterByStatus")) {
            viewAllClaimManageFilterByStatus(request, response, session);
        }
        if (action.equals("createClaim")) {
            final String idU = request.getParameter("Uid");
            String title = request.getParameter("subject");
            String description = request.getParameter("description");
            int idMajor = Integer.parseInt(request.getParameter("idM"));
            file_name = request.getParameter("linkfile");

            if (!title.equals("") && !description.equals("")) {
                String date_send = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
                int status = 0;
                int idCM = Integer.parseInt(request.getParameter("idCM"));
               
                Claim claim = new Claim(title, description, date_send, file_name, idU, idCM, status);
             
                if (connectDB.createClaim(claim)) {
                    List<Account> accountecco = connectDB.getListEccoor(idMajor);
                    Mail mail = new Mail();
                    for (Account ec : accountecco) {
                        String mailtext = "Hello " + ec.getFullName() + "(" + ec.getIdUser() + ")"
                                + "\n You have new claim of the student.";
                        try {
                            mail.sendMail(ec.getEmail(), mailtext);
                        } catch (MessagingException ex) {
                            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    response.sendRedirect("StudentsController?idCM="+idCM+"&idUser="+idU+"&action=viewAllClaim");
                } else {
                    sendMessage(response, "Add claim is failed!", "createclaimST.jsp");
                }
            }
        }
        if (action.equals("viewDecision")) {
            viewDecision(request, response);
        }
        if (action.equals("viewAllClaim")) {
            viewAllClaim(request, response);
        }
        if (action.equals("updateFile")) {
            final String idU = (String) session.getAttribute("idUser");
            Claim claim = (Claim) session.getAttribute("beanClaim");
            String file_name = request.getParameter("linkfile");
            System.out.println("file: "+ file_name);
            if (!file_name.equals("")) {
                if (connectDB.updateFileofClaim(file_name, claim.getIdClaim())) {
                    claim.setFiledata(file_name);
                    session.setAttribute("beanClaim", claim);
                    sendMessage(response, "Update file complete successfull!", "detailclaimST.jsp");
                } else {
                    sendMessage(response, "Update file failed!", "detailclaimST.jsp");
                }
            } else {
                sendMessage(response, "If you want update file, you must select file want update!", "detailclaimST.jsp");
            }

        }
    }

    private void viewDecision(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        int idC = Integer.parseInt(id);
        Decision d = connectDB.getDecisionOfAClaim(idC);
        Claim c = connectDB.getClaimById(idC);
        HttpSession session = request.getSession();
        session.setAttribute("beanDecision", d);
        session.setAttribute("beanClaim", c);
        response.sendRedirect("detailclaimST.jsp");
    }

    private void viewAllClaim(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("idCM");
        String idUser = request.getParameter("idUser");
        int idC = Integer.parseInt(id);
        Claim c = new Claim();
        c.setListClaim(connectDB.getAllClaimOfStudent(idUser, idC));
        HttpSession session = request.getSession();
        session.setAttribute("beanAllStudentClaim", c);
        response.sendRedirect("allClaimsST.jsp");
    }

    private void viewAllClaimManage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        Account acc = (Account) session.getAttribute("account");
        Claim c = new Claim();
        c.setListClaim(connectDB.getAllClaimManage(acc.getIdFaculty()));
        session.setAttribute("idUser", acc.getIdUser());
        session.setAttribute("idMajor", acc.getIdFaculty());
        session.setAttribute("fullName", acc.getFullName());
        session.setAttribute("beanAllClaim", c);
        response.sendRedirect("indexST.jsp");
    }
    private void viewAllClaimManageFilterByStatus(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        Account acc = (Account) session.getAttribute("account");
        Claim c = new Claim();
        int idStt = 0;
        String status = request.getParameter("status");
        if(status.equals("Open")) {
            idStt = 1;
        }
        c.setListClaim(connectDB.getAllClaimManageFilterByStatus(acc.getIdFaculty(),idStt));
        session.setAttribute("idUser", acc.getIdUser());
        session.setAttribute("idMajor", acc.getIdFaculty());
        session.setAttribute("fullName", acc.getFullName());
        session.setAttribute("beanAllClaim", c);
        response.sendRedirect("indexST.jsp");
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
