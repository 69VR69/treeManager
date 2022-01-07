package treeManager.Entity;
import java.util.ArrayList;

import treeManager.Data.Association;
import treeManager.Data.Tree;

public class Member implements Entity
    {
        // --- Variables ---
        private String nom;
        private boolean hasPayed;
        private ArrayList<Tree> proposedTrees;

        // --- Getters & Setters ---
        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public boolean hasPayed() {
            return hasPayed;
        }

        public ArrayList<Tree> getProposedTrees() {
            return proposedTrees;
        }

        public void setProposedTrees(ArrayList<Tree> proposedTrees) {
            this.proposedTrees = proposedTrees;
        }

        // --- Fonctions membres ---
        public void payCotisation() {
            //payer cotisation
            if (!hasPayed) {
                hasPayed=false;
                asso.solde += 1; // TODO combien ils payent déjà ? also 'association' = l'objet qu'on manipulera à tout bout de champ
            } else {
                System.out.println("Paiement réalisé !");
            }
        }

        public void proposeTrees() {
            //proposer 5 arbre to association
            boolean stop = false;

            // TODO make sure to have a variable amount of trees to give as arguments
            // Number of possible given Tree is in [1, 5]
            // Potentiellement passer un tableau de Tree ? ça simplifierait le taff
            System.out.println("You currently have proposed " + proposedTrees.size() + "trees.");
            System.out.println("Enter a tree to propose: ");
            Tree t = Entity.lireClavier(); // TODO clarifier le choix de l'Arbre à ajouter (recherche par attribut ? tout lister et choix par id ?)

            /* TODO
            Si on atteint les 5, proposer d'en remplacer un par le 6ème choisi
            Si on quitte avant, probablement rentrer -1 ou un truc du genre
            */
            while(!stop) {
                if (t == -1) { //TODO make a clean "stop!" input condition
                    stop = true;
                } else if (proposedTrees.size() > 5) {
                    System.out.println("Trop d'arbres!");
                    stop = true;
                } else {
                    proposedTrees.add(t);
                    System.out.println("Enter a tree to propose: "); // TODO clarifier le choix de l'Arbre à ajouter (recherche par attribut ? tout lister et choix par id ?)
                    t = Entity.lireClavier();
                }
            }
        }


        //TODO add attribute nb_visits

        public int get_nb_visite(){
            //TODO retunr attribute
            return 0;
        }

        public void  inc_visite(){
            //TODO increments by one the number of visits
        }

        // --- Constructeurs ---
        public Member(String nom) {
            this.hasPayed = false;
            this.proposedTrees = new ArrayList<Tree>();
            this.nom = nom;
        }
    }
;