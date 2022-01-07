package treeManager;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import treeManager.Data.Tree;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImportData
    {
        private final CsvParser parser;
        
        public ImportData()
            {
                CsvParserSettings settings = new CsvParserSettings();
                settings.detectFormatAutomatically();
                this.parser = new CsvParser(settings);
            }
        
        public List<String[]> importCSV(String path)
            {
                InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(ImportData.class.getResourceAsStream(path)), StandardCharsets.UTF_8);
                return parser.parseAll(reader);
            }
        
        public ArrayList<Tree> importCSVAndCreateObject(String path)
            {
                ArrayList<Tree> treeList = new ArrayList<>();
                List<String[]> data = importCSV(path);
                data.remove(0);
                
                for (String[] o : data)
                    treeList.add(new Tree(Integer.parseInt(o[0]), o[8], o[14], Integer.parseInt(o[13]), Integer.parseInt(o[12]), o[10], o[9], !(o[15] == null || o[15].equalsIgnoreCase("non")), o[7], 0, o[2], o[3], o[6], o[4], new ArrayList<>()));
                
                return treeList;
            }
        
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
