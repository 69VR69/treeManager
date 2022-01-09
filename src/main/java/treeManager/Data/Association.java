package treeManager.Data;

import treeManager.Entity.Externe;
import treeManager.Entity.Member;

import java.util.ArrayList;
import java.util.Date;

public class Association {
    private int nb_max_visite;
    private int montant_remboursement;
    private int montant_cotise;

    private int solde; //can't negative

    private int cotisations; //somme des cotisation
    private int dons; //somme des dons
    private int facture; //somme des facture
    private int defreiment; //somme des defreiments

    private ArrayList<Member> members;
    private ArrayList<Externe> donnateurs;
    private ArrayList<Tree> trees;

    //region builder
    Association() {
        new Association(0, 5, 15, 5);
    }

    Association(int solde) {
        new Association(solde, 5, 15, 5);
    }

    Association(int solde, int nb_max_visite, int montant_cotise, int montant_remboursement) {
        this.solde = solde;
        this.nb_max_visite = nb_max_visite;
        this.montant_cotise = montant_cotise;
        this.montant_remboursement = montant_remboursement;
        this.solde = solde;

        cotisations = 0;
        dons = 0;
        facture = 0;
        defreiment = 0;
    }

    public Association(int nb_max_visite, int montant_remboursement, int montant_cotise, int solde, int cotisations, int dons, int facture, int defreiment, ArrayList<Member> members, ArrayList<Externe> donnateurs, ArrayList<Tree> trees) {
        this.nb_max_visite = nb_max_visite;
        this.montant_remboursement = montant_remboursement;
        this.montant_cotise = montant_cotise;
        this.solde = solde;
        this.cotisations = cotisations;
        this.dons = dons;
        this.facture = facture;
        this.defreiment = defreiment;
        this.members = members;
        this.donnateurs = donnateurs;
        this.trees = trees;
    }
    //endregion

    //region private money manage
    //verifiy asso has the money
    private boolean can_pay(int m) {
        return ((solde = +m) > 0);
    }

    //modify the sold of asso
    private void change_sold(int m) {
        solde += m;
    }

    private void pay_facture(int m) {
        facture += m;
        change_sold(-m);
    }


    private void pay_visite() {
        defreiment += montant_remboursement;
        change_sold(-montant_remboursement);
    }


    private void add_don(int m) {
        dons += m;
        change_sold(m);
    }


    private void add_cotise() {
        cotisations += montant_cotise;
        change_sold(montant_cotise);
    }

    private void reset_money() {
        cotisations = 0;
        dons = 0;
        facture = 0;
        defreiment = 0;

    }
    //endregion

    //envoie au donateur une demende de don elle contient le dernier rapport
    public void ask_money() {
        //TODO send report to one donator
    }

    public void ask_money_all() {
        //TODO send report to all donator
    }


    public Tree mayor_tree_select(ArrayList<Tree> t_list) {
        //TODO moove into mairie
        return null;
    }

    //fait le bilan de fin d'annee
    public void end_year() {
        ban();
        reset_money();
        reset_member_payment();
        ArrayList<Tree> trees5 = top5tree();
        Tree the_choosen_one = mayor_tree_select(trees5);
        make_tree_remarkable(the_choosen_one);
        String report = generateRapport();
        ask_money_all();
    }

    //generateRapport() rapport financier
    public String generateRapport() {
        String report = "--- RAPPORT ---\n" + "--- Dépenses --- \n " + "Total Factures : " + this.facture + "\n" + " Total Visites : " + this.defreiment + "\n" + "--- Revenus ---\n" + "Total Factures : " + this.facture + "\n" + " Total Visites : " + this.defreiment + "\n" + "--- Solde ---\n" + "Solde : " + this.solde + "\n";

        return report;

    }


    //region members manage

    //exclusion des membres qui ont pas payer
    private void reset_member_payment() {
        //TODO reset memeber payment
    }


    public void pay_cotisation() {
        //TODO add member and make it pay
        add_cotise();
    }

    public void desinscrire(Member m) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i) == m) {
                desinscrire(i, m);
                return;
            }
        }
        //TODO warn user of the execution?
        System.out.println("Not done yet!");
    }

    public void desinscrire(int index, Member m) {
        m.deleteMember();//clean BD
        members.remove(index); //remove from member list
    }


    public void ban() {
        for (int i = 0; i < members.size(); i++) {
            if (!members.get(i).hasPayed()) {
                desinscrire(i, members.get(i));
            }
        }
        //TODO warn user of ban?
    }

    //endregion

    //region trees manage
    private void make_tree_remarkable(Tree the_chosen_one) {
        //TODO make one remarkable
    }

    //selection 5 arbres dans ceux proposé par les membres
    private ArrayList<Tree> top5tree() {
        //TODO do sql request
        return null;
    }
    //endregion

    //region visite manage

    //demende de visite (check si arbre dispo dans +add si oui)
    public void ask_visite(Tree t, Member member, Date date) {
        if (!can_pay(montant_remboursement)) {
            //TODO warn, currently not enough money
            //cancel visit creation
            return;
        }

        if (member.getNbVisites() < nb_max_visite) {
            Visite v = new Visite(date, t);
            for (int i = 0; i < members.size(); i++) {
                ArrayList<Visite> member_visite = members.get(i).get_visites();
                for (int j = 0; j < member_visite.size(); j++) {
                    if (member_visite.get(j).getDate() == v.getDate() && member_visite.get(j).getTree() == v.getTree()) {
                        //TODO warn the user a visit is already on the same tree visit was not accepted
                        return;

                    }
                }
            }

            //add visite to member
            member.add_visite(v);
            member.incVisites();
        } else {
            //TODO warn user : member cant more do visit and visit not creeated
        }
    }

    //ajoute le rapport a la visite + defreiment
    public void do_visite(Visite v, String report) {
        if (!can_pay(montant_remboursement)) {
            //TODO warn user not enough money, report not validate
            return;
        }
        pay_visite();
        v.setRapport(report);
    }

    public void ask_visite(Tree t, Member member) {
        Date date = new Date(); // This object contains the current date value
        ask_visite(t, member, date);
    }

    //endregion

    public void do_facture(int m) {
        if (!can_pay(m)) {
            //TODO warn user not enough money, invoice not paid
            return;
        }
        pay_facture(m);
    }

    public void give_don(int m) {
        add_don(m);
    }


    //region Simple getter and setter
    public int getNb_max_visite() {
        return nb_max_visite;
    }

    public void setNb_max_visite(int nb_max_visite) {
        this.nb_max_visite = nb_max_visite;
    }

    public int getMontant_remboursement() {
        return montant_remboursement;
    }

    public void setMontant_remboursement(int montant_remboursement) {
        this.montant_remboursement = montant_remboursement;
    }

    public int getMontant_cotise() {
        return montant_cotise;
    }

    public void setMontant_cotise(int montant_cotise) {
        this.montant_cotise = montant_cotise;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Externe> getDonnateurs() {
        return donnateurs;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public int getSolde() {
        return solde;
    }

    public int getCotisations() {
        return cotisations;
    }

    public int getDons() {
        return dons;
    }

    public int getFacture() {
        return facture;
    }

    public int getDefreiment() {
        return defreiment;
    }

    //endregion

    //region adder in arraylist
    public void add_member(Member m) {
        members.add(m);
    }

    public void add_tree(Tree t) {
        trees.add(t);
    }

    public void add_donnateurs(Externe e) {
        donnateurs.add(e);
    }
    //endregion

}
