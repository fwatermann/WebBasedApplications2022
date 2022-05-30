import { Projekt } from "./models";

export function calcProjectRuntime(project){
    
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