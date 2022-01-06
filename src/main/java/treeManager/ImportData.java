package treeManager;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import treeManager.Data.Tree;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
        
        public void importCSVAndCreateObject(String path)
            {
                List<String[]> data = importCSV(path);
                data.remove(0);
                //TODO : create Tree
                ArrayList<Tree> treeList = new ArrayList<>();
                for (String[] o : data)
                    {
                        System.out.println("TODO : Create Tree" + Arrays.toString(o));
                        treeList.add(new Tree(Integer.parseInt(o[0]), o[8], o[14], Integer.parseInt(o[13]), Integer.parseInt(o[12]), o[10], o[9], o[15].equalsIgnoreCase("oui"), Integer.parseInt(o[7]), 0, o[2], o[3], o[6], o[4], new ArrayList<>()));
                    }
                System.out.println(List.of(treeList));
            }
        
        public static void main(String[] args)
            {
                ImportData importData = new ImportData();
                importData.importCSVAndCreateObject("/data.csv");
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
