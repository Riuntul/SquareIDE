package ide.square.template;

public class TemplateFile {
    private String fileName;
    private String content;

    public TemplateFile(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public String toString() {
        return "TemplateFile{fileName='" + fileName + "'}";
    }
}