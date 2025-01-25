package ide.square.plugin;
import android.util.Log;

public abstract class BasePlugin {
    private String pluginName;
    
    public BasePlugin(String pluginName) {
        this.pluginName = pluginName;
        if (this.pluginName == null) {
            Log.e("BasePlugin", "Plugin name is null");
        }
    }
    
    protected abstract void onOpenProject();
    protected abstract void onCloseProject();
}