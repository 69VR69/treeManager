package treeManager.Data;

import java.util.Date;

public class Visite
    {
        private Date date;
        private String rapport;
        private int id_member;

        public Visite(Date date, String rapport) {
            this.date = date;
            this.rapport = rapport;
        }

        public Visite(){

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
