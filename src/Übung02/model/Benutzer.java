package Übung02.model;

public class Benutzer {
	private int benutzerid;
	private String email;
	private String vname;
	private String nname ;
	private String passwort; 
	private String nickname ;
	private String usergroup;
	private boolean istgesperrt;
	
	public Benutzer() {
		super();
		this.benutzerid = -1;
		this.email = "";
		this.vname = "";
		this.nname = "";
		this.passwort = "";
		this.nickname = "";
		this.usergroup = "B";
		this.istgesperrt = true;
	}
	
	public Benutzer(String email, String vname, String nname,
			String passwort, String nickname, String usergroup,
			boolean istgesperrt) {
		super();
		this.email = email;
		this.vname = vname;
		this.nname = nname;
		this.passwort = passwort;
		this.nickname = nickname;
		this.usergroup = usergroup;
		this.istgesperrt = istgesperrt;
	}
	public int getBenutzerid() {
		return benutzerid;
	}
	public void setBenutzerid(int benutzerid) {
		this.benutzerid = benutzerid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
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
		result = prime * result + benutzerid;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (istgesperrt ? 1231 : 1237);
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((nname == null) ? 0 : nname.hashCode());
		result = prime * result
				+ ((passwort == null) ? 0 : passwort.hashCode());
		result = prime * result
				+ ((usergroup == null) ? 0 : usergroup.hashCode());
		result = prime * result + ((vname == null) ? 0 : vname.hashCode());
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
		Benutzer other = (Benutzer) obj;
		if (benutzerid != other.benutzerid)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (istgesperrt != other.istgesperrt)
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (nname == null) {
			if (other.nname != null)
				return false;
		} else if (!nname.equals(other.nname))
			return false;
		if (passwort == null) {
			if (other.passwort != null)
				return false;
		} else if (!passwort.equals(other.passwort))
			return false;
		if (usergroup == null) {
			if (other.usergroup != null)
				return false;
		} else if (!usergroup.equals(other.usergroup))
			return false;
		if (vname == null) {
			if (other.vname != null)
				return false;
		} else if (!vname.equals(other.vname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Benutzer [benutzerid=" + benutzerid + ", email=" + email
				+ ", vname=" + vname + ", nname=" + nname + ", passwort="
				+ passwort + ", nickname=" + nickname + ", usergroup="
				+ usergroup + ", istgesperrt=" + istgesperrt + "]";
	}
	
	
}