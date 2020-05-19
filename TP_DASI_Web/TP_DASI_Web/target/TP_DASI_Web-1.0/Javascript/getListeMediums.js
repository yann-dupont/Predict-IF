/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import fr.insalyon.dasi.metier.service.Service;


$(document)
    
    .ready(
        function getListeMediums() {

    console.log("==========================\nclic sur le bouton 'nos mediums', appel à getListeMediums (JS)\n=========================="); // LOG dans Console Javascript
    var text = "";

    // bail inutile pour vérifier que tout va bien
    if (navigator.cookieEnabled === true) {
        text = "Cookies are enabled.";
    } else {
        text = "Cookies are not enabled.";
    }
    document.getElementById("demo").innerHTML = text;


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
        console.log('Response',response); // LOG dans Console Javascript
        if (response.success) {
            
            console.log("GG WP Json bien recu");
            
            var htmlContent = "<table id='tableMediums'>";
            
            for (var i = 0; i < response.liste.length; i++){
                
                var medium = response.liste[i];
                htmlContent += "<tr>";
                htmlContent += "<td>"+medium.denom+"</td>";
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
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de getListeMediums");
    })
    .always( function () { // Fonction toujours appelée

    });
});


// appelée quand on clique sur "contacter"
function contacterMedium(id){
    
    if(confirm("Contacter ce médium ?")){
        
        var userId = getCookie("user");
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
        }

    }else{      // "annuler" dans la boite de dialogue de confirmation
        console.log("Annulé");
    }
}


