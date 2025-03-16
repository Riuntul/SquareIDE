package ide.square.app.language;

import ide.square.tooling.GradleToolingServer;

public class JavaLanguage {
    private GradleToolingServer mServer;
    
    public JavaLanguage(GradleToolingServer server) {
        mServer = server;
    }
    
    public void onConfigureProject() {
    }

    public void onOpenProject(String path) {}
}