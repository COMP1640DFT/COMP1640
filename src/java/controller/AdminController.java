/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Assessment;
import entity.AsssessmentDetail;
import entity.Claim;
import entity.ItemSelected;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ConnectDB;
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
        response.sendRedirect("../admin/index.jsp");
    }

    private void openShedule(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        List<ItemSelected> list = connectDB.getAllAssessmentDetail();
        AsssessmentDetail at = new AsssessmentDetail();
        at.setList(list);
        session.setAttribute("beanASD", at);
        response.sendRedirect("../admin/schedule.jsp");
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

         if(connectDB.insertClaimM(c)){
            response.sendRedirect("AdminController?action=adminViewAll");
         }   else {
            String mes = "Create new failed!";
            sendMessage(response, mes, "AdminController?action=adminViewAll");
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