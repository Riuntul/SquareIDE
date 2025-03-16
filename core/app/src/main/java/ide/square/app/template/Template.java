package ide.square.app.template;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class Template implements Parcelable {
    private int name;
    private Drawable image;
    
    private String projectName;
    private String packageName;
    private String projectPath;

    private TemplateManager templateManager;
    
    public Template(String projectName, String packageName, String projectPath) {
        this.projectName = projectName;
        this.packageName = packageName;
        this.projectPath = projectPath;
        
        this.templateManager = new TemplateManager();
    }
    
    public Template(int name, Drawable image) {
        this.name = name;
        this.image = image;
    }

    protected Template(Parcel in) {
        name = in.readInt();
        
        projectName = in.readString();
        packageName = in.readString();
        projectPath = in.readString();
    }

    public abstract void onCreate();

    public int getName() {
        return name;
    }

    public Drawable getImage() {
        return image;
    }

    public TemplateManager getTemplateManager() {
        return templateManager;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(name);
        
        dest.writeString(projectName);
        dest.writeString(packageName);
        dest.writeString(projectPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Template> CREATOR = new Creator<Template>() {
        @Override
        public Template createFromParcel(Parcel in) {
            throw new UnsupportedOperationException("Cannot create instances of abstract class Template");
        }

        @Override
        public Template[] newArray(int size) {
            return new Template[size];
        }
    };
}