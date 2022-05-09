package de.fhbi.webbasedapps.projektsammlung.classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Aufgabenbereich")
@NamedQueries({
        @NamedQuery(name = "Aufgabenbereich.findAll", query = "SELECT a FROM Aufgabenbereich a"),
})
public class Aufgabenbereich implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String titel;
    private String kurzbeschreibung;

    public Aufgabenbereich(String id,String titel,String kurzbeschreibung){
        this.id = id;
        this.titel = titel;
        this.kurzbeschreibung = kurzbeschreibung;
    }

    public Aufgabenbereich(){}

    public void setString(String titel) {
        this.titel = titel;
    }

    public void setKurzbeschreibung(String kurzbeschreibung) {
        if(kurzbeschreibung.length() <= 255) {
            this.kurzbeschreibung = kurzbeschreibung;
        }
    }

    public String getTitel() {
        return this.titel;
    }

    public String getKurzbeschreibung() {
        return this.kurzbeschreibung;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }


}
