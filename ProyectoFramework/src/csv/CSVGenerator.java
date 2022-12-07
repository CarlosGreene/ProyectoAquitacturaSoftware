package csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVGenerator {
    
    public void generateCSV(List<String[]> list) throws IOException{
        String archCSV = "Votos.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(archCSV));

        writer.writeAll(list);

        writer.close();
    }
}
