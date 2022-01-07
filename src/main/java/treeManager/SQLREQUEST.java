package treeManager;

public class SQLREQUEST
    {
        // Tree
        public static String selectTree = "SELECT TOP 1 FROM tree WHERE id = ?";
        public static String selectAllTree = "SELECT * FROM tree";
        public static String selectVisiteForTree = "SELECT * FROM visite WHERE id = (SELECT TOP 1 id_visite FROM tree_visite WHERE id_tree = ?)";
        public static String selectFiveTreeByVote = "";
        public static String insertTree = "INSERT INTO tree VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        public static String insertTreeVisite = "INSERT INTO tree_visite VALUES (?,?)";
        public static String updateTree = "UPDATE tree SET name_fr = ?, age = ?, height = ?, thickness = ?, species = ?, type = ?, remarquable = ?, location = ?, num_votes = ?, domain = ?, address = ?, address_details = ?, district = ? WHERE id = ?";
        public static String updateTreeVisite = "UPDATE tree_visite SET id_tree = ?, id_visite = ? WHERE id = ?";
        
        // Visite
        public static String selectVisite = "SELECT TOP 1 FROM visite WHERE id = ?";
        public static String insertVisite = "INSERT INTO visite VALUES (?,?,?)";
        public static String updateVisite = "UPDATE visite SET date = ? WHERE id = ?";
    }
