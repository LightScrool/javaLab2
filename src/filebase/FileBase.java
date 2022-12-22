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
}
