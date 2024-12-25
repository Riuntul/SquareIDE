package ide.square.app.util;
import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilesUtil {
    public static void copyAssets(String sourcePath, String targetPath, Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            String[] files = assetManager.list(sourcePath);
            for (String file : files) {
                String inputPath = sourcePath + "/" + file;
                String outputPath = targetPath + "/" + file;
                if (assetManager.list(inputPath).length == 0) {
                    InputStream in = assetManager.open(inputPath);
                    File outputFile = new File(outputPath);
                    if (!outputFile.exists()) {
                        outputFile.getParentFile().mkdirs();
                        outputFile.createNewFile();
                    }
                    FileOutputStream output = new FileOutputStream(outputFile);
                    copyFile(in, output);
                    in.close();
                    output.close();
                } else {
                    copyAssets(inputPath, outputPath, context);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void copyFile(InputStream in, FileOutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}