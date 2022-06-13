import * as models from "./models.js";

const API_URL = "http://localhost:8080/ProjektSammlung-1.0-SNAPSHOT/api"

export async function getProjekte() {

    return new Promise((resolve, reject) => {
        fetch(API_URL + "/projekte")
            .then(response => response.json())
            .then(data => resolve(data))
            .catch(err => reject(err));
    });

}

export async function getAufgabenbereiche(projektId) {

    return new Promise((resolve, reject) => {
        fetch(API_URL + "/projekte/" + projektId + "/aufgabenbereiche")
            .then(response => response.json())
            .then(data => resolve(data))
            .catch(err => reject(err));
    });

}

export async function getArtefakte(aufgabenbereichId) {

    return new Promise((resolve, reject) => {
        fetch(API_URL + "/projekte/" + aufgabenbereichId + "/artefakte")
            .then(response => response.json())
            .then(data => resolve(data))
            .catch(err => reject(err));
    });

}


export async function postProjekt(titel,kurzbeschreibung,logo,projektstart){

    return new Promise((resolve, reject) => {
        fetch(API_URL + "/projekte",{
            method:"POST",
            body:JSON.stringify({
                "titel":titel,
                "kurzbeschreibung":kurzbeschreibung,
                "logo":logo,
                "projektstart":projektstart
            }),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => resolve(response.json()))
            .catch(err => reject(err));
    })
}


export async function postAufgabenbereich(titel,kurzbeschreibung){
    
    return new Promise((resolve,reject) => {
        fetch(API_URL + "/aufgabenbereich",{
            method:"POST",
            body:JSON.stringify({
                "titel":titel,
                "kurzbeschreibung":kurzbeschreibung,
            }),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => resolve(response.json()))
            .catch(err => reject(err));
    })
}