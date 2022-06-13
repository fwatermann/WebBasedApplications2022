import { Projekt } from "./models.js";

export function calcProjectRuntime(project){
    let ret = 0;
    if(project instanceof Projekt){
        for(let i = 0;i<project.artefakte.length;i++){
            ret  += project.artefakte[i].geplanteArbeitszeit;
        }
        return ret;
    }
    return 0;
}

export function sortProjects(projects, sortBy) {

    switch(sortBy) {
        case "startDate":
            projects.sort((a, b) => {
                return a.projektStart - b.projektStart;
            });
        break;
        case "runtime":
            projects.sort((a, b) => {
                return calcProjectRuntime(a) - calcProjectRuntime(b);
            });
        break;
        default:
            return projects;
    }

}

export function createProjectList(projects) {
    let list = document.createElement("ul");
    for(let i = 0;i<projects.length;i++){
        let listItem = document.createElement("li");
        let link = document.createElement("a");
        link.href = "./projekt.html?id=" + projects[i].id;
        link.innerHTML = projects[i].titel;
        listItem.appendChild(link);
        list.appendChild(listItem);
    }
    console.log(list);
    return list;
}