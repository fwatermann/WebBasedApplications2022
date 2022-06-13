import { postProjekt,postAufgabenbereich, postArtefakt } from "./api.js";


export async function doPostTests(){
    await testPostProject();
    await testPostAufgabenbereich();
    await testPostArtefakt();
}

export async function testPostProject(){
    let resp = await postProjekt("TestProjekt1","dies ist ein Testprojekt","dies ist ein Logo",0);
    if (resp){
        console.log(resp);
    }
}

export async function testPostAufgabenbereich(){
    let resp = await postAufgabenbereich("TestAufgabenbereich1","dies ist ein Test-Aufgabenbereich");
    if (resp){
        console.log(resp);
    }
}

export async function testPostArtefakt(){
    let resp = await postArtefakt("TestArtefakt1","dies ist ein Test-Artefakt");
    if (resp){
        console.log(resp);
    }
}
