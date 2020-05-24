/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document)
    
    .ready(
        function infosEmploye() {

    console.log("==========================\n appel à infosEmploye (JS)\n=========================="); // LOG dans Console Javascript

    // Appel AJAX
    $.ajax({
        url: './MainController',
        method: 'GET',
        data: {
            todo: 'infosEmploye'
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.success) {

            console.log("GG WP Json bien recu");

                for (var tag of document.getElementsByClassName("nomEmploye")){
                    console.log(tag);
                    tag.innerHTML = response.prenom;
                }
        }
        else {

            alert("Erreur... :(");
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de infosEmploye");
    });
});