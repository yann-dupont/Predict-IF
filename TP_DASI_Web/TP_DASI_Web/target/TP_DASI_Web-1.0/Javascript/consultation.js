/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function demarrer() {
    
    document.getElementById("boutonDemarrerTerminer").innerHTML = "Terminer la consultation";
    document.getElementById("boutonDemarrerTerminer").setAttribute("onClick", "terminer()");
}

function terminer() {
    window.location = "commentaire.html";
}
