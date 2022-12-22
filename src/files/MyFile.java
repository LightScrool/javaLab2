package files;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFile implements IContent{
    private final String id;
    private final LinkedList<MyFileContent> contentList;

    public MyFile(String id, String inputStr) {
        this.id = id;

        this.contentList = new LinkedList<>();
        String isRequireRegExp = "require '.*'";
        Pattern pattern = Pattern.compile(isRequireRegExp);

        while (!"".equals(inputStr)) {
            Matcher matcher = pattern.matcher(inputStr);
            if(matcher.find()){
                String textBefore = inputStr.substring(0, matcher.start());
                if (!"".equals(textBefore)) {
                    this.contentList.add(new MyFileContent(textBefore));
                }
                this.contentList.add(new MyFileContent(new MyFile("1", "2"))); // TODO
                inputStr = inputStr.substring(matcher.end());
            } else {
                this.contentList.add(new MyFileContent(inputStr));
                inputStr = "";
            }
        }
    }

    @Override
    public String getContent() {
        StringBuilder result = new StringBuilder();

        for (MyFileContent item : contentList) {
            result.append(item.getContent());
        }

        return result.toString();
    }

    public String getId() {
        return id;
    }
}
