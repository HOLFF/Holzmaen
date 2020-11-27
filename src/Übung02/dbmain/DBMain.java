package Übung02.dbmain;

import Übung02.model.DBManager;

import java.sql.Connection;
import java.sql.SQLException;

public class DBMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBManager db = DBManager.getInstance();
        Connection con = null;
        try {
            con = db.getConnection();
            System.out.println("Connection ok and running");

        } finally {
            if (con != null) db.releaseConnection(con);
        }
        System.out.println("Close ok");
    }
}