package ide.square.app.template;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.List;

public abstract class Template {
    private int name;
    private Drawable image;
    
    public Template(int name, Drawable image) {
        this.name = name;
        this.image = image;
    }
    
    public abstract void onCreate();
    
    public int getName() {
        return name;
    }
    
    public Drawable getImage() {
        return image;
    }
}