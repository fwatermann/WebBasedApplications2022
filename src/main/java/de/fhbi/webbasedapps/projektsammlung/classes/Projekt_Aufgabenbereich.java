package de.fhbi.webbasedapps.projektsammlung.classes;

import java.io.Serializable;

public class Projekt_Aufgabenbereich implements Serializable {

    public static final long serialVersionUID = 1L;

    private String projektId;
    private String aufgabenbereichId;

    public Projekt_Aufgabenbereich(String projektId, String aufgabenbereichId) {
        this.projektId = projektId;
        this.aufgabenbereichId = aufgabenbereichId;
    }

    public Projekt_Aufgabenbereich() {}

    public String getProjektId() {
        return projektId;
    }

    public void setProjektId(String projektId) {
        this.projektId = projektId;
    }

    public String getAufgabenbereichId() {
        return aufgabenbereichId;
    }

    public void setAufgabenbereichId(String aufgabenbereichId) {
        this.aufgabenbereichId = aufgabenbereichId;
    }
}
