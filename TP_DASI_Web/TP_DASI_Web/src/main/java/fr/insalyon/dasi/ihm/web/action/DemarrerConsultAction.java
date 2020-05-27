/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Yumahey
 */
public class DemarrerConsultAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request){
        System.out.println("DemarrerConsultAction bien appelé pour l'employe d'id "+request.getParameter("employeId"));

        Service s = new Service();
        long idEmploye = Long.parseLong(request.getParameter("employeId"));
        Employe e = s.rechercherEmployeParId(idEmploye);
        
        Consultation consult = s.obtenirConsultationAFaire(e);
        
        Boolean success = s.commencerConsultation(consult);

        request.setAttribute("success", success);
        
    }
}
