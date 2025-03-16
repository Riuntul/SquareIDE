package ide.square.app.language;

import org.eclipse.lsp4j.*;
import android.util.Log;

import java.util.concurrent.CompletableFuture;
import org.eclipse.lsp4j.services.LanguageClient;

public class LanguageClientImpl implements LanguageClient {
    private static final String TAG = "LanguageClientImpl";

    @Override
    public void telemetryEvent(Object object) {
        Log.d(TAG, "Telemetry Event: " + object);
    }

    @Override
    public void publishDiagnostics(PublishDiagnosticsParams diagnosticsParams) {
        Log.d(TAG, "Diagnostics: " + diagnosticsParams);
        diagnosticsParams.getDiagnostics().forEach(diagnostic -> {
            System.out.println("Range: " + diagnostic.getRange() + ", Message: " + diagnostic.getMessage());
        });
    }

    @Override
    public void showMessage(MessageParams messageParams) {
        Log.d(TAG, "Message: " + messageParams);
    }

    @Override
    public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams showMessageRequestParams) {
        Log.d(TAG, "Message Request: " + showMessageRequestParams);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void logMessage(MessageParams messageParams) {
        Log.d(TAG, "Log: " + messageParams);
    }
}