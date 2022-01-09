package treeManager.Entity;

import treeManager.Data.Tree;
import treeManager.Data.Visite;
import treeManager.DatabaseTools;

import java.util.ArrayList;

public class Member implements Entity {
    // --- Variables ---
    /**
     * ID of the Member in the DB
     */
    private int id;
    /**
     * Name of the Member
     */
    private String nom;
    /**
     * Status of the member's cotisation payment
     */
    private boolean hasPayed;
    /**
     * Trees proposed for nomination by the member
     */
    private ArrayList<Tree> proposedTrees;

    /**
     * Visits done by the member
     */
    private ArrayList<Visite> visites;

    /**
     * Number of tree visits done
     */
    private int nbVisites;

    // --- Getters & Setters ---

    /**
     * Get the id of the member
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the member
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name of the member
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the name of the member
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Return if the member has payed its cotisation
     */
    public boolean hasPayed() {
        return hasPayed;
    }

    /**
     * Get the trees proposed for nomination by the member
     */
    public ArrayList<Tree> getProposedTrees() {
        return proposedTrees;
    }

    /**
     * Set the trees proposed for nomination by the member
     *
     * @param proposedTrees
     */
    public void setProposedTrees(ArrayList<Tree> proposedTrees) {
        this.proposedTrees = proposedTrees;
    }

    /**
     * Get the number of visits done by the member
     */
    public int getNbVisites() {
        return nbVisites;
    }

    /**
     * Get visits done by the member
     * @return An array of Visite
     */
    public ArrayList<Visite> get_visites() {
        return visites;
    }

    /**
     * Finds a visit done by the member with the given ID
     * @param id
     * @return Visit with the given ID, or null if no visits had this ID
     */
    public Visite getVisiteById(int id) {
        for (Visite v : this.get_visites()) {
            if (v.getId() == id) return v;
        }
        return null;
    }

    /**
     * Print all visits done by the member
     */
    public void printVisites() {
        for (Visite v : this.get_visites()) {
            System.out.println(v.getId() + " - " + v.getDate().toString() + " - Tree ID " + v.getTree().getId());
        }
    }

    /**
     * Add a visit
     * @param v
     */
    public void add_visite(Visite v) {
        visites.add(v);
    }

    /**
     * Increments the number of visits done by the member
     */
    public void incVisites() {
        this.nbVisites++;
    }

    public void setVisites(int i){
        this.nbVisites=i;
    }

    // --- Fonctions membres ---

    /**
     * Pay the cotisation of the member
     */
    public void payCotisation() {
        if (!hasPayed) {
            hasPayed = true;
            // TODO gérer le paiement côté assoc (et penser à appeler cette fonction à ce
            // moment!)
        } else {
            System.out.println("Paiement déjà réalisé !");
        }
    }

    /**
     * Unpay the cotisation of the member
     */
    public void unpayCotisation() {
        if (hasPayed) {
            hasPayed = false;
            // TODO gérer le paiement côté assoc (et penser à appeler cette fonction à ce
            // moment!)
        }
    }

    /**
     * Add a Tree to member's proposed trees list
     */
    public void proposeTree(Tree t) {
        /*
         * TODO
         * Si on atteint les 5, proposer d'en remplacer un par le 6ème choisi (ou pas)
         */
        if (proposedTrees.size() > 5) {
            System.out.println("You already proposed " + proposedTrees.size()
                    + "trees. Please select one to delete: (NOT IMPLEMENTED YET)");
        } else {
            proposedTrees.add(t);
        }
    }

    public void deleteMember(DatabaseTools dbt) {
        // TODO delete the memeber information from the BD (to be RGPD compliant)
        dbt.removeMember(this);
    }

     /*
        // DEPRECATED (mais mis de côté au cas où : du code pour choisir l'arbre à entrer)
        public void proposeTree() {
            //proposer 5 arbre to association
            boolean stop = false;            
            System.out.println("You currently have proposed " + proposedTrees.size() + "trees.");

             //   Gros problème -> comment proposer/lister les arbres possibles ??? Par nom ?
             //   Ou alors faut implémenter une recherche dans la BD pour avoir les arbres voulus... et ça sera chiant
            System.out.println("<Print possible trees here>"); 
            String input = Entity.lireClavier();
            String t = input; // WARN t is temporary - should be a Tree

            while(!stop) {
                if (t == "-1") { 
                    stop = true;
                } else if (proposedTrees.size() > 5) {
                    System.out.println("Trop d'arbres!");
                    stop = true;
                } else {
                    //TMP
                    Tree tmpTree = new Tree(5, "nom", "15", 15, 15, "espece", "genre", false, "emplacement", 0, "domaine", "arrondissement", "adresse", "complement", new ArrayList<Visite>());
                    proposedTrees.add(tmpTree);
                    System.out.println("Enter a tree to propose: "); // WARN clarifier le choix de l'Arbre à ajouter (recherche par attribut ? tout lister et choix par id ?)
                    t = Entity.lireClavier();
                }
            }
        }
        */

    // --- Constructeurs ---
    /**
     * Simple member constructor
     * 
     * @param nom
     */
    public Member(String nom) {
        this.id = 0;
        this.hasPayed = false;
        this.proposedTrees = new ArrayList<Tree>();
        this.nom = nom;
        this.nbVisites = 0;
    }
    
    /**
     * Member constructor
     *
     * @param id
     * @param nom
     * @param hasPayed
     * @param proposedTrees
     * @param visites
     * @param nbVisites
     */
    public Member(int id, String nom, boolean hasPayed, ArrayList<Tree> proposedTrees, ArrayList<Visite> visites, int nbVisites)
        {
            this.id = id;
            this.nom = nom;
            this.hasPayed = hasPayed;
            this.proposedTrees = proposedTrees;
            this.visites = visites;
            this.nbVisites = nbVisites;
        }
}