import * as Models from "./models.js";
import * as API from "./api.js";
import {calcProjectRuntimeTest} from "./utiltest.js";

fetch("./lang/" + navigator.language + ".json")
.then(async (response) => {
    console.log(await response.json());
});

let t = async ()=> {
    console.log("TEST");
    console.log(await API.getProjekte());
};
t();

calcProjectRuntimeTest();