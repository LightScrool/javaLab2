import data.StaticData;
import filebase.FileBase;
import graph.exceptions.GraphException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DataReader dataReader = new DataReader(StaticData.INPUT_FOLDER);
        var data = dataReader.readData();
        FileBase fileBase = new FileBase(data);

        try {
            String[] sortedFiles = fileBase.topologicalSort();
            System.out.println(Arrays.toString(sortedFiles));

        } catch (GraphException exception) {
            System.out.println(exception.getMessage());
        }
    }
}