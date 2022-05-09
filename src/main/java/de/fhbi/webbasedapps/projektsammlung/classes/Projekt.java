package de.fhbi.webbasedapps.projektsammlung.classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Projekt")
@NamedQueries({
        @NamedQuery(name = "Projekt.findAll", query = "SELECT p FROM Projekt p"),
})
public class Projekt implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String titel;
    private String kurzbeschreibung;
    private String logo;
    private long projektStart; //Unix-Timestamp in MS

    @ManyToMany
    @JoinTable(name = "Projekt_aufgabenbereiches",
            joinColumns = @JoinColumn(name = "projekt_id"),
            inverseJoinColumns = @JoinColumn(name = "aufgabenbereiches_id"))
    private Collection<Aufgabenbereich> aufgabenbereiches = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "Projekt_artefakte",
            joinColumns = @JoinColumn(name = "projekt_id"),
            inverseJoinColumns = @JoinColumn(name = "artefakt_id"))
    private Collection<Artefakt> artefakte = new ArrayList<>();

    public Projekt(String id, String titel, String kurzbeschreibung, String logo, long projektStart) {
        this.id = id;
        this.titel = titel;
        this.kurzbeschreibung = kurzbeschreibung;
        this.logo = logo;
        this.projektStart = projektStart;
    }

    public Projekt() {}

    public String getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getKurzbeschreibung() {
        return kurzbeschreibung;
    }

    public String getLogo() {
        return logo;
    }

    public long getProjektStart() {
        return projektStart;
    }

    public Collection<Artefakt> getArtefakte() {
        return artefakte;
    }

    public Collection<Aufgabenbereich> getAufgabenbereiches() {
        return aufgabenbereiches;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setKurzbeschreibung(String kurzbeschreibung) {
        if(kurzbeschreibung.length() < 255) {
            this.kurzbeschreibung = kurzbeschreibung;
        }
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setProjektStart(long projektStart) {
        this.projektStart = projektStart;
    }

    public void setAufgabenbereiches(Collection<Aufgabenbereich> aufgabenbereiches) {
        this.aufgabenbereiches = aufgabenbereiches;
    }

    public void setArtefakte(Collection<Artefakt> artefakte) {
        this.artefakte = artefakte;
    }

}
