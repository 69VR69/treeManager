package treeManager.Entity;

import treeManager.Data.Tree;
import treeManager.Data.Visite;

import java.util.ArrayList;

public class President extends Member {
    // --- Fonctions membres ---

    /**
     * Return a new member of the association
     *
     * @return Member
     */
    public Member inscrire(String nom) {
        return (new Member(nom));
    }

    // --- Constructeurs ---

    /**
     * President constructor
     *
     * @param nom
     */
    public President(String nom) {
        super(nom);
    }
    
    /**
     * Complete president constructor
     * @param id ID
     * @param nom Name
     * @param hasPayed Boolean
     * @param proposedTrees ArrayList of trees
     * @param visites ArrayList of visits
     * @param nbVisites Number of visits
     */
    public President(int id, String nom, boolean hasPayed, ArrayList<Tree> proposedTrees, ArrayList<Visite> visites, int nbVisites)
        {
            super(id, nom, hasPayed, proposedTrees, visites, nbVisites);
        }
}
