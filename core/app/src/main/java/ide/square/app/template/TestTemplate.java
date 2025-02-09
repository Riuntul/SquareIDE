package ide.square.app.template;

import android.content.Context;

import ide.square.app.R;
import ide.square.app.util.ResourceUtils;

public class TestTemplate extends Template {
    public TestTemplate(Context context) {
        super(R.string.template_noactivity_title, new ResourceUtils(context).getAssetsDrawable("template/res/image/no_activity.png"));
    }

    @Override
    public void onCreate() {}
}