package ide.square.tooling;

import java.io.File;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;

public class GradleToolingServer {
    private GradleConnector connector;
    
    public GradleToolingServer(String projectPath) {
        connector = GradleConnector.newConnector().forProjectDirectory(new File(projectPath));
    }
    
    public void configureProject() {
        try (ProjectConnection connection = connector.connect()) {
            connection.model(Void.class).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}