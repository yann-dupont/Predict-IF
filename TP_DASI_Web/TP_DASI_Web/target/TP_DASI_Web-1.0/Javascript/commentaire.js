/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready( function () {
    
    var comm;
    
    /*
     * Comportement du bouton confirmer
     */
    $('#confirmer').on( 'click', function () { // Fonction appelée lors du clic sur le bouton

        comm = $('#input').val();
        console.log(comm);
        
    });
    
     // Appel AJAX pour l'inscription client
    $.ajax({
        url: './MainController',
        method: 'POST',
        data: {
            todo: 'commentaire',
            text: comm
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi

        console.log('Response : ',response); // LOG dans Console Javascript
        if (response.success) {

//            document.cookie = "user=" + response.userId;
            alert("Commentaire enregistré !");

//            var redir = GetURLParameter("prev");
//            console.log("Redir : ", redir);
//            if(redir === null){
//                window.location = "index.html";
//            }else{
//                window.location = redir;
//            }
//            return;
        }
        else {
            alert("Erreur :("); // Message pour le paragraphe de notification
        }
    })
    .fail( function (xhr, status, error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX

        console.log(xhr.responseText);
        alert("Erreur lors de l'appel AJAX");
    });
    
});