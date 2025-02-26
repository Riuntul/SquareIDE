package ide.square.app.language.service;

import android.util.Log;
import ide.square.lsp.JavaLanguageServer;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Future;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;

public class JavaLanguageService {
    private JavaLanguageServer mLanguageServer;
    private Thread mLanguageServerThread;
    
    public JavaLanguageService() {}
    
    public void startServer() {
        try {
            PipedOutputStream clientIn = new PipedOutputStream();
            PipedInputStream clientOut = new PipedInputStream(clientIn);
            PipedOutputStream serverIn = new PipedOutputStream();
            PipedInputStream serverOut = new PipedInputStream(serverIn);
        
            mLanguageServer = new JavaLanguageServer();
            
	    	Launcher<LanguageClient> launcher = LSPLauncher.createServerLauncher(mLanguageServer, clientOut, serverIn);
            
            new Thread(() -> {
                try {
                    launcher.startListening().get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "JavaLanguageService").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}