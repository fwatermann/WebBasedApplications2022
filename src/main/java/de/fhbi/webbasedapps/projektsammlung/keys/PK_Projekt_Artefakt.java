package de.fhbi.webbasedapps.projektsammlung.keys;

import java.io.Serializable;

public class PK_Projekt_Artefakt implements Serializable {

    public static final long serialVersionUID = 1L;

    private String projekt_id;
    private String artefakt_id;

    public PK_Projekt_Artefakt() {}

    public PK_Projekt_Artefakt(String projektId, String artefactId) {
        this.projekt_id = projektId;
        this.artefakt_id = artefactId;
    }
}
