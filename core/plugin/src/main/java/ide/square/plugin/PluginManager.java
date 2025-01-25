package ide.square.plugin;
import java.util.ArrayList;
import java.util.List;

public class PluginManager {
    private List<BasePlugin> plugins = new ArrayList<BasePlugin>();
    
    public void init() {
    }
    
    public void removePlugin(BasePlugin plugin) {
        plugins.remove(plugin);
    }
}