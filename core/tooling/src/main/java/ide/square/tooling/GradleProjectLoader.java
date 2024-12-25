package ide.square.tooling;

import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.eclipse.EclipseProject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GradleProjectLoader {
    private ProjectConnection connection;

    public void connect(File projectDir) {
        connection = GradleConnector.newConnector().forProjectDirectory(projectDir).connect();
    }

    public List<File> getClasspath() {
        List<File> classpath = new ArrayList<>();
        
        if (connection != null) {
            EclipseProject project = connection.getModel(EclipseProject.class);
            project.getClasspath().forEach(entry -> classpath.add(entry.getFile()));
        }
        
        return classpath;
    }

    public void close() {
        if (connection != null) {
            connection.close();
        }
    }
}