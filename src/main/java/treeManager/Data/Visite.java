package treeManager.Data;

import treeManager.Entity.Member;

import java.util.Date;

public class Visite
    {
        private int id;
        private Date date;
        private String rapport;
        private Tree tree;
        
        public Visite( Date date, Tree tree)
            {
                this.id = 0;
                this.date = date;
                this.tree=tree;
                this.rapport = null;
            }
        
        public Visite(int id, Date date, String rapport,Tree tree)
            {
                this.id = id;
                this.date = date;
                this.tree=tree;
                this.rapport = rapport;
            }
        
        public Visite()
            {
            
            }
        
        public int getId()
            {
                return id;
            }
        
        public void setId(int id)
            {
                this.id = id;
            }
        
        public Date getDate()
            {
                return date;
            }
        
        public void setDate(Date date)
            {
                this.date = date;
            }
        
        public String getRapport()
            {
                return rapport;
            }
        
        public void setRapport(String rapport)
            {
                this.rapport = rapport;
            }
    
        public Tree getTree()
            {
                return tree;
            }
    
        public void setTree(Tree tree)
            {
                this.tree = tree;
            }
    
        @Override public String toString()
            {
                return "Visite{" + "date=" + date + ", rapport='" + rapport + '\'' + '}';
            }
    }
