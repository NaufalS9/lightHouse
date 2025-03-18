package nl.saxion.re.sponsorrun.data;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import javafx.scene.control.Alert;
import nl.saxion.re.sponsorrun.util.WindowHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {

    // the data fields
    public static ArrayList<Politician> politicians = new ArrayList<>();
    public static Politician selectedPolitician;

    public static void updateFromDisk() {
        // clear the list
        politicians.clear();
        // read all rows and columns from the csv
        ArrayList<String[]> allRows = readData("politicians.csv", ';', true);

        // create politician objects from all the rows/columns and add to the ArrayList.
        for (String[] row : allRows) {
            Politician p = new Politician();
            p.firstName = row[0];
            p.lastName = row[1];
            p.politicalParty = row[2];
            politicians.add(p);
        }
    }

    private static ArrayList<String[]> readData(String csvFile, char separator, boolean skipRow) {
        try {
            // what a horrible syntax for configuring the csvparser. But ok.
            CSVReaderBuilder readerBuilder = new CSVReaderBuilder(new FileReader(csvFile));
            if (skipRow) {
                readerBuilder.withSkipLines(1);
            }
            CSVParserBuilder parserBuilder = new CSVParserBuilder();
            parserBuilder.withSeparator(separator);
            readerBuilder.withCSVParser(parserBuilder.build());
            CSVReader reader = readerBuilder.build();

            ArrayList<String[]> allLines = new ArrayList<>(reader.readAll());
            return allLines;
        } catch (IOException e) {
            WindowHelper.showAlert("An error occurred reading " + csvFile + "\n\n" + e, Alert.AlertType.ERROR);
        }
        return new ArrayList<>();
    }

}
