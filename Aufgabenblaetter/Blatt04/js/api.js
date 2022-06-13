import * as models from "./models.js";

const API_URL = "http://localhost:8080/ProjektSammlung-1.0-SNAPSHOT/api"

export async function getProjekte() {

    return new Promise((resolve, reject) => {
        fetch(API_URL + "/projekte")
            .then(response => response.json())
            .then(resolve(data))
            .catch(reject(err));
    });

}