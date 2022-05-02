package de.fhbi.webbasedapps.projektsammlung.classes;

import java.io.Serializable;

public class Projekt implements Serializable {

    public static final long serialVersionUID = 1L;

    private String id;
    private String titel;
    private String kurzbeschreibung;
    private String logo;
    private long projektStart; //Unix-Timestamp in MS

    public Projekt(String id, String titel, String kurzbeschreibung, String logo, long projektStart) {
        this.id = id;
        this.titel = titel;
        this.kurzbeschreibung = kurzbeschreibung;
        this.logo = logo;
        this.projektStart = projektStart;
    }

    public Projekt() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getKurzbeschreibung() {
        return kurzbeschreibung;
    }

    public void setKurzbeschreibung(String kurzbeschreibung) {
        this.kurzbeschreibung = kurzbeschreibung;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public long getProjektStart() {
        return projektStart;
    }

    public void setProjektStart(long projektStart) {
        this.projektStart = projektStart;
    }
}
