package ide.square.lsp;

import android.util.Log;
import ide.square.lsp.service.GradleWorkspaceService;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.InitializedParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.SetTraceParams;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.WorkDoneProgressCancelParams;
import org.eclipse.lsp4j.jsonrpc.CompletableFutures;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.NotebookDocumentService;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

public class JavaLanguageServer implements LanguageServer {
    private final GradleWorkspaceService workspaceService;
    
    public String projectPath;
    
    public JavaLanguageServer() {
        workspaceService = new GradleWorkspaceService();
    }
    
    @Override
    public CompletableFuture<Object> shutdown() {
        return null;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        return null;
    }

    @Override
    public TextDocumentService getTextDocumentService() {
        return null;
    }

    @Override
    public NotebookDocumentService getNotebookDocumentService() {
        return null;
    }

    @Override
    public void exit() {}

    @Override
    public void cancelProgress(WorkDoneProgressCancelParams params) {
    }

    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        ServerCapabilities capabilities = new ServerCapabilities();
        capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);

        if (params.getWorkspaceFolders() != null) {
            projectPath = params.getWorkspaceFolders().get(0).getUri();
            Log.i("Java Language Server", "Project Path: '" + projectPath + "'");
        } else {
            Log.e("Java Language Server", "Unable get workspace root");
            System.exit(1);
        }
        
        File gradleKts = new File(projectPath + "/settings.gradle.kts");
        if (gradleKts.isFile() && gradleKts.exists()) {
            Log.i("Java Language Server", "Opening Gradle KTS Project");
        } else {
            Log.e("Java Language Server", "This Project No settings.gradle.kts File or Not Gradle KTS Project");
            System.exit(1);
        }
        
        InitializeResult result = new InitializeResult(capabilities);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public void initialized(InitializedParams params) {
    }

    @Override
    public void setTrace(SetTraceParams params) {
    }
}
