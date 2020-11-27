package Übung02.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DBManager {

	private DBManager() {}
	private static DBManager instance = null;
	public static DBManager getInstance () {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public Connection getConnection() 
			throws ClassNotFoundException, SQLException {
		Connection con = null;	
		// com.mysql.cj.jdbc.Driver f�r MySql Version 8
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/zeidung"
					// + "?serverTimezone=UTC&useSSL=false"
					, "root"  // User
					, ""      // Passwort
					);
		return con;
	}

	public void releaseConnection (Connection con)
			throws SQLException {
			if (con != null)
				con.close();
	}

	public boolean canLogin (Connection con,
			String email, String pwd)
		throws SQLException {
	boolean result = false;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
		String sql = "select count(*) from benutzer" +
	       "where email = ? and passwort = ?";
		stm= con.prepareStatement(sql);
		stm.setString(1, email);
		stm.setString(2, pwd);
		rs = stm.executeQuery();
		if (rs.next()) {
			int anzahl = rs.getInt(1);
			result = anzahl == 1;
		}
	} 
	finally 
	{
		if (stm != null)
			stm.close();
	}
	return result;
	}
	
	public void speichereNeuenBenutzer(Connection con, Benutzer b) throws SQLException {
		String sql = "insert into Benutzer (" + 
				"email,vname,nname,passwort" +
				",nickname,usergroup,istgesperrt" +
				") values (?,?,?,?,?,?,?)";
		PreparedStatement stm = null;
		try { 
			stm = con.prepareStatement(sql);
			stm.setString( 1, b.getEmail() );
			stm.setString( 2, b.getVname() );
			stm.setString( 3, b.getNname() );
			stm.setString( 4, b.getPasswort() );
			stm.setString( 5, b.getNickname() );
			stm.setString( 6, b.getUsergroup());
			stm.setBoolean(7, b.isIstgesperrt());
			stm.executeUpdate();
		}
		finally {
			if (stm != null)
				stm.close();
		}
	}
	
	public boolean benutzerEmailIstVorhanden (Connection con, 
			String email)
		throws SQLException {
	boolean result = false;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {                 
		String sql = "select count(*) from benutzer" +
	       "where email = ?";
		stm= con.prepareStatement(sql);
		stm.setString(1, email);
		rs = stm.executeQuery();
		if (rs.next()) {
			int anzahl = rs.getInt(1);
			result = anzahl == 1;
		}
	} 
	finally 
	{
		if (stm != null)
			stm.close();
	}
	return result;
	}

	public boolean benutzerNicknameIstVorhanden (Connection con, 
			String nickname)
		throws SQLException {
	boolean result = false;
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {                 
		String sql = "select count(*) from benutzer" +
	       "where nickname = ?";
		stm= con.prepareStatement(sql);
		stm.setString(1, nickname);
		rs = stm.executeQuery();
		if (rs.next()) {
			int anzahl = rs.getInt(1);
			result = anzahl == 1;
		}
	} 
	finally 
	{
		if (stm != null)
			stm.close();
	}
	return result;
	}

	public ArrayList<Themengebiet> leseAlleThemengebiete(Connection con)
			throws SQLException {
		Statement stm = null;
		ResultSet rs = null;
		ArrayList<Themengebiet> result =  new ArrayList<Themengebiet>();
		try {
			String sql = "SELECT themengebietid, themengebiet FROM themengebiete";
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				int themengebietid = rs.getInt(1);
				String themengebiet = rs.getString(2);
				Themengebiet a = new Themengebiet(themengebietid, themengebiet);
				result.add(a);
			}
		}
		finally {
			if (stm != null)
				stm.close();
		}
		return result;
	}
	
	public ArrayList<Artikel> leseArtikelVonThemenebiet
	      (Connection con, int themengebietid) throws SQLException {
		String sql = "SELECT " +
				 "   artikelid, autorid, kopfzeile, " +
				 "   zusammenfassung, artikeltext, istgesperrt" +
				 "FROM Artikel WHERE themengebietid = ? ";
		ArrayList<Artikel> result =  new ArrayList<Artikel>();
		try (PreparedStatement stm = con.prepareStatement(sql)) {
			ResultSet rs = null;
			stm.setInt(1, themengebietid);
			rs = stm.executeQuery();
			while (rs.next()) {
				int artikelid = rs.getInt(1);
				int autorid = rs.getInt(2);
				String kopfzeile = rs.getString(3);
				String zusammenfassung = rs.getString(4);
				String artikeltext = rs.getString(5);
				boolean istgesperrt = rs.getBoolean(6);
				Artikel a = new Artikel(
						artikelid,	autorid, kopfzeile,
						zusammenfassung,artikeltext,istgesperrt,
						themengebietid
						);
				result.add(a);
			}
		}
		return result;
	}
	
	public void speichereNeuenKommentar(Connection con, Kommentar k) 
			throws SQLException {
		String sql = "insert into Kommentare (" + 
				"vonbenutzerid,zuartikelid,kommentar,zukommentarid" +
				") values (?,?,?,?)";
		PreparedStatement stm = null;
		try { 
			stm = con.prepareStatement(sql);
			stm.setInt   ( 1, k.getVonbenutzerid() );
			stm.setInt   ( 2, k.getZuartikelid() );
			stm.setString( 3, k.getKommentar() );
			stm.setInt   ( 4, k.getZukommentarid() );
			stm.executeUpdate();
		}
		finally {
			if (stm != null)
				stm.close();
		}
	}

	public ArrayList<Kommentar> leseKommentareZuArtikel
    (Connection con, int artikelid) throws SQLException {
	String sql = "SELECT " +
			 "   kommentarid, vonbenutzerid, zuartikelid, " +
			 "   kommentar, zeitpunkt, zukommentarid, istgesperrt" +
			 "FROM Kommentare WHERE zuartikelid = ? ";
	ArrayList<Kommentar> result =  new ArrayList<Kommentar>();
	try (PreparedStatement stm = con.prepareStatement(sql)) {
		ResultSet rs = null;
		stm.setInt(1, artikelid);
		rs = stm.executeQuery();
		while (rs.next()) {
			int kommentarid = rs.getInt(1);
			int vonbenutzerid = rs.getInt(2);
			int zuartikelid = rs.getInt(3);
			String kommentar = rs.getString(4);
			Timestamp zeitpunkt = rs.getTimestamp(5);
			int zukommentarid = rs.getInt(6);
			boolean istgesperrt = rs.getBoolean(7);
			Kommentar a = new Kommentar(
					kommentarid,vonbenutzerid, zuartikelid,
					kommentar,zeitpunkt,zukommentarid,
					istgesperrt
					);
			result.add(a);
		}
	}
	return result;
}

}	