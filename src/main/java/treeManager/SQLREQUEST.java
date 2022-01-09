package treeManager;

public class SQLREQUEST
    {
        // Tree
        public static String selectTree = "SELECT * FROM tree WHERE id = ? LIMIT 1";
        public static String selectAllTree = "SELECT * FROM tree";
        public static String selectFiveTreeByVote = "SELECT * FROM tree WHERE remarquable = 'false' ORDER BY num_votes, thickness, height  LIMIT 5";
        public static String insertTree = "INSERT INTO tree (name_fr,age,height,thickness,species,type,remarquable,location,num_votes,domain,address,address_details,district) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        public static String updateTree = "UPDATE tree SET name_fr = ?, age = ?, height = ?, thickness = ?, species = ?, type = ?, remarquable = ?, location = ?, num_votes = ?, domain = ?, address = ?, address_details = ?, district = ? WHERE id = ?";
        
        // Visite
        public static String selectVisite = "SELECT * FROM visite WHERE id = ? LIMIT 1";
        public static String selectAllVisite = "SELECT * FROM visite";
        public static String insertVisite = "INSERT INTO visite VALUES (?,?,?,?)";
        public static String updateVisite = "UPDATE visite SET date = ?, rapport = ?, id_membre = ? WHERE id = ?";
        
        //Membre
        public static String selectMember = "SELECT * FROM member WHERE id = ? LIMIT 1";
        public static String insertMember = "INSERT INTO tree VALUES (?,?,?,?)";
        public static String insertMemberTree = "INSERT INTO member_tree VALUES (?,?)";
        public static String insertMemberVisite = "INSERT INTO member_visite VALUES (?,?)";
        public static String updateMember = "UPDATE membre SET name = ?, num_visite = ?, has_payed = ?, is_president = ? WHERE id = ?";
        public static String updateMemberTree = "UPDATE member_tree SET id_tree = ? WHERE id_member = ?";
        public static String updateMemberVisite = "UPDATE member_visite SET id_visite = ? WHERE id_member = ?";
        public static String deleteMember = "DELETE FROM member WHERE id = ?";
        
        //Association
        public static String selectAssociation = "SELECT * FROM association";
        public static String selectAssociationExterne = "SELECT * FROM externe WHERE id = (SELECT * id_externe FROM association_externe WHERE id_association = ? LIMIT 1)";
        public static String selectAssociationMember = "SELECT * FROM member WHERE id = (SELECT * id_member FROM association_member WHERE id_association = ? LIMIT 1)";
        public static String selectAssociationTree = "SELECT * id_tree FROM association_tree WHERE id_association = ? LIMIT 1";
        public static String updateAssociation = "UPDATE association SET num_max_visite = ?, refund_amount = ?, contributed_amount = ?, balance = ?, contribution = ?, donation = ?, invoice = ?, defrayal = ? WHERE id = ?";
        public static String updateAssociationMember = "UPDATE association_member SET id_member = ? WHERE id_association = ?";
        public static String updateAssociationExterne = "UPDATE association_externe SET id_externe = ? WHERE id_association = ?";
        public static String updateAssociationTree = "UPDATE association_tree SET id_tree = ? WHERE id_association = ?";
    }
