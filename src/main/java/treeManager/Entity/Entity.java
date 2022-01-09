package treeManager.Entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface Entity {   
    /**
     * Utility - Reads keyboard input
     * 
     * @return Entered text
     */
    static public String lireClavier() {
        try {
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            return clavier.readLine();
        }
        catch (Exception e) {
            return "ERREUR - lireClavier()";
        }
    }

    /**
     * Utility - Check if given String is a correct Int
     * 
     * @param str String to parse as an Integer
     * @return True if correct, or False
     */
    static public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
