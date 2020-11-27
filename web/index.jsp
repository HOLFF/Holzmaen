<%--
  Created by IntelliJ IDEA.
  User: leerh
  Date: 11/23/2020
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC
"-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type"
        content="text/html; charset=ISO-8859-1">
  <title>Login</title>
</head>
<body>
<h1>Anmeldung:</h1>
<%  // Mit diesem Tag können wir Java Code in eine JSP Seite einbauen
// und DYNAMISCHE Seiteninhalte (die Fehlermeldung) erzeugen:
// Die Objekte "session", "request", "out" (dazu später mehr)
// sind IMMER bereits gesetzt und wir können sie direkt verwenden.
// Versuchen wir das Attribute auszulesen:
  String msg = (String) request.getAttribute("errormsg");
// Ist das Attribut nicht vorhanden hat, liefert getAttribute den Wert "null"
  if (msg != null) {
    // Fehler in rot ausgeben
    out.append("<p style='color:red'>"+msg+"</p>");
    // War ein Benutzer eingeloggt ... => ausloggen
    if (session != null) session.invalidate();
  }

// Hier endet der Java Code
%>
<form method="post" action="LoginCheck">
  Name: <input type="text" name="user" ><br>
  Passwort: <input type="password" name="pwd"><br>
  <button type="submit">Senden</button>
</form>
</body>
</html>