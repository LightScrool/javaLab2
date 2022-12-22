package data;

public class StaticData {
    private StaticData() {
    }

    public static final String IS_REQUIRE_REG_EXP = "require (‘|')[^’']*(’|')";

    public static String getMissingDependencyExceptionText(String node, String dependency) {
        return "У узла \"" + node + "\" указана зависимость \"" + dependency + "\", которой нет в графе";
    }
}
