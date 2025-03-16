package ide.square.app.project;

import android.util.Log;
import java.io.File;

public class IDEProject {
    private static String TAG = "IDEProject";
    
    private String projectPath;
    
    private File projectDir;
    
    private boolean isOpen;
    
    public IDEProject(String projectPath) {
        this.projectPath = projectPath;
    }
    
    public void open() {
        projectDir = new File(projectPath);
        if (projectDir.exists()) {
            if (projectDir.isDirectory()) {
                
                isOpen = true;
            } else {
                Log.e(TAG, "Unable to open project, Not Directory");
            }
        } else {
            Log.e(TAG, "Unable to open project, Directory not exists.");
        }
    }
    
    public boolean isOpen() {
        return isOpen;
    }
}