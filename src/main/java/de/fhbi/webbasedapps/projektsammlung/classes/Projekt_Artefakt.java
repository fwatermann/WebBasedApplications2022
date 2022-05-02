package de.fhbi.webbasedapps.projektsammlung.classes;

import java.io.Serializable;

public class Projekt_Artefakt implements Serializable {

    public static final long serialVersionUID = 1L;

    private String projektId;
    private String artefaktId;
    private double arbeitszeit;

    public Projekt_Artefakt(String projektId,String artefaktId,double arbeitszeit){
        this.projektId = projektId;
        this.artefaktId = artefaktId;
        this.arbeitszeit = arbeitszeit;
    }

    public Projekt_Artefakt(){}

    public String getProjektId() {
        return projektId;
    }

    public void setProjektId(String projektId) {
        this.projektId = projektId;
    }

    public String getArtefaktId() {
        return artefaktId;
    }

    public void setArtefaktId(String artefaktId) {
        this.artefaktId = artefaktId;
    }

    public double getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(double arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }
}
