package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class GetListeMediumsAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();
        List<Medium> mediums = service.listerMediums();

        request.setAttribute("mediums", mediums);
        
    }
    
}
