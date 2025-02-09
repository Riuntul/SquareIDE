package ide.square.app.template;

public class TemplateFile {
    private String path;
    private String content;
    
    public TemplateFile(String path, String content) {
        this.path = path;
        this.content = content;
    }
    
    public String getPath() {
        return path;
    }
    
    public String getContent() {
        return content;
    }
}