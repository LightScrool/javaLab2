import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileBase {
    private static class FileBaseNode {
        // Хотя IDEA и ругается на "Single character alternation in RegExp", без скобок результат
        // выражения будет другим
        private static final String isRequireRegExp = "require (‘|')[^’']*(’|')";
        private static final Pattern isRequirePattern = Pattern.compile(isRequireRegExp);

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

    private final HashMap<String, FileBaseNode> data = new HashMap<>();

    /**
     * Очистить базу
     */
    public void clear() {
        data.clear();
    }

    /**
     * Добавление нового файла в базу
     *
     * @param path Абсолютный путь до файла
     * @param text Содержание файла
     */
    public void upsertFile(String path, String text) throws IllegalArgumentException {
        data.put(path, new FileBaseNode(text));
    }
}
