/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document)
    .ready(
        function consultation() {
    
    console.log("==========================\nchargement page consultation : consultation(JS)\n=========================="); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './MainController',
        method: 'GET',
        data: {
            todo: 'getInfosConsultation',
            userId: getCookie("user")
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.success) {

            console.log("GG WP Json bien recu");

            // informations principales
            document.getElementById("clientNom").innerHTML += response.client.nom;
            document.getElementById("clientTel").innerHTML += response.client.tel;
            document.getElementById("clientPrenom").innerHTML += response.client.prenom;
            document.getElementById("clientMail").innerHTML += response.client.mail;

            // profil astral
            document.getElementById("clientZodiaque").innerHTML += response.client.zodiaque;
            document.getElementById("clientCouleur").innerHTML += response.client.couleur;
            document.getElementById("clientSgChinois").innerHTML += response.client.sgChinois;
            document.getElementById("clientAnimal").innerHTML += response.client.animal;
            
            // historique
            var htmlContent = "<table id='tableConsults' style='font-size:200%;'>";
            for (var i = 0; i < response.consultations.length; i++){

                var consult = response.consultations[i];
                htmlContent += "<tr>";
                htmlContent += "<td>"+consult.mediumNom+"</td>";
                htmlContent += "<td class='presentation'>"+consult.date+"</td>";
                // htmlContent += "<td><a href='contactMedium.html?id="+ medium.id +"'>Contacter</a></td>";
                htmlContent += "<td>"+consult.commentaire+"</td>";
                htmlContent += "</tr>";
            }

            htmlContent += "</table>";

            document.getElementById("historique").innerHTML += htmlContent;

            document.getElementById("chargement").innerHTML = "";

        }
        else {

            document.getElementById("historique").innerHTML = "Erreur... :(";
            $('#notification').html("Erreur de Connexion"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de getInfosConsultation");
    })
    .always( function () { // Fonction toujours appelée

    });
});


function demarrer() {
    
    document.getElementById("boutonDemarrerTerminer").innerHTML = "Terminer la consultation";
    document.getElementById("boutonDemarrerTerminer").setAttribute("onClick", "terminer()");
    document.getElementById("IfAstroNet").style.display = "block";
}

function terminer() {
    window.location = "commentaire.html";
}

function demanderAide() {
    
        // Appel AJAX
    $.ajax({
        url: './MainController',
        method: 'GET',
        data: {
            todo: 'astroNet',
            userId: getCookie("user"),
            amour: $("input[name=amour]:checked").val(),
            sante: $("input[name=sante]:checked").val(),
            travail: $("input[name=travail]:checked").val()
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.success) {

            console.log("GG WP Json bien recu");
            document.getElementById("aides").style.display = "";
            
            document.getElementById("aideAmour").innerHTML = response.amour;
            document.getElementById("aideSante").innerHTML = response.sante;
            document.getElementById("aideTravail").innerHTML = response.travail;


        }
        else {

            alert("Erreur... :(");
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de astronet");
    });
    
}