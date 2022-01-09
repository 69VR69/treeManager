package treeManager.Entity;

import treeManager.Data.Tree;

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

    /**
     * DEPRECATED - Delete a member from the Association
     */
    public void desinscrire() {
        // useless ? La suppression du Membre se fait côté Association
        /*
         * for (Member m : asso.getMembres()) {
         * String paye = m.hasPayed() ? "A payé" : "";
         * System.out.println(m.getNom() + paye); // pour aider à la suppression, liste
         * les (non-)payeurs
         * }
         */
        System.out.println("WARN - President.desinscrire() non implementee");
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

    public President(int id, String nom, int nbVisites, boolean hasPayed, ArrayList<Tree> proposedTrees) {
        super(id, nom, nbVisites, hasPayed, proposedTrees);
    }
}
