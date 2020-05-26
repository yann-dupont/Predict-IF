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


function confirmerInscr(){

    console.log("clic sur le bouton de confirmation"); // LOG dans Console Javascript

    // Récupération de la valeur des champs du formulaire
    var nom = $('#nom').val();
    var prenom = $('#prenom').val();
    var mail = $('#mail').val();
    var mdp = $('#password').val();
    var tel = $('#tel').val();
    var jour = $('#ddn').val().substring(8,10);
    var mois = $('#ddn').val().substring(5,7);
    var annee = $('#ddn').val().substring(0,4);

    console.log("jour, mois, année : ",jour, mois, annee);

    // Appel AJAX pour l'inscription client
    $.ajax({
        url: './MainController',
        method: 'POST',
        data: {
            todo: 'inscrireClient',
            nom: nom,
            prenom: prenom,
            mail: mail,
            password: mdp,
            tel: tel,
            jour: jour,
            mois: mois,
            annee: annee
        },
        dataType: 'json'
    })
    .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
        console.log('Response : ',response); // LOG dans Console Javascript
        if (response.success) {

            document.cookie = "user=" + response.userId;
            console.log("Inscription reussie !");
            alert("Inscription terminée !");

            var redir = GetURLParameter("prev");
            console.log("Redir : ", redir);
            if(redir === null){
                window.location = "index.html";
            }else{
                window.location = redir;
            }
            return;
        }
        else {
            alert("Erreur d'inscription :("); // Message pour le paragraphe de notification
        }
    })
    .fail( function (xhr, status, error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX

        console.log(xhr.responseText);
        alert("Erreur lors de l'appel AJAX");
        document.getElementById("boutonConfirmer").innerHTML = "Erreur :/";
    });
}
