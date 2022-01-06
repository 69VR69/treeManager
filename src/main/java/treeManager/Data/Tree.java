package treeManager.Data;


import java.util.ArrayList;


public class Tree
    {
        private int nb_votes;
        private boolean remarkable;
        private String age;
        private int hauteur;
        private int epeisseur;
        private String nomfr;
        private  String genre;
        private String espece;
        private int emplacement;
        private int id;
        private String adr;
        private String arrondissement;
        private String complement;
        private String domaine;


        private ArrayList<Visite> visites;
        //array de rapports de visite "comtpe rendu"

        public Tree(int nb_votes, boolean remarkable, String age, int hauteur, int epeisseur, String nomfr, String genre, String espece, int emplacement, int id, String adr, String arrondissement, String domaine, ArrayList<Visite> visites) {
            this.nb_votes = nb_votes;
            this.remarkable = remarkable;
            this.age = age;
            this.hauteur = hauteur;
            this.epeisseur = epeisseur;
            this.nomfr = nomfr;
            this.genre = genre;
            this.espece = espece;
            this.emplacement = emplacement;
            this.id = id;
            this.adr = adr;
            this.arrondissement = arrondissement;
            this.domaine = domaine;
            this.visites = visites;
        }


        public int getNb_votes() {
            return nb_votes;
        }

        public void setNb_votes(int nb_votes) {
            this.nb_votes = nb_votes;
        }

        public boolean isRemarkable() {
            return remarkable;
        }

        public void setRemarkable(boolean remarkable) {
            this.remarkable = remarkable;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public int getHauteur() {
            return hauteur;
        }

        public void setHauteur(int hauteur) {
            this.hauteur = hauteur;
        }

        public int getEpeisseur() {
            return epeisseur;
        }

        public void setEpeisseur(int epeisseur) {
            this.epeisseur = epeisseur;
        }

        public String getNomfr() {
            return nomfr;
        }

        public void setNomfr(String nomfr) {
            this.nomfr = nomfr;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getEspece() {
            return espece;
        }

        public void setEspece(String espece) {
            this.espece = espece;
        }

        public int getEmplacement() {
            return emplacement;
        }

        public void setEmplacement(int emplacement) {
            this.emplacement = emplacement;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAdr() {
            return adr;
        }

        public void setAdr(String adr) {
            this.adr = adr;
        }

        public String getArrondissement() {
            return arrondissement;
        }

        public void setArrondissement(String arrondissement) {
            this.arrondissement = arrondissement;
        }

        public String getDomaine() {
            return domaine;
        }

        public void setDomaine(String domaine) {
            this.domaine = domaine;
        }


        public ArrayList<Visite> getVisites() {
            return visites;
        }

        public void setVisites(ArrayList<Visite> visites) {
            this.visites = visites;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "remarkable=" + remarkable +
                    ", age='" + age + '\'' +
                    ", hauteur (m)=" + hauteur +
                    ", epeisseur (cm)=" + epeisseur +
                    ", nom francais='" + nomfr + '\'' +
                    ", genre='" + genre + '\'' +
                    ", espece='" + espece + '\'' +
                    ", emplacement=" + emplacement +
                    ", adresse='" + adr + '\'' +
                    ", arrondissement='" + arrondissement + '\'' +
                    ", domaine='" + domaine + '\'' +
                    '}';
        }


        Tree(){
            visites = new ArrayList<Visite>();
        }

        public void visite(Visite v){
            visites.add(v);
        }


    }
