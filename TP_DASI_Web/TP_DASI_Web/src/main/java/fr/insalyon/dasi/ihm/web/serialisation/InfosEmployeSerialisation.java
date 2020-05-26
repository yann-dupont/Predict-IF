/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author emman
 */
public class InfosEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject container = new JsonObject();
        
        container.addProperty("success", (Boolean)request.getAttribute("success"));
        
        if((Boolean)request.getAttribute("success")){
            Employe e = (Employe)request.getAttribute("employe");
            System.out.println("Serialisation pour la page de l'employe : "+e.toString());
            container.addProperty("prenom", e.getPrenom());
        
            if((Boolean)request.getAttribute("a_faire")){
                
                container.addProperty("a_faire", true);
                
                JsonObject consultJson = new JsonObject();
                
                // infos consultation
                Consultation consult = (Consultation)request.getAttribute("consultation");
                consultJson.addProperty("idConsult", consult.getId());
                // date
                Date date = (Date)request.getAttribute("date");
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
                String strDate = dateFormat.format(date);       
                consultJson.addProperty("date", strDate);
                
                // infos client
                Client client = (Client)request.getAttribute("client");
                JsonObject clientJson = new JsonObject();
                clientJson.addProperty("id", client.getId());
                clientJson.addProperty("prenom", client.getPrenom());
                clientJson.addProperty("nom", client.getNom());
                
                // medium
                JsonObject mediumJson = new JsonObject();
                Medium medium = (Medium)request.getAttribute("medium");
                mediumJson.addProperty("id", medium.getId());
                mediumJson.addProperty("denom", medium.getDenom());
                mediumJson.addProperty("type", medium.getType());
                
                // raccorder
                consultJson.add("client", clientJson);
                consultJson.add("medium", mediumJson);
                container.add("consultation", consultJson);
            }else{
                container.addProperty("a_faire", false);
            }
            // donnees pour les graphiques
            Map<String, Integer> classementMediums = (Map<String, Integer>)request.getAttribute("classementMediums");
            Set<String> keysMap = classementMediums.keySet();
            JsonArray liste = new JsonArray();
            Iterator<String> it = keysMap.iterator();
            while(it.hasNext()){
                String denom = it.next();
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("denom", denom);
                jsonMedium.addProperty("nombreConsults", classementMediums.get(denom));
                liste.add(jsonMedium);
            }

            container.add("listeStats", liste);
        }else{
            container.addProperty("cause", (String)request.getAttribute("cause"));
        }
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }
    
}
