package treeManager;

import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

public class ImportData
    {
        //csv parser
        public static void main(String[] args) throws UnsupportedEncodingException
            {
                CsvParserSettings settings = new CsvParserSettings();
                settings.detectFormatAutomatically();
                CsvParser parser = new CsvParser(settings);
                
                System.out.println("\nData in /examples/european.csv:");
                List<String[]> rows = parser.parseAll(getReader("/data.csv"));
                
                for (String[] row : rows)
                    {
                        for (String s : row)
                            {
                                System.out.print(s + '|');
                            }
                        System.out.println("");
                    }
                
                System.out.println("\nFormat detected in /examples/european.csv:");
                CsvFormat detectedFormat = parser.getDetectedFormat();
                System.out.println(detectedFormat);
            }
        
        public static Reader getReader(String relativePath) throws UnsupportedEncodingException
            {
                return new InputStreamReader(Objects.requireNonNull(ImportData.class.getResourceAsStream(relativePath)), "UTF-8");
                
            }
    }
