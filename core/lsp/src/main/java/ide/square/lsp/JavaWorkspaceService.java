package ide.square.lsp;

import ide.square.tooling.GradleToolingServer;
import org.eclipse.lsp4j.CreateFilesParams;
import org.eclipse.lsp4j.DeleteFilesParams;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWorkspaceFoldersParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.RenameFilesParams;
import org.eclipse.lsp4j.services.WorkspaceService;

public class JavaWorkspaceService implements WorkspaceService {
    
    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {
        
    }

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams params) {
        GradleToolingServer tooling = new GradleToolingServer("");
        tooling.configureProject();
    }

    @Override
    public void didRenameFiles(RenameFilesParams params) {
    }

    @Override
    public void didDeleteFiles(DeleteFilesParams params) {
    }

    @Override
    public void didCreateFiles(CreateFilesParams params) {
    }

    @Override
    public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params) {
    }
}