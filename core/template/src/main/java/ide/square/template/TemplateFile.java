package ide.square.template;

public class TemplateFile {
    private String fileName;
    private String fileContent;

    public TemplateFile(String fileName, String fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public String getFilesContent() {
        return fileContent;
    }
    
    public String getFilesName() {
        return fileName;
    }
    
    @Override
    public String toString() {
        return "TemplateFile{fileName='" + fileName + "'}";
    }
}