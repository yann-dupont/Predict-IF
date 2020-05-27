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
import java.util.Map;
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
        

        id = Long.parseLong(request.getParameter("id"));
        
        if (id == null){
            request.setAttribute("success", false);
            request.setAttribute("cause", "session expiree");
            return;
        }
//        System.out.println("ID Employe de la session : "+id.toString());
        
        Employe e = s.rechercherEmployeParId(id);
        
        if (e != null){
            request.setAttribute("success", true);
            
            request.setAttribute("employe", e);
//            System.out.println("Employe trouve : "+e.toString());
            
            Consultation consult = s.obtenirConsultationAFaire(e);
            
            if (consult != null){

                request.setAttribute("a_faire", true);
                
//                System.out.print("Consultation a faire : ");
//                System.out.println(consult);
                request.setAttribute("consultation", consult);
                
                // recup client & medium de la consult
                Client client = consult.getClient();
                Medium medium = consult.getMedium();
                
                if (client == null){
                    request.setAttribute("success", false);
                    request.setAttribute("cause", "client non trouve");
                }else if (medium == null){
                    request.setAttribute("success", false);
                    request.setAttribute("cause", "medium non trouve");
                }else{
                    request.setAttribute("client", client);
                    request.setAttribute("medium", medium);
                }
                
                // date
                request.setAttribute("date", consult.getDate());
                
            }else{  // pas de consult
                request.setAttribute("a_faire", false);
            }
            
            Map<String, Integer> classementMediums = s.obtenirClassementMediums();
            request.setAttribute("classementMediums", classementMediums);
            
        }else{
            request.setAttribute("success", false);
            request.setAttribute("cause", "employe non trouve");
//            System.out.println("Employe non trouve pour l'id "+request.getAttribute("id"));
        }
    }
}
