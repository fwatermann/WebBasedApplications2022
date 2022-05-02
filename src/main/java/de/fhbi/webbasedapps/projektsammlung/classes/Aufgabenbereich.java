package de.fhbi.webbasedapps.projektsammlung.classes;

import java.io.Serializable;

public class Aufgabenbereich implements Serializable {

    public static final long serialVersionUID = 1L;

    private String id;
    private String titel;
    private String kurzbeschreibung;

    public void setString(String titel) {
        this.titel = titel;
    }

    public void setKurzbeschreibung(String kurzbeschreibung) {
        if(kurzbeschreibung.length() <= 255) {
            this.kurzbeschreibung = kurzbeschreibung;
        }
    }

    public String getTitel() {
        return this.titel;
    }

    public String getKurzbeschreibung() {
        return this.kurzbeschreibung;
    }

    public String getId() {
        return this.id;
    }

}
