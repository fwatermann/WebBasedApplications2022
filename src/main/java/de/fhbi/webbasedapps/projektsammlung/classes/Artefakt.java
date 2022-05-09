package de.fhbi.webbasedapps.projektsammlung.classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Artefakt")
@NamedQueries({
        @NamedQuery(name = "Artefakt.findAll", query = "SELECT a FROM Artefakt a"),
})
public class Artefakt implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String titel;
    private String kurzbeschreibung;

    @ManyToOne
    @JoinColumn(name = "aufgabenbereich_id")
    private Aufgabenbereich aufgabenbereich;

    private long geplanteArbeitszeit;

    public Artefakt(String id, String titel, String kurzbeschreibung, Aufgabenbereich aufgabenbereich, long geplanteArbeitszeit) {
        this.id = id;
        this.titel = titel;
        this.kurzbeschreibung = kurzbeschreibung;
        this.aufgabenbereich = aufgabenbereich;
        this.geplanteArbeitszeit = geplanteArbeitszeit;
    }

    public Artefakt(){}

    public String getKurzbeschreibung() {
        return kurzbeschreibung;
    }

    public void setKurzbeschreibung(String kurzbeschreibung) {
        if(kurzbeschreibung.length() <= 255) {
            this.kurzbeschreibung = kurzbeschreibung;
        }
    }

    public Aufgabenbereich getAufgabenbereich() {
        return aufgabenbereich;
    }

    public void setAufgabenbereich(Aufgabenbereich aufgabenbereich) {
        this.aufgabenbereich = aufgabenbereich;
    }

    public long getGeplanteArbeitszeit() {
        return geplanteArbeitszeit;
    }

    public void setGeplanteArbeitszeit(long geplanteArbeitszeit) {
        this.geplanteArbeitszeit = geplanteArbeitszeit;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
