package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public class GetProfilClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client client = (Client)request.getAttribute("client");
        List<Consultation> consults = (List<Consultation>)request.getAttribute("consults");
        
        JsonObject container = new JsonObject();
        
        Boolean success = (client != null);
        container.addProperty("success", success);
        
        if (client != null) {
            
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", client.getId());
            jsonClient.addProperty("nom", client.getNom());
            jsonClient.addProperty("prenom", client.getPrenom());
            jsonClient.addProperty("mail", client.getMail());
            jsonClient.addProperty("tel", client.getTel());
            jsonClient.addProperty("dateNaissance", client.getDateNaissance().toString());
            ProfilAstral profil = client.getProfil();
            jsonClient.addProperty("couleur", profil.getCouleur());
            jsonClient.addProperty("animal", profil.getAnimal());
            jsonClient.addProperty("zodiaque", profil.getZodiaque());
            jsonClient.addProperty("sgChinois", profil.getSgChinois());

            container.add("client", jsonClient);
            
            JsonArray liste = new JsonArray();
            Consultation consultTemp;
            for (int i = 0; i < consults.size(); ++i){           
                JsonObject jsonConsult = new JsonObject();
                consultTemp = consults.get(i);
                
                jsonConsult.addProperty("date", consultTemp.getDate().toString());
                Medium medium = consultTemp.getMedium();
                jsonConsult.addProperty("mediumId", medium.getId());
                jsonConsult.addProperty("mediumNom", medium.getDenom());
                
                // normalement pas nécessaire
                //jsonMedium.addProperty("genre", mediumTemp.getGenre());
                //jsonMedium.addProperty("statut", mediumTemp.getStatut());
                
                liste.add(jsonConsult);
            }
            container.add("consultations", liste);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
