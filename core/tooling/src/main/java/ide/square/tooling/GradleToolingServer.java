package ide.square.tooling;

import java.io.ByteArrayOutputStream;
import java.io.File;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ModelBuilder;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;

public class GradleToolingServer {
    private String mProjectPath;
    
    private GradleConnector mConnector;
    
    private ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();
    
    public GradleToolingServer(String projectPath) {
        this.mProjectPath = projectPath;
    }
    
    public void init(String userHome) {
        mConnector = GradleConnector.newConnector().forProjectDirectory(new File(mProjectPath)).useGradleUserHomeDir(new File(userHome));
    }
    
    public void executeGradleTask(String gradleTask) {
        try (ProjectConnection connection = mConnector.connect()) {
            BuildLauncher launcher = connection.newBuild();
        
            launcher.setStandardOutput(mOutputStream);
            launcher.setStandardError(mOutputStream);

            launcher.forTasks(gradleTask);
            launcher.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ByteArrayOutputStream getLogOutputStream() {
        return mOutputStream;
    }
}