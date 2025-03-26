package ide.square.app.template;

import android.content.Context;

import android.os.Parcel;
import android.util.Log;
import ide.square.app.R;
import ide.square.app.utils.ResourceUtils;

public class NoActivityTemplate extends Template {
    public NoActivityTemplate(Context context) {
        super("No Activity", R.string.template_noactivity_name, new ResourceUtils(context).getAssetsDrawable("framework/templates/res/image/no_activity.png"));
    }
    
    protected NoActivityTemplate(Parcel in) {
        super(in); 
    }

    @Override
    public void onCreate(TemplateManager templateManager) {
        templateManager.addFile(new TemplateFile("src/test.txt", "Test"));
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