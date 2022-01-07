package treeManager.Entity;
import treeManager.Data.Tree;

public class Maire extends Externe
    {
        public void nominate() {
        //nominer un arbre à partir de la liste de l'asso
        // JGI - quelle liste ? le top 5 parmi les proposés par les membres ? ou all trees ?
            System.out.println("Here are the possible trees, chosen by the association:");
            for (int i = 0 ; i < asso.proposedTrees.size() ; i++) { // TODO proposedTrees s'obtient comment au juste ?
                Tree t = asso.proposedTrees[i];
                System.out.println(i + " - " + t); // TODO esthétique pour affichage d'un arbre, OU écriture d'une fonction de print pour Tree
                System.out.println();
            }

            System.out.println();
            System.out.println("Please enter the tree ID to nominate, or -1 to exit : ");
            String input = Entity.lireClavier();
            while (input != "-1") {
                asso.trees[input].setRemarkable(true);

                System.out.println("Please enter another tree ID to nominate, or -1 to exit : ");
                input = Entity.lireClavier();
            }
        }
        
    }
