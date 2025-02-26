package ide.square.lsp;

import android.util.Log;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;
import java.util.concurrent.CompletableFuture;

public class JavaTextDocumentService implements TextDocumentService {
    private static final String TAG = "JavaTextDocumentService";

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
        String uri = params.getTextDocument().getUri();
        Log.d(TAG, "Open File : " + uri);
    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {
        
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {}

    @Override
    public void didSave(DidSaveTextDocumentParams params) {}

    @Override
    public CompletableFuture<Hover> hover(HoverParams params) {
        Hover hover = new Hover();
        MarkupContent markupContent = new MarkupContent();
        markupContent.setKind("markdown");
        markupContent.setValue("This is a hover text example.");

        hover.setContents(Either.forRight(markupContent));
        return CompletableFuture.completedFuture(hover);
    }

    @Override
    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams params) {
        List<CompletionItem> items = new ArrayList<>();
        
        items.add(createCompletionItem("Test", "Test", "Test"));

        return CompletableFuture.completedFuture(Either.forLeft(items));
    }

    private CompletionItem createCompletionItem(String label, String detail, String documentation) {
        CompletionItem item = new CompletionItem(label);
        item.setDetail(detail);
        item.setDocumentation(documentation);
        item.setKind(CompletionItemKind.Class); // 设置补全项的种类为类
        return item;
    }

    @Override
    public CompletableFuture<CompletionItem> resolveCompletionItem(CompletionItem unresolved) {
        return CompletableFuture.completedFuture(unresolved);
    }

    @Override
    public CompletableFuture<SignatureHelp> signatureHelp(SignatureHelpParams params) {
        SignatureInformation signatureInformation = new SignatureInformation();
        signatureInformation.setLabel("Example Function");

        SignatureHelp signatureHelp = new SignatureHelp();
        signatureHelp.setSignatures(Collections.singletonList(signatureInformation));
        return CompletableFuture.completedFuture(signatureHelp);
    }
}