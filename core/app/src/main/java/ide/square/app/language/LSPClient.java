package ide.square.app.language;

import ide.square.lsp.JavaLanguageServer;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LSPClient implements LanguageClient {
    private static final String TAG = "LSPClient";
    private LanguageServer mLanguageServer;

    public void initialize() {
        Log.d(TAG, "Initializing LSPClient...");
        try {
            JavaLanguageServer javaLanguageServer = new JavaLanguageServer();
            JavaLanguageClient javaLanguageClient = new JavaLanguageClient();
            mLanguageServer = javaLanguageServer;

            JavaLanguageLauncher launcher = new JavaLanguageLauncher(javaLanguageServer, javaLanguageClient);
            launcher.start();
            
            Log.d(TAG, "LSP Client initialized successfully.");
        } catch (Exception e) {
            Log.e(TAG, "LSP Client initialization failed", e);
        }
    }

    // 获取补全建议
    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> getCompletion(CompletionParams params) {
        if (mLanguageServer == null) {
            Log.e(TAG, "Language server is not initialized.");
            throw new IllegalStateException("Language server is not initialized");
        }
        Log.d(TAG, "Requesting completion...");
        return mLanguageServer.getTextDocumentService().completion(params);
    }

    @Override
    public void telemetryEvent(Object o) {}
    @Override
    public void publishDiagnostics(PublishDiagnosticsParams publishDiagnosticsParams) {}
    @Override
    public void showMessage(MessageParams messageParams) {}
    @Override
    public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams showMessageRequestParams) {
        return CompletableFuture.completedFuture(new MessageActionItem(""));
    }
    @Override
    public void logMessage(MessageParams messageParams) {}
}