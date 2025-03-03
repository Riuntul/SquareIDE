package ide.square.app.template;

import android.content.Context;

import ide.square.app.R;
import ide.square.app.utils.ResourceUtils;

public class NoActivityTemplate extends Template {
    public NoActivityTemplate(Context context) {
        super(R.string.template_noactivity_name, new ResourceUtils(context).getAssetsDrawable("framework/templates/res/image/no_activity.png"));
    }

    @Override
    public void onCreate() {}
}