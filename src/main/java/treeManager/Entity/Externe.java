package treeManager.Entity;

public class Externe implements Entity {
    // TODO No idea what to put here

    int id;

    public Externe(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void ask_donnation(String money_report){
        //TODO print the report and ask if donnator want to give money (and how much)
    }
}
