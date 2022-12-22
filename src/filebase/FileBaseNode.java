package filebase;

import data.StaticData;
import graph.GraphNode;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileBaseNode implements GraphNode {
    private static final Pattern isRequirePattern = Pattern.compile(StaticData.IS_REQUIRE_REG_EXP);

    private final LinkedList<String> dependencies = new LinkedList<>();
    private final String text;

    private static String getPath(String text, Matcher matcher) {
        int sliceStart = matcher.start() + "require '".length();
        int sliceEnd = matcher.end() - "'".length();
        return text.substring(sliceStart, sliceEnd);
    }

    public FileBaseNode(String text) throws IllegalArgumentException {
        this.text = text;

        // Я понимаю, что можно было просто проитерироваться по
        // строке и такой алгоритм работал бы быстрее
        // (одинаковая асимптотическая скорость, но меньшая константа)
        // но в учебных целях, чтобы на практике заиспользовать регулярные выражения,
        // я пошёл другим путём.
        while (!"".equals(text)) {
            Matcher matcher = isRequirePattern.matcher(text);
            if (matcher.find()) {
                this.dependencies.add(getPath(text, matcher));
                text = text.substring(matcher.end());
            } else {
                break;
            }
        }
    }

    public String getText() {
        return text;
    }

    public LinkedList<String> getDependencies() {
        return dependencies;
    }
}
