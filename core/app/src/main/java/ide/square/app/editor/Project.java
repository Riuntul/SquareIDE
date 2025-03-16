package ide.square.app.editor;

import ide.square.tooling.GradleToolingServer;
import java.io.File;

public class Project {
    private GradleToolingServer mTooling;
    
    private String mName;
    private String mPath;
    
    private String mTaskName;
    
    public Project(String path) {
        mPath = path;
        init();
    }
    
    private void init() {
        mTooling = new GradleToolingServer(mPath);
        }
    
    public String getName() {
        return mName;
    }
}