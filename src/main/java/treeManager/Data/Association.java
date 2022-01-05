package treeManager.Data;

import treeManager.Entity.Member;

import java.util.ArrayList;

public class Association
    {
        int solde;
        private ArrayList<Member> members;
        private ArrayList<Tree> trees;


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
        public void visite(int tree_id,int members_id, String repport){ //giv tree + connard
            for (int i =0;i<trees.size();i++){
                if (trees.get(i).getId()==tree_id){

                }
            }
        }


    }
