package ide.square.app.ui.widget.sheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.GravityInt;
import androidx.annotation.UiThread;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textview.MaterialTextView;
import ide.square.app.R;
import ide.square.app.databinding.LayoutEditorBottomSheetBinding;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EditorBottomSheet extends RelativeLayout {
    public LayoutEditorBottomSheetBinding mBinding;
    
    private float collapsedHeight;
    private BottomSheetBehavior<EditorBottomSheet> behavior;
    
    private Insets windowInsets = null;
    
    private final float COLLAPSE_HEADER_AT_OFFSET = 0.5f;
    
    final int CHILD_HEADER = 0;
    final int CHILD_SYMBOL_INPUT = 1;
    final int CHILD_ACTION = 2;
    
    public EditorBottomSheet(Context context) {
        super(context);
        init(context);
    }

    public EditorBottomSheet(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditorBottomSheet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    
    public EditorBottomSheet(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    
    private void initialize(Context context) {
        mBinding.headerContainer.setOnClickListener(new View.OnClickListener() {
                
            @Override
            public void onClick(View view) {
                if (getBottomSheetBehavior().getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    getBottomSheetBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(this, new androidx.core.view.OnApplyWindowInsetsListener() {
                
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) {
                windowInsets = insets.getInsets(WindowInsetsCompat.Type.mandatorySystemGestures());
                return insets;    
            }
        });
        
        MaterialTextView logTextView = mBinding.logcatOutput;
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line).append("\n");
            }
            logTextView.setText(log.toString());
        } catch (Exception e) {
            logTextView.setText("Error fetching logcat logs: " + e.getMessage());
        }
    }
    
    private void init(Context context) {
        if (!(context instanceof FragmentActivity)) {
            throw new IllegalArgumentException("EditorBottomSheet must be set up with a FragmentActivity");
        }
        
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = LayoutEditorBottomSheetBinding.inflate(inflater, this, false);
        
        addView(mBinding.getRoot());
        
        initialize(context);
    }
    
    private float getCollapsedHeight(Context context) {
        return context.getResources().getDimension(Math.round(5.6f * context.getResources().getDisplayMetrics().density));
    }

    private BottomSheetBehavior<EditorBottomSheet> getBottomSheetBehavior() {
        BottomSheetBehavior<EditorBottomSheet> behavior = BottomSheetBehavior.from(this);
        
        behavior.setFitToContents(false);
        behavior.setSkipCollapsed(true);
        
        return behavior;
    }

    public void showChild(int index) {
        mBinding.headerContainer.setDisplayedChild(index);
    }
    
    public void setStatus(final CharSequence text, @GravityInt final int gravity) {
        post(new Runnable() {
                
            @Override
            public void run() {
                mBinding.buildStatus.statusText.setGravity(gravity);
                mBinding.buildStatus.statusText.setText(text);
            }
        });
    }
}