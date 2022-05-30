import * as Models from "./models.js";
import {calcProjectRuntimeTest} from "./utiltest.js";

fetch("./lang/" + navigator.language + ".json")
.then(async (response) => {
    console.log(await response.json());
});

calcProjectRuntimeTest();