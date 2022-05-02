package de.fhbi.webbasedapps.projektsammlung.classes;

import java.io.Serializable;

public class Artefakt implements Serializable {

    public static final long serialVersionUID = 1L;

    private String id;
    private String titel;
    private String kurzbeschreibung;
    private Aufgabenbereich aufgabenbereich;
    private long geplanteArbeitszeit;

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

    public String getId() {
        return id;
    }

}
