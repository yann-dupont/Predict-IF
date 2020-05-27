/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import fr.insalyon.dasi.metier.service.Service;

/*
 * Source de la fonction GetURLParameter :
 * http://www.jquerybyexample.net/2012/06/get-url-parameters-using-jquery.html
 */
function GetURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}


$(document).ready( function nomMedium() {

    console.log("==========================\n appel à nomMedium (JS)\n=========================="); // LOG dans Console Javascript
    
    console.log("ID de la requete : "+GetURLParameter('id'));

    // Appel AJAX
    $.ajax({
        url: './MainController',
        method: 'GET',
        data: {
            todo: 'getMediumById',
            id: GetURLParameter('id')
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response',response); // LOG dans Console Javascript
        if (response.success) {
            
            console.log("GG WP Json bien recu");
            
            var htmlContent = response.denom;

            //document.getElementsByClassName("nomMedium").innerHTML = response.denom;
            console.log(document.getElementsByClassName("nomMedium"));
            
            for (var tag of document.getElementsByClassName("nomMedium")){
                console.log(tag);
                tag.innerHTML = response.medium.denom;
            }
            
        }
        else {
            alert("erreur du côté serveur pour nomMedium");
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Erreur ajax nomMedium',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de nomMedium");
    });
}); 


