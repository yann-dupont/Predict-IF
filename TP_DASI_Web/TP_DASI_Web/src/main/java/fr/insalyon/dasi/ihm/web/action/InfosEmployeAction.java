/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author emman
 */
public class InfosEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service s = new Service();
        Long id = Long.valueOf(-1);
        
        // Gestion de la Session: ici, enregistrer l'ID de l'Employe authentifié
        HttpSession session = request.getSession();
        id = (Long)session.getAttribute("idEmploye");
        
        if (id == null){
            request.setAttribute("success", false);
            request.setAttribute("cause", "session expiree");
            return;
        }
        System.out.println("ID Employe de la session : "+id.toString());
        
        Employe e = s.rechercherEmployeParId(id);
        
        if (e != null){
            request.setAttribute("success", true);
            
            request.setAttribute("employe", e);
            System.out.println("Employe trouve : "+e.toString());
            
            Consultation c = s.obtenirConsultationAFaire(e);
            System.out.println("Consultation a faire : ");
            System.out.print(c);
            request.setAttribute("consultation", c);
            
        }else{
            request.setAttribute("success", false);
            System.out.println("Employe non trouve pour l'id "+request.getAttribute("id"));
        }
    }
}
