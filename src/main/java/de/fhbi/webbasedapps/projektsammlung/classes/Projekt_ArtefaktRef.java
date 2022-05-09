package de.fhbi.webbasedapps.projektsammlung.classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Projekt_Artefakte")
public class Projekt_ArtefaktRef implements Serializable {

    public static final long serialVersionUID = 1L;

    private String projekt_id;

    private double arbeitszeit;

    @Id
    @ManyToOne
    @JoinColumn(name = "artefakt_id")
    private Artefakt artefakt;

    public Artefakt getArtefakt() {
        return artefakt;
    }

    public void setArtefakt(Artefakt artefakt) {
        this.artefakt = artefakt;
    }

    public String getProjekt_id() {
        return projekt_id;
    }

    public void setProjekt_id(String projekt_id) {
        this.projekt_id = projekt_id;
    }

    public double getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(double arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }
}
