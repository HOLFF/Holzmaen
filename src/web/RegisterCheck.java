package web;

import Übung02.model.Benutzer;
import Übung02.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "RegisterCheck")
public class RegisterCheck extends HttpServlet {

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Parameter lesen - Wert aus Attribute 'name' verwenden
        String fn = request.getParameter("fn");
        String ln = request.getParameter("ln");
        String email = request.getParameter("email");
        String u = request.getParameter("user");
        String p = request.getParameter("pwd");
//---------------------------------------
// Variable für Register Erfolg
        boolean registerSuccess = true;

// Verarbeitung der Daten => Registerdaten überprüfen
// Zugriff auf DBManager Objekt
        DBManager dbm = DBManager.getInstance();
        try (final Connection connection = dbm.getConnection()) {
            if (!dbm.benutzerEmailIstVorhanden(connection, email) && !dbm.benutzerNicknameIstVorhanden(connection, u)) {
                dbm.speichereNeuenBenutzer(connection, new Benutzer(email,fn,ln,p,u,"B",false));
            }
            else{
                registerSuccess =false;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

// Weiterleitung
        if (registerSuccess) {
// Alles OK

// Benutzername in Session setzen
//===================================
// Session Objekt speichert am Server Benutzerinformationen.
// Session existiert während der "Sitzung" mit der Anwendung

// Neue oder bestehende Session holen
            HttpSession session = request.getSession();

// Zum Verständnis:
// Nachfolgende Anweisung erzeugt einen Eintrag in einer
// ähnliche Struktur wie eine Java "Hashmap" bzw. ein C# Dictionary
// => Mittels des Schlüsselwertes "benutzer" wird das Objekt (=der String) "u" abgelegt.
            session.setAttribute("benutzer", u);
// auslesen können wir ihn mit
// String usr = (String) session.getAttribute("benutzer");
// Der Cast wir benötigt da alle Einträge als Object gespeichert werden

// Benutzer wird nach Anmeldung auf eine andere Seite weitergeleitet.
// =====================================================================
// Weiterleitungsobjekt anfordern für Weiterleigung zur Seite Main.html
            RequestDispatcher d = request.getRequestDispatcher("Index.html");
// Weiterleitung durchführen
            d.forward(request, response);
        } else {
// Session löschen
            // mit false als Argument darf keine neue Session angelegt werden
            HttpSession session = request.getSession(false);
            // Sollte eine Session vorhanded sein, session freigeben
            if (session != null) session.invalidate();
            // Fehlermeldung setzen
            // weiterleiten - Beschreibung oberhalb.

            // Fehlermeldung:
            // Fehlerobjekt im Request eintragen: request.setAttribute
            // Dieses Objekt wird dann im Login.jsp ausgelesen: request.getAttribute
            request.setAttribute("errormsg", "Benutzername oder Email bereits vergeben!");  // EINFÜGEN
            // Weiterleitung:
            RequestDispatcher d = request.getRequestDispatcher("Register.jsp");  // AUCH HIER DAS ZIEL ÄNDERN !!!
            d.forward(request, response);
        }

    }

}