package Übung02.model;

public class Themengebiet {
	private int themengebietid;
	private String themengebiet;

public Themengebiet() {
	super();
	this.themengebietid = -1;
	this.themengebiet = "";
}

public Themengebiet(int themengebietid, String themengebiet) {
	super();
	this.themengebietid = themengebietid;
	this.themengebiet = themengebiet;
}

@Override
public String toString() {
	return "Themengebiet [themengebietid=" + themengebietid + ", themengebiet="
			+ themengebiet + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((themengebiet == null) ? 0 : themengebiet.hashCode());
	result = prime * result + themengebietid;
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
	Themengebiet other = (Themengebiet) obj;
	if (themengebiet == null) {
		if (other.themengebiet != null)
			return false;
	} else if (!themengebiet.equals(other.themengebiet))
		return false;
	if (themengebietid != other.themengebietid)
		return false;
	return true;
}

public int getThemengebietid() {
	return themengebietid;
}
public void setThemengebietid(int themengebietid) {
	this.themengebietid = themengebietid;
}
public String getThemengebiet() {
	return themengebiet;
}
public void setThemengebiet(String themengebiet) {
	this.themengebiet = themengebiet;
}


}
