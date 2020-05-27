/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Load google charts
google.charts.load('current', {'packages':['corechart']});
//google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart(data, titre) {
    // Optional; add a title and set the width and height of the chart
    var options = {'title': titre, 'width': 750, 'height': 400};

    // Display the chart inside the <div> element with id="piechart"
    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    chart.draw(google.visualization.arrayToDataTable(data), options);
}

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
            
            if(response.a_faire){
                
                var contenu = "";
                contenu += "Consultation avec ";
                contenu += response.consultation.client.prenom;
                contenu += " ";
                contenu += response.consultation.client.nom;
                contenu += " en attente (";
                contenu += response.consultation.date;
                contenu += ")";
                contenu += "<br/>Medium à incarner : ";
                contenu += response.consultation.medium.denom;
                contenu += " (";
                contenu += response.consultation.medium.type;
                contenu += ")<br/>";
                contenu += "<a style='font-size:200%;' href='consultation.html' title='Consulter les informations du client avant consultation'>Accéder à la consultation</a>";
                
                document.getElementById("a_faire").innerHTML = contenu;
                
            }else{
                document.getElementById("a_faire").innerHTML = "Aucune consultation en attente";
            }
            
            // graphiques
            var data = [["medium", "nombre de consultations"]];
            for(let med of response.listeStats) {
                data.push([med.denom, med.nombreConsults]);
            }
            drawChart(data, "Médiums les plus consultés");
        }
        else {

            if(response.cause === "session expiree"){
                alert("Session expirée, merci de vous reconnecter");
                window.location = "connexion.html?prev=employe.html";
            }else{
                alert("Erreur... :(");
                document.getElementById("a_faire").innerHTML = "Erreur :(";
            }
            
        }
    })
    .fail( function (error) { // Fonction appelée en cas d'erreur lors de l'appel AJAX
        console.log('Error',error); // LOG dans Console Javascript
        alert("Erreur lors de l'appel AJAX de infosEmploye");
    });
});