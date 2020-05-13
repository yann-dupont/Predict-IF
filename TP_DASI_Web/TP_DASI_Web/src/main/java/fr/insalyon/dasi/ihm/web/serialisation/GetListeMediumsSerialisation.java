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

/**
 *
 * @author DASI Team
 */
public class GetListeMediumsSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> mediums = (List<Medium>)request.getAttribute("mediums");
        
        JsonObject container = new JsonObject();

        Boolean success = (mediums != null);
        container.addProperty("connexion", success);
        container.addProperty("success", success);

        if (mediums != null) {
            
            JsonArray liste = new JsonArray();
            
            Medium mediumTemp;
            for (int i = 0; i < mediums.size(); ++i){           
                JsonObject jsonMedium = new JsonObject();
                mediumTemp = mediums.get(i);
                
                jsonMedium.addProperty("id", mediumTemp.getId());
                jsonMedium.addProperty("denom", mediumTemp.getDenom());
                jsonMedium.addProperty("presentation", mediumTemp.getPresentation());
                //jsonMedium.addProperty("genre", mediumTemp.getGenre());
                //jsonMedium.addProperty("statut", mediumTemp.getStatut());
                
                liste.add(jsonMedium);
            }

            container.add("liste", liste);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
