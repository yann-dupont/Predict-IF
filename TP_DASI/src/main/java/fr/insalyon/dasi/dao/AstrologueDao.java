package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Astrologue;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class AstrologueDao {
    
    public void creer(Astrologue astrologue) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(astrologue);
    }
    
    public Astrologue chercherParId(Long astrologueId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Astrologue.class, astrologueId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Astrologue chercherParDenom(String astrologueDenom) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Astrologue> query = em.createQuery("SELECT a FROM Astrologue a WHERE a.denom = :denom", Astrologue.class);
        query.setParameter("denom", astrologueDenom); // correspond au paramètre ":denom" dans la requête
        List<Astrologue> astrologues = query.getResultList();
        Astrologue result = null;
        if (!astrologues.isEmpty()) {
            result = astrologues.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Astrologue> listerAstrologues() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Astrologue> query = em.createQuery("SELECT c FROM Astrologue c ORDER BY c.nom ASC, c.prenom ASC", Astrologue.class);
        return query.getResultList();
    }
    
    // modifier / supprimer  ... 
}
