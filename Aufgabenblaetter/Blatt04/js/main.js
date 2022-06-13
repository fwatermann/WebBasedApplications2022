import * as Models from "./models.js";
import * as API from "./api.js";
import * as Util from "./util.js";
import {calcProjectRuntimeTest} from "./utiltest.js";

fetch("./lang/" + navigator.language + ".json")
.then(async (response) => {
    console.log(await response.json());
});

document.getElementById("project_list").innerHTML = Util.createProjectList(await API.getProjekte()).innerHTML;

calcProjectRuntimeTest();