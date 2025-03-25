package ide.square.app.settings.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import ide.square.app.settings.SettingsActivity;

public class SubSettingLauncher {
    private final Context mContext;
    private final LaunchRequest mLaunchRequest;
    private boolean mLaunched;

    public SubSettingLauncher(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must be non-null.");
        }
        mContext = context;
        mLaunchRequest = new LaunchRequest();
    }

    public SubSettingLauncher setDestination(String fragmentName) {
        mLaunchRequest.mDestinationName = fragmentName;
        return this;
    }

    public SubSettingLauncher setTitleRes(@StringRes int titleResId) {
        return setTitleRes(null, titleResId);
    }

    public SubSettingLauncher setTitleRes(String titlePackageName, @StringRes int titleResId) {
        mLaunchRequest.mTitleResPackageName = titlePackageName;
        mLaunchRequest.mTitleResId = titleResId;
        mLaunchRequest.mTitle = null;
        return this;
    }

    public SubSettingLauncher setTitleText(CharSequence title) {
        mLaunchRequest.mTitle = title;
        return this;
    }

    public SubSettingLauncher setArguments(Bundle arguments) {
        mLaunchRequest.mArguments = arguments;
        return this;
    }

    public SubSettingLauncher setExtras(Bundle extras) {
        mLaunchRequest.mExtras = extras;
        return this;
    }

    public SubSettingLauncher setSourceMetricsCategory(int sourceMetricsCategory) {
        mLaunchRequest.mSourceMetricsCategory = sourceMetricsCategory;
        return this;
    }

    public SubSettingLauncher setResultListener(Fragment listener, int resultRequestCode) {
        mLaunchRequest.mRequestCode = resultRequestCode;
        mLaunchRequest.mResultListener = listener;
        return this;
    }

    public SubSettingLauncher addFlags(int flags) {
        mLaunchRequest.mFlags |= flags;
        return this;
    }

    public SubSettingLauncher setUserHandle(UserHandle userHandle) {
        mLaunchRequest.mUserHandle = userHandle;
        return this;
    }

    public SubSettingLauncher setTransitionType(int transitionType) {
        mLaunchRequest.mTransitionType = transitionType;
        return this;
    }

    public SubSettingLauncher setIsSecondLayerPage(boolean isSecondLayerPage) {
        mLaunchRequest.mIsSecondLayerPage = isSecondLayerPage;
        return this;
    }

    public void launch() {
        launchWithIntent(toIntent());
    }
    
    public void launchWithIntent(@NonNull Intent intent) {
        if (mLaunched) {
            throw new IllegalStateException("This launcher has already been executed. Do not reuse");
        }
        mLaunched = true;

        launch(intent);
    }
    
    public Intent toIntent() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        copyExtras(intent);
        intent.setClass(mContext, SettingsActivity.class);
        if (TextUtils.isEmpty(mLaunchRequest.mDestinationName)) {
            throw new IllegalArgumentException("Destination fragment must be set");
        }
        
        intent.putExtra("fragmentClass", mLaunchRequest.mDestinationName);
        intent.putExtra("fragmentTitle", mLaunchRequest.mTitle);
        
        return intent;
    }

    @VisibleForTesting
    void launch(Intent intent) {
        mContext.startActivity(intent);
    }

    private void copyExtras(Intent intent) {
        if (mLaunchRequest.mExtras != null) {
            intent.replaceExtras(mLaunchRequest.mExtras);
        }
    }

    static class LaunchRequest {
        String mDestinationName;
        int mTitleResId;
        String mTitleResPackageName;
        CharSequence mTitle;
        int mSourceMetricsCategory = -100;
        int mFlags;
        Fragment mResultListener;
        int mRequestCode;
        UserHandle mUserHandle;
        int mTransitionType;
        Bundle mArguments;
        Bundle mExtras;
        boolean mIsSecondLayerPage;
    }
}