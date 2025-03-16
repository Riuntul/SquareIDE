package ide.square.app.language;

import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.jsonrpc.messages.Message;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;
import android.util.Log;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Future;

public class LanguageLauncher {
    private static final String TAG = "LanguageLauncher";
    
    private final LanguageServer mServer;
    private final LanguageClient mClient;

    public LanguageLauncher(LanguageServer server, LanguageClient client) {
        this.mServer = server;
        this.mClient = client;
    }

    public void start() {
        try {
            Log.d(TAG, "Starting...");
            
            PipedInputStream clientIn = new PipedInputStream();
            PipedOutputStream clientOut = new PipedOutputStream(clientIn);
            PipedInputStream serverIn = new PipedInputStream();
            PipedOutputStream serverOut = new PipedOutputStream(serverIn);

            Log.d(TAG, "Creating Server Launcher...");
            Launcher<LanguageClient> serverLauncher = LSPLauncher.createServerLauncher(mServer, serverIn, serverOut);
            Log.d(TAG, "Server Launcher Created");

            Log.d(TAG, "Creating Client Launcher...");
            Launcher<LanguageServer> clientLauncher = LSPLauncher.createClientLauncher(mClient, clientIn, clientOut);
            Log.d(TAG, "Client Launcher Created");

            Log.d(TAG, "Starting Server Future and Client Future...");
            Future<Void> serverFuture = serverLauncher.startListening();
            Future<Void> clientFuture = clientLauncher.startListening();
            
            Log.d(TAG, "Server and Client started.");

            new Thread(() -> {
                try {
                    Log.d(TAG, "Waiting for serverFuture and clientFuture to complete...");
                    serverFuture.get();
                    clientFuture.get();
                    Log.d(TAG, "Server and Client connections completed.");
                } catch (Exception e) {
                    Log.e(TAG, "Error waiting for futures", e);
                }
            }, "JavaLanguageService").start();
        } catch (Exception e) {
            Log.e(TAG, "Start failed", e);
        }
    }
}