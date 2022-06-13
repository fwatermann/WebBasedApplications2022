import * as Models from "./models.js";
import * as API from "./api.js";
import * as Util from "./util.js";
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