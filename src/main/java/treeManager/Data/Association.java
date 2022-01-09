package treeManager.Data;

import treeManager.Entity.Externe;
import treeManager.Entity.Maire;
import treeManager.Entity.Member;

import java.util.ArrayList;
import java.util.Date;

/**
 *  gestion des associations
 */
public class Association {
    //region parameters

    /**
     * nombre maximum de visite pour un membre
     */
    private int nb_max_visite;
    /**
     * montant des remboursement des visites
     */
    private int montant_remboursement;
    /**
     * montant des cotisation
     */
    private int montant_cotise;

    /**
     * le solde du compte de l'association
     */
    private int solde; //can't negative

    /**
     * somme des cotisations (pour l'exercice fiscale)
     */
    private int cotisations; //somme des cotisation
    /**
     * somme des dons (pour l'exercice fiscale)
     */
    private int dons; //somme des dons
    /**
     * somme des factures (pour l'exercice fiscale)
     */
    private int facture; //somme des facture
    /**
     * somme des defreiment de visites (pour l'exercice fiscale)
     */
    private int defreiment; //somme des defreiments

    /**
     * la liste de tout les membres de l'association (presidents compris)
     */
    private ArrayList<Member> members;
    /**
     * la listes de tout des externes de l'association, (mairie et donnateurs compris)
     */
    private ArrayList<Externe> donnateurs;
    /**
     * la liste de tout les arbres gerer par l'associations
     */
    private ArrayList<Tree> trees;
    //endregion

    //region builder

    /**
     * contructeur vide on estime que le solde est a 0 (appel le constructeur avec le sold)
     */
    public Association() {
        new Association(0 );
    }

    /**
     * constructeur avec un solde, on met des valaurs par defaut et appel le constructeur avec sold, nombre de visite maximum (defaut 5) montant de la cotisation (defaut 15) et de remboursement (defaut 5)
     * @param solde le sold initial de l'association
     */
    public Association(int solde) {
        new Association(solde, 5, 15, 5);
    }

    /**
     * Constructeur qui permet d'initaliser une association lors d'une première creation en choisissant le sold et les valuer des cotisation, remboursement et le nombre maximum de visite
     *
     * @param solde le sold initial de l'association
     * @param nb_max_visite le nombre maximum qu'un membre peux faire
     * @param montant_cotise le montant des cotisations
     * @param montant_remboursement le montant des remboursement des visites
     */
    public Association(int solde, int nb_max_visite, int montant_cotise, int montant_remboursement) {
        this.nb_max_visite = nb_max_visite;
        this.montant_cotise = montant_cotise;
        this.montant_remboursement = montant_remboursement;
        this.solde = solde;

        this.cotisations = 0;
        this.dons = 0;
        this.facture = 0;
        this.defreiment = 0;

        this.members = new ArrayList<Member>();
        this.donnateurs = new ArrayList<Externe>();
        this.trees = new ArrayList<Tree>();

    }

    /**
     * Constructeur utile lors du démarage, permet de remplir toutes les onformation de l'association si elle existe deja en Base de Donnée
     *
     * @param nb_max_visite le nombre maximum de visite
     * @param montant_remboursement le montant du rembrousement des visites
     * @param montant_cotise le montant de la cotisation
     * @param solde le solde de l'association
     * @param cotisations la somme des ctisations sur l'exercice en cours
     * @param dons la somme des dons sur l'exercice en cours
     * @param facture la somme des facture sur l'exercice en cours
     * @param defreiment la somme des defreiment sur l'exercice en cours
     * @param members la liste de tout les membre de l'association
     * @param donnateurs la liste liste de tout les donnateur de l'association
     * @param trees la liste de tout les arbre gerer par l'association
     */
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

    /**
     * verifie si l'association peux payer (rappel, l'association ne peux avoir un solde negatif)
     *
     * @param m largent qu'elle devrait payer
     * @return le booleen true si elle peux se permettre la depense, false sinon
     */
    private boolean can_pay(int m) {
        return ((solde = +m) > 0);
    }


    /**
     * change le solde du compte del'association
     *
     * @param m le changement a appliquer au solde
     */
    private void change_sold(int m) {
        solde += m;
    }

    /**
     * paye une facture
     * met a jour le solde et l'exercice fiscale
     *
     * @param m le montant de la facture a payer
     */
    private void pay_facture(int m) {
        facture += m;
        change_sold(-m);
    }


    /**
     * rembourse une facture du montant de remboursement
     * met a jour le solde et l'exercice fiscale
     */
    private void pay_visite() {
        defreiment += montant_remboursement;
        change_sold(-montant_remboursement);
    }

    /**
     * permet de faire le don a l'association
     * met a jour le solde et l'exercice fiscale
     *
     * @param m le montant du don
     */
    private void add_don(int m) {
        dons += m;
        change_sold(m);
    }

    /**
     * enregistree le paiment d'une cotisation
     * met a jour le solde et l'exercice fiscale
     */
    private void add_cotise() {
        cotisations += montant_cotise;
        change_sold(montant_cotise);
    }

    /**
     * remet a 0 les sommes des cotisation, dons, factures et defreiment pour l'exercice fiscale suivant
     */
    private void reset_money() {
        cotisations = 0;
        dons = 0;
        facture = 0;
        defreiment = 0;

    }
    //endregion

    //region public money manage

    /**
     * paye une facture et verifie si l'association peux se le permetre
     *
     * @param m le montant de la facture
     */
    public void do_facture(int m) {
        if (!can_pay(m)) {
            //TODO warn user not enough money, invoice not paid
            return;
        }
        pay_facture(m);
    }

    /**
     * effectue un don a l'association
     *
     * @param m le montant du don
     */
    public void give_don(int m) {
        add_don(m);
    }

    //endregion

    //region donators



    /**
     * envoie a un donateur une demende de don, contenant le dernier rapport
     *
     * @param e ledonnateur a qui envoyer la demende de don
     * @param money_report le bilan financier actuel
     */
    public void ask_money(Externe e,String money_report) {
        e.ask_donnation(money_report);
    }

    /**
     * fait la demende a tout les donnateur de faire un don
     *
     * @param money_report le bilan financier actuel
     */
    public void ask_money_all(String money_report) {
        for (int i =0; i<donnateurs.size();i++){
            ask_money(donnateurs.get(i),money_report);
        }
    }

    //endregion

    //region end year process

    /**
     * cloture un exercice fiscale et prepar le prochain
     */
    public void end_year() {
        //search the mayor
        Maire mairie = null;
        for (int i=0; i<donnateurs.size();i++){
            if (donnateurs.get(i).getClass()== Maire.class){
                mairie = (Maire) donnateurs.get(i);
                break;
            }
        }

        if (mairie==null){
            //TODO warn user error during execution end year report was not done because their is no mayor to validate a tree
            return;
        }


        ban();
        reset_money();
        reset_member_payment();
        ArrayList<Tree> trees5 = top5tree();
        Tree the_choosen_one = mairie.nominate(trees5);
        make_tree_remarkable(the_choosen_one);
        String report = generateRapport();
        ask_money_all(report);
    }

    /**
     * genere le bilan fiscale
     *
     * @return un String du bilan fiscale
     */
    private String generateRapport() {
        String report = "--- RAPPORT ---\n" + "--- Dépenses --- \n " + "Total Factures : " + this.facture + "\n" + " Total Visites : " + this.defreiment + "\n" + "--- Revenus ---\n" + "Total Factures : " + this.facture + "\n" + " Total Visites : " + this.defreiment + "\n" + "--- Solde ---\n" + "Solde : " + this.solde + "\n";

        return report;

    }

    //endregion

    //region members manage

    //reset le payment des membres
    private void reset_member_payment() {

        for(int i = 0; i<members.size();i++){
            //TODO define function
            members.get(i).unpayCotisation();
        }

    }


    public void pay_cotisation(Member m) {
        m.payCotisation();
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
        the_chosen_one.setRemarquable(true);
    }

    //selection 5 arbres dans ceux proposé par les membres
    private ArrayList<Tree> top5tree() {
        //TODO do sql request
        String request = "select top(5) from tree where remarquable = 'false' order by num_votes,thickness,height";
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

    public void ask_visite(Tree t, Member member) {
        Date date = new Date(); // This object contains the current date value
        ask_visite(t, member, date);
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



    //endregion

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
