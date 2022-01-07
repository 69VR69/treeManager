package treeManager;

public class SQLREQUEST
    {
        // Tree
        public static String selectTree = "SELECT TOP 1 FROM Tree WHERE id = ?";
        public static String insertTree = "INSERT INTO Tree VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        public static String insertTreeVisite = "INSERT INTO tree_visite VALUES (?,?)";
        public static String updateTree = "UPDATE Tree SET name_fr = ?, age = ?, height = ?, thickness = ?, species = ?, type = ?, remarquable = ?, location = ?, num_votes = ?, domain = ?, address = ?, address_details = ?, district = ? WHERE id = ?";
        public static String updateTreeVisite = "UPDATE tree_visite SET id_tree = ?, id_visite = ? WHERE id = ?";
        
        // Visite
        public static String selectVisite = "SELECT TOP 1 FROM Visite WHERE id = ?";
        public static String insertVisite = "INSERT INTO Visite VALUES ()";
        public static String updateVisite = "UPDATE Visite SET date = ? WHERE id = ?";
    }
