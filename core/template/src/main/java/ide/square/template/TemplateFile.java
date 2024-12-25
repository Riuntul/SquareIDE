package ide.square.template;

public class TemplateFile extends BaseTemplateFile {
    private String content;

    public TemplateFile(String fileName, String content) {
        super(fileName);
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}