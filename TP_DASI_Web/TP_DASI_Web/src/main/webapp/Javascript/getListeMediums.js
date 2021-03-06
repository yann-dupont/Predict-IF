/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import fr.insalyon.dasi.metier.service.Service;


$(document)
    
    .ready(
        function getListeMediums() {

//    console.log("==========================\nclic sur le bouton 'nos mediums', appel à getListeMediums (JS)\n=========================="); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './MainController',
        method: 'GET',
        data: {
            todo: 'getListeMediums'
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
//        console.log('Response',response); // LOG dans Console Javascript
        if (response.success) {

//            console.log("GG WP Json bien recu");

            var htmlContent = "<table id='tableMediums'>";

            for (var i = 0; i < response.liste.length; i++){

                var medium = response.liste[i];
                htmlContent += "<tr>";
                htmlContent += "<td>" + medium.denom;
                htmlContent += "<br/>" + "<span class='typeMedium'>";
                htmlContent += medium.type + "</span></td>";
                htmlContent += "<td class='presentation'>"+medium.presentation+"</td>";
                // htmlContent += "<td><a href='contactMedium.html?id="+ medium.id +"'>Contacter</a></td>";
                htmlContent += "<td><a onclick='contacterMedium("+ medium.id +")'>Contacter</a></td>";
                htmlContent += "</tr>";
            }

            htmlContent += "</table>";

            document.getElementById("listeMediums").innerHTML += htmlContent;

            document.getElementById("chargement").innerHTML = "";

        }
        else {

            document.getElementById("listeMediums").innerHTML = "Erreur... :(";
            $('#notification').html("Erreur de Connexion"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
//        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de getListeMediums");
    })
    .always( function () { // Fonction toujours appelée

    });
});


