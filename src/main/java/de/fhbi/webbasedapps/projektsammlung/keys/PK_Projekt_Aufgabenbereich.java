package de.fhbi.webbasedapps.projektsammlung.keys;

import java.io.Serializable;

public class PK_Projekt_Aufgabenbereich implements Serializable {

    public static final long serialVersionUID = 1L;

    private String projektId;
    private String aufgabenbereichId;

    public PK_Projekt_Aufgabenbereich() {}

    public PK_Projekt_Aufgabenbereich(String projektId, String aufgabenbereichId) {
        this.projektId = projektId;
        this.aufgabenbereichId = aufgabenbereichId;
    }
}
