package Übung01.dbmodel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public Connection getConnection()
            throws ClassNotFoundException, SQLException {

        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schule1",  // DB
                "root",                                 // User
                ""                                      // Passwort
        );
        return con;
    }

    public void releaseConnection (Connection con)
            throws SQLException {

        if (con != null)
            con.close();
    }

    public Abteilung getAbteilungByAid(int aid,Connection con)
            throws SQLException {
        Abteilung result = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select aid, name from abteilung where aid = ?";
            stm= con.prepareStatement(sql);
            stm.setInt(1, aid);
            rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                result = new Abteilung(id, name);
            }
        }
        finally
        {
            if (stm != null)
                stm.close();
        }
        return result;
    }

    public List<Abteilung> getAbteilungen(Connection con)
            throws SQLException {
        ArrayList<Abteilung> result = new ArrayList<Abteilung>();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT aid, name FROM Abteilung");
            while(rs.next()) {
                int aid = rs.getInt(1);
                String name = rs.getString(2);
                Abteilung a = new Abteilung(aid, name);
                result.add(a);
            }
        }
        finally {
            // wird immer ausgeführt#
            if (stm != null) {
                stm.close();
            }
        }
        return  result;
    }

}
