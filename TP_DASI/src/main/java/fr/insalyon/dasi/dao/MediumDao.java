package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class MediumDao {
    
    /*public void creer(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(medium);
    }*/
    
    public Medium chercherParId(Long mediumId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, mediumId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Medium chercherParDenom(String mediumDenom) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m WHERE m.denom = :denom", Medium.class);
        query.setParameter("denom", mediumDenom); // correspond au paramètre ":denom" dans la requête
        List<Medium> mediums = query.getResultList();
        Medium result = null;
        if (!mediums.isEmpty()) {
            result = mediums.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m ORDER BY m.denom ASC", Medium.class);
        return query.getResultList();
    }
    
    // modifier / supprimer  ... 
}
