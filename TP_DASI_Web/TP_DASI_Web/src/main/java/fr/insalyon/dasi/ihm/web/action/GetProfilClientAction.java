package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class GetProfilClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        Long userId = Long.parseLong(request.getParameter("userId"));
        System.out.println("userId : " + userId);
        
        Service service = new Service();
        
        Client client = service.rechercherClientParId(userId);
        List<Consultation> consults = service.rechercherConsultsParClient(client);
        
        System.out.println("client : " + client);
        System.out.println("consults : " + consults);
        
        
        
        List<Medium> mediums = service.listerMediums();

        request.setAttribute("mediums", mediums);
        
    }
    
}
