package files;

public class MyFileContent implements IContent {
    private final String text;
    private final MyFile dependency;

    public MyFileContent(String text) {
        this.text = text;
        this.dependency = null;
    }

    public MyFileContent(MyFile dependency) {
        this.dependency = dependency;
        this.text = null;
    }

    @Override
    public String getContent() {
        if (this.text != null) {
            return this.text;
        }
        if (this.dependency != null) {
            return this.dependency.getContent();
        }
        return "";
    }
}
