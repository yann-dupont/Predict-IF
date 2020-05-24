/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
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
        
            if((Consultation)request.getAttribute("consultation") != null){
                
                container.addProperty("a_faire", true);
            }else{
                container.addProperty("a_faire", false);
            }
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
