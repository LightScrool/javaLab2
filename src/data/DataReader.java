package data;

import writers.Logging;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataReader {
    private final File fileOrDir;
    private final HashMap<String, String> currentData;

    public DataReader(String fileOrDir) {
        this.fileOrDir = new File(fileOrDir);
        this.currentData = new HashMap<>();
    }

    public DataReader(File fileOrDir) {
        this.fileOrDir = fileOrDir;
        this.currentData = new HashMap<>();
    }

    public String readFile(File file) {
        StringBuilder result = new StringBuilder();
        try (FileReader reader = new FileReader(file)) {
            int character;
            while ((character = reader.read()) != -1) {
                result.append((char) character);
            }
        } catch (IOException ex) {
            Logging.print(ex.getMessage());
        }
        return result.toString();
    }

    public String readFile(String file) {
        return readFile(new File(file));
    }

    private void readDataInner(File fileOrDir, String fullName) {
        if (!fileOrDir.isDirectory()) {
            String text = readFile(fileOrDir);
            currentData.put(fullName, text);
            return;
        }

        File[] items = fileOrDir.listFiles();
        if (items == null) {
            return;
        }
        for (File item : items) {
            readDataInner(item, ("".equals(fullName) ? fullName : fullName + "/") + item.getName());
        }
    }

    public HashMap<String, String> readData() {
        currentData.clear();
        readDataInner(fileOrDir, "");
        return currentData;
    }
}
