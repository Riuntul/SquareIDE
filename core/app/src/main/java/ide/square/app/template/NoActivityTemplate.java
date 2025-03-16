package ide.square.app.template;

import android.content.Context;

import android.os.Parcel;
import ide.square.app.R;
import ide.square.app.utils.ResourceUtils;

public class NoActivityTemplate extends Template {
    public NoActivityTemplate(String projectName, String packageName, String projectPath) {
        super(projectName, packageName, projectPath);
    }
    
    public NoActivityTemplate(Context context) {
        super(R.string.template_noactivity_name, new ResourceUtils(context).getAssetsDrawable("framework/templates/res/image/no_activity.png"));
    }
    
    protected NoActivityTemplate(Parcel in) {
        super(in); 
    }

    @Override
    public void onCreate() {
        getTemplateManager().addFile(new TemplateFile("src/test.txt", "Test"));
    }
    
    public static final Creator<NoActivityTemplate> CREATOR = new Creator<NoActivityTemplate>() {
        
        @Override
        public NoActivityTemplate createFromParcel(Parcel in) {
            return new NoActivityTemplate(in);
        }

        @Override
        public NoActivityTemplate[] newArray(int size) {
            return new NoActivityTemplate[size];
        }
    };
}