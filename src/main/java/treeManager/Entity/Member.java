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
     * @return id ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the member
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the name of the member
     * @return Name
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the name of the member
     * @param nom Name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Return true if the member has payed its cotisation
     * @return Boolean
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
     * @param proposedTrees ArrayList of trees
     */
    public void setProposedTrees(ArrayList<Tree> proposedTrees) {
        this.proposedTrees = proposedTrees;
    }

    /**
     * Get the number of visits done by the member
     * @return Number of visits
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
     * @param id Searched ID
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
     * @param v Visite being added
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

    /**
     * Sets the number of visits
     * @param i Number of visits
     */
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
     * @param t Proposed tree for nomination
     */
    public void proposeTree(Tree t) {
        if (proposedTrees.size() > 5) {
            System.out.println("You already proposed " + proposedTrees.size() + "trees.");
        } else {
            proposedTrees.add(t);
        }
    }

    /**
     * Delete a member from the DB
     * @param dbt DatabaseTools
     */
    public void deleteMember(DatabaseTools dbt) {
        // TODO delete the memeber information from the BD (to be RGPD compliant)
        dbt.removeMember(this);
    }

    /**
     * Return a member as a human readable String
     * @return Readable String
     */
    public String toString() {
        return(this.nom + (this.hasPayed ? " - A payé sa cotisation" : " - N'a pas payé sa cotisation"));
    }

    // --- Constructeurs ---
    /**
     * Simple member constructor
     * @param nom Name
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
     * @param id ID
     * @param nom Name
     * @param hasPayed Boolean
     * @param proposedTrees ArrayList of trees
     * @param visites ArrayList of visits
     * @param nbVisites Number of visits
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