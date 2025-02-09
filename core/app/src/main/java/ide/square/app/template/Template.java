package ide.square.app.template;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.List;

public abstract class Template {
    private int title;
    private Drawable image;
    
    public Template(int title, Drawable image) {
        this.title = title;
        this.image = image;
    }
    
    public abstract void onCreate();
    
    public int getTitle() {
        return title;
    }
    
    public Drawable getImage() {
        return image;
    }
}