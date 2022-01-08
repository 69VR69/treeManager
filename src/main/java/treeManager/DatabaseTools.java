package treeManager;

import treeManager.Data.Tree;
import treeManager.Data.Visite;
import treeManager.Entity.Externe;
import treeManager.Entity.Member;
import treeManager.Entity.President;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseTools
    {
        private final String ip;
        private final String user;
        private final String password;
        private Connection connection;
        
        public DatabaseTools(String ip, String user, String password)
            {
                this.ip = ip;
                this.user = user;
                this.password = password;
                this.open();
            }
        
        public void open()
            {
                try
                    {
                        Class.forName("org.mariadb.jdbc.Driver");
                        connection = DriverManager.getConnection(this.composeUrl());
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
    
        private String composeUrl()
            {
                return "jdbc:mariadb://" + ip + ":3306/tree_manager?user=" + getUser() + "&password=" + getPassword();
            }
    
    
        //region Tree
        public ArrayList<Tree> getAllTree()
            {
                ArrayList<Tree> treeList = new ArrayList<>();
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectAllTree);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next())
                            {
                                PreparedStatement ps2 = connection.prepareStatement(SQLREQUEST.selectVisiteForTree);
                                ps2.setInt(1, rs.getInt("idPrimaire"));
                                ResultSet rs2 = ps2.executeQuery();
                                ArrayList<Visite> visiteList = new ArrayList<>();
                                while (rs2.next())
                                    {
                                        Member member = new Member("test"); // TODO get member
                                        visiteList.add(new Visite(rs2.getInt("id"), rs2.getDate("date"), rs2.getString("rapport"), member));
                                    }
                                
                                treeList.add(new Tree(rs.getInt("idPrimaire"), rs.getString("name_fr"), rs.getString("age"), rs.getInt("height"), rs.getInt("thickness"), rs.getString("species"), rs.getString("type"), rs.getBoolean("remarquable"), rs.getString("location"), rs.getInt("num_votes"), rs.getString("domain"), rs.getString("address"), rs.getString("address_details"), rs.getString("district"), visiteList));
                            }
                        return treeList;
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return treeList;
            }
        
     /*   public ArrayList<Tree> getFiveTreeByVote()
            {
                ArrayList<Tree> treeList = new ArrayList<>();
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectFiveTreeByVote);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next())
                            {
                                PreparedStatement ps2 = connection.prepareStatement(SQLREQUEST.selectVisiteForTree);
                                ps2.setInt(1, rs.getInt("idPrimaire"));
                                ResultSet rs2 = ps2.executeQuery();
                                ArrayList<Visite> visiteList = new ArrayList<>();
                                while (rs2.next())
                                    {
                                        Member member = getMemberById(rs2.getInt("id_member"));
                                        visiteList.add(new Visite(rs2.getInt("id"), rs2.getDate("date"), rs2.getString("rapport"), member));
                                    }
                                
                                treeList.add(new Tree(rs.getInt("idPrimaire"), rs.getString("name_fr"), rs.getString("age"), rs.getInt("height"), rs.getInt("thickness"), rs.getString("species"), rs.getString("type"), rs.getBoolean("remarquable"), rs.getString("location"), rs.getInt("num_votes"), rs.getString("domain"), rs.getString("address"), rs.getString("address_details"), rs.getString("district"), visiteList));
                            }
                        return treeList;
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return treeList;
            }*/
    
        public void addAllTree(ArrayList<Tree> treeList)
            {
                for (Tree t : treeList)
                    addTree(t);
            }
        
        public void addTree(Tree t)
            {
                if (isAlreadyInBD(t))
                    updateTree(t);
                else
                    insertTree(t);
            }
        
        private boolean isAlreadyInBD(Tree t)
            {
                if (t.getId() == 0)
                    return false;
                
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectTree);
                        ps.setInt(1, t.getId());
                        ResultSet rs = ps.executeQuery();
                        return rs.next();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return false;
            }
        
        private void insertTree(Tree t)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertTree);
                        
                        ps.setString(1, t.getNomfr());
                        ps.setString(2, t.getAge());
                        ps.setInt(3, t.getHauteur());
                        ps.setInt(4, t.getEpaisseur());
                        ps.setString(5, t.getEspece());
                        ps.setString(6, t.getGenre());
                        ps.setBoolean(7, t.isRemarquable());
                        ps.setString(8, t.getEmplacement());
                        ps.setInt(9, t.getNb_votes());
                        ps.setString(10, t.getDomaine());
                        ps.setString(11, t.getAdresse());
                        ps.setString(12, t.getComplement());
                        ps.setString(13, t.getArrondissement());
                        
                        ps.executeUpdate();
                        
                        ResultSet getId = ps.getGeneratedKeys();
                        getId.next();
                        t.setId(getId.getInt("id"));
                        
                        for (Visite v : t.getVisites())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.insertTreeVisite);
                                ps.setInt(1, t.getId());
                                ps.setInt(2, v.getId());
                                ps.executeUpdate();
                            }
                        ps.close();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        private void updateTree(Tree t)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateTree);
                        
                        ps.setString(1, t.getNomfr());
                        ps.setString(2, t.getAge());
                        ps.setInt(3, t.getHauteur());
                        ps.setInt(4, t.getEpaisseur());
                        ps.setString(5, t.getEspece());
                        ps.setString(6, t.getGenre());
                        ps.setBoolean(7, t.isRemarquable());
                        ps.setString(8, t.getEmplacement());
                        ps.setInt(9, t.getNb_votes());
                        ps.setString(10, t.getDomaine());
                        ps.setString(11, t.getAdresse());
                        ps.setString(12, t.getComplement());
                        ps.setString(13, t.getArrondissement());
                        //Where
                        ps.setInt(14, t.getId());
                        
                        ps.executeUpdate();
                        
                        for (Visite v : t.getVisites())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.updateTreeVisite);
                                ps.setInt(1, t.getId());
                                ps.setInt(2, v.getId());
                                ps.executeUpdate();
                            }
                        
                        ps.close();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        //endregion Tree
        
        //region Visite
        public void addAllVisite(ArrayList<Visite> visiteList)
            {
                for (Visite v : visiteList)
                    addVisite(v);
            }
        
        public void addVisite(Visite v)
            {
                if (isAlreadyInBD(v))
                    updateVisite(v);
                else
                    insertVisite(v);
            }
        
        private boolean isAlreadyInBD(Visite v)
            {
                if (v.getId() == 0)
                    return false;
                
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectVisite);
                        ps.setInt(1, v.getId());
                        ResultSet rs = ps.executeQuery();
                        return rs.next();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return false;
            }
        
        private void insertVisite(Visite v)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertVisite);
                        
                        ps.setInt(1, v.getId());
                        ps.setDate(2, (Date) v.getDate());
                        ps.setString(3, v.getRapport());
                        
                        ps.executeUpdate();
                        
                        ResultSet getId = ps.getGeneratedKeys();
                        getId.next();
                        v.setId(getId.getInt("id"));
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        private void updateVisite(Visite v)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateVisite);
                        
                        ps.setDate(1, (Date) v.getDate());
                        ps.setString(2, v.getRapport());
                        //Where
                        ps.setInt(3, v.getId());
                        
                        ps.executeUpdate();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        //endregion
        /*
        //region Membre
        public void addAllMember(ArrayList<Member> memberList)
            {
                for (Member m : memberList)
                    addMember(m);
            }
        
        public void addMember(Member m)
            {
                if (isAlreadyInBD(m))
                    updateMember(m);
                else
                    insertMember(m);
            }
        
        private Member getMemberById(int id_member)
            {
                //TODO : complete
                return null;
            }
        
        private boolean isAlreadyInBD(Member m)
            {
                if (m.getId() == 0)
                    return false;
                
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectVisite);
                        ps.setInt(1, m.getId());
                        ResultSet rs = ps.executeQuery();
                        return rs.next();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return false;
            }
        
        private void insertMember(Member m)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertVisite);
                        
                        ps.setString(1, m.getNom());
                        ps.setInt(2, m.getNbVisites());
                        ps.setBoolean(3, m.hasPayed());
                        
                        ps.executeUpdate();
                        
                        ResultSet getId = ps.getGeneratedKeys();
                        getId.next();
                        m.setId(getId.getInt("id"));
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        private void updateMember(Member m)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateVisite);
                        
                        ps.setDate(1, (Date) m.getDate());
                        ps.setString(2, m.getRapport());
                        ps.setBoolean(3, (m instanceof President));
                        //Where
                        ps.setInt(4, m.getId());
                        
                        ps.executeUpdate();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        private void removeMember(Member m)
            {
                //TODO : complete
            }
        
        //endregion*/
        /*
        //region Externe
        public void addAllExterne(ArrayList<Externe> externeList)
            {
                for (Externe e : externeList)
                    addExterne(e);
            }
        
        public void addExterne(Externe e)
            {
                if (isAlreadyInBD(e))
                    updateExterne(e);
                else
                    insertExterne(e);
            }
        
        private boolean isAlreadyInBD(Externe e)
            {
                if (e.getId() == 0)
                    return false;
                
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectVisite);
                        ps.setInt(1, e.getId());
                        ResultSet rs = ps.executeQuery();
                        return rs.next();
                    }
                catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                return false;
            }
        
        private void insertExterne(Externe e)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertVisite);
                        
                        ps.setInt(1, e.getId());
                        ps.setDate(2, (Date) e.getDate());
                        ps.setString(3, e.getRapport());
                        
                        ps.executeUpdate();
                        
                        ResultSet getId = ps.getGeneratedKeys();
                        getId.next();
                        e.setId(getId.getInt("id"));
                    }
                catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
            }
        
        private void updateExterne(Externe e)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateVisite);
                        
                        ps.setDate(1, (Date) e.getDate());
                        ps.setString(2, e.getRapport());
                        //Where
                        ps.setInt(3, e.getId());
                        
                        ps.executeUpdate();
                    }
                catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
            }
        //endregion*/
        
        
        public String getUser()
            {
                return user;
            }
        
        public String getPassword()
            {
                return password;
            }
    }
