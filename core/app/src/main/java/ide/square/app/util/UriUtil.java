package ide.square.app.util;

import android.net.Uri;

public class UriUtil {
    public static String removeTreePrimaryPrefix(String path) {
        if (path.contains("tree/primary:")) {
            path = path.replace("tree/primary:", "");
        }
        
        return path;
    }
}