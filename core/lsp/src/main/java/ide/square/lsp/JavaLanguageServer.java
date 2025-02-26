package ide.square.lsp;

import java.util.List;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;
import android.util.Log;

import java.util.concurrent.CompletableFuture;

public class JavaLanguageServer implements LanguageServer, LanguageClientAware {
    private static final String TAG = "JavaLanguageServer";
    
    private final JavaTextDocumentService mTextDocumentService;
    private final JavaWorkspaceService mWorkspaceService;
    
    private LanguageClient client;

    public JavaLanguageServer() {
        this.mTextDocumentService = new JavaTextDocumentService();
        this.mWorkspaceService = new JavaWorkspaceService();
        Log.d(TAG, "JavaLanguageServer initialized");
    }

    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        Log.d(TAG, "JavaLanguageServer: Initializing...");
        ServerCapabilities capabilities = new ServerCapabilities();
        capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
        capabilities.setCompletionProvider(new CompletionOptions());
        InitializeResult result = new InitializeResult(capabilities);
        Log.d(TAG, "JavaLanguageServer: Initialization completed.");
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public CompletableFuture<Object> shutdown() {
        Log.d(TAG, "JavaLanguageServer: Shutting down...");
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void exit() {
        Log.d(TAG, "JavaLanguageServer: Exiting...");
    }

    @Override
    public TextDocumentService getTextDocumentService() {
        return mTextDocumentService;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        return mWorkspaceService;
    }
    
    @Override
    public void connect(LanguageClient client) {
        this.client = client;
    }
    
    public void sendDiagnostics(String uri, List<Diagnostic> diagnostics) {
        if (client != null) {
            PublishDiagnosticsParams params = new PublishDiagnosticsParams(uri, diagnostics);
            client.publishDiagnostics(params);
        }
    }
}