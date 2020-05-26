package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class GetInfosConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        Long userId = Long.parseLong(request.getParameter("userId"));
        System.out.println("userId : " + userId);
        
        Service service = new Service();
        
        //Client client = service.rechercherClientParId(userId);
        Employe employe = service.rechercherEmployeParId(userId);
        Consultation consult = service.obtenirConsultationAFaire(employe);
        Client client = consult.getClient();
        List<Consultation> consults = service.rechercherConsultsParClient(client);
        
        System.out.println("client : " + client);
        System.out.println("consults : " + consults);

        request.setAttribute("client", client);
        request.setAttribute("consults", consults);
        
    }
}
