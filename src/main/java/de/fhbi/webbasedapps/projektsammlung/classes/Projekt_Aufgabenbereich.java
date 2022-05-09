package de.fhbi.webbasedapps.projektsammlung.classes;

import de.fhbi.webbasedapps.projektsammlung.keys.PK_Projekt_Artefakt;
import de.fhbi.webbasedapps.projektsammlung.keys.PK_Projekt_Aufgabenbereich;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "Projekt_Aufgabenbereich")
@IdClass(PK_Projekt_Aufgabenbereich.class)
@NamedQueries({
        @NamedQuery(name = "Projekt_Aufgabenbereich.findAll", query = "SELECT p FROM Projekt_Aufgabenbereich p WHERE p.projektId = :projektId"),
})
public class Projekt_Aufgabenbereich implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    private String projektId;
    @Id
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
