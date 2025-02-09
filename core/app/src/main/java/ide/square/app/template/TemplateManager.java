package ide.square.app.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateManager {
    private List<Template> templates = new ArrayList<>();
    private List<TemplateFile> files = new ArrayList<>();
    
    public void addFile(TemplateFile file) {
        files.add(file);
    }
    
    public void outputProject(Template template, String path) {
        template.onCreate();
        
        for (TemplateFile file : files) {
        	File outFile = new File(path, new File(file.getPath()).getName());
            try {
                FileWriter writer = new FileWriter(outFile);
                writer.write(file.getContent());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void register(Template template) {
        templates.add(template);
    }
    
    public List<Template> getTemplates() {
        return templates;
    }
}