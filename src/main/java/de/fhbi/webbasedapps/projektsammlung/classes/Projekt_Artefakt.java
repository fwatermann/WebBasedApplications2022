package de.fhbi.webbasedapps.projektsammlung.classes;

import de.fhbi.webbasedapps.projektsammlung.keys.PK_Projekt_Artefakt;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Projekt_Artefakte")
@IdClass(PK_Projekt_Artefakt.class)
@NamedQueries({
        @NamedQuery(name = "Projekt_Artefakt.findAll", query = "SELECT p FROM Projekt_Artefakt p WHERE p.projekt_id = :projektId"),
})
public class Projekt_Artefakt implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    private String projekt_id;

    @Id
    private String artefakt_id;

    private double arbeitszeit;

    public Projekt_Artefakt(String projektId, String artefaktId, double arbeitszeit) {
        this.projekt_id = projektId;
        this.artefakt_id = artefaktId;
        this.arbeitszeit = arbeitszeit;
    }

    public Projekt_Artefakt() {
    }

    public String getProjekt_id() {
        return projekt_id;
    }

    public void setProjekt_id(String projektId) {
        this.projekt_id = projektId;
    }

    public String getArtefakt_id() {
        return artefakt_id;
    }

    public void setArtefakt_id(String artefaktId) {
        this.artefakt_id = artefaktId;
    }

    public double getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(double arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }
}
