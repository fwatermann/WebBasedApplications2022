import { Artefakt, Projekt } from "./models";
import { calcProjectRuntime,sortProjects } from "./util";


export function calcProjectRuntimeTest(){
    let artefact1 = new Artefakt(0,0,"Artefakt 1","dies ist das erste Artefakt :D",420);
    let artefact2 = new Artefakt(0,0,"Artefakt 2","dies ist das zweite Artefakt :D",31);
    let artefact3 = new Artefakt(0,0,"Artefakt 3","dies ist das dritte Artefakt :D",69);

    let testProject = new Projekt(0,"testProjekt 1","dies ist ein Test :D"," ",0);

    testProject.artefakte.push(artefact1,artefact2.artefact3);

    console.log(calcProjectRuntime(testProject));
}