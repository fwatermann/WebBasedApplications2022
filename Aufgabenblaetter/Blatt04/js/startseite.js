import * as Util from "./util.js";
import * as API from "./api.js";

export async function loadProjects() {
    document.getElementById("project_list").innerHTML = Util.createProjectList(await API.getProjekte()).innerHTML;
}