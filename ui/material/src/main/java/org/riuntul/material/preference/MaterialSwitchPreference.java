package org.riuntul.material.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;
import org.riuntul.material.R;

public class MaterialSwitchPreference extends SwitchPreferenceCompat {
    public MaterialSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    
    public MaterialSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    
    public MaterialSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    
    public void init(Context context) {
        setLayoutResource(R.layout.preference_widget_material_switch);
    }
}