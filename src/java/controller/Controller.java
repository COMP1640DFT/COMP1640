/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Claim;
import entity.ItemSelected;
import entity.Major;
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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action.equals("checklogin")) {
            Account acc = checkLogin(request, response, session);
            session.setAttribute("session_Account", acc);
        }
        if (action.equals("viewstatistic")) {
            viewStatistic(request, response);
        }
        if (action.equals("viewstatisticwithfilter")) {
            viewStatisticWithFilter(request, response);
        }
        if (action.equals("viewC")) {

            List<Claim> lClaim = connectDB.getAllClaim();
            session.setAttribute("listClaim", lClaim);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("process.jsp");
            requestDispatcher.forward(request, response);
        }
        if (action.equals("viewDetail")) {
            String id = request.getParameter("id");
            Claim claim = connectDB.getClaimByIdClaim(Integer.parseInt(id));

            session.setAttribute("ClaimDetail", claim);
            response.sendRedirect("sample/detail-process.jsp");
        }

        if (action.equals("viewStatisticChart")) {
            viewStaticsChart(request, response);
        }

    }

    private void viewStatistic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String idmajor = request.getParameter("idmajor");
        String major = "";
        HttpSession session = request.getSession();
        session.setAttribute("beanStatistic", null);
        if (idmajor == null) {
            idmajor = "1";
        }
        List<Major> listM = connectDB.getListMajor();
        List<ItemSelected> listMajor = new ArrayList<>();
        for (Major listM1 : listM) {
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
        claim.setListClaimWithoutEvidence(connectDB.getStudentUpClaimWithOutEvidence());
        claim.setListClaimUnresolved(connectDB.getAllClaimUnresolvedAfterTwoWeek());
        claim.setListSelectedMajor(listMajor);
        session.setAttribute("beanClaim", claim);
        response.sendRedirect("statistics.jsp");
    }

    private void viewStatisticWithFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String idmajor = request.getParameter("idmajor");
        String major = "";
        int idM = Integer.parseInt(idmajor);
        HttpSession session = request.getSession();
        session.setAttribute("beanClaim", null);
        if (idmajor == null) {
            idmajor = "1";
        }
        List<Major> listM = connectDB.getListMajor();
        List<ItemSelected> listMajor = new ArrayList<>();
        for (Major listM1 : listM) {
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
        claim.setListClaimWithoutEvidence(connectDB.getStudentUpClaimWithOutEvidenceInMajor(idM));
        claim.setListClaimUnresolved(connectDB.getAllClaimUnresolvedAfterTwoWeekInMajor(idM));
        claim.setListSelectedMajor(listMajor);
        session.setAttribute("beanClaim", claim);
        response.sendRedirect("statistics.jsp");
    }

    private Account checkLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        String idUser = request.getParameter("username");
        String pass = request.getParameter("password");
        Account acc = connectDB.checkLogin(idUser, pass);
        Claim c = new Claim();
        c.setListClaimUnresolved(connectDB.getAllClaimOfStudent(idUser));
        if (acc != null) {
            switch (acc.getLever()) {
                //student
                case 1:
                    session.setAttribute("idUser", acc.getIdUser());
                    session.setAttribute("fullName", acc.getFullName());
                    session.setAttribute("beanAllClaim", c);
                    response.sendRedirect("student/index.jsp");
                    break;
                //admin
                case 2:
                    sendMessage(response, acc.getFullName(), "login.jsp");
                    break;
                //manager
                case 3:
                    sendMessage(response, acc.getFullName(), "login.jsp");
                    break;
                //condinator
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

    private void viewStaticsChart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Major> listM = connectDB.getListMajor();
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
        for (Major listM1 : listM) {
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

        List<Major> dataCount = connectDB.getCountClaimByMajor(year);
        for (Major dataCount1 : dataCount) {
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
                        for (int j = 0; j< listNameUser.size();j++) {
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

        session.setAttribute("beanStatistic", s);
        response.sendRedirect("statisticsChart.jsp");
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
