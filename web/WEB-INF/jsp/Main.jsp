<%--
  Created by IntelliJ IDEA.
  User: leerh
  Date: 12/14/2020
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="Übung02.model.*,java.sql.*,java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Artikel</title>
    <style>
        .content {
            display: flex;
            flex-wrap: nowrap;
        }
        .left {
            width: 20%;
            height: 500px;
            border-right-style: solid;
            border-width: 1px;
            padding-left: 10px;
            margin: 0px;
            overflow: auto;
        }
        .right {
            width: 80%;
            border-left-style: solid;
            border-width: 1px;
            padding-left: 10px;
            margin: 0 0 0 -1px;
            overflow: auto;
        }
        #Artikel {
            height:75%;
        }
        #ArtikelKommentare ul {
            list-style-type : none;
        }
        #ArtikelKommentare div.abstand {
            margin-left : 30px;
        }
        #ArtikelKommentare div.comment{
            display: inline-block;
            margin: 2px;
            padding:5px;
            border: 2px solid LightGray;
            border-radius: 8px;
            background-color:  LightGray;
        }
        li {
            margin:10px;
            text-decoration: underline;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="head">
        <h1>Willkommen</h1>
    </div>

    <div class="content">

        <div class="left">
            <h2>Themengebiete:</h2>

            <%
                // BEISPIEL f�r HTML Erzeugung in der JSP Seite
                // Auflistung der Themengebiete
                // Wir listen die alle Themengebiete auf:
                DBManager m = DBManager.getInstance();
                Connection con = m.getConnection();
                ArrayList<Themengebiet> themenliste = m.leseAlleThemengebiete(con);
                m.releaseConnection(con);

                // Zum Beispiel in einer Liste
                out.append("<ul>");

                for(Themengebiet t : themenliste ) {
                    // Beim Klicken auf ein Themengebiet
                    // werden die zugeh�rigen Artikel eingelesen
                    // dazu definieren wir eine JavaScript Funktion
                    // der die themengebietid als Argument �bergeben wird.
                    // im erzeugten Code steht dann:
                    // <li onclick='ArtikelVonThemaAnzeigen(1)'>Sport</li>
                    // <li onclick='ArtikelVonThemaAnzeigen(2)'>Politik</li>
                    // ...
                    out.append("<li onclick='ArtikelVonThemaAnzeigen("+
                            t.getThemengebietid()+")'>"+
                            t.getThemengebiet()+"</li>\n");

                }
                out.append("</ul>\n");
                if (themenliste.size() > 0) {
                    // Falls Themengebiete angezeigt wurden,
                    // merken wir uns die ThemengebietId des ersten
                    // (um sp�ter von diesem die Artikel anzuzeigen).
                    // Diese werden im JavaScript Teil
                    // mit einer neuen Technik ausgelesen:
                    // AJAX: Daten, die vom Server via JavaScript gelesen werden
                    // werden im Client mitttels JavaScript in die Seite eingebaut.
                    // Anmerkung: "\n" bewirkt einen Zeilenumbruch im Code
                    // Erzeugter Code wird besser lesbar bei
                    // "Seitenquellcode anzeigen" im Browser
                    out.append("<script>\n");
                    out.append("var firstThemenGebietId = ");
                    out.append(themenliste.get(0).getThemengebietid()+";\n");
                    out.append("</script>\n");
                }
            %>
            <!-- NUN der HTML TEIL -->
            <!--  Darunter stellen ich die Artikelliste dar ... -->
            <h2>Artikel</h2>
            <div id='ArtikelListe'></div>
        </div>  <!--  Ende von leftcol -->
        <!-- rechts daneben ein wenig Platz f�r den Artikel
             und die Kommentare  -->
        <div class='right'>
            <div id='Artikel'>
                <h2 id='ArtikelName'></h2>
                <div id='ArtikelText'></div>
            </div> <!-- Ende von id='Artikel' -->
            <hr>
            <h2>Kommentare:</h2>
            <div id='ArtikelKommentare'></div>
        </div> <!-- Ende von class='right' -->
    </div> <!-- class="content" -->
</div>  <!-- class="wrapper" -->

<script>
    // eine Varaible f�r die Artikelliste
    // eines Themengebietes
    var artikelListe;

    function loaded() {
        if (firstthemenid > 0)
            ArtikelVonThemaAnzeigen(firstthemenid);
    }
    // Sobald der Browser die Seite fertig geladen hat,
    // soll die Funktion "loaded" ausgef�hrt werden
    // Funtion ist oberhalb definiert.
    window.onload = loaded;

    // Hilfsroutine, um den Inhalt eines Elementes zu setzen
    // Auf das Element wird mittels seiner Id zugegriffen
    // und der Inhalt wird ersetzt:
    function setContentById(id, htm) {
        var u = document.getElementById(id);
        u.innerHTML = htm;
    }
    // Wir bleiben auf der Seite und holen uns
    // nur Daten vom Server:
    // Dazu verwendetet wir den AJAX Request:
    // Ajax arbeitet meist asynchron:
    //    Man schickt eine Anforderung ab und arbeitet weiter.
    //    Der Browser ist so NICHT blockiert - der Benutzer kann weiterarbeiten
    //    Man wartet daher nicht auf das Ergebnis sondern definiert
    //    in der Methode "onreadystatechange",
    //    was zu passieren hat, wenn die Antwort kommt.
    function ArtikelVonThemaAnzeigen(themenid) {
        // AJAX Objekt erzeugen - vorgegebene Klasse
        var xhttp = new XMLHttpRequest();

        // Was ist zu tun wenn die Antwort kommt
        xhttp.onreadystatechange = function() {
            //  readystate beschreibt wie "weit" die Antwort ist:
            //  4 bedeutet "fertig" empfangen
            //  status entsprciht den ErrorCode: 200 = OK
            if (this.readyState == 4 && this.status == 200) {
                // Der Datenaustausch erfolgte via JSON
                // JSON ist eine Stringdarstellung von
                // Javascript Objekten und kann sehr einfach
                // generiert und geparset werden:
                // Bitte auf w3schools nachlesen!
                // In JavaScript �bernimmt dies die Klasse JSON
                // Im Folgenden wird aus dem Text eine JavaScript
                // Datenstruktur:
                var arr = JSON.parse(this.responseText);
                // Merken wir uns die Liste in der globalen Variablen
                // Dann haben wir sie zur Verarbeitung bereits im
                // Client und k�nnen immer darauf zugreifen
                artikelListe = arr;
                // Der n�chste Aufruf soll das JavaScriptArray Artikelliste
                // im einen HTML Text umbauen:
                var htmlcode = ArtikelArrayFormatieren(arr);
                // Wir stellen den Inhalt dar
                setContentById('ArtikelListe',htmlcode);
                // Und zeigen, wenn vorhanden den ersten Artikel an
                // Die 0 beschreibt den Index im Array und nicht die ID
                // => dies ist eine weitere Technik, die �fters eingesetzt wird.
                if (arr.length > 0) {
                    ArtikelAnzeigen(0);

                }
            }
        };  //  Ende der Zuweisung der Funktionsdefinition f�r onreadystatechange

        // Nun bereiten wir das "Absenden" vor:
        // Die Argumente werden via POST �bergeben
        // Die am Server aufgerufene Seite (=Servlet) ist "ReadArtikel"
        // true: asynchrone Verarbeitung wird verwendet
        xhttp.open("POST", "ReadArtikel", true);

        // Formate festlegen +  Request abschicken
        // Die n�chste Zeile ist immer dieselbe
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        // Nun erfolgt das senden
        // Bei POST werden hier die Parameter mitgegeben
        xhttp.send("themenId="+themenid);
    }


    // das JavaScriptArray im Paramater "arr" wird
    // im einen HTML Text umgebaut:
    function ArtikelArrayFormatieren(arr) {
        var len, text, i, elem;

        len = arr.length;
        text = "<dl>";
        for (i = 0; i < len; i++) {
            elem = arr[i];
            text += "<dt onclick='ArtikelAnzeigen("+i+")' >" +
                elem.kopfzeile + "</dt>";
            text += "<dd>" + elem.zusammenfassung + "</dd>";
        }
        text += "</dl>";
        return text;
    }


    function ArtikelAnzeigen(index) {
        // alert(index);
        var act_artikel = artikelListe[index];

        setContentById('ArtikelName',act_artikel.ueberschrift);
        setContentById('ArtikelText', act_artikel.langtext);

        // kommt sp�ter: ReadKommentareVomArtikel(act_artikel.id);
        // Reicht f�r diese Woche.
    }

</script>
</body>
</html>