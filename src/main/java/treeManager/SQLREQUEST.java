package treeManager;

public class SQLREQUEST
    {
        // Tree
        public static String selectTree = "SELECT TOP 1 FROM tree WHERE id = ?";
        public static String selectAllTree = "SELECT * FROM tree";
        public static String selectVisiteForTree = "SELECT * FROM visite WHERE id = (SELECT TOP 1 id_visite FROM tree_visite WHERE id_tree = ?)";
        public static String selectFiveTreeByVote = "";
        public static String insertTree = "INSERT INTO tree VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        public static String insertTreeVisite = "INSERT INTO tree_visite VALUES (?,?)";
        public static String updateTree = "UPDATE tree SET name_fr = ?, age = ?, height = ?, thickness = ?, species = ?, type = ?, remarquable = ?, location = ?, num_votes = ?, domain = ?, address = ?, address_details = ?, district = ? WHERE id = ?";
        public static String updateTreeVisite = "UPDATE tree_visite SET id_visite = ? WHERE id_tree = ?";
        
        // Visite
        public static String selectVisite = "SELECT TOP 1 FROM visite WHERE id = ?";
        public static String insertVisite = "INSERT INTO visite VALUES (?,?,?,?)";
        public static String updateVisite = "UPDATE visite SET date = ?, rapport = ?, id_membre = ? WHERE id = ?";
        
        //Membre
        public static String deleteMember = "DELETE FROM member WHERE id = ?";
        public static String insertMember = "INSERT INTO tree VALUES (?,?,?,?)";
        public static String insertMemberTree = "INSERT INTO member_tree VALUES (?,?)";
        public static String updateMember = "UPDATE membre SET name = ?, num_visite = ?, has_payed = ?, is_president = ? WHERE id = ?";
        public static String updateMemberTree = "UPDATE member_tree SET id_tree = ? WHERE id_member = ?";
        public static String selectMember = "SELECT TOP 1 FROM member WHERE id = ?";
        public static String selectTreeForMember = "SELECT * FROM tree WHERE id = (SELECT TOP 1 id_tree FROM member_tree WHERE id_member = ?)";
    
        //Externe
        public static String selectExterne = "SELECT * FROM externe WHERE id = ?";
        public static String insertExterne = "INSERT INTO externe VALUES ()";
        public static String updateExterne = "UPDATE externe SET ? = ? WHERE id = ?";
        
        //Association
        public static String selectAssociation = "SELECT * FROM association";
        public static String selectAssociationExterne = "SELECT * FROM externe WHERE id = (SELECT TOP 1 id_externe FROM association_externe WHERE id_association = ?)";
        public static String selectAssociationMember = "SELECT * FROM member WHERE id = (SELECT TOP 1 id_member FROM association_member WHERE id_association = ?)";
        public static String selectAssociationTree = "SELECT * FROM tree WHERE id = (SELECT TOP 1 id_tree FROM association_tree WHERE id_association = ?)";
        public static String updateAssociation = "UPDATE association SET num_max_visite = ?, refund_amount = ?, contributed_amount = ?, balance = ?, contribution = ?, donation = ?, invoice = ?, defrayal = ? WHERE id = ?";
        public static String updateAssociationMember = "UPDATE association_member SET id_member = ? WHERE id_association = ?";
        public static String updateAssociationExterne = "UPDATE association_externe SET id_externe = ? WHERE id_association = ?";
        public static String updateAssociationTree = "UPDATE association_tree SET id_tree = ? WHERE id_association = ?";
    }
