package de.fhbi.webbasedapps.projektsammlung.classes;

import java.io.Serializable;

public class ProjektIdArtefaktId implements Serializable {

    public static final long serialVersionUID = 1L;

    private String projekt_id;
    private String artefakt_id;

    public ProjektIdArtefaktId() {}

    public String getProjekt_id() {
        return projekt_id;
    }

    public void setProjekt_id(String projekt_id) {
        this.projekt_id = projekt_id;
    }

    public String getArtefakt_id() {
        return artefakt_id;
    }

    public void setArtefakt_id(String artefakt_id) {
        this.artefakt_id = artefakt_id;
    }
}
