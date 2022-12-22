import data.DataReader;
import data.StaticData;
import filebase.FileBase;
import graph.exceptions.GraphException;
import writers.Output;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        DataReader dataReader = new DataReader(StaticData.INPUT_FOLDER);
        HashMap<String, String> data = dataReader.readData();
        FileBase fileBase = new FileBase(data);

        try {
            String[] sortedFiles = fileBase.topologicalSort();
            Output.printInConsole(sortedFiles);
            String[] sortedTexts = fileBase.getText(sortedFiles);
            String concatText = Helpers.concatStrings(sortedTexts);
            Output.printInFile(concatText);
        } catch (GraphException exception) {
            Output.printInConsole(exception.getMessage());
        }
    }
}