package ide.square.app.editor;

import java.io.File;

public class ProjectManager {
    private Project project;

    public void openProject(String projectPath) {
        File projectDir = new File(projectPath);
        
        if (projectDir.exists() && projectDir.isDirectory()) {
            project = new Project(projectPath);
            
            System.out.println("Project opened: " + project.getName());
        } else {
            System.out.println("Project directory not found: " + projectPath);
        }
    }
    
    public Project getProject() {
        return project;
    }
}