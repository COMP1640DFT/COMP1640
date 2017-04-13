/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Claim;
import entity.Faculty;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ConnectDB;
import model.Encode;

/**
 *
 * @author DaoMinhThien
 */
public class LoginController extends HttpServlet {

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
        if (action.equals("checklogin")) {
            Account acc = checkLogin(request, response, session);
        }
        if(action.equals("logout")){
            session.removeAttribute("account");
            response.sendRedirect("login.jsp");
        }
    }
    
    private Account checkLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        String idUser = request.getParameter("username");
        String pass = request.getParameter("password");
        
        Account acc = connectDB.checkLogin(idUser, Encode.encryptPass(pass));
        Claim c = new Claim();
        if (!acc.getIdUser().equals("")) {
            session.setAttribute("account", acc);
            switch (acc.getLever()) {
                //student
                case 1:
                    response.sendRedirect("StudentsController?action=viewAllCM");
                    break;
                //admin
                case 2:
                    response.sendRedirect("AdminController?action=adminViewAll");
                    break;
                //manager
                case 3:
                    response.sendRedirect("Controller?action=viewC");
                    break;
                //condinator
                case 4:
                    response.sendRedirect("CoordinatorController?action=viewAllClaim");
                    break;
            }
        } else {
            String mes = "UserName or PassWord is wrong!";
            sendMessage(response, mes, "login.jsp");
        }
        return acc;
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
