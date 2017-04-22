/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Claim;
import entity.Decision;
import entity.ItemSelected;
import entity.Faculty;
import entity.Statistic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ConnectDB;
import model.Encode;

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
    ConnectDB connectDB = new ConnectDB();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null && account.getLever() == 3) {
            action(request, response, session);
        } else {
            response.sendRedirect("logout.jsp");
        }
    }

    private void action(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action.equals("viewstatistic")) {
            viewStatistic(request, response);
            viewStaticsChart(request, response);
        }
        if (action.equals("viewstatisticwithfilter")) {
            viewStatisticWithFilter(request, response);
        }
        if (action.equals("changePass")) {
            changePass(request, response, session);
        }
        if (action.equals("viewC")) {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            List<Claim> lClaim = connectDB.getAllClaim(year);
            session.setAttribute("listClaim", lClaim);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("processECM.jsp");
            requestDispatcher.forward(request, response);
        }
        if (action.equals("viewDetail")) {
            String id = request.getParameter("id");
            Claim claim = connectDB.getClaimByIdClaim(Integer.parseInt(id));
            Decision d = connectDB.getDecissionById(Integer.parseInt(id));
            session.setAttribute("ClaimDetail", claim);
            session.setAttribute("DecisionDetail", d);
            response.sendRedirect("sample/detail-process.jsp");
        }

        if (action.equals("viewStatisticChart")) {
            viewStaticsChart(request, response);
        }
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
                sendMessage(response, message, "managerChangePwd.jsp");
            }
        } else {
            String message = "Old password is incorrect!";
            sendMessage(response, message, "managerChangePwd.jsp");
        }
    }

    private void viewStatistic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String y = request.getParameter("year");
        int year = 0;
        String idmajor = request.getParameter("idmajor");
        String major = "";
        HttpSession session = request.getSession();
        session.setAttribute("beanStatistic", null);
        
        if(y == null){
            year = Calendar.getInstance().get(Calendar.YEAR);
        }else{
            year = Integer.parseInt(y);
        }
        List<ItemSelected> listYear = new ArrayList<>();
        for (int i = 2010; i < 2030; i++) {
            ItemSelected item = new ItemSelected();
            item.setData(i + "");
            item.setValue(i + "");
            item.setSelected(false);
            if (year == i) {
                item.setSelected(true);
            }
            listYear.add(item);
        }
        
        if (idmajor == null) {
            idmajor = "1";
        }
        List<Faculty> listM = connectDB.getListMajor();
        List<ItemSelected> listMajor = new ArrayList<>();
        for (Faculty listM1 : listM) {
            ItemSelected item = new ItemSelected();
            item.setData(listM1.getName());
            item.setValue(listM1.getId() + "");
            item.setSelected(false);
            if (idmajor.equals(listM1.getId() + "")) {
                item.setSelected(true);
                major = listM1.getName();
            }
            listMajor.add(item);
        }
        Claim claim = new Claim();
        claim.setListClaimWithoutEvidence(connectDB.getStudentUpClaimWithOutEvidence(year));
        claim.setListClaimUnresolved(connectDB.getAllClaimUnresolvedAfterTwoWeek(year));
        claim.setListSelectedMajor(listMajor);
        claim.setListSelectedYear(listYear);
        session.setAttribute("beanClaim", claim);
        response.sendRedirect("statisticECM.jsp");
    }

    private void viewStatisticWithFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String y = request.getParameter("year");
        String idmajor = request.getParameter("idmajor");
        int idM = Integer.parseInt(idmajor);
        int year = 0;
        HttpSession session = request.getSession();
        session.setAttribute("beanClaim", null);
        if (idmajor == null) {
            idmajor = "1";
        }
        if(y == null){
            year = Calendar.getInstance().get(Calendar.YEAR);
        }else{
            year = Integer.parseInt(y);
        }
        List<ItemSelected> listYear = new ArrayList<>();
        for (int i = 2010; i < 2030; i++) {
            ItemSelected item = new ItemSelected();
            item.setData(i + "");
            item.setValue(i + "");
            item.setSelected(false);
            if (year == i) {
                item.setSelected(true);
            }
            listYear.add(item);
        }
        List<Faculty> listM = connectDB.getListMajor();
        List<ItemSelected> listMajor = new ArrayList<>();
        for (Faculty listM1 : listM) {
            ItemSelected item = new ItemSelected();
            item.setData(listM1.getName());
            item.setValue(listM1.getId() + "");
            item.setSelected(false);
            if (idmajor.equals(listM1.getId() + "")) {
                item.setSelected(true);
            }
            listMajor.add(item);
        }
        Claim claim = new Claim();
        claim.setListClaimWithoutEvidence(connectDB.getStudentUpClaimWithOutEvidenceInMajor(idM,year));
        claim.setListClaimUnresolved(connectDB.getAllClaimUnresolvedAfterTwoWeekInMajor(idM,year));
        claim.setListSelectedMajor(listMajor);
        claim.setListSelectedYear(listYear);
        session.setAttribute("beanClaim", claim);
        response.sendRedirect("statisticECM.jsp");
    }

    private void sendMessage(HttpServletResponse response, String sms, String path) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + sms + "');");
        out.println("location='" + path + "';");
        out.println("</script>");
    }

    private void viewStaticsChart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Faculty> listM = connectDB.getListMajor();
        String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String year = request.getParameter("year");
        String idmajor = request.getParameter("idmajor");
        String major = "";
        Calendar cal = Calendar.getInstance();
        HttpSession session = request.getSession();
        session.setAttribute("beanStatistic", null);
        if (idmajor == null) {
            idmajor = "1";
        }

        List<ItemSelected> listMajor = new ArrayList<>();

        if (year == null) {
            year = cal.get(Calendar.YEAR) + "";
        }

        List<ItemSelected> listYear = new ArrayList<>();

        for (int i = 2010; i < 2030; i++) {
            ItemSelected item = new ItemSelected();
            item.setData(i + "");
            item.setValue(i + "");
            item.setSelected(false);
            if (year.equals(i + "")) {
                item.setSelected(true);
            }
            listYear.add(item);
        }

        //chart2
        List<Statistic> listS = new ArrayList<>();
        for (Faculty listM1 : listM) {
            ItemSelected item = new ItemSelected();
            item.setData(listM1.getName());
            item.setValue(listM1.getId() + "");
            item.setSelected(false);
            if (idmajor.equals(listM1.getId() + "")) {
                item.setSelected(true);
                major = listM1.getName();
            }
            listMajor.add(item);

            Statistic s = new Statistic();
            s.setTitle(listM1.getName());
            s.setData(0);
            listS.add(s);
        }

        List<Faculty> dataCount = connectDB.getCountClaimByMajor(year);
        for (Faculty dataCount1 : dataCount) {
            for (Statistic s : listS) {
                if (s.getTitle().equals(dataCount1.getName())) {
                    s.setData(s.getData() + 1);
                }
            }
        }

        int idM = Integer.parseInt(idmajor);
        //chart1
        List<Statistic> listSOfClaims = new ArrayList<>();
        List<Statistic> listTotal = connectDB.getAllClaim(year, idM);
        for (int i = 0; i < 12; i++) {
            Statistic s = new Statistic();
            s.setData(0);
            for (Statistic st : listTotal) {
                if (st.getYear().substring(5, 7).equals(month[i])) {
                    s.setData(s.getData() + 1);
                }
            }
            listSOfClaims.add(s);
        }

        //chart3
        List<Statistic> listSOfStd = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            List<String> listNameUser = new ArrayList<>();
            Statistic s = new Statistic();
            for (Statistic st : listTotal) {
                if (st.getYear().substring(5, 7).equals(month[i])) {
                    if (listNameUser.size() > 0) {
                        for (int j = 0; j < listNameUser.size(); j++) {
                            if (!listNameUser.get(j).equals(st.getUser())) {
                                listNameUser.add(st.getUser());

                            }
                        }
                    } else {
                        listNameUser.add(st.getUser());
                    }
                    s.setData(listNameUser.size());
                }
            }
            listSOfStd.add(s);
        }

        Statistic s = new Statistic();
        s.setListStatisticAllMajor(listS);
        s.setListNumOfClaim(listSOfClaims);
        s.setListNumOfStudent(listSOfStd);
        s.setListItemYear(listYear);
        s.setListItemMajor(listMajor);
        s.setMajor(major);
        s.setYear(year);
        s.setListItemTableClaim(listTotal);

        session.setAttribute("beanStatistic", s);
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
