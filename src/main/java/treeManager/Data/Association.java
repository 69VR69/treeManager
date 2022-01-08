package treeManager.Data;

import treeManager.Entity.Externe;
import treeManager.Entity.Member;

import java.util.ArrayList;
import java.util.Date;

public class Association
    {

        private ArrayList<Member> members;
        private ArrayList<Externe> donnaterus;
        private ArrayList<Tree> trees;
        private int nb_max_visite;
        private int montant_remboursement;
        private int montant_cotise;

        private int solde; //can't negative
        private int cotisation; //somme cotisation
        private int dons; //somme des dons
        private int facture; //somme des facture
        private int defreiment; //somes des defreiments

        Association(){
            this.solde=0;
        }

        Association(int solde){
            this.solde = solde;
        }

        public void add_member(Member m){
            members.add(m);
        }

        public void add_tree(Tree t){
            trees.add(t);
        }



        //verifiy asso has the money
        private boolean can_pay(int m){
            return ((solde=+m)>0);
        }

        //modify the sold of asso
        private void change_sold(int m){
            solde +=m;
        }

        private void pay_facture(int m){
            facture += m;
            change_sold(-m);
        }


        private void pay_visite(){
            defreiment+=montant_remboursement;
            change_sold(-montant_remboursement);
        }


        private void add_don(int m){
            dons+=m;
            change_sold(m);
        }


        private void add_cotise(){
            cotisation+=montant_cotise;
            change_sold(montant_cotise);
        }







        public void ask_money(){//envoie au donateur une demende de don elle contient le dernier rapport

        }


        //fait le bilan de fin d'annee
        public void end_year(){
            //ban membre


        }

        //generateRapport() rapport financier
        public void generateRapport(){
            String report =
                    "--- RAPPORT ---\n"+
                    "--- Dépenses --- \n " +
                    "Total Factures : "+ this.facture+ "\n" +
                    " Total Visites : "+this.defreiment+"\n"+
                    "--- Revenus ---\n"+
                    "Total Factures : "+ this.facture+ "\n" +
                    " Total Visites : "+this.defreiment+"\n"+
                    "--- Solde ---\n"+
                    "Solde : "+this.solde+"\n"
                    ;

            //TODO soit le return soit le print

        }


        //exclusion des membres () maybe trigger sql
        public void ban(){

            //TODO iterate over members and trash unpaid one
        }

        //selection 5 arbres dans ceux proposé par les membres
        public void top5tree(){

        }

        //créer visite
        public void ask_visite(Tree t, Member member, Date date){ //demende de visite (check si arbre dispo dans +add si oui
            if (!can_pay(montant_remboursement)){
                //TODO warn, currently not enough money
                //TODO to see if block the reservation of visite

            }



            if (member.getNbVisites()<nb_max_visite){//check if not
               //TODO chage the id
                Visite v= new Visite(0, member,date);
                //TODO add check not multiple visit on the same day
                t.add_visite(v);
                member.incVisites();
            }
            else {
                //TODO warn user : member cant do visit and visit not creeated
            }
        }

        //ajoute le rapport a la visite + defreiment
        public void  do_visite(Visite v,String report){
            if (!can_pay(montant_remboursement)){
                //TODO warn user not enough money, report not validate
                return;
            }

            pay_visite();
            v.setRapport(report);



        }

        public void ask_visite(Tree t, Member member ){
            Date date = new Date(); // This object contains the current date value
            ask_visite(t,member,date);
        }

        public void desinscrire(Member m) {
            for (int i =0; i< members.size();i++){
                if (members.get(i)==m){
                    desinscrire(i,m);
                    return;
                }
            }

            //TODO warn user the execution?
            System.out.println("Not done yet!");
        }

        public void desinscrire(int index,Member m) {
            m.deleteMember();//clean BD
            members.remove(index); //remove from member list

        }


    }
