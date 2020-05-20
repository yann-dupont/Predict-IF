/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import fr.insalyon.dasi.metier.service.Service;


$(document)
    .ready(
        function getProfilClient() {

    console.log("==========================\nchargement profil client : getProfilClient (JS)\n=========================="); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './MainController',
        method: 'GET',
        data: {
            todo: 'getProfilClient',
            userId: getCookie("user")
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.success) {

            console.log("GG WP Json bien recu");

            var htmlContent = "<table id='tableInfos'>";
            htmlContent += "<tr>";
            htmlContent += "<td>Nom</td>";
            htmlContent += "<td>"+response.client.nom+"</td>";
            htmlContent += "<td>Téléphone</td>";
            htmlContent += "<td>"+response.client.tel+"</td>";
            htmlContent += "</tr>";
            
            htmlContent += "<div class='gauche'>" + response.client.nom + "<div/>";
            document.getElementById("informations").innerHTML += htmlContent;
            
            htmlContent = "<table id='tableConsults'>";
            for (var i = 0; i < response.consultations.length; i++){

                var consult = response.consultations[i];
                htmlContent += "<tr>";
                htmlContent += "<td>"+consult.mediumNom+"</td>";
                htmlContent += "<td class='presentation'>"+consult.date+"</td>";
                // htmlContent += "<td><a href='contactMedium.html?id="+ medium.id +"'>Contacter</a></td>";
                htmlContent += "<td><a onclick='contacterMedium("+ consult.mediumId +")'>Contacter</a></td>";
                htmlContent += "</tr>";
            }

            htmlContent += "</table>";

            document.getElementById("listeConsults").innerHTML += htmlContent;

            document.getElementById("chargement").innerHTML = "";

        }
        else {

            document.getElementById("listeConsults").innerHTML = "Erreur... :(";
            $('#notification').html("Erreur de Connexion"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de getListeMediums");
    })
    .always( function () { // Fonction toujours appelée

    });
});


