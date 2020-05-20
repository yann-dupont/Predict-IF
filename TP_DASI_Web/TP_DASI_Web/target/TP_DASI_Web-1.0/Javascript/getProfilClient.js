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

// appelée quand on clique sur le bouton "modifier le profil"
function modeModif(){
    document.getElementById("boutonModifProfil").innerHTML = "Valider les modifications";
    document.getElementById("clientNom").innerHTML ='<input type="text" size="40" value="' + document.getElementById("clientNom").innerHTML + '"></input>';
    document.getElementById("clientTel").innerHTML ='<input type="text" size="40" value="' + document.getElementById("clientTel").innerHTML + '"></input>';
    document.getElementById("clientPrenom").innerHTML ='<input type="text" size="40" value="' + document.getElementById("clientPrenom").innerHTML + '"></input>';
    document.getElementById("clientMail").innerHTML ='<input type="text" size="40" value="' + document.getElementById("clientMail").innerHTML + '"></input>';
    document.getElementById("boutonModifProfil").setAttribute("onClick", "validerModif()");
}

// appelée quand on clique sur le bouton "valider les modifications"
function validerModif(){
    if(confirm("Valider les modifications ?")){
        document.getElementById("boutonModifProfil").innerHTML = "Modifier les informations";
        document.getElementById("clientNom").innerHTML = document.getElementById("clientNom").firstChild.value;
        document.getElementById("clientTel").innerHTML = document.getElementById("clientTel").firstChild.value;
        document.getElementById("clientPrenom").innerHTML = document.getElementById("clientPrenom").firstChild.value;
        document.getElementById("clientMail").innerHTML = document.getElementById("clientMail").firstChild.value;
        document.getElementById("boutonModifProfil").setAttribute("onClick", "modeModif()");
        
        /*var userId = getCookie("user");
        console.log('ID user : '+userId);
        
        if(userId==="")
        {
            window.location = "connexion.html?prev=listeMediums.html";
        }else{  // utilisateur connecté
            // TODO : tester si c'est un employé, si oui refuser
            console.log("Validation contact");

            // Appel AJAX
            $.ajax({

                url: './MainController',
                method: 'GET',
                data: {
                    todo: 'contacterMedium',
                    id: id,
                    idClient: getCookie("user")
                },
                dataType: 'json'
            })
            .done( function (response) { // Fonction appelée en cas d'appel AJAX réussi
                console.log("Done");
                console.log("Appel AJAX pour contacter le medium réussi");
                console.log('Response',response); // LOG dans Console Javascript

                if(response.success){
                    console.log("Youpi le medium a été contacté");
                    window.location = response.url;
                }else{
                    alert("Désolé, ce medium n'est pas disponible pour le moment...\nRetentez votre chance plus tard !");
                }

            })
            .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
                console.log('Error',error); // LOG dans Console Javascript
                alert("Erreur lors de l'appel AJAX de contacterMedium");
            })
            .always( function () { // Fonction toujours appelée
                console.log("Always");
            });            
        }*/

    }else{      // "annuler" dans la boite de dialogue de confirmation
        console.log("Annulé");
    }
}

