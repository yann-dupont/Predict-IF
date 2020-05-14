package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Genre;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.util.AstroTest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) {

        // TODO : Pensez à créer une unité de persistance "DASI-PU" et à vérifier son nom dans la classe JpaUtil
        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();

        // Clients
        initialiserClients();            // Question 3
        /*testerInscriptionClient();       // Question 4 & 5
        testerInscriptionClientAvecDate();
        testerRechercheClient();         // Question 6
        testerListeClients();            // Question 7
        testerAuthentificationClient();  // Question 8*/
        //saisirInscriptionClient();       // Question 9
        //saisirRechercheClient();
        
        // Employes
        initialiserEmployes();
        /*testerInscriptionEmployes();
        testerRechercheEmploye();
        testerListeEmployes();
        testerAuthentificationEmploye();*/
        
        // Mediums
        testerInscriptionSpirites();
        testerInscriptionCartomanciens();
        testerInscriptionAstrologues();
        testerRechercheMedium();
        testerRechercheMediumParDenom();
        testerListeMediums();
        
        
        // ProfilAstral
        /*testerProfilAstro();
        testerPrediction();*/
        
        // Consultation
        testerCreationConsultation();
        testerObtenirCommentairesClient();
        testerContacterMedium();

        JpaUtil.destroy();
    }

    // Client
    
    public static void init(){
        JpaUtil.init();
    }
    
    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }

    public static void initialiserClients() {
        
        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        Client ada = new Client("Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012");
        Client blaise = new Client("Pascal", "Blaise", "blaise.pascal@insa-lyon.fr", "Blaise1906");
        Client fred = new Client("Fotiadu", "Frédéric", "frederic.fotiadu@insa-lyon.fr", "INSA-Forever");
        
        System.out.println();
        System.out.println("** Clients avant persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(ada);
            em.persist(blaise);
            em.persist(fred);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserClients()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Clients après persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();
    }

    public static void testerInscriptionClient() {
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client claude = new Client("Chappe", "Claude", "claude.chappe@insa-lyon.fr", "HaCKeR");
        Long idClaude = service.inscrireClient(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(claude);

        Client hedy = new Client("Lamarr", "Hedy", "hlamarr@insa-lyon.fr", "WiFi");
        Long idHedy = service.inscrireClient(hedy);
        if (idHedy != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedy);

        Client hedwig = new Client("Lamarr", "Hedwig Eva Maria", "hlamarr@insa-lyon.fr", "WiFi");
        Long idHedwig = service.inscrireClient(hedwig);
        if (idHedwig != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedwig);
    }

    public static void testerRechercheClient() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }

    public static void testerListeClients() {
        
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();
        
        Service service = new Service();
        List<Client> listeClients = service.listerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            for (Client client : listeClients) {
                afficherClient(client);
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationClient() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client client;
        String mail;
        String motDePasse;

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada1012";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada2020";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "etudiant.fictif@insa-lyon.fr";
        motDePasse = "********";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }

    public static void saisirInscriptionClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE INSCRIPTION **");
        System.out.println("**************************");
        System.out.println();

        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");

        Client client = new Client(nom, prenom, mail, motDePasse);
        Long idClient = service.inscrireClient(client);

        if (idClient != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(client);

    }

    public static void saisirRechercheClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("*********************");
        System.out.println("** MENU INTERACTIF **");
        System.out.println("*********************");
        System.out.println();

        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** RECHERCHE de CLIENTS **");
        System.out.println("**************************");
        System.out.println();
        System.out.println();
        System.out.println("** Recherche par Identifiant:");
        System.out.println();

        Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        while (idClient != 0) {
            Client client = service.rechercherClientParId(idClient.longValue());
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client #" + idClient + " non-trouvé");
            }
            System.out.println();
            idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("** AUTHENTIFICATION de CLIENT **");
        System.out.println("********************************");
        System.out.println();
        System.out.println();
        System.out.println("** Authentifier Client:");
        System.out.println();

        String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

        while (!clientMail.equals("0")) {
            String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
            Client client = service.authentifierClient(clientMail, clientMotDePasse);
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client non-authentifié");
            }
            clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("*****************");
        System.out.println("** AU REVOIR ! **");
        System.out.println("*****************");
        System.out.println();

    }
    
    
    // tests perso
    
    public static void testerInscriptionClientAvecDate() {
        System.out.println();
        System.out.println("**** testerInscriptionClientAvecDate() ****");
        System.out.println();
        
        Service service = new Service();
        Client lee = new Client("Sin", "Lee", "lee.sin@bronze.fr", "insec", "06 60 06 60 06", 6, 6, 1500);
        Long idLee = service.inscrireClient(lee);
        if (idLee != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(lee);
    }
    
    // Employe
    
    public static void afficherEmploye(Employe employe) {
        System.out.println("-> " + employe);
    }
    
    public static void initialiserEmployes() {
        
        System.out.println();
        System.out.println("**** initialiserEmployes() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        Employe jeanf = new Employe("SUPERMAN", "Jean-Farouk", "jfarouk.superman@predictif.fr", "123mdp", "06 12 34 56 78", Genre.H);
        Employe raph = new Employe("BARDANE", "Raphael", "raphael.bardane@predictif.fr", "jailaclasse", "06 77 77 77 77", Genre.H);
        Employe mamieg = new Employe("GOURGETTE", "Mamie", "mamie.gourgette@predictif.fr", "miammiamdu67", "06 67 67 67 67", Genre.F);
        
        System.out.println();
        System.out.println("** Employes avant persistance: ");
        afficherEmploye(jeanf);
        afficherEmploye(raph);
        afficherEmploye(mamieg);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(jeanf);
            em.persist(raph);
            em.persist(mamieg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserEmployes()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Employes après persistance: ");
        afficherEmploye(jeanf);
        afficherEmploye(raph);
        afficherEmploye(mamieg);
        System.out.println();
    }

    public static void testerInscriptionEmployes() {
        System.out.println();
        System.out.println("**** testerInscriptionEmployes() ****");
        System.out.println();
        
        Service service = new Service();
        Employe camille = new Employe("MARTIN", "Camille", "camille.martin@predictif.fr", "ilovearnaque", "06 12 34 56 78", Genre.F);
        Long idCamille = service.inscrireEmploye(camille);
        if (idCamille != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherEmploye(camille);
        
        Employe camille2 = new Employe("MARTIN2", "Camille2", "camille.martin@predictif.fr", "ilovearnaque", "06 87 65 43 21", Genre.F);
        Long idCamille2 = service.inscrireEmploye(camille2);
        if (idCamille2 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherEmploye(camille2);
    }

    public static void testerRechercheEmploye() {
        
        System.out.println();
        System.out.println("**** testerRechercheEmploye() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Employe employe;

        id = 1;
        System.out.println("** Recherche du Employe #" + id);
        employe = service.rechercherEmployeParId(id);
        if (employe != null) {
            afficherEmploye(employe);
        } else {
            System.out.println("=> Employe non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Employe #" + id);
        employe = service.rechercherEmployeParId(id);
        if (employe != null) {
            afficherEmploye(employe);
        } else {
            System.out.println("=> Employe non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Employe #" + id);
        employe = service.rechercherEmployeParId(id);
        if (employe != null) {
            afficherEmploye(employe);
        } else {
            System.out.println("=> Employe #" + id + " non-trouvé");
        }
    }

    public static void testerListeEmployes() {
        
        System.out.println();
        System.out.println("**** testerListeEmployes() ****");
        System.out.println();
        
        Service service = new Service();
        List<Employe> listeEmployes = service.listerEmployes();
        System.out.println("*** Liste des Employes");
        if (listeEmployes != null) {
            for (Employe employe : listeEmployes) {
                afficherEmploye(employe);
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationEmploye() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationEmploye() ****");
        System.out.println();
        
        Service service = new Service();
        Employe employe;
        String mail;
        String motDePasse;

        mail = "mamie.gourgette@predictif.fr";
        motDePasse = "miammiamdu67";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "mamie.gourgette@predictif.fr";
        motDePasse = "miammiamdu68";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "employe.fictif@insa-lyon.fr";
        motDePasse = "********";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }

    public static void saisirInscriptionEmploye() {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE INSCRIPTION **");
        System.out.println("**************************");
        System.out.println();

        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");
        String tel = Saisie.lireChaine("Numero de telephone ? ");

        Employe employe = new Employe(nom, prenom, mail, motDePasse, tel, Genre.F);
        Long idEmploye = service.inscrireEmploye(employe);

        if (idEmploye != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherEmploye(employe);

    }

    public static void saisirRechercheEmploye() {
        Service service = new Service();

        System.out.println();
        System.out.println("*********************");
        System.out.println("** MENU INTERACTIF **");
        System.out.println("*********************");
        System.out.println();

        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** RECHERCHE de CLIENTS **");
        System.out.println("**************************");
        System.out.println();
        System.out.println();
        System.out.println("** Recherche par Identifiant:");
        System.out.println();

        Integer idEmploye = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        while (idEmploye != 0) {
            Employe employe = service.rechercherEmployeParId(idEmploye.longValue());
            if (employe != null) {
                afficherEmploye(employe);
            } else {
                System.out.println("=> Employe #" + idEmploye + " non-trouvé");
            }
            System.out.println();
            idEmploye = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("** AUTHENTIFICATION de CLIENT **");
        System.out.println("********************************");
        System.out.println();
        System.out.println();
        System.out.println("** Authentifier Employe:");
        System.out.println();

        String employeMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

        while (!employeMail.equals("0")) {
            String employeMotDePasse = Saisie.lireChaine("Mot de passe ? ");
            Employe employe = service.authentifierEmploye(employeMail, employeMotDePasse);
            if (employe != null) {
                afficherEmploye(employe);
            } else {
                System.out.println("=> Employe non-authentifié");
            }
            employeMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("*****************");
        System.out.println("** AU REVOIR ! **");
        System.out.println("*****************");
        System.out.println();

    }
    
    public static void testerChoixEmploye(){
        initialiserEmployes();
        testerCreationConsultation();
        testerObtenirCommentairesClient();
        
        System.out.println();
        System.out.println("**** testerChoixEmploye() ****");
        System.out.println();
        
        Service service = new Service();
        
    }
    
    
    // Medium
    
    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium);
    }
    
    public static void testerInscriptionSpirites() {
        System.out.println();
        System.out.println("**** testerInscriptionSpirites() ****");
        System.out.println();
        
        Service service = new Service();
        Spirite eldrad = new Spirite("Eldrad Ulthran", "Que savent les humains de notre souffrance? Nous chantions des chants de lamentation tandis que vos ancêtres rampaient encore au fond des océans.", Genre.H, "Bâton d'Ulthamar");
        Long idEldrad = service.inscrireSpirite(eldrad);
        if (idEldrad != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        System.out.println(eldrad);
    }
    
    public static void testerInscriptionCartomanciens() {
        System.out.println();
        System.out.println("**** testerInscriptionCartomanciens() ****");
        System.out.println();
        
        Service service = new Service();
        Cartomancien yugi = new Cartomancien("Yugi <<Yami>> Muto", "Crois en l'âme des cartes, le pharaon est ton ami!", Genre.H);
        Long idYugi = service.inscrireCartomancien(yugi);
        if (idYugi != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        System.out.println(yugi);
    }
    
    public static void testerInscriptionAstrologues() {
        System.out.println();
        System.out.println("**** testerInscriptionAstrologues() ****");
        System.out.println();
        
        Service service = new Service();
        Astrologue astrid = new Astrologue("Astrid de l'île du Feu", "Je suis coincée dans ma cave :'(", Genre.F, "Institut des Nouveaux Savoirs Astrologiques", 666);
        Long idAstrid = service.inscrireAstrologue(astrid);
        if (idAstrid != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        System.out.println(astrid);
    }
    
    public static void testerRechercheMedium() {
        
        System.out.println();
        System.out.println("**** testerRechercheMedium() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Medium medium;

        id = 1;
        System.out.println("** Recherche du Medium #" + id);
        medium = service.rechercherMediumParId(id);
        if (medium != null) {
            afficherMedium(medium);
        } else {
            System.out.println("=> Medium non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Medium #" + id);
        medium = service.rechercherMediumParId(id);
        if (medium != null) {
            afficherMedium(medium);
        } else {
            System.out.println("=> Medium non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Medium #" + id);
        medium = service.rechercherMediumParId(id);
        if (medium != null) {
            afficherMedium(medium);
        } else {
            System.out.println("=> Medium #" + id + " non-trouvé");
        }
    }

    public static void testerRechercheMediumParDenom() {
        
        System.out.println();
        System.out.println("**** testerRechercheMediumParDenom() ****");
        System.out.println();
        
        Service service = new Service();
        String denom;
        Medium medium;

        denom = "Mme Irma";
        System.out.println("** Recherche du Medium " + denom);
        medium = service.rechercherMediumParDenom(denom);
        if (medium != null) {
            afficherMedium(medium);
        } else {
            System.out.println("=> Medium non-trouvé");
        }

        denom = "Eldrad Ulthran";
        System.out.println("** Recherche du Medium " + denom);
        medium = service.rechercherMediumParDenom(denom);
        if (medium != null) {
            afficherMedium(medium);
        } else {
            System.out.println("=> Medium non-trouvé");
        }

        denom = "JeNExistePas";
        System.out.println("** Recherche du Medium " + denom);
        medium = service.rechercherMediumParDenom(denom);
        if (medium != null) {
            afficherMedium(medium);
        } else {
            System.out.println("=> Medium " + denom + " non-trouvé");
        }
    }

    public static void testerListeMediums() {
        
        System.out.println();
        System.out.println("**** testerListeMediums() ****");
        System.out.println();
        
        Service service = new Service();
        List<Medium> listeMediums = service.listerMediums();
        System.out.println("*** Liste des Mediums");
        if (listeMediums != null) {
            for (Medium medium : listeMediums) {
                afficherMedium(medium);
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    // Profil Astral
    
    public static void testerProfilAstro(){
        System.out.println();
        System.out.println("**** testerProfilAstro() ****");
        System.out.println();
        
        Client momo = new Client("Eric", "Maurincomme", "momo-le-papa@gmail.com", "PC4ever", "0123456789", 7, 10, 1966);
        ProfilAstral profil = momo.getProfil();
        if (profil != null) {
            System.out.println("> Succès génération profil");
        } else {
            System.out.println("> Échec génération profil");
        }
        System.out.println(profil);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(momo);
            em.persist(profil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserClients()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        System.out.println("** Profil après persistance: ");
        System.out.println(profil);
    }
    
    public static void testerPrediction(){
        System.out.println();
        System.out.println("**** testerPrediction() ****");
        System.out.println();
        
        Service service = new Service();
        Client momo = new Client("Eric", "Maurincomme", "momo-le-papa@gmail.com", "PC4ever", "0123456789", 7, 10, 1966);
        List<String> predic = service.obtenirPredictions(momo, 4, 2, 3);
        if (predic != null) {
            System.out.println("> Succès génération profil");            
            System.out.println(predic);

        } else {
            System.out.println("> Échec génération profil");
        }
    }
    
    // Consultation
    
    public static void afficherConsultation(Consultation consultation) {
        System.out.println("-> " + consultation);
    }
    
    public static void testerCreationConsultation() {
        System.out.println();
        System.out.println("**** testerCreationConsultation() ****");
        System.out.println();
        
        Client ada = new Client("Lovelace2", "Ada2", "ada.lovelace2@insa-lyon.fr", "Ada10122");
        Employe camille = new Employe("MARTIN2", "Camille2", "camille.martin@predictif.fr2", "ilovearnaque2", "06 12 34 56 79", Genre.F);
        Spirite eldrad = new Spirite("Eldrad Ulthran2", "Que savent les humains de notre souffrance? Nous chantions des chants de lamentation tandis que vos ancêtres rampaient encore au fond des océans.", Genre.H, "Bâton d'Ulthamar");

        Service service = new Service();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ada);
            em.persist(camille);
            em.persist(eldrad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service testerCreationConsultation()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        Consultation consult = new Consultation(camille, ada, eldrad);
        //consult.setDate(2020, 1, 13, 20, 20);
        consult.setDateNow();
        Long idConsult = service.enregistrerConsultation(consult);
        if (idConsult != null) {
            System.out.println("> Succès création consultation");
        } else {
            System.out.println("> Échec création consultation");
        }
        System.out.println(consult);
    }
    
    public static void testerObtenirCommentairesClient() {
        System.out.println();
        System.out.println("**** testerObtenirCommentairesClient() ****");
        System.out.println();
        
        Client ada = new Client("Lovelace3", "Ada3", "ada.lovelace3@insa-lyon.fr", "Ada10133");
        Employe camille = new Employe("MARTIN3", "Camille3", "camille.martin@predictif.fr3", "ilovearnaque3", "06 13 34 56 79", Genre.F);
        Employe mat = new Employe("Mat", "Mut", "mot@gmail.com", "oui", "06 13 34 55 79", Genre.H);
        Spirite eldrad = new Spirite("Eldrad Ulthran3", "Que savent les humains de notre souffrance? Nous chantions des chants de lamentation tandis que vos ancêtres rampaient encore au fond des océans.", Genre.H, "Bâton d'Ulthamar");
        Astrologue astrid = new Astrologue("Astrid de l'île du Feu 2", "Je suis coincée dans ma cave :'(", Genre.F, "Institut des Nouveaux Savoirs Astrologiques", 666);
        
        Service service = new Service();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(ada);
            em.persist(camille);
            em.persist(mat);
            em.persist(eldrad);
            em.persist(astrid);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service testerObtenirCommentairesClient()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        // consult 1
        Consultation consult1 = new Consultation(camille, ada, eldrad);
        consult1.setDate(2020, 4, 14, 14, 30);
        consult1.setCommentaire("La consultation s'est bien déroulée.");
        Long idConsult1 = service.enregistrerConsultation(consult1);
        if (idConsult1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        
        // consult 2
        Consultation consult2 = new Consultation(camille, ada, astrid);
        consult2.setDate(2020, 4, 8, 14, 56);
        consult2.setCommentaire("Cette consultation aussi s'est bien déroulée.");
        Long idConsult2 = service.enregistrerConsultation(consult2);
        if (idConsult2 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        
        // consult 3
        Consultation consult3 = new Consultation(camille, ada, astrid);
        consult3.setDate(2020, 4, 30, 14, 21);
        consult3.setCommentaire("Cette consultation s'est bien déroulée tiens dis donc.");
        Long idConsult3 = service.enregistrerConsultation(consult3);
        if (idConsult3 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        
        // consult 4
        Consultation consult4 = new Consultation(mat, ada, astrid);
        consult4.setDate(2020, 4, 15, 14, 21);
        consult4.setCommentaire("Haha. C'était drôle.");
        Long idConsult4 = service.enregistrerConsultation(consult4);
        if (idConsult4 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        
        // consult 5
        Consultation consult5 = new Consultation(camille, ada, astrid);
        consult5.setDate(2020, 4, 13, 21, 21);
        consult5.setCommentaire("Pareil");
        Long idConsult5 = service.enregistrerConsultation(consult5);
        if (idConsult5 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        
        List<Consultation> listeConsult = service.rechercherConsultsParClient(ada);
        System.out.println("*** Liste des Commentaires ***");
        if (listeConsult != null) {
            for (Consultation consultation : listeConsult) {
                System.out.println(consultation.getCommentaire());
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }
    
    // a faire quand des employes sont déjà initialisés
    // (ça fait beaucoup de trucs à recopier sinon)
    public static void testerContacterMedium(){
        System.out.println();
        System.out.println("**** testerContacterMedium() ****");
        System.out.println();
        
        Client ada = new Client("Lovelace4", "Ada4", "ada.lovelace4@insa-lyon.fr", "Ada10144");
        Spirite eldrad = new Spirite("Eldrad Ulthran4", "Que savent les humains de notre souffrance? Nous chantions des chants de lamentation tandis que vos ancêtres rampaient encore au fond des océans.", Genre.H, "Bâton d'Ulthamar");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ada);
            em.persist(ada);
            em.persist(eldrad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service testerContacterMedium()", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        Service service = new Service();
        
        Consultation consult = service.contacterMedium(eldrad, ada);
        System.out.println("Consultation générée :");
        System.out.println("Employé :");
        System.out.println(consult.getEmploye());
    }
    
}
