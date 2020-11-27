package Übung01.dbmodel;

public class Abteilung {
    int aid;
    String name;

    public Abteilung(int aid, String name) {
        super();
        this.aid = aid;
        this.name = name;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Abteilung{" +
                "aid=" + aid +
                ", name='" + name + '\'' +
                '}';
    }

}

