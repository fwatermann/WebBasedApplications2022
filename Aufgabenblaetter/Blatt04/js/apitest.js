import { postProjekt } from "./api.js";

export async function testPostProject(){
    let resp = await postProjekt("TestProjekt1","dies ist ein Testprojekt","dies ist ein Logo",0);
    if (resp){
        console.log(resp);
    }
}

