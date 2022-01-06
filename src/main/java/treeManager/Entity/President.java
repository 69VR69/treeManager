package treeManager.Entity;

import treeManager.Data.Association;

public class President extends Member
    {
        // --- Fonctions membres ---
        public void inscrire() {
            System.out.println("--- NEW MEMBER ---");
            System.out.println("Nom : ");
            String nom = Entity.lireClavier(); // TODO implements lireClavier()

            Member m = new Member(nom);
            Association asso;
            asso.addMember(m);
        }

        public void desinscrire() {
            Association asso;
            for (Member m:asso.getMembres()) { //TODO get membres ???
                String paye = m.hasPayed() ? "A payé" : "";
                System.out.println(m.getNom() + paye);
            }
        }

        // --- Constructeurs ---
        public President(String nom) {
            super(nom);
        }
    }
