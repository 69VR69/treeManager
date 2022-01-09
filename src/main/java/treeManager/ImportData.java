package treeManager;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import treeManager.Data.Tree;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe qui gère les import depuis un fichier CSV
 */
public class ImportData
    {
        private final CsvParser parser;
        
        /**
         * Constructeur
         */
        public ImportData()
            {
                CsvParserSettings settings = new CsvParserSettings();
                settings.detectFormatAutomatically();
                this.parser = new CsvParser(settings);
            }
        
        /**
         * Importe les données d'un fichier CSV
         *
         * @param path chemin vers le fichier CSV
         * @return Retourne une liste de tableau représentant le fichier CSV
         */
        public List<String[]> importCSV(String path)
            {
                InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(ImportData.class.getResourceAsStream(path)), StandardCharsets.UTF_8);
                return parser.parseAll(reader);
            }
        
        /**
         * Importe les données depuis le fichier CSV et créer les arbres
         *
         * @param path chemin vers le fichier CSV
         * @return Retourne un ArrayList d'arbres
         */
        public ArrayList<Tree> importCSVAndCreateObject(String path)
            {
                ArrayList<Tree> treeList = new ArrayList<>();
                List<String[]> data = importCSV(path);
                data.remove(0);
                
                for (String[] o : data)
                    treeList.add(new Tree(Integer.parseInt(o[0]), ((o[8] == null) ? "" : o[8]), ((o[14] == null) ? "." : o[14]), Integer.parseInt(o[13]), Integer.parseInt(o[12]), ((o[10] == null) ? "." : o[10]), ((o[9] == null) ? "." : o[9]), !(o[15] == null || o[15].equalsIgnoreCase("non")), ((o[7] == null) ? "." : o[7]), 0, ((o[2] == null) ? "." : o[2]), ((o[3] == null) ? "." : o[3]), ((o[6] == null) ? "." : o[6]), ((o[4] == null) ? "." : o[4])));
                
                return treeList;
            }
        
        /**
         * Ajoute tous les arbres du fichier CSV à la Base de Donnée
         *
         * @param path chemin vers le fichier CSV
         * @param db   objet de gestion de la Base de Donnée
         */
        public void csvToDB(String path, DatabaseTools db)
            {
                db.addAllTree(importCSVAndCreateObject(path));
            }
        
        /**
         * Permet d'afficher dans la console le contenu du fichier CSV (outil de debugging)
         *
         * @param path chemin vers le fichier CSV
         */
        public void printCSV(String path)
            {
                for (String[] row : importCSV(path))
                    {
                        for (String s : row)
                            {
                                System.out.print(s + '\t');
                            }
                        System.out.print('\n');
                    }
            }
    }
