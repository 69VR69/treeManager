package treeManager;

import treeManager.Entity.*;
import treeManager.Data.*;

public class Main {
    static public Association asso;
    public static void main(String[] args) {
        asso = new Association();

        System.out.println("ACCUEIL");
        System.out.println("0 : Gerer les membres");
        System.out.println("1 : Gerer les visites");
        System.out.println("2 : Gerer les arbres");
        System.out.println("-1 : Quitter");

        System.out.print("> ");
        String input = Entity.lireClavier();
        while (!isValidInt(input, -1, 2)) {
            System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
            System.out.print("> ");
            input = Entity.lireClavier();
        }
        
        System.out.println("DEBUG - input = " + input);

        while (input != "-1") {
            switch (input) {    
                case "0":
                    gestionMembres();
                    break;
    
                case "1":
                    gestionVisites();
                    break;
    
                case "2":
                    gestionArbres();
                    break;
    
                default:
                    System.out.println("ERROR in switch menu");
                    break;
            }

            System.out.println("0 : Gerer les membres");
            System.out.println("1 : Gerer les visites");
            System.out.println("2 : Gerer les arbres");
            System.out.println("-1 : Quitter");
            System.out.print("> ");
            input = Entity.lireClavier();
            while (!isValidInt(input, -1, 2)) {
                System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
                System.out.print("> ");
                input = Entity.lireClavier();
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

        String input = Entity.lireClavier();
        while (!isValidInt(input, -1, 2)) {
            // Checks input (int included in [-1, 2])
            System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
            input = Entity.lireClavier();
        }

        System.out.println("DEBUG - input = " + input);

        while (input != "0") {
            switch (input) {
                case "0":
                    System.out.println("---");
                    break;
    
                case "1":
                    System.out.print("Nom du nouveau membre : ");
                    asso.add_member(new Member(Entity.lireClavier()));
                    System.out.println();
                    break;
    
                case "2":
                    System.out.print("Nom du membre à supprimer : ");
                    asso.desinscrire(new Member(Entity.lireClavier()));
                    System.out.println();
                    break;
    
                case "3":
                    System.out.print("Nom du membre qui a payé sa cotisation : ");
                    asso.pay_cotisation(new Member(Entity.lireClavier()));;
                    System.out.println();
                    break;
            } 
            
            System.out.println();
            System.out.println("0 : Nouveau membre");
            System.out.println("1 : Supprimer un membre");
            System.out.println("2 : Payer la cotisation d'un membre");
            System.out.println("-1 : Retour au menu principal");

            input = Entity.lireClavier();
            while (!isValidInt(input, -1, 2)) {
                System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
                input = Entity.lireClavier();
            }
        }
    }

    /**
     * Command line interaction for anything visit related
     */
    public static void gestionVisites() {
        System.out.println("VISITES");
        System.out.println("0 : Nouvelle visite");
        System.out.println("1 : Voir arbres sans visite");
        System.out.println("-1 : Retour au menu principal");

        String input = Entity.lireClavier();
        while (!isValidInt(input, -1, 1)) {
            // Checks input (int included in [-1, 1])
            System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
            input = Entity.lireClavier();
        }

        while (input != "-1") {
            switch (input) {
                case "-1":
                    System.out.println("---");
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
                    asso.pay_cotisation(new Member(Entity.lireClavier()));
                    System.out.println();
                    break;
    
                default:
                    System.out.println("ERROR in switch menu");
                    break;
            } 
            
            System.out.println();
            System.out.println("0 : Nouveau membre");
            System.out.println("1 : Supprimer un membre");
            System.out.println("2 : Payer la cotisation d'un membre");
            System.out.println("-1 : Retour au menu principal");

            input = Entity.lireClavier();
            while (!isValidInt(input, -1, 2)) {
                System.out.println("ERROR - Veuillez entrer une valeur correcte : ");
                input = Entity.lireClavier();
            }
        }
    }

    /**
     * Command line interaction for anything tree related
     */
    public static void gestionArbres() {
        
    }

    /**
     * Checks if given string is an integer included in [min, max]
     * @param input
     * @param min
     * @param max
     * @return
     */
    public static boolean isValidInt(String input, int min, int max) {
        return (Entity.isInteger(input) && Integer.parseInt(input) <= 2 && Integer.parseInt(input) >= -1);
    }
}
