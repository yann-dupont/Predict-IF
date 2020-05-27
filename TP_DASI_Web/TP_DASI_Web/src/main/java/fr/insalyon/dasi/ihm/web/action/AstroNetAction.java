/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author emman
 */
public class AstroNetAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        
        Service s = new Service();
        Long userId = Long.parseLong(request.getParameter("userId"));
        System.out.println("userId employe : " + userId);
        
        Employe employe = s.rechercherEmployeParId(userId);
        Consultation consult = s.obtenirConsultationAFaire(employe);
        Client client = consult.getClient();
        
        int amour = Integer.parseInt(request.getParameter("amour"));
        int sante = Integer.parseInt(request.getParameter("sante"));
        int travail = Integer.parseInt(request.getParameter("travail"));
        
        System.out.println("Amour : " + Integer.toString(amour) + ", sante : " + Integer.toString(sante) + ", travail : " + Integer.toString(travail));
        
        List<String> res = s.obtenirPredictions(client, amour, sante, travail);
        
        request.setAttribute("amour", res.get(0));
        request.setAttribute("sante", res.get(1));
        request.setAttribute("travail", res.get(2));
        
        request.setAttribute("success", true); // par défaut : false
    }
}
