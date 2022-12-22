package filebase;

import graph.Graph;

import java.util.Map;

public class FileBase extends Graph<FileBaseNode> {
    public FileBase() {
        super();
    }

    public FileBase(Map<String, String> data) {
        super();
        if (data == null || data.size() == 0) {
            return;
        }
        for (String key : data.keySet()) {
            upsertFile(key, data.get(key));
        }
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

    public String getText(String path) {
        FileBaseNode node = data.get(path);
        if (node == null) {
            return null;
        }
        return node.getText();
    }

    public String[] getText(String[] paths) {
        if (paths == null) {
            return null;
        }
        String[] result = new String[paths.length];
        for (int i = 0; i < paths.length; ++i) {
            result[i] = getText(paths[i]);
        }
        return result;
    }
}
