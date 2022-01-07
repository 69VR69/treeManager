package treeManager;

import treeManager.Data.Tree;
import treeManager.Data.Visite;
import treeManager.Entity.Member;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseTools
    {
        private String ip = "192.168.56.101";
        private String user = "";
        private String password = "";
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
                        connection = DriverManager.getConnection(composeUrl(), this.getUser(), this.getPassword());
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        /* TREE */
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
                                        visiteList.add(new Visite(rs2.getInt("id") ,rs2.getDate("date"),rs2.getString("rapport"),member));
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
                        
                        ps.setInt(1, t.getId());
                        ps.setString(2, t.getNomfr());
                        ps.setString(3, t.getAge());
                        ps.setInt(4, t.getHauteur());
                        ps.setInt(5, t.getEpaisseur());
                        ps.setString(6, t.getEspece());
                        ps.setString(7, t.getGenre());
                        ps.setBoolean(8, t.isRemarquable());
                        ps.setString(9, t.getEmplacement());
                        ps.setInt(10, t.getNb_votes());
                        ps.setString(11, t.getDomaine());
                        ps.setString(12, t.getAdresse());
                        ps.setString(13, t.getComplement());
                        ps.setString(14, t.getArrondissement());
                        
                        ps.executeUpdate();
                        
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
        
        /* Visite */
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
        
        private String composeUrl() //TODO : finish
        {
            return ip;
        }
        
        public String getUser()
            {
                return user;
            }
        
        public String getPassword()
            {
                return password;
            }
    }
