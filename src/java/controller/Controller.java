/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Claim;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Connect_DB;

/**
 *
 * @author minamaurer
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action.equals("checklogin")) {
            Account acc = checkLogin(request, response);
            session.setAttribute("session_Account", acc);

        }
        if (action.equals("viewC")) {

            List<Claim> lClaim = Connect_DB.getAllClaim();
            session.setAttribute("listClaim", lClaim);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("process.jsp");
            requestDispatcher.forward(request, response);
        }
        if(action.equals("viewDetail")){
            String id=request.getParameter("id");
            Claim claim=Connect_DB.getClaimByIdClaim(Integer.parseInt(id));
          
            session.setAttribute("ClaimDetail", claim);
            response.sendRedirect("sample/detail-process.jsp");
        }

    }

    private Account checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idUser = request.getParameter("username");
        String pass = request.getParameter("password");
        Account acc = Connect_DB.checkLogin(idUser, pass);
        if (acc != null) {
            switch (acc.getLever()) {
                case 1:
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("process.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                case 2:
                    sendMessage(response, acc.getFullName(), "login.jsp");
                    break;
                case 3:
                    sendMessage(response, acc.getFullName(), "login.jsp");
                    break;
                case 4:
                    sendMessage(response, acc.getFullName(), "login.jsp");
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
