package ide.square.template;

import android.content.Context;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTemplate {
    private String name;
    private List<BaseTemplateFile> files;
    
    private Context context;

    public BaseTemplate(String name) {
        this.name = name;
        this.files = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<BaseTemplateFile> getFiles() {
        return files;
    }

    public void addFile(BaseTemplateFile file) {
        files.add(file);
    }

    public void removeFile(BaseTemplateFile file) {
        files.remove(file);
    }

    public abstract void createTemplateFiles();

    public void saveTemplateFiles(String directoryPath) throws IOException {
        for (BaseTemplateFile file : files) {
            File dir = new File(directoryPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(dir, file.getFileName())))) {
                writer.write(file.getContent());
            }
        }
    }

    public void applyTemplateFiles(String targetDirectoryPath) throws IOException {
        for (BaseTemplateFile file : files) {
            File dir = new File(targetDirectoryPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(dir, file.getFileName())))) {
                writer.write(file.getContent());
            }
        }
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