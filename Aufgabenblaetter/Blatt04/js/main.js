import * as Startseite from "./startseite.js";

fetch("./lang/" + navigator.language + ".json")
.then(async (response) => {
    console.log(await response.json());
});

function startup(page) {

    if(page === "startseite") {
        Startseite.loadProjects();
    }

}

window.startup = startup;