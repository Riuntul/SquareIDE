package ide.square.app.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;

public class ResourceUtils {
    private Context context;
    
    private Drawable drawable;
    private InputStream resource;
    
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
    
    public InputStream getAssetsResource(String path) {
        try {
            resource = context.getAssets().open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resource;
    }
}