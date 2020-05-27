/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author emman
 */
public class AstroNetSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        
        JsonObject container = new JsonObject();
        
        container.addProperty("success", (Boolean)request.getAttribute("success"));
        
        container.addProperty("amour", (String)request.getAttribute("amour"));
        container.addProperty("sante", (String)request.getAttribute("sante"));
        container.addProperty("travail", (String)request.getAttribute("travail"));
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
        System.out.println("Serialisation effectuee");
    }
        
}
