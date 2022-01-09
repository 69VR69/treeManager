package treeManager;

import treeManager.Data.Association;
import treeManager.Data.Tree;
import treeManager.Data.Visite;
import treeManager.Entity.Externe;
import treeManager.Entity.Member;
import treeManager.Entity.President;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe gérant les accès à la base de donnée
 */
public class DatabaseTools
    {
        private final String ip;
        private final String user;
        private final String password;
        private Connection connection;
        
        /**
         * Constructeur
         *
         * @param ip       ip du serveur MariaDB
         * @param user     identifiant de l'utilisateur du serveur MariaDB
         * @param password mot de passe de l'utilisateur du serveur MariaDB
         */
        public DatabaseTools(String ip, String user, String password)
            {
                this.ip = ip;
                this.user = user;
                this.password = password;
                this.open(user, password);
            }
        
        /**
         * Permet d'ouvrir la connexion
         *
         * @param user     identifiant de l'utilisateur du serveur MariaDB
         * @param password mot de passe de l'utilisateur du serveur MariaDB
         */
        public void open(String user, String password)
            {
                try
                    {
                        Class.forName("org.mariadb.jdbc.Driver");
                        connection = DriverManager.getConnection(this.composeUrl(), user, password);
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        /**
         * Construit l'url de connexion au serveur
         *
         * @return retourne l'url construit
         */
        private String composeUrl()
            {
                String url = "jdbc:mariadb://" + ip + ":3306/tree_manager";
                
                System.out.println("Connection string : " + url);
                
                return url;
            }
        
        /**
         * Recupère l'utilisateur
         *
         * @return retourne l'utilisateur
         */
        public String getUser()
            {
                return user;
            }
        
        /**
         * Récupère le mot de passe
         *
         * @return retourne ce mot passe
         */
        public String getPassword()
            {
                return password;
            }
        
        //region Tree
        
        /**
         * Récupère tous les arbres
         *
         * @return retourne un ArrayList d'arbres
         */
        public ArrayList<Tree> getAllTree()
            {
                ArrayList<Tree> treeList = new ArrayList<>();
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectAllTree);
                        ResultSet rs = ps.executeQuery();
                        
                        while (rs.next())
                            {
                                treeList.add(new Tree(rs.getInt("id"), rs.getString("name_fr"), rs.getString("age"), rs.getInt("height"), rs.getInt("thickness"), rs.getString("species"), rs.getString("type"), rs.getBoolean("remarquable"), rs.getString("location"), rs.getInt("num_votes"), rs.getString("domain"), rs.getString("address"), rs.getString("address_details"), rs.getString("district")));
                            }
                        return treeList;
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return treeList;
            }
        
        /**
         * Récupère les cinq arbres avec le plus de vote
         *
         * @return retourne un ArrayList d'arbres
         */
        public ArrayList<Tree> getFiveTreeByVote()
            {
                ArrayList<Tree> treeList = new ArrayList<>();
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectFiveTreeByVote);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next())
                            {
                                treeList.add(new Tree(rs.getInt("id"), rs.getString("name_fr"), rs.getString("age"), rs.getInt("height"), rs.getInt("thickness"), rs.getString("species"), rs.getString("type"), rs.getBoolean("remarquable"), rs.getString("location"), rs.getInt("num_votes"), rs.getString("domain"), rs.getString("address"), rs.getString("address_details"), rs.getString("district")));
                            }
                        return treeList;
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return treeList;
            }
        
        /**
         * Récupère les arbres remarquables trié par visite la plus ancienne
         *
         * @return retourne un ArrayList d'arbres
         */
        public ArrayList<Tree> getRemarquableTreeByOldVisite()
            {
                ArrayList<Tree> treeList = new ArrayList<>();
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectRemarquableTreeByAge);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next())
                            {
                                treeList.add(new Tree(rs.getInt("id"), rs.getString("name_fr"), rs.getString("age"), rs.getInt("height"), rs.getInt("thickness"), rs.getString("species"), rs.getString("type"), rs.getBoolean("remarquable"), rs.getString("location"), rs.getInt("num_votes"), rs.getString("domain"), rs.getString("address"), rs.getString("address_details"), rs.getString("district")));
                            }
                        return treeList;
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return treeList;
            }
        
        /**
         * Récupère un arbre en fonction de son identifiant de Base de Donnée
         *
         * @param id identifiant de Base de Donnée
         * @return retourne un arbre
         */
        public Tree getTreeById(int id)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectTree);
                        ps.setInt(1, id);
                        ResultSet rs = ps.executeQuery();
                        rs.next();
                        return new Tree(rs.getInt("id"), rs.getString("name_fr"), rs.getString("age"), rs.getInt("height"), rs.getInt("thickness"), rs.getString("species"), rs.getString("type"), rs.getBoolean("remarquable"), rs.getString("location"), rs.getInt("num_votes"), rs.getString("domain"), rs.getString("address"), rs.getString("address_details"), rs.getString("district"));
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return null;
            }
        
        /**
         * Ajoute une liste d'arbres dans la Base de Donnée
         *
         * @param treeList ArrayList d'arbres
         */
        public void addAllTree(ArrayList<Tree> treeList)
            {
                for (Tree t : treeList)
                    addTree(t);
            }
        
        /**
         * Ajoute un arbre à la base de donnée et décide s'il doit passer par une insertion ou une mise à jour
         *
         * @param t un Arbre
         */
        public void addTree(Tree t)
            {
                if (isAlreadyInBD(t))
                    updateTree(t);
                else
                    insertTree(t);
            }
        
        /**
         * Vérifie si l'arbre existe en Base de Donnée
         *
         * @param t un Arbre
         * @return retourne vrai si l'arbre existe en Base de Donnée
         */
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
        
        /**
         * Insere un arbre dans la Base de Donnée
         *
         * @param t un Arbre
         */
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
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                        System.exit(-1);
                    }
            }
        
        /**
         * Met à jour un arbre de la Base de Donnée
         *
         * @param t un Arbre
         */
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
                        
                        ps.close();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        //endregion Tree
        
        //region Visite
        
        /**
         * Récupère tous les visite
         *
         * @return retourne un ArrayList de visite
         */
        public ArrayList<Visite> getAllVisite()
            {
                ArrayList<Visite> visiteList = new ArrayList<>();
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectAllVisite);
                        ResultSet rs = ps.executeQuery();
                        
                        while (rs.next())
                            {
                                visiteList.add(new Visite(rs.getInt("id"), rs.getDate("date"), rs.getString("rapport"), getTreeById(rs.getInt("id_tree"))));
                            }
                        
                        return visiteList;
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return visiteList;
            }
        
        /**
         * Récupère une visite en fonction de son identifiant de Base de Donnée
         *
         * @param id identifiant de Base de Donnée
         * @return retourne une visite
         */
        public Visite getVisiteById(int id)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectTree);
                        ps.setInt(1, id);
                        
                        ResultSet rs = ps.executeQuery();
                        rs.next();
                        
                        return new Visite(rs.getInt("id"), rs.getDate("date"), rs.getString("rapport"), getTreeById(rs.getInt("id_tree")));
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                
                return null;
            }
        
        /**
         * Ajoute une liste de visite dans la Base de Donnée
         *
         * @param visiteList ArrayList de visite
         */
        public void addAllVisite(ArrayList<Visite> visiteList)
            {
                for (Visite v : visiteList)
                    addVisite(v);
            }
        
        /**
         * Ajoute une visite à la base de donnée et décide s'il doit passer par une insertion ou une mise à jour
         *
         * @param v une visite
         */
        public void addVisite(Visite v)
            {
                if (isAlreadyInBD(v))
                    updateVisite(v);
                else
                    insertVisite(v);
            }
        
        /**
         * Vérifie si la visite existe en Base de Donnée
         *
         * @param v une visite
         * @return retourne vrai si la visite existe en Base de Donnée
         */
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
        
        /**
         * Insere un visite dans la Base de Donnée
         *
         * @param v un visite
         */
        private void insertVisite(Visite v)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertVisite);
                        
                        ps.setDate(1, (Date) v.getDate());
                        ps.setString(2, v.getRapport());
                        ps.setInt(3, v.getTree().getId());
                        //Where
                        ps.setInt(4, v.getId());
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        /**
         * Met à jour un visite de la Base de Donnée
         *
         * @param v un visite
         */
        private void updateVisite(Visite v)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.updateVisite);
                        
                        ps.setDate(1, (Date) v.getDate());
                        ps.setString(2, v.getRapport());
                        ps.setInt(3, v.getTree().getId());
                        //Where
                        ps.setInt(4, v.getId());
                        
                        ps.executeUpdate();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        //endregion
        
        //region Membre
        
        /**
         * Ajoute une liste de membre dans la Base de Donnée
         *
         * @param memberList ArrayList de membre
         */
        public void addAllMember(ArrayList<Member> memberList)
            {
                for (Member m : memberList)
                    addMember(m);
            }
        
        /**
         * Ajoute une membre à la base de donnée et décide s'il doit passer par une insertion ou une mise à jour
         *
         * @param m un membre
         */
        public void addMember(Member m)
            {
                if (isAlreadyInBD(m))
                    updateMember(m);
                else
                    insertMember(m);
            }
        
        /**
         * Récupère une membre en fonction de son identifiant de Base de Donnée
         *
         * @param id identifiant de Base de Donnée
         * @return retourne un membre
         */
        public Member getMemberById(int id)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectTree);
                        ps.setInt(1, id);
                        ResultSet rs = ps.executeQuery();
                        rs.next();
                        
                        // Tree
                        PreparedStatement ps1 = connection.prepareStatement(SQLREQUEST.selectAssociationTree);
                        ps1.setInt(1, rs.getInt("id"));
                        ResultSet rs1 = ps1.executeQuery();
                        ArrayList<Tree> treeList = new ArrayList<>();
                        while (rs1.next())
                            {
                                treeList.add(getTreeById(rs1.getInt("id")));
                            }
                        
                        // Visite
                        PreparedStatement ps2 = connection.prepareStatement(SQLREQUEST.selectAssociationTree);
                        ps2.setInt(1, rs.getInt("id"));
                        ResultSet rs2 = ps2.executeQuery();
                        ArrayList<Visite> visiteList = new ArrayList<>();
                        
                        while (rs2.next())
                            {
                                visiteList.add(getVisiteById(rs1.getInt("id")));
                            }
                        
                        if (rs.getBoolean("is_president"))
                            return new President(rs.getInt("id"), rs.getString("name"), rs.getBoolean("has_played"), treeList, visiteList, rs.getInt("num_visite"));
                        else
                            return new Member(rs.getInt("id"), rs.getString("name"), rs.getBoolean("has_played"), treeList, visiteList, rs.getInt("num_visite"));
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return null;
            }
        
        /**
         * Récupère tous les membre
         *
         * @return retourne un ArrayList de membre
         */
        private ArrayList<Member> getAllMember()
            {
                ArrayList<Member> memberList = new ArrayList<>();
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.selectTree);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next())
                            {
                                
                                // Tree
                                PreparedStatement ps1 = connection.prepareStatement(SQLREQUEST.selectAssociationTree);
                                ps1.setInt(1, rs.getInt("id"));
                                ResultSet rs1 = ps1.executeQuery();
                                ArrayList<Tree> treeList = new ArrayList<>();
                                while (rs1.next())
                                    {
                                        treeList.add(getTreeById(rs1.getInt("id")));
                                    }
                                
                                // Visite
                                PreparedStatement ps2 = connection.prepareStatement(SQLREQUEST.selectAssociationTree);
                                ps2.setInt(1, rs.getInt("id"));
                                ResultSet rs2 = ps2.executeQuery();
                                ArrayList<Visite> visiteList = new ArrayList<>();
                                
                                while (rs2.next())
                                    {
                                        visiteList.add(getVisiteById(rs1.getInt("id")));
                                    }
                                
                                if (rs.getBoolean("is_president"))
                                    memberList.add(new President(rs.getInt("id"), rs.getString("name"), rs.getBoolean("has_played"), treeList, visiteList, rs.getInt("num_visite")));
                                else
                                    memberList.add(new Member(rs.getInt("id"), rs.getString("name"), rs.getBoolean("has_played"), treeList, visiteList, rs.getInt("num_visite")));
                            }
                        return memberList;
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                return memberList;
            }
        
        /**
         * Vérifie si le membre existe en Base de Donnée
         *
         * @param m un membre
         * @return retourne vrai si le membre existe en Base de Donnée
         */
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
        
        /**
         * Insere un membre dans la Base de Donnée
         *
         * @param m un membre
         */
        private void insertMember(Member m)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.insertMember);
                        
                        ps.setString(1, m.getNom());
                        ps.setInt(2, m.getNbVisites());
                        ps.setBoolean(3, m.hasPayed());
                        ps.setBoolean(4, (m instanceof President));
                        //Where
                        ps.setInt(5, m.getId());
                        
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
                        
                        for (Visite v : m.get_visites())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.insertMemberVisite);
                                ps.setInt(1, m.getId());
                                ps.setInt(2, v.getId());
                                ps.executeUpdate();
                            }
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        /**
         * Met à jour un membre de la Base de Donnée
         *
         * @param m un membre
         */
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
                        
                        for (Visite v : m.get_visites())
                            {
                                ps = connection.prepareStatement(SQLREQUEST.updateMemberVisite);
                                ps.setInt(1, v.getId());
                                ps.setInt(2, m.getId());
                                ps.executeUpdate();
                            }
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        /**
         * Supprime un membre de la Base de Donnée
         *
         * @param m un membre
         */
        public void removeMember(Member m)
            {
                try
                    {
                        PreparedStatement ps = connection.prepareStatement(SQLREQUEST.deleteMember);
                        //Where
                        ps.setInt(1, m.getId());
                        
                        ps.executeUpdate();
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
            }
        
        //endregion
        
        //region Association
        
        /**
         * Récupère l'association
         *
         * @return retourne l'association
         */
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
                                memberList.add(getMemberById(rs2.getInt("id")));
                            }
                        
                        //Tree
                        PreparedStatement ps3 = connection.prepareStatement(SQLREQUEST.selectAssociationTree);
                        ps3.setInt(1, rs.getInt("id"));
                        ResultSet rs3 = ps3.executeQuery();
                        ArrayList<Tree> treeList = new ArrayList<>();
                        while (rs3.next())
                            {
                                treeList.add(getTreeById(rs3.getInt("id")));
                            }
                        
                        return new Association(rs.getInt("num_max_visite"), rs.getInt("refund_amount"), rs.getInt("contributed_amount"), rs.getInt("balance"), rs.getInt("contribution"), rs.getInt("donation"), rs.getInt("invoice"), rs.getInt("defrayal"), memberList, externeList, treeList);
                    }
                catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                
                return null;
            }
        
        /**
         * Met à jour l'association
         *
         * @param a l'association
         */
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
