export class Projekt {
    
    id;
    titel;
    kurzbeschreibung;
    logoUrl;
    projektStart;

    constructor(id, titel, kurzbeschreibung, logoUrl, projektStart) {
        this.id = id;
        this.titel = titel;
        this.kurzbeschreibung = kurzbeschreibung;
        this.logoUrl = logoUrl;
        this.projektStart = projektStart;
    }

}

export class Aufgabenbereich {

    id;
    kurzbeschreibung;
    titel;

    constructor(id, kurzbeschreibung, titel) {
        this.id = id;
        this.kurzbeschreibung = kurzbeschreibung;
        this.titel = titel;
    }

}

export class Artefakt {

    id;
    aufgabenbereich_id;
    titel;
    kurzbeschreibung;
    geplanteArbeitszeit;

    constructor(id, aufgabenbereich_id, titel, kurzbeschreibung, geplanteArbeitszeit) {
        this.id = id;
        this.aufgabenbereich_id = aufgabenbereich_id;
        this.titel = titel;
        this.kurzbeschreibung = kurzbeschreibung;
        this.geplanteArbeitszeit = geplanteArbeitszeit;
    }

}

