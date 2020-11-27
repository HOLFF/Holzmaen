package Übung02.model;

import java.sql.Timestamp;;

public class Kommentar {
	private int kommentarid;
	private int vonbenutzerid;
	private int zuartikelid;
	private String kommentar;
	private Timestamp zeitpunkt;
	private int zukommentarid;
	private boolean istgesperrt;
	
	public Kommentar() {
		super();
		this.kommentarid = -1;
		this.vonbenutzerid = -1;
		this.zuartikelid = -1;
		this.kommentar = "";
		this.zeitpunkt = null;
		this.zukommentarid = -1;
		this.istgesperrt = true;
	}

	public Kommentar(int kommentarid, int vonbenutzerid, int zuartikelid,
			String kommentar, Timestamp zeitpunkt, int zukommentarid,
			boolean istgesperrt) {
		super();
		this.kommentarid = kommentarid;
		this.vonbenutzerid = vonbenutzerid;
		this.zuartikelid = zuartikelid;
		this.kommentar = kommentar;
		this.zeitpunkt = zeitpunkt;
		this.zukommentarid = zukommentarid;
		this.istgesperrt = istgesperrt;
	}

	public int getKommentarid() {
		return kommentarid;
	}

	public void setKommentarid(int kommentarid) {
		this.kommentarid = kommentarid;
	}

	public int getVonbenutzerid() {
		return vonbenutzerid;
	}

	public void setVonbenutzerid(int vonbenutzerid) {
		this.vonbenutzerid = vonbenutzerid;
	}

	public int getZuartikelid() {
		return zuartikelid;
	}

	public void setZuartikelid(int zuartikelid) {
		this.zuartikelid = zuartikelid;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public Timestamp getZeitpunkt() {
		return zeitpunkt;
	}

	public void setZeitpunkt(Timestamp zeitpunkt) {
		this.zeitpunkt = zeitpunkt;
	}

	public int getZukommentarid() {
		return zukommentarid;
	}

	public void setZukommentarid(int zukommentarid) {
		this.zukommentarid = zukommentarid;
	}

	public boolean isIstgesperrt() {
		return istgesperrt;
	}

	public void setIstgesperrt(boolean istgesperrt) {
		this.istgesperrt = istgesperrt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (istgesperrt ? 1231 : 1237);
		result = prime * result
				+ ((kommentar == null) ? 0 : kommentar.hashCode());
		result = prime * result + kommentarid;
		result = prime * result + vonbenutzerid;
		result = prime * result
				+ ((zeitpunkt == null) ? 0 : zeitpunkt.hashCode());
		result = prime * result + zuartikelid;
		result = prime * result + zukommentarid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kommentar other = (Kommentar) obj;
		if (istgesperrt != other.istgesperrt)
			return false;
		if (kommentar == null) {
			if (other.kommentar != null)
				return false;
		} else if (!kommentar.equals(other.kommentar))
			return false;
		if (kommentarid != other.kommentarid)
			return false;
		if (vonbenutzerid != other.vonbenutzerid)
			return false;
		if (zeitpunkt == null) {
			if (other.zeitpunkt != null)
				return false;
		} else if (!zeitpunkt.equals(other.zeitpunkt))
			return false;
		if (zuartikelid != other.zuartikelid)
			return false;
		if (zukommentarid != other.zukommentarid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kommentar [kommentarid=" + kommentarid + ", vonbenutzerid="
				+ vonbenutzerid + ", zuartikelid=" + zuartikelid
				+ ", kommentar=" + kommentar + ", zeitpunkt=" + zeitpunkt
				+ ", zukommentarid=" + zukommentarid + ", istgesperrt="
				+ istgesperrt + "]";
	}
	
	
}