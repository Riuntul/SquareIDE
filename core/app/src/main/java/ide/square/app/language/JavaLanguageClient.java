package ide.square.app.language;

import org.eclipse.lsp4j.*;
import android.util.Log;

import java.util.concurrent.CompletableFuture;
import org.eclipse.lsp4j.services.LanguageClient;

public class JavaLanguageClient implements LanguageClient {
    private static final String TAG = "JavaLanguageClient";

    @Override
    public void telemetryEvent(Object o) {
        Log.d(TAG, "telemetryEvent: " + o);
    }

    @Override
    public void publishDiagnostics(PublishDiagnosticsParams publishDiagnosticsParams) {
        Log.d(TAG, "publishDiagnostics: " + publishDiagnosticsParams);
    }

    @Override
    public void showMessage(MessageParams messageParams) {
        Log.d(TAG, "showMessage: " + messageParams);
    }

    @Override
    public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams showMessageRequestParams) {
        Log.d(TAG, "showMessageRequest: " + showMessageRequestParams);
        return CompletableFuture.completedFuture(new MessageActionItem(""));
    }

    @Override
    public void logMessage(MessageParams messageParams) {
        Log.d(TAG, "logMessage: " + messageParams);
    }
}