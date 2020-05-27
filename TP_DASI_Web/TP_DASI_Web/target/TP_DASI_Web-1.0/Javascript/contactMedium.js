/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// appelée quand on clique sur "contacter"
function contacterMedium(id){
    
    if(confirm("Contacter ce médium ?")){
        
        var userId = getCookie("user");
//        console.log('ID user : '+userId);
        
        if(userId==="")
        {
            window.location = "connexion.html?prev=listeMediums.html";
        }else{  // utilisateur connecté
            // TODO : tester si c'est un employé, si oui refuser
//            console.log("Validation contact");

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
//                console.log("Done");
//                console.log("Appel AJAX pour contacter le medium réussi");
//                console.log('Response',response); // LOG dans Console Javascript

                if(response.success){
//                    console.log("Youpi le medium a été contacté");
                    window.location = response.url;
                }else{
                    alert("Désolé, ce medium n'est pas disponible pour le moment...\nRetentez votre chance plus tard !");
                }

            })
            .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
//                console.log('Error',error); // LOG dans Console Javascript
                alert("Erreur lors de l'appel AJAX de contacterMedium");
            });            
        }

    }else{      // "annuler" dans la boite de dialogue de confirmation
//        console.log("Annulé");
    }
}

