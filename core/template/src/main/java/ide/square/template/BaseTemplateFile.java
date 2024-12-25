package ide.square.template;

public abstract class BaseTemplateFile {
    private String fileName;

    public BaseTemplateFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public abstract String getContent();

    @Override
    public String toString() {
        return "BaseTemplateFile{fileName='" + fileName + "'}";
    }
}