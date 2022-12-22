package data;

public class StaticData {
    private StaticData() {
    }

    public static final String INPUT_FOLDER = "input";
    public static final String OUTPUT_FILE = "output.txt";
    public static final String IS_REQUIRE_REG_EXP = "require (‘|')[^’']*(’|')";

    public static String getMissingDependencyExceptionText(String node, String dependency) {
        return "У узла \"" + node + "\" указана зависимость \"" + dependency + "\", которой нет в графе!";
    }

    public static String getCycleExceptionText(Iterable<String> cycle) {
        StringBuilder result = new StringBuilder("Невозможно выполнить операцию из-за цикла в графе!\nЦикл:\n");
        String separator = " -> ";

        for (String item : cycle) {
            result.append(item).append(separator);
        }
        result.delete(result.length() - separator.length(), result.length());

        return result.toString();
    }
}
