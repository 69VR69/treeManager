package treeManager;

import treeManager.Entity.*;

import java.util.ArrayList;
import java.util.Date;

import treeManager.Data.*;

public class Main {
    static public Association asso;
    static public DatabaseTools db;
    public static void main(String[] args) {
        asso = new Association();
        //db = new DatabaseTools(ip, user, password); // TODO ip, user, password for db pls
       // db.open(user, password); // TODO same (est-ce que c'est ici qu'il faut le faire tho... ?)

        // TODO peupler l'asso avec le pouvoir de la DB (AFO, c'est à toi-)

        System.out.println("ACCUEIL");
        System.out.println("0 : Gerer les membres");
        System.out.println("1 : Gerer les visites");
        System.out.println("2 : Gerer les arbres");
        System.out.println("3 : Nouvel exercice fiscal");
        System.out.println("-1 : Quitter");

        System.out.print("> ");
        String input = Entity.lireClavier();
        while (!isValidInt(input, -1, 2)) {
            System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
            System.out.print("> ");
            input = Entity.lireClavier();
        }

        boolean exit = (input == "-1" ? true : false);
        System.out.println("exit = " + exit);
        while (!exit) {
            switch (input) {    
                case "-1":
                    exit = true;
                    break;

                case "0":
                    gestionMembres();
                    break;
    
                case "1":
                    gestionVisites();
                    break;
    
                case "2":
                    gestionArbres();
                    break;

                case "3":
                    asso.end_year();
                    break;
    
                default:
                    System.out.println("ERROR in switch menu");
                    break;
            }

            if (!exit) {
                System.out.println("0 : Gerer les membres");
                System.out.println("1 : Gerer les visites");
                System.out.println("2 : Gerer les arbres");
                System.out.println("3 : Reset cotisation (nouvel exercice fiscal)");
                System.out.println("-1 : Quitter");
                System.out.print("> ");
                input = Entity.lireClavier();
                while (!isValidInt(input, -1, 2)) {
                    System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
                    System.out.print("> ");
                    input = Entity.lireClavier();
                }
            }
        }

        System.out.println("Goodbye");
    }

    /**
     * Command line interaction for anything member related
     */
    public static void gestionMembres() {
        System.out.println("MEMBRES");
        System.out.println("0 : Nouveau membre");
        System.out.println("1 : Supprimer un membre");
        System.out.println("2 : Payer la cotisation d'un membre");
        System.out.println("-1 : Retour au menu principal");

        System.out.print("> ");
        String input = Entity.lireClavier();
        while (!isValidInt(input, -1, 2)) {
            // Checks input (int included in [-1, 2])
            System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
            System.out.print("> ");
            input = Entity.lireClavier();
        }

        boolean exitMembres = (input == "-1" ? true : false);
        while (!exitMembres) {
            switch (input) {    
                case "-1":
                    exitMembres = true;
                    break;
    
                case "0":
                    System.out.print("Nom du nouveau membre : ");
                    asso.add_member(new Member(Entity.lireClavier()));
                    System.out.println();
                    break;
    
                case "1":
                    System.out.print("Nom du membre à supprimer : ");
                    asso.desinscrire(new Member(Entity.lireClavier()));
                    System.out.println();
                    break;
    
                case "2":
                    System.out.print("Nom du membre qui a payé sa cotisation : ");
                    asso.pay_cotisation(new Member(Entity.lireClavier()));;
                    System.out.println();
                    break;
            } 
            
            if (!exitMembres) {
                System.out.println();
                System.out.println("0 : Nouveau membre");
                System.out.println("1 : Supprimer un membre");
                System.out.println("2 : Payer la cotisation d'un membre");
                System.out.println("-1 : Retour au menu principal");

                System.out.print("> ");
                input = Entity.lireClavier();
                while (!isValidInt(input, -1, 2)) {
                    System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
                    input = Entity.lireClavier();
                }
            }
        }
    }

    /**
     * Command line interaction for anything visit related
     */
    public static void gestionVisites() {
        System.out.println("VISITES");
        System.out.println("0 : Nouvelle visite");
        //System.out.println("1 : Voir arbres sans visite");
        System.out.println("-1 : Retour au menu principal");

        String input = Entity.lireClavier();
        while (!isValidInt(input, -1, 0)) {
            // Checks input (int included in [-1, 1])
            System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
            System.out.print("> ");
            input = Entity.lireClavier();
        }

        boolean exitVisites = (input == "-1") ? true : false;

        while (!exitVisites) {
            switch (input) {
                case "-1":
                    exitVisites = true;
                    break;
    
                case "0":
                    System.out.print("ID de l'arbre visite : ");
                    String tmpVisite = Entity.lireClavier();
                    while (!isValidInt(tmpVisite)) {
                        System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
                        tmpVisite = Entity.lireClavier();
                    }
                    Tree treeVisite = db.getTreeById(Integer.parseInt(tmpVisite));
                    Visite v = new Visite(new Date(), treeVisite); // TODO proper ask for date of visit

                    System.out.print("Entrer le report : ");
                    String reportVisite = Entity.lireClavier();
                    asso.do_visite(v, reportVisite); 
                    System.out.println();
                    break;
    
                default:
                    System.out.println("ERROR in switch menu");
                    break;
            } 
            
            if (!exitVisites) {
                System.out.println();
                System.out.println("0 : Nouvelle visite");
                //System.out.println("1 : Voir arbres sans visite");
                System.out.println("-1 : Retour au menu principal");
    
                input = Entity.lireClavier();
                while (!isValidInt(input, -1, 0)) {
                    System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
                    input = Entity.lireClavier();
                }
            }
        }
    }

    /**
     * Command line interaction for anything tree related
     */
    public static void gestionArbres() {
        System.out.println("ARBRES");
        System.out.println("0 : Nouvel arbre");
        System.out.println("1 : Voter pour un arbre");
        System.out.println("2 : Nominer un arbre");
        System.out.println("-1 : Retour au menu principal");

        String input = Entity.lireClavier();
        while (!isValidInt(input, -1, 2)) {
            // Checks input (int included in [-1, 1])
            System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
            System.out.print("> ");
            input = Entity.lireClavier();
        }

        boolean exitArbres = (input == "-1") ? true : false;

        while (!exitArbres) {
            switch (input) {
                case "-1":
                    exitArbres = true;
                    break;
    
                // Ajouter un nouvel arbre
                case "0":
                    System.out.print("Nom de l'arbre : ");
                    String nom = Entity.lireClavier();
                    
                    System.out.println();
                    System.out.print("Age : ");
                    String age = Entity.lireClavier();

                    System.out.println();
                    System.out.print("Hauteur : ");
                    String tmp = Entity.lireClavier();
                    while(!isValidInt(tmp)) {
                        System.out.println("ERROR - Hauteur invalide :");
                        tmp = Entity.lireClavier();
                    }
                    int hauteur = Integer.parseInt(Entity.lireClavier());

                    System.out.println();
                    System.out.print("Epaisseur : ");
                    tmp = Entity.lireClavier();
                    while(!isValidInt(tmp)) {
                        System.out.println("ERROR - Epaisseur invalide :");
                        tmp = Entity.lireClavier();
                    }
                    int epaisseur = Integer.parseInt(Entity.lireClavier());

                    System.out.println();
                    System.out.print("Espece : ");
                    String espece = Entity.lireClavier();

                    System.out.println();
                    System.out.print("Genre : ");
                    String genre = Entity.lireClavier();

                    System.out.println();
                    System.out.print("Remarquable ? (y/n) : ");
                    boolean remarquable = (Entity.lireClavier().toLowerCase() == "y" ? true : false);

                    System.out.println();
                    System.out.print("Emplacement : ");
                    String emplacement = Entity.lireClavier();

                    System.out.println();
                    System.out.print("Domaine : ");
                    String domaine = Entity.lireClavier();

                    System.out.println();
                    System.out.print("Arrondissement : ");
                    String arrondissement = Entity.lireClavier();

                    System.out.println();
                    System.out.print("Adresse : ");
                    String adresse = Entity.lireClavier();

                    System.out.println();
                    System.out.print("Complement : ");
                    String complement = Entity.lireClavier();

                    Tree t = new Tree(0, nom, age, hauteur, epaisseur, espece, genre, remarquable, emplacement, 0, domaine, arrondissement, adresse, complement);
                    asso.add_tree(t);
                    System.out.println();
                    break;

                // Voter pour un arbre
                case "1":
                    System.out.print("ID du membre votant: ");
                    String idMembre = Entity.lireClavier();
                    while(!isValidInt(idMembre)) {
                        System.out.println("ERROR - ID invalide :");
                        idMembre = Entity.lireClavier();
                    }
                    Member memberVote = db.getMemberById(Integer.parseInt(idMembre));
                    System.out.println("Votes realises: " + memberVote.getProposedTrees().size());
                    if (memberVote.getProposedTrees().size() == 5) {
                        System.out.println("Vous avez deja vote pour cinq arbres.");
                    } else {
                        System.out.print("ID du membre votant: ");
                        String idTree = Entity.lireClavier();
                        while(!isValidInt(idTree)) {
                            System.out.println("ERROR - ID invalide :");
                            idTree = Entity.lireClavier();
                        }
                        Tree tVote = db.getTreeById(Integer.parseInt(idTree));
                        tVote.setNb_votes(tVote.getNb_votes() + 1);
                        ArrayList<Tree> newProposedTrees = memberVote.getProposedTrees();
                        newProposedTrees.add(tVote);
                        memberVote.setProposedTrees(newProposedTrees);
                    }

                    System.out.println();
                    break;
    
                // Nominer un arbre
                case "2":
                    System.out.print("ID de l'arbre à nominer: ");
                    String id = Entity.lireClavier();
                    while(!isValidInt(id)) {
                        System.out.println("ERROR - ID invalide :");
                        id = Entity.lireClavier();
                    }
                    db.getTreeById(Integer.parseInt(id)).setRemarquable(true);
                    System.out.println();
                    break;
    
                default:
                    System.out.println("ERROR in switch menu");
                    break;
            } 
            
            if (!exitArbres) {
                System.out.println();
                System.out.println("0 : Nouvel arbre");
                System.out.println("1 : Voter pour un arbre");
                System.out.println("2 : Nominer un arbre");
                System.out.println("-1 : Retour au menu principal");
    
                input = Entity.lireClavier();
                while (!isValidInt(input, -1, 2)) {
                    System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
                    input = Entity.lireClavier();
                }
            }
        }
    }

    /**
     * Checks if given string is an integer
     * @param input
     * @return
     */
    public static boolean isValidInt(String input) {
        return (Entity.isInteger(input));
    }

    /**
     * Checks if given string is an integer included in [min, max]
     * @param input
     * @param min
     * @param max
     * @return
     */
    public static boolean isValidInt(String input, int min, int max) {
        return (Entity.isInteger(input) && Integer.parseInt(input) <= max && Integer.parseInt(input) >= min);
    }
}
