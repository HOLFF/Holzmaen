package Übung02.model;

public class Artikel {
	private int	artikelid;
	private int autorid;
	private String kopfzeile;
	private String zusammenfassung;
	private String artikeltext;
	private boolean istgesperrt;
	private int themengebietid ;

	public Artikel() {
		super();
		this.artikelid = -1;
		this.autorid = -1;
		this.kopfzeile = "";
		this.zusammenfassung = "";
		this.artikeltext = "";
		this.istgesperrt = false;
		this.themengebietid = -1;
	}
	
	public Artikel(int artikelid, int autorid, String kopfzeile,
			String zusammenfassung, String artikeltext, boolean istgesperrt,
			int themengebietid) {
		super();
		this.artikelid = artikelid;
		this.autorid = autorid;
		this.kopfzeile = kopfzeile;
		this.zusammenfassung = zusammenfassung;
		this.artikeltext = artikeltext;
		this.istgesperrt = istgesperrt;
		this.themengebietid = themengebietid;
	}

	@Override
	public String toString() {
		return "Artikel [artikelid=" + artikelid + ", autorid=" + autorid
				+ ", kopfzeile=" + kopfzeile + ", zusammenfassung="
				+ zusammenfassung + ", artikeltext=" + artikeltext
				+ ", istgesperrt=" + istgesperrt + ", themengebietid="
				+ themengebietid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + artikelid;
		result = prime * result
				+ ((artikeltext == null) ? 0 : artikeltext.hashCode());
		result = prime * result + autorid;
		result = prime * result + (istgesperrt ? 1231 : 1237);
		result = prime * result
				+ ((kopfzeile == null) ? 0 : kopfzeile.hashCode());
		result = prime * result + themengebietid;
		result = prime * result
				+ ((zusammenfassung == null) ? 0 : zusammenfassung.hashCode());
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
		Artikel other = (Artikel) obj;
		if (artikelid != other.artikelid)
			return false;
		if (artikeltext == null) {
			if (other.artikeltext != null)
				return false;
		} else if (!artikeltext.equals(other.artikeltext))
			return false;
		if (autorid != other.autorid)
			return false;
		if (istgesperrt != other.istgesperrt)
			return false;
		if (kopfzeile == null) {
			if (other.kopfzeile != null)
				return false;
		} else if (!kopfzeile.equals(other.kopfzeile))
			return false;
		if (themengebietid != other.themengebietid)
			return false;
		if (zusammenfassung == null) {
			if (other.zusammenfassung != null)
				return false;
		} else if (!zusammenfassung.equals(other.zusammenfassung))
			return false;
		return true;
	}

	public int getArtikelid() {
		return artikelid;
	}

	public void setArtikelid(int artikelid) {
		this.artikelid = artikelid;
	}

	public int getAutorid() {
		return autorid;
	}

	public void setAutorid(int autorid) {
		this.autorid = autorid;
	}

	public String getKopfzeile() {
		return kopfzeile;
	}

	public void setKopfzeile(String kopfzeile) {
		this.kopfzeile = kopfzeile;
	}

	public String getZusammenfassung() {
		return zusammenfassung;
	}

	public void setZusammenfassung(String zusammenfassung) {
		this.zusammenfassung = zusammenfassung;
	}

	public String getArtikeltext() {
		return artikeltext;
	}

	public void setArtikeltext(String artikeltext) {
		this.artikeltext = artikeltext;
	}

	public boolean isIstgesperrt() {
		return istgesperrt;
	}

	public void setIstgesperrt(boolean istgesperrt) {
		this.istgesperrt = istgesperrt;
	}

	public int getThemengebietid() {
		return themengebietid;
	}

	public void setThemengebietid(int themengebietid) {
		this.themengebietid = themengebietid;
	}
	
	
	
	
}