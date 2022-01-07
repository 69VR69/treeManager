package treeManager.Entity;
import java.util.ArrayList;

import treeManager.Data.Tree;

public class Maire extends Externe
    {
        /**
         * Return a Tree being nominated among the proposed ones
         * @param trees
         * @return Nominated tree, or null if none
         */
        public Tree nominate(ArrayList<Tree> trees) {
        //nominer un arbre à partir de la liste de l'asso
        // TODO : JGI - quelle liste ? le top 5 parmi les proposés par les membres ? ou all trees ?
            
            
            System.out.println("Here are the possible trees, chosen by the association:");
            for (int i = 0 ; i < trees.size() ; i++) { // RAF: proposedTrees s'obtient comment au juste ?
                Tree t = trees.get(i);
                System.out.println(i + " - " + t); // RAF: esthétique pour affichage d'un arbre, OU écriture d'une fonction de print pour Tree
                System.out.println();
            }

            System.out.println();
            System.out.println("Please enter the tree ID to nominate, or -1 to exit : ");
            String input = Entity.lireClavier();

            while (!isInteger(input)) {
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

        /**
         * Utility - Check if given String is a correct Int
         * @param str
         * @return True if correct, or False
         */
        private static boolean isInteger(String str) { // TODO move in appropriate class/package ?
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
