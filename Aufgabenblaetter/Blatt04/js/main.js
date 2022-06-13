import * as Startseite from "./startseite.js";
import * as Apitest from "./apitest.js";

fetch("./lang/" + navigator.language + ".json")
.then(async (response) => {
    console.log(await response.json());
});

function startup(page) {

    if(page === "startseite") {
        Startseite.loadProjects();
    }
    Apitest.doPostTests();
}

window.startup = startup;