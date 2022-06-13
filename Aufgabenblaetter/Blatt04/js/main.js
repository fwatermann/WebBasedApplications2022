import * as Startseite from "./startseite.js";
import testPostProject from "./apitest.js";

fetch("./lang/" + navigator.language + ".json")
.then(async (response) => {
    console.log(await response.json());
});

function startup(page) {

    if(page === "startseite") {
        Startseite.loadProjects();
    }
    testPostProject();
}

window.startup = startup;