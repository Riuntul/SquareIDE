package ide.square.app.template;

public class TemplateBuilder {
    private final LaunchRequest mLaunchRequest;
    
    private final TemplateManager mTemplateManager;
    
    public TemplateBuilder(Template template) {
        mLaunchRequest = new LaunchRequest();
        mTemplateManager = new TemplateManager();
        
        if (template == null) {
            throw new IllegalArgumentException("Template must be non-null.");
        }
        
        mLaunchRequest.mTemplate = template;
    }
    
    public TemplateBuilder setOutputDir(String outputDir) {
        mLaunchRequest.mOutputDir = outputDir;
        return this;
    }
    
    public void commit() {
        mTemplateManager.outputProject(mLaunchRequest.mTemplate, mLaunchRequest.mOutputDir);
    }
    
    static class LaunchRequest {
        Template mTemplate;
        String mOutputDir;
    }
}