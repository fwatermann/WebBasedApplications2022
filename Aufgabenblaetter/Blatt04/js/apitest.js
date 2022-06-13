import { postProjekt } from "./api.js";

async function testPostProject(){
    let resp = postProjekt("TestProjekt1","dies ist ein Testprojekt","dies ist ein Logo",0);
    if (resp){
        console.log(resp);
    }
}

