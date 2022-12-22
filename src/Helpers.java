import java.util.Arrays;

public class Helpers {
    private Helpers() {
    }

    public static String concatStrings(Iterable<String> data) {
        if (data == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();

        for (String item : data) {
            result.append(item);
        }

        return result.toString();
    }

    public static String concatStrings(String[] data) {
        return concatStrings(Arrays.asList(data));
    }
}
