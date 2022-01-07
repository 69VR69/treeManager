package treeManager.Data;

import treeManager.Entity.Member;

import java.util.Date;

public class Visite
    {
        private int id;
        private Date date;
        private String rapport;
        private Member member;

        public Visite(int id, Member member, Date date) {
            this.id = id;
            this.date = date;
            this.rapport = "";
            this.member = member;
        }
    
        public Visite(int id, Date date, String rapport, Member member)
            {
                this.id = id;
                this.date = date;
                this.rapport = rapport;
                this.member = member;
            }
    
        public Visite(){

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
        
        @Override public String toString()
            {
                return "Visite{" + "date=" + date + ", rapport='" + rapport + '\'' + '}';
            }
    }
