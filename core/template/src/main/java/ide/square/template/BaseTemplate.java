package ide.square.template;

import android.content.Context;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTemplate {
    private String name;
    private List<TemplateFile> files;
    
    private Context context;

    public BaseTemplate(String name, Context context) {
        this.name = name;
        this.files = new ArrayList<>();
        
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public List<TemplateFile> getFiles() {
        return files;
    }

    public void addFile(TemplateFile file) {
        files.add(file);
    }

    public void removeFile(TemplateFile file) {
        files.remove(file);
    }

    public abstract void createTemplateFiles();

    public void saveTemplateFiles(String directoryPath) throws IOException {
        for (TemplateFile file : files) {
            Path filePath = Paths.get(directoryPath, file.getFileName());
            Path dir = filePath.getParent();
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Files.write(filePath, file.getContent().getBytes());
        }
    }
    
    public void applyTemplateFiles(String targetDirectoryPath) throws IOException {
        saveTemplateFiles(targetDirectoryPath); 
    }
    
    public void addFileFromApkAssets(String file, String assetsFilePath) throws IOException { 
        InputStream is = context.getAssets().open(assetsFilePath);
        
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        is.close();
        
        String content = new String(buffer, StandardCharsets.UTF_8);
        addFile(new TemplateFile(file, content)); 
    }

    @Override
    public String toString() {
        return "BaseTemplate{name='" + name + "', files=" + files + "}";
    }
}