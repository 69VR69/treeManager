package treeManager.Entity;

import java.io.*; // pour la fonction lireClavier

public interface Entity
    {
        static public String lireClavier() {
            //TODO how the fuck do you make this function visible aux autres-
            try {
                BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
                return clavier.readLine();
            } catch (Exception e) {
                return "ERREUR - lireClavier()";
            }
        }
        
        // TODO implements on member + externe
    }
