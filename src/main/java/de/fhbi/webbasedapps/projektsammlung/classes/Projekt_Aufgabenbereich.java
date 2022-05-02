package de.fhbi.webbasedapps.projektsammlung.classes;

import java.io.Serializable;

public class Projekt_Aufgabenbereich implements Serializable {

    public static final long serialVersionUID = 1L;

    private int projektId;
    private int aufgabenbereichId;

    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    public int getAufgabenbereichId() {
        return aufgabenbereichId;
    }

    public void setAufgabenbereichId(int aufgabenbereichId) {
        this.aufgabenbereichId = aufgabenbereichId;
    }
}
