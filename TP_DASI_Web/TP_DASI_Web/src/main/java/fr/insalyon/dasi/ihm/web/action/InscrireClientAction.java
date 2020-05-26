package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author DASI Team
 */
public class InscrireClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        int jour = Integer.parseInt(request.getParameter("jour"));
        int mois = Integer.parseInt(request.getParameter("mois"));
        int annee = Integer.parseInt(request.getParameter("annee"));
        
        System.out.println("Jour, mois, annee : "+jour+mois+annee);

        Client c = new Client(nom, prenom, mail, password, tel, jour, mois, annee);
        Service service = new Service();
        Long res = service.inscrireClient(c);

        if (res != null) {
            request.setAttribute("success", true);
            request.setAttribute("userId", c.getId());
            System.out.println("Succès InscrireClientAction");
        }
        else {
            request.setAttribute("success", false);
            System.out.println("Echec InscrireClientAction... :'(");
        }
    }
    
}
