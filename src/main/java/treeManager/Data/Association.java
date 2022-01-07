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

        private int solde; //can't negative
        private int cotisation;
        private int dons;
        private int facture;
        private int defreiment;

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

        private void add_money(){

        }

        public void ask_money(){//envoie au donateur une demende de don elle contient le dernier rapport

        }


        //generateRapport() rapport financier
        public void generateRapport(){

        }


        //exclusion des membres () maybe trigger sql
        public void ban(){

        }

        //selection 5 arbres dans ceux proposé par les membres
        public void top5tree(){

        }

        //créer visite
        public void ask_visite(Tree t, Member member, Date date){ //demende de visite (check si arbre dispo dans +add si oui
            //TODO check if money else warn currently have not enougn money
            if (member.get_nb_visite()<nb_max_visite){//check if not
                Visite v= new Visite(member,date);
                //TODO add check not multiple visit on the same day
                t.add_visite(v);
                member.inc_visite();
            }
            else {
                //TODO warn user : member cant do visit and visit not creeated
            }
        }

        //ajoute le rapport a la visite + defreiment
        public void  do_visite(Visite v,String report){
            //TODO check if money else warn not money so no refund
        }

        public void ask_visite(Tree t, Member member ){
            Date date = new Date(); // This object contains the current date value
            ask_visite(t,member,date);
        }


    }
