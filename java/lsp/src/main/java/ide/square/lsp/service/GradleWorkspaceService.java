package ide.square.lsp.service;

import ide.square.tooling.GradleProjectLoader;

import org.eclipse.lsp4j.CreateFilesParams;
import org.eclipse.lsp4j.DeleteFilesParams;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DidChangeWorkspaceFoldersParams;
import org.eclipse.lsp4j.ExecuteCommandParams;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.RenameFilesParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.SymbolInformation;
import org.eclipse.lsp4j.WorkspaceDiagnosticParams;
import org.eclipse.lsp4j.WorkspaceDiagnosticReport;
import org.eclipse.lsp4j.WorkspaceEdit;
import org.eclipse.lsp4j.WorkspaceSymbol;
import org.eclipse.lsp4j.WorkspaceSymbolParams;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.WorkspaceService;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GradleWorkspaceService implements WorkspaceService {
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        ServerCapabilities capabilities = new ServerCapabilities();
        File projectDir = new File(params.getWorkspaceFolders().get(0).getUri());
        
        GradleProjectLoader loader = new GradleProjectLoader();
        loader.connect(projectDir);
        
        List<File> classpath = loader.getClasspath();
        
        loader.close();

        updateLSPClasspath(classpath);

        InitializeResult result = new InitializeResult(capabilities);
        return CompletableFuture.completedFuture(result);
    }

    private void updateLSPClasspath(List<File> classpath) {
        System.out.println("Updating LSP classpath: " + classpath);
    }

    @Override
    public void didCreateFiles(CreateFilesParams params) {}

    @Override
    public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params) {}

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {}

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams params) {}

    @Override
    public CompletableFuture<WorkspaceDiagnosticReport> diagnostic(WorkspaceDiagnosticParams params) {
        return null;
    }

    @Override
    public void didDeleteFiles(DeleteFilesParams params) {}

    @Override
    public void didRenameFiles(RenameFilesParams params) {}

    @Override
    public CompletableFuture<Object> executeCommand(ExecuteCommandParams params) {
        return null;
    }

    @Override
    public CompletableFuture<WorkspaceSymbol> resolveWorkspaceSymbol(WorkspaceSymbol params) {
        return null;
    }

    @Override
    public CompletableFuture<Either<List<? extends SymbolInformation>, List<? extends WorkspaceSymbol>>> symbol(WorkspaceSymbolParams params) {
        return null;
    }

    @Override
    public CompletableFuture<WorkspaceEdit> willCreateFiles(CreateFilesParams params) {
        return null;
    }

    @Override
    public CompletableFuture<WorkspaceEdit> willDeleteFiles(DeleteFilesParams params) {
        return null;
    }

    @Override
    public CompletableFuture<WorkspaceEdit> willRenameFiles(RenameFilesParams params) {
        return null;
    }
}