package ide.square.app.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtils {
    private Context context;
    private Drawable drawable;
    
    public ResourceUtils(Context context) {
        this.context = context;
    }
    
    public Drawable getAssetsDrawable(String path) {
        try {
        	InputStream image = context.getAssets().open(path);
            drawable = Drawable.createFromStream(image, null);
        } catch(IOException e) {
        	e.printStackTrace();
        }
        return drawable;
    }
}