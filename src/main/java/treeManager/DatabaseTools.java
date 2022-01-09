package treeManager;

import treeManager.Data.Association;
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
                this.open(user, password);
            }
        
        public void open(String user, String password)
            {
                try
                    {
                        Class.forName("org.mariadb.jdbc.Driver");
                        connection = DriverManager.getConnection(this.composeUrl(),user,password);
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        private String composeUrl()
            {
                String url =
                 "jdbc:mariadb://" + ip + ":3306/tree_manager";
                
                System.out.println("Connection string : " + url);
                
                return url;
            }
        
        public String getUser()
            {
                return user;
            }
    
        public String getPassword()
            {
                return password;
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
                                ArrayList<Visite> visiteList = new ArrayList<>();
                                PreparedStatement ps2 = connection.prepareStatement(SQLREQUEST.selectVisiteForTree);
                                ps2.setInt(1, rs.getInt("id"));
                                ResultSet rs2 = ps2.executeQuery();
                                
                                while (rs2.next())
                                    {
                                        Member member = getMemberById(rs2.getInt("id_membre"));
                                        visiteList.add(new Visite(rs2.getInt("id"), rs2.getDate("date"), rs2.getString("rapport"), member));
                                    }
                                
                                treeList.add(new Tree(rs.getInt("id"), rs.getString("name_fr"), rs.getString("age"), rs.getInt("height"), rs.getInt("thickness"), rs.getString("species"), rs.getString("type"), rs.getBoolean("remarquable"), rs.getString("location"), rs.getInt("num_votes"), rs.getString("domain"), rs.getString("address"), rs.getString("address_details"), rs.getString("district"), visiteList));
                            }
                        return treeList;
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return treeList;
            }
        
        public ArrayList<Tree> getFiveTreeByVote()
            {
                ArrayList<Tree> treeList = new ArrayList<>();
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectFiveTreeByVote);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next())
                            {
                                PreparedStatement ps2 = connection.prepareStatement(SQLREQUEST.selectVisiteForTree);
                                ps2.setInt(1, rs.getInt("id"));
                                ResultSet rs2 = ps2.executeQuery();
                                ArrayList<Visite> visiteList = new ArrayList<>();
                                while (rs2.next())
                                    {
                                        Member member = getMemberById(rs2.getInt("id_member"));
                                        visiteList.add(new Visite(rs2.getInt("id"), rs2.getDate("date"), rs2.getString("rapport"), member));
                                    }
                                
                                treeList.add(new Tree(rs.getInt("id"), rs.getString("name_fr"), rs.getString("age"), rs.getInt("height"), rs.getInt("thickness"), rs.getString("species"), rs.getString("type"), rs.getBoolean("remarquable"), rs.getString("location"), rs.getInt("num_votes"), rs.getString("domain"), rs.getString("address"), rs.getString("address_details"), rs.getString("district"), visiteList));
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
                                ps.setInt(1, v.getId());
                                ps.setInt(2, t.getId());
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
                       // ps.setInt(4, v.getMember().getId());
                        
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
                        //ps.setInt(3, v.getMember().getId());
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
        
        private Member getMemberById(int id_member) //TODO : is in loop
        {
            try
                {
                    PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectMember);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    
                    ArrayList<Tree> treeList = new ArrayList<>();
                    PreparedStatement ps2 = connection.prepareStatement(SQLREQUEST.selectTreeForMember);
                    ps2.setInt(1, rs.getInt("id"));
                    ResultSet rs2 = ps2.executeQuery();
                    
                    while (rs2.next())
                        {
                            //treeList.add(new Tree());
                        }
                    
                    if (rs.getBoolean("is_president"))
                        return new President(rs.getInt("id"), rs.getString("name"), rs.getInt("num_visite"), rs.getBoolean("has_played"), treeList);
                    else
                        return new Member(rs.getInt("id"), rs.getString("name"), rs.getInt("num_visite"), rs.getBoolean("has_played"), treeList);
                }
            catch (Exception e)
                {
                    e.printStackTrace();
                }
            
            return null;
        }
        
        private boolean isAlreadyInBD(Member m)
            {
                if (m.getId() == 0)
                    return false;
                
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectMember);
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
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertMember);
                        
                        ps.setString(1, m.getNom());
                        ps.setInt(2, m.getNbVisites());
                        ps.setBoolean(3, m.hasPayed());
                        ps.setBoolean(4, (m instanceof President));
                        
                        ps.executeUpdate();
                        
                        ResultSet getId = ps.getGeneratedKeys();
                        getId.next();
                        m.setId(getId.getInt("id"));
                        
                        for (Tree t : m.getProposedTrees())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.insertMemberTree);
                                ps.setInt(1, m.getId());
                                ps.setInt(2, t.getId());
                                ps.executeUpdate();
                            }
                        
                        ps.close();
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
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateMember);
                        
                        ps.setString(1, m.getNom());
                        ps.setInt(2, m.getNbVisites());
                        ps.setBoolean(3, m.hasPayed());
                        ps.setBoolean(4, (m instanceof President));
                        //Where
                        ps.setInt(5, m.getId());
                        
                        ps.executeUpdate();
                        
                        for (Tree t : m.getProposedTrees())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.updateMemberTree);
                                ps.setInt(1, t.getId());
                                ps.setInt(2, m.getId());
                                ps.executeUpdate();
                            }
                        
                        ps.close();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        private void removeMember(Member m)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.deleteMember);
                        //Where
                        ps.setInt(1, m.getId());
                        
                        ps.executeUpdate();
                        
                        ps.close();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        //endregion
        
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
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectExterne);
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
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertExterne);
                        
                        //ps.setInt(1, e.getId());
                        
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
        
        private void updateExterne(Externe e) //Do nothing
        {
            try
                {
                    PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateExterne);
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
        
        //region Association
        public Association getAssociation()
            {
                Association association;
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectAssociation);
                        ResultSet rs = ps.executeQuery();
                        rs.next();
                        
                        //Externe
                        PreparedStatement ps1 = connection.prepareStatement(SQLREQUEST.selectAssociationExterne);
                        ps1.setInt(1, rs.getInt("id"));
                        ResultSet rs1 = ps1.executeQuery();
                        ArrayList<Externe> externeList = new ArrayList<>();
                        while (rs1.next())
                            {
                                Externe tempExterne = new Externe(rs1.getInt("id"));
                                externeList.add(tempExterne);
                            }
                        
                        //Member
                        PreparedStatement ps2 = connection.prepareStatement(SQLREQUEST.selectAssociationMember);
                        ps2.setInt(1, rs.getInt("id"));
                        ResultSet rs2 = ps2.executeQuery();
                        ArrayList<Member> memberList = new ArrayList<>();
                        while (rs2.next())
                            {
                               // Member tempMember = new Member(rs1.getInt("id"), ); //TODO : will loop with tree
                               // memberList.add(tempMember);
                            }
                        
                        //Tree
                        PreparedStatement ps3 = connection.prepareStatement(SQLREQUEST.selectAssociationTree);
                        ps3.setInt(1, rs.getInt("id"));
                        ResultSet rs3 = ps3.executeQuery();
                        ArrayList<Tree> treeList = new ArrayList<>();
                        while (rs3.next())
                            {
                                ArrayList<Visite> visiteList = new ArrayList<>();
                                PreparedStatement ps3bis = connection.prepareStatement(SQLREQUEST.selectVisiteForTree);
                                ps3bis.setInt(1, rs3.getInt("id"));
                                ResultSet rs3bis = ps3bis.executeQuery();
    
                                while (rs3bis.next())
                                    {
                                        Member member = getMemberById(rs3bis.getInt("id_membre"));
                                        visiteList.add(new Visite(rs3bis.getInt("id"), rs3bis.getDate("date"), rs3bis.getString("rapport"), member));
                                    }
                                
                                Tree tempTree = new Tree(rs3.getInt("id"), rs3.getString("name_fr"), rs3.getString("age"), rs3.getInt("height"), rs3.getInt("thickness"), rs3.getString("species"), rs3.getString("type"), rs3.getBoolean("remarquable"), rs3.getString("location"), rs3.getInt("num_votes"), rs3.getString("domain"), rs3.getString("address"), rs3.getString("address_details"), rs3.getString("district"), visiteList);
                                treeList.add(tempTree);
                            }
                        
                        return new Association(rs.getInt("num_max_visite"), rs.getInt("refund_amount"), rs.getInt("contributed_amount"), rs.getInt("balance"), rs.getInt("contribution"), rs.getInt("donation"), rs.getInt("invoice"), rs.getInt("defrayal"), memberList, externeList, treeList);
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                
                return null;
            }
        
        private void updateAssociation(Association a)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateAssociation);
                        
                        ps.setInt(1, a.getNb_max_visite());
                        ps.setInt(2, a.getMontant_remboursement());
                        ps.setInt(3, a.getMontant_cotise());
                        ps.setInt(4, a.getSolde());
                        ps.setInt(5, a.getCotisations());
                        ps.setInt(6, a.getDons());
                        ps.setInt(7, a.getFacture());
                        ps.setInt(8, a.getDefreiment());
                        //Where
                        ps.setInt(14, 0);
                        
                        ps.executeUpdate();
                        
                        for (Member m : a.getMembers())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.updateAssociationMember);
                                ps.setInt(1, 0);
                                ps.setInt(2, m.getId());
                                ps.executeUpdate();
                            }
                        
                        for (Externe e : a.getDonnateurs())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.updateAssociationExterne);
                                ps.setInt(1, 0);
                                ps.setInt(2, e.getId());
                                ps.executeUpdate();
                            }
                        
                        for (Tree t : a.getTrees())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.updateAssociationTree);
                                ps.setInt(1, 0);
                                ps.setInt(2, t.getId());
                                ps.executeUpdate();
                            }
                        
                        ps.close();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        //endregion
    }
