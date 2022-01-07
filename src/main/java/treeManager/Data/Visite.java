package treeManager.Data;

import treeManager.Entity.Member;

import java.util.Date;

public class Visite
    {
        private Date date;
        private String rapport;
        private Member member;

        Visite(Member member,Date date) {
            this.date = date;
            this.rapport = "";
            this.member = member;
        }

        Visite(){

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
