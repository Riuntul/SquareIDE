package ide.square.app.settings.widget;

import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import ide.square.app.R;

public class HomepagePreferenceLayoutHelper {
    private View mIcon;
    private View mText;
    
    private boolean mIconVisible = true;
    
    private int mIconPaddingStart = -1;
    private int mTextPaddingStart = -1;

    public interface HomepagePreferenceLayout {
        HomepagePreferenceLayoutHelper getHelper();
    }
    
    public HomepagePreferenceLayoutHelper(Preference preference) {
        preference.setLayoutResource(R.layout.preference_homepage);
    }
    
    public void setIconVisible(boolean visible) {
        mIconVisible = visible;
        if (mIcon != null) {
            mIcon.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }
    
    public void setIconPaddingStart(int paddingStart) {
        mIconPaddingStart = paddingStart;
        if (mIcon != null && paddingStart >= 0) {
            mIcon.setPaddingRelative(paddingStart, mIcon.getPaddingTop(), mIcon.getPaddingEnd(),
            mIcon.getPaddingBottom());
        }
    }
    
    public void setTextPaddingStart(int paddingStart) {
        mTextPaddingStart = paddingStart;
        if (mText != null && paddingStart >= 0) {
            mText.setPaddingRelative(paddingStart, mText.getPaddingTop(), mText.getPaddingEnd(),
            mText.getPaddingBottom());
        }
    }
    
    void onBindViewHolder(PreferenceViewHolder holder) {
        mIcon = holder.findViewById(R.id.icon_frame);
        mText = holder.findViewById(R.id.text_frame);
        
        setIconVisible(mIconVisible);
        
        setIconPaddingStart(mIconPaddingStart);
        setTextPaddingStart(mTextPaddingStart);
    }
}