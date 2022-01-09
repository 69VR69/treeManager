package treeManager.Entity;

public class Externe implements Entity {
    /**
     * ID of the external entity
     */
    int id;

    /**
     * Extern constructor
     * @param id ID
     */
    public Externe(int id) {
        this.id = id;
    }

    /**
     * Returns the ID of the Extern
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Extern
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ask for a donation to the Extern
     * @param money_report String
     */
    public void ask_donnation(String money_report){
        //TODO print the report and ask if donnator want to give money (and how much)
    }
}
