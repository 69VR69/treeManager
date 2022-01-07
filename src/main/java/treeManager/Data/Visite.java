package treeManager.Data;

import treeManager.Entity.Member;

import java.util.Date;

public class Visite
    {
        private int id;
        private Date date;
        private String rapport;
        private Member member;

        Visite(int id, Member member,Date date) {
            this.id = id;
            this.date = date;
            this.rapport = "";
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
    
        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Visite{" +
                    "date=" + date +
                    ", rapport='" + rapport + '\'' +
                    '}';
        }

        public String getRapport() {
            return rapport;
        }

        public void setRapport(String rapport) {
            this.rapport = rapport;
        }
    }
