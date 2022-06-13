import { postProjekt,postAufgabenbereich, postArtefakt, getProjekte, getProjektById, getAufgabenbereiche, getAllAufgabenbereiche, getArtefakte, getAllArtefakte, setArbeitszeit } from "./api.js";
const PROJEKTID = "93f3fc40-1b87-433a-b5c8-011eb27b3545";
const ARTEFAKTID = "138c403e-9860-4f75-9872-825ecce6e6d1";

export async function doPostTests(){
    await testPostProject();
    await testPostAufgabenbereich();
    await testPostArtefakt();
}

export async function doGetTestByProjectID(){
    await testGetProjektById();
    await testGetAufgabenbereiche();
    await testGetArtefakte();
}

export async function doGetTestAll(){
    await testGetProjekte();
    await testGetAllAufgabenbereiche();
    await testGetAllArtefakte();
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

export async function testGetProjekte(){
    let resp = await getProjekte();
    if(resp){
        console.log(resp);
    }
}

export async function testGetProjektById(){
    let resp = await getProjektById(PROJEKTID);
    if(resp){
        console.log(resp);
    }
} 

export async function testGetAufgabenbereiche(){
    let resp = await getAufgabenbereiche(PROJEKTID);
    if(resp){
        console.log(resp);
    }
}

export async function testGetArtefakte(){
    let resp = await getArtefakte(PROJEKTID);
    if(resp){
        console.log(resp);
    }
}

export async function testGetAllArtefakte(){
    let resp = await getAllArtefakte();
    if(resp){
        console.log(resp);
    }
}

export async function testGetAllAufgabenbereiche(){
    let resp = await getAllAufgabenbereiche();
    if(resp){
        console.log(resp);
    }
}

export async function testSetArbeitsZeit(){
    let resp = await setArbeitszeit(PROJEKTID,ARTEFAKTID,122);
    if(resp){
        console.log(resp);
    }
}