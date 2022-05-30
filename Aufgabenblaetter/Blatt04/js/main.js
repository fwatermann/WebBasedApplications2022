import * as Models from "./models.js";

fetch("./lang/" + navigator.language + ".json")
.then(async (response) => {
    console.log(await response.json());
});