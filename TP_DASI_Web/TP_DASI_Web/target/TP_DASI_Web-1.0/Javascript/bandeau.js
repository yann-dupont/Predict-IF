/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import {getCookie} from "./Javascript/gestionCookies.js";

$(document).ready(
        function bandeau() {

    console.log("==========================\nchargement d'une page a bandeau, appel à bandeau (JS)\n=========================="); // LOG dans Console Javascript
    
    document.getElementById("menu").innerHTML += "<a id=logo style='color:White;font-size:500%;' href='index.html' title='Retour à la page d&apos;accueil'>Predicti'IF</a>";
    var userId = getCookie("user");
    if(userId==="")
    {
        document.getElementById("menu").innerHTML += "<div id=userWelcome style='color:White;font-size:200%;'>Pas connecté :(</div>";
    } else {
        document.getElementById("menu").innerHTML += "<div id=userWelcome style='color:White;font-size:200%;'>ID de l'utilisateur : " + userId + "</div>";
    }
});

