package treeManager.Entity;

import treeManager.Data.Tree;

import java.util.ArrayList;

public class Maire extends Externe {
    public Maire(int id) {
        super(id);
    }

    /**
     * Return a Tree being nominated among the proposed ones
     *
     * @param trees
     * @return Nominated tree, or null if none
     */
    public Tree nominate(ArrayList<Tree> trees) {
        // Nominer un arbre à partir de la liste de l'asso

        System.out.println("Here are the possible trees, chosen by the association:");
        for (int i = 0; i < trees.size(); i++) { // JGI - Funfact, mon IDE trouve que "i++" c'est du 'dead code' ('-' )
            Tree t = trees.get(i);
            System.out.println(i + " - " + t); // TODO esthétique pour affichage d'un arbre, OU écriture d'une fonction
                                               // de print pour Tree
            System.out.println();
        }

        System.out.println();
        System.out.println("Please enter the tree ID to nominate, or -1 to exit : ");
        String input = Entity.lireClavier();

        // Checks input (should be an integer included in [0, trees.size() [
        while (!Entity.isInteger(input) && Integer.parseInt(input) >= trees.size()) {
            System.out.println("ERROR - Please enter a valid answer");
            input = Entity.lireClavier();
        }

        int id = Integer.parseInt(input);
        if (id != -1) {
            return trees.get(id);
        } else {
            System.out.println("No tree nominated");
            return null;
        }
    }
}
