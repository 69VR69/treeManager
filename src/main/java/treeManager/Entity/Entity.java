package treeManager.Entity;

import java.io.*; // pour la fonction lireClavier

public interface Entity
    {
        /**
         * Utility - Reads keyboard input
         * @return Entered text
         */
        static public String lireClavier() {
            try {
                BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
                return clavier.readLine();
            } catch (Exception e) {
                return "ERREUR - lireClavier()";
            }
        }
        
        // TODO implements on member + externe
    }
