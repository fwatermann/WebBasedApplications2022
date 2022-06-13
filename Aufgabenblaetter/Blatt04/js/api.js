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
        fetch(API_URL + "/aufgabenbereiche",{
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


export async function postArtefakt(titel,kurzbeschreibung){

    return new Promise((resolve,reject) => {
        fetch(API_URL + "/artefakte",{
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

export async function setArbeitszeit(projektId, artefaktId, arbeitszeit) {
        return new Promise((resolve, reject) => {
            fetch(API_URL + "/projekte/" + projektId + "/artefakte/" + artefaktId + "/arbeitszeit",{
                method:"PATCH",
                body:JSON.stringify({
                    "arbeitszeit":arbeitszeit,
                }),
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(resolve(true))
            .catch(err => reject(err));
        });
}