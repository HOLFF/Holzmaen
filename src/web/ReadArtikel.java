package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Übung02.model.*;

import com.google.gson.Gson;

/**
 * Servlet implementation class ReadArtikel
 */
@WebServlet("/ReadArtikel")
public class ReadArtikel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadArtikel() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("themenId");
        int themengebietid = Integer.parseInt(strId);
        DBManager m = DBManager.getInstance();
        Connection con = null;
        ArrayList<Artikel> liste  = new ArrayList<>();
        try {
            con = m.getConnection();
            liste = m.leseArtikelVonThemenebiet(con, themengebietid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null)
                try {
                    m.releaseConnection(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        Gson gson = new Gson();
        String jsonResult = gson.toJson(liste);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.append(jsonResult);
        out.flush();
    }


}