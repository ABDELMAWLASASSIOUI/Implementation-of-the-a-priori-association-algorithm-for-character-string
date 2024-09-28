

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String> loadTransactions(String filePath) throws IOException {
        if (filePath.endsWith(".csv")) {
            return loadTransactionsFromCSV(filePath);
        } else if (filePath.endsWith(".txt")) {
            return loadTransactionsFromTXT(filePath);
        } else {
            throw new IOException("Format de fichier non pris en charge.");
        }
    }

    //


    public static List<String> loadTransactionsFromCSV(String filePath) throws IOException {
        List<String> transactions = new ArrayList<>();
        Files.lines(Paths.get(filePath)).forEach(transactions::add);
        return transactions;
    }



    public static List<String> loadTransactionsFromTXT(String filePath) throws IOException {
        List<String> transactions = new ArrayList<>();
        Files.lines(Paths.get(filePath)).forEach(transactions::add);
        return transactions;
    }
}
