/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// TODO : mettre dans un autre JS séparé quand on saura importer correctement
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
    
    return null;
}


$(document).ready( function () {
    

    /*
     * Bind le bouton enter sur le champ mdp
     * avec le bouton connexion (source : W3Schools)
     */
    // Get the input field
    var input = document.getElementById("champ-password");
    console.log(input);

    // Execute a function when the user releases a key on the keyboard
    input.addEventListener("keyup", function(event) {
      // Number 13 is the "Enter" key on the keyboard
      if (event.keyCode === 13) {
        // Cancel the default action, if needed
        event.preventDefault();
        // Trigger the button element with a click
        document.getElementById("bouton-connexion").click();
      }
    }); 

    /*
     * Comportement du bouton connexion (ou enter sur champ mdp)
     */
    $('#bouton-connexion').on( 'click', function () { // Fonction appelée lors du clic sur le bouton

        console.log("clic sur le bouton de connexion"); // LOG dans Console Javascript
        $('#notification').html("Connexion..."); // Message pour le paragraphe de notification

        // Récupération de la valeur des champs du formulaire
        var champLogin = $('#champ-login').val();
        var champPassword = $('#champ-password').val();

        // Appel AJAX pour la connexion client
        $.ajax({
            url: './MainController',
            method: 'POST',
            data: {
                todo: 'connecterClient',
                login: champLogin,
                password: champPassword
            },
            dataType: 'json'
        })
        .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
            console.log('Response : ',response); // LOG dans Console Javascript
            if (response.connexion) {
                //$('#notification').html("Connexion OK");  // Message pour le paragraphe de notification
                // TODO: afficher les informations du client dans la notification
                // Exemple: Connexion de Ada Lovelace (ID 1)
                var client = response.client;
                $('#notification').html("Connexion du client " + client.prenom + " " + client.nom + " (ID " + client.id + ")");  // Message pour le paragraphe de notification
                document.cookie = "user=" + client.id;
                
                console.log("Allo ? Le bouton connexion ??");
                
                var redir = GetURLParameter("prev");
                console.log("Redir : ", redir);
                if(redir === null){
                    window.location = "index.html";  // test -> mettre sur index
                }else{
                    window.location = redir;
                }
                return;
            }
            else {
                $('#notification').html("Erreur de Connexion"); // Message pour le paragraphe de notification
            }
        })
        .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
            console.log('Error',error); // LOG dans Console Javascript
            alert("Erreur lors de l'appel AJAX");
        })
        .always( function () { // Fonction toujours appelée

        });

        // Appel AJAX pour la connexion employé
        $.ajax({
            url: './MainController',
            method: 'POST',
            data: {
                todo: 'connecterEmploye',
                login: champLogin,
                password: champPassword
            },
            dataType: 'json'
        })
        .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
            console.log('Response',response); // LOG dans Console Javascript
            if (response.connexion) {
                //$('#notification').html("Connexion OK");  // Message pour le paragraphe de notification
                // TODO: afficher les informations du client dans la notification
                // Exemple: Connexion de Ada Lovelace (ID 1)
                var employe = response.employe;
                $('#notification').html("Connexion de l'employé " + employe.prenom + " " + employe.nom + " (ID " + employe.id + ")");  // Message pour le paragraphe de notification
                document.cookie = "user=" + employe.id;
                
                var redir = GetURLParameter("prev");
                console.log("Redir : ", redir);
                if(redir === null){
                    window.location = "index.html";
                }else{
                    window.location = redir;
                }
                
            }
            else {
                $('#notification').html("Erreur de Connexion"); // Message pour le paragraphe de notification
            }
        })
        .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
            console.log('Error',error); // LOG dans Console Javascript
            alert("Erreur lors de l'appel AJAX1");
        })
        .always( function () { // Fonction toujours appelée

        });

    });
});