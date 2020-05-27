/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import {getCookie} from "./Javascript/gestionCookies.js";

$(document).ready(
        function bandeau() {

//    console.log("==========================\nchargement d'une page a bandeau, appel à bandeau (JS)\n=========================="); // LOG dans Console Javascript
    
    document.getElementById("menu").innerHTML += "<a id=logo style='color:White;font-size:500%;' href='index.html' title='Retour à la page d&apos;accueil'>Predicti'IF</a>";
    var userId = getCookie("user");
    if(userId === "")
    {
        // aucune session connectée
        var redir = window.location.toString().split('/')
        redir = redir[redir.length - 1];
        //document.getElementById("menu").innerHTML += "<div id=userWelcome style='color:White;font-size:200%;'>Pas connecté :(</div>";
        document.getElementById("menu").innerHTML += "<a id=boutonBandeau1 class=bouton style='font-size:200%;' href='connexion.html?prev="+redir+"' title='Se connecter'>Connexion</a>";
        document.getElementById("menu").innerHTML += "<a id=boutonBandeau2 class=bouton style='font-size:200%;' href='inscription.html' title='S&apos;inscrire'>Inscription</a>";
    } else {
        // il y a utilisateur connecté dans une session
        var userPrenom = getCookie("prenom");
        document.getElementById("menu").innerHTML += "<div id=userWelcome style='color:White;font-size:200%;' >Bonjour " + userPrenom + " !</div>";
        var userPosition = getCookie("position");
        if(userPosition === "client")
        {
            document.getElementById("menu").innerHTML += "<a id=boutonBandeau1 class=bouton style='font-size:200%;' href='profilClient.html' title='Accéder à mon profil (informations, profil astral, historique, ...)'>Profil</a>";
        } else {
            document.getElementById("menu").innerHTML += "<a id=boutonBandeau1 class=bouton style='font-size:200%;' href='employe.html' title='Accéder à mon dashboard (consultations, statistiques, ...)'>Dashboard</a>";
        }
        document.getElementById("menu").innerHTML += "<a id=boutonDeco class=bouton style='font-size:200%;' href='index.html' title='Se déconnecter de mon compte'>Déconnexion</a>";
    }
});


$(document).ready( function () {
    $('#boutonDeco').on( 'click', function () {
        document.cookie = "user=";
        window.location = "index.html";
    });
});
