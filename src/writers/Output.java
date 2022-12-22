package writers;

import data.StaticData;

import java.io.FileWriter;
import java.io.IOException;

public class Output {
    private Output() {
    }

    public static void printInFile(String message) {
        try (FileWriter writer = new FileWriter(StaticData.OUTPUT_FILE)) {
            writer.write(message);
        } catch (IOException ex) {
            Logging.print(ex.getMessage());
        }
    }

    public static void printInConsole(String message) {
        System.out.println(message);
    }

    public static void printInConsole(String[] message) {
        if (message == null || message.length == 0) {
            return;
        }
        for (String str : message) {
            printInConsole(str);
        }
    }

    public static void print(String message) {
        printInFile(message);
        printInConsole(message);
    }
}
