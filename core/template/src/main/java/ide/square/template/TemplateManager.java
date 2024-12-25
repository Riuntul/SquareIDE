package ide.square.template;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TemplateManager {
    private Map<String, BaseTemplate> templateModules;
    private Path projectPath;

    public TemplateManager(String projectPath) {
        templateModules = new HashMap<>();
        this.projectPath = Paths.get(projectPath);
    }

    public Path getProjectPath() {
        return projectPath;
    }

    public void loadTemplate(BaseTemplate templateModule) {
        templateModules.put(templateModule.getName(), templateModule);
    }

    public BaseTemplate getTemplate(String name) {
        return templateModules.get(name);
    }

    public void saveTemplate(String name) throws IOException {
        if (projectPath == null) {
            throw new IllegalStateException("Project location is not set.");
        }
        BaseTemplate templateModule = templateModules.get(name);
        if (templateModule != null) {
            templateModule.saveTemplateFiles(projectPath.toString());
        } else {
            throw new IllegalArgumentException("Template not found: " + name);
        }
    }

    public void applyTemplate(String name) throws IOException {
        if (projectPath == null) {
            throw new IllegalStateException("Project location is not set.");
        }
        BaseTemplate templateModule = templateModules.get(name);
        if (templateModule != null) {
            templateModule.applyTemplateFiles(projectPath.toString());
        } else {
            throw new IllegalArgumentException("Template not found: " + name);
        }
    }

    public void removeTemplate(String name) {
        templateModules.remove(name);
    }
}