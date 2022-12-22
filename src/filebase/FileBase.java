package filebase;

import graph.Graph;

public class FileBase extends Graph<FileBaseNode> {
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
