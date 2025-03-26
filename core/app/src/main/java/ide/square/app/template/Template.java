package ide.square.app.template;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class Template implements Parcelable {
    private String mTitle;
    private int mTitleIntRes;
    private Drawable mImage;
    
    public Template(String title, int titleIntRes, Drawable image) {
        mTitle = title;
        mTitleIntRes = titleIntRes;
        mImage = image;
    }

    protected Template(Parcel in) {
        mTitle = in.readString();
        mTitleIntRes = in.readInt();
    }

    public abstract void onCreate(TemplateManager templateManager);

    public String getTitle() {
        return mTitle;
    }
    
    public int getTitleIntRes() {
        return mTitleIntRes;
    }

    public Drawable getImage() {
        return mImage;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeInt(mTitleIntRes);
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