package treeManager.Data;


import java.util.ArrayList;


public class Tree
    {
        private int id;
        private String nomfr;
        private String age;
        private int hauteur;
        private int epaisseur;
        private String espece;
        private String genre;
        private boolean remarquable;
        private String emplacement;
        private int nb_votes;
        private String domaine;
        private String arrondissement;
        private String adresse;
        private String complement;
        
        private ArrayList<Visite> visites; //TODO : in bd link it as relational BD
        //array de rapports de visite "comtpe rendu"
        
        
        public Tree(int id, String nomfr, String age, int hauteur, int epaisseur, String espece, String genre, boolean remarquable, String emplacement, int nb_votes, String domaine, String arrondissement, String adresse, String complement, ArrayList<Visite> visites)
            {
                this.id = id;
                this.nomfr = nomfr;
                this.age = age;
                this.hauteur = hauteur;
                this.epaisseur = epaisseur;
                this.espece = espece;
                this.genre = genre;
                this.remarquable = remarquable;
                this.emplacement = emplacement;
                this.nb_votes = nb_votes;
                this.domaine = domaine;
                this.arrondissement = arrondissement;
                this.adresse = adresse;
                this.complement = complement;
                this.visites = visites;
            }
        
        public int getNb_votes()
            {
                return nb_votes;
            }
        
        public void setNb_votes(int nb_votes)
            {
                this.nb_votes = nb_votes;
            }
        
        public boolean isRemarquable()
            {
                return remarquable;
            }
        
        public void setRemarquable(boolean remarquable)
            {
                this.remarquable = remarquable;
            }
        
        public String getAge()
            {
                return age;
            }
        
        public void setAge(String age)
            {
                this.age = age;
            }
        
        public int getHauteur()
            {
                return hauteur;
            }
        
        public void setHauteur(int hauteur)
            {
                this.hauteur = hauteur;
            }
        
        public int getEpaisseur()
            {
                return epaisseur;
            }
        
        public void setEpaisseur(int epaisseur)
            {
                this.epaisseur = epaisseur;
            }
        
        public String getNomfr()
            {
                return nomfr;
            }
        
        public void setNomfr(String nomfr)
            {
                this.nomfr = nomfr;
            }
        
        public String getGenre()
            {
                return genre;
            }
        
        public void setGenre(String genre)
            {
                this.genre = genre;
            }
        
        public String getEspece()
            {
                return espece;
            }
        
        public void setEspece(String espece)
            {
                this.espece = espece;
            }
        
        public String getEmplacement()
            {
                return emplacement;
            }
        
        public void setEmplacement(String emplacement)
            {
                this.emplacement = emplacement;
            }
        
        public int getId()
            {
                return id;
            }
        
        public void setId(int id)
            {
                this.id = id;
            }
        
        public String getAdresse()
            {
                return adresse;
            }
        
        public void setAdresse(String adresse)
            {
                this.adresse = adresse;
            }
        
        public String getArrondissement()
            {
                return arrondissement;
            }
        
        public void setArrondissement(String arrondissement)
            {
                this.arrondissement = arrondissement;
            }
        
        public String getDomaine()
            {
                return domaine;
            }
        
        public void setDomaine(String domaine)
            {
                this.domaine = domaine;
            }
        
        public String getComplement()
            {
                return complement;
            }
        
        public void setComplement(String complement)
            {
                this.complement = complement;
            }
        
        public ArrayList<Visite> getVisites()
            {
                return visites;
            }
        
        public void setVisites(ArrayList<Visite> visites)
            {
                this.visites = visites;
            }
        
        @Override public String toString()
            {
                return "Tree{" + "remarquable=" + remarquable + ", age='" + age + '\'' + ", hauteur (m)=" + hauteur + ", epaisseur (cm)=" + epaisseur + ", nom francais='" + nomfr + '\'' + ", genre='" + genre + '\'' + ", espece='" + espece + '\'' + ", emplacement=" + emplacement + ", adresse='" + adresse + '\'' + ", arrondissement='" + arrondissement + '\'' + ", domaine='" + domaine + '\'' + "}\n";
            }
        
        
        Tree()
            {
                visites = new ArrayList<Visite>();
            }
        
        public void add_visite(Visite v)
            {
                visites.add(v);
            }
        
        
    }
