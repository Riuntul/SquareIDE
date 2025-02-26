package ide.square.app.editor;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ibm.icu.text.Normalizer2;

import ide.square.app.language.LSPClient;

import org.eclipse.lsp4j.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CodeEditorView extends LinearLayout {
    private static final String TAG = "CodeEditorView";

    private EditText mEditText;
    private LSPClient mLspClient;
    private ListView mSuggestionListView;
    
    private Handler mHandler;
    private boolean mIsRequestInProgress = false;

    public CodeEditorView(Context context) {
        super(context);
        init(context);
    }

    public CodeEditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CodeEditorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);

        mHandler = new Handler(Looper.getMainLooper());

        mLspClient = new LSPClient();
        mLspClient.initialize();

        mEditText = new EditText(context);
        mEditText.setBackgroundColor(Color.TRANSPARENT);
        mEditText.setTextColor(Color.WHITE);
        mEditText.setTextSize(16);
        mEditText.setGravity(Gravity.TOP | Gravity.START);
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mEditText.setMaxLines(Integer.MAX_VALUE);
        mEditText.setSingleLine(false);
        mEditText.addTextChangedListener(new TextWatcher() {
                
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mHandler.removeCallbacksAndMessages(null);
                        
                    mHandler.postDelayed(() -> {
                        if (!mIsRequestInProgress) {
                            mIsRequestInProgress = true;
                            requestCompletion(s.toString(), mEditText.getSelectionStart());
                        }
                    }, 300);
                } else {
                    hideSuggestionList();
                }
            }
        });

        mEditText.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus && mEditText.getText().length() > 0) {
                requestCompletion(mEditText.getText().toString(), mEditText.getSelectionStart());
            }
        });

        addView(mEditText, new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));

        mSuggestionListView = new ListView(context);
        mSuggestionListView.setVisibility(GONE);
        
        addView(mSuggestionListView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        
        Log.d(TAG, "CodeEditorView initialized");
    }

    private void requestCompletion(String code, int position) {
        try {
            int lineNumber = mEditText.getLayout().getLineForOffset(position);
            int column = position - mEditText.getLayout().getLineStart(lineNumber);
            
            TextDocumentIdentifier documentIdentifier = new TextDocumentIdentifier("file:///sdcard/test.java");
            Position pos = new Position(lineNumber, column);
            CompletionParams params = new CompletionParams(documentIdentifier, pos);

            CompletableFuture.runAsync(() -> mLspClient.getCompletion(params).thenAccept(completionEither -> {
                if (completionEither.isLeft()) {
                    List<CompletionItem> completionItems = completionEither.getLeft();
                            
                    if (completionItems != null) {
                        Normalizer2 normalizer = Normalizer2.getNFCInstance();
                        String normalizedCode = normalizer.normalize(code);
                        List<String> suggestions = completionItems.stream().map(item -> item.getLabel()).filter(label -> isMeaningfulMatch(normalizedCode, label)).collect(Collectors.toList());
                                
                        if (!suggestions.isEmpty()) {
                            mHandler.post(() -> {
                                updateSuggestionList(suggestions.toArray(new String[0]));
                                mIsRequestInProgress = false;
                            });
                        } else {
                            mHandler.post(() -> {
                                hideSuggestionList();
                                mIsRequestInProgress = false;
                            });
                        }
                    } else {
                        mHandler.post(() -> {
                            hideSuggestionList();
                            mIsRequestInProgress = false;
                        });
                    }
                } else {
                    mHandler.post(() -> {
                        hideSuggestionList();
                        mIsRequestInProgress = false;
                    });
                }
            }).exceptionally(ex -> {
                Log.e(TAG, "Completion request failed", ex);
                        
                mHandler.post(() -> {
                    hideSuggestionList();
                    mIsRequestInProgress = false;
                });
                        
                return null;
            }));
        } catch (Exception e) {
            Log.e(TAG, "Error in requestCompletion", e);
            mIsRequestInProgress = false;
        }
    }

    private boolean isMeaningfulMatch(String normalizedCode, String label) {
        return label.startsWith(normalizedCode);
    }

    private void updateSuggestionList(String[] suggestions) {
        Log.d(TAG, "Updating suggestion list with " + suggestions.length + " items");
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, suggestions);
        
        mSuggestionListView.setAdapter(adapter);
        mSuggestionListView.setVisibility(VISIBLE);
        
        Log.d(TAG, "ListView should be visible with suggestions");
    }

    private void hideSuggestionList() {
        Log.d(TAG, "Hiding suggestion list");
        mSuggestionListView.setVisibility(GONE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mEditText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            
            if (imm != null) {
                imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        }
        
        return true;
    }
}