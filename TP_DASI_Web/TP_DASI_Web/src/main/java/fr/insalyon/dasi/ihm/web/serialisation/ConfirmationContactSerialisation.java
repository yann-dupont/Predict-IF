package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConfirmationContactSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("ConfirmationContactSerialisation");
        
        
        JsonObject container = new JsonObject();
        container.addProperty("success", (Boolean)request.getAttribute("success"));
        
        if((Boolean)request.getAttribute("success")){       // medium dispo
            container.addProperty("url", "contactMedium.html?id="+request.getParameter("id"));
        }
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        //response.sendRedirect("contactMedium.html");
        out.close();
    }

}
