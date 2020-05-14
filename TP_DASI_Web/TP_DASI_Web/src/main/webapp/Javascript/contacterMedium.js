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

$(document)
    
    .ready(
        function getListeMediums() {

    console.log("==========================\n appel à contacterMedium (JS)\n=========================="); // LOG dans Console Javascript
    
    console.log("ID de la requete : "+"pas encore trouvé");  //
    var text = "";

    // bail inutile pour vérifier que tout va bien
    if (navigator.cookieEnabled === true) {
        text = "Cookies are enabled.";
    } else {
        text = "Cookies are not enabled.";
    }
    document.getElementById("test").innerHTML = text;


    // Appel AJAX
    $.ajax({
        url: './MainController',
        method: 'GET',
        data: {
            todo: 'getMediumById',
            id: GetURLParameter('id')   // à changer
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
            
            //document.getElementById("listeMediums").innerHTML = "Erreur... :(";
            $('#notification').html("Erreur de Connexion"); // Message pour le paragraphe de notification
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de contacterMedium");
    })
    .always( function () { // Fonction toujours appelée

    });
})
