package ide.square.app.ui.listener;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class DrawableClickListener {
    public interface OnDrawableClickListener {
        void onDrawableStartClick(View view);
        void onDrawableEndClick(View view);
    }

    public static void setOnDrawableClickListener(final EditText editText, final OnDrawableClickListener listener) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (listener != null) {
                        Drawable drawableStart = editText.getCompoundDrawables()[0];
                        Drawable drawableEnd = editText.getCompoundDrawables()[2];
                            
                        if (drawableStart != null && event.getX() <= (drawableStart.getBounds().width() + editText.getPaddingLeft())) {
                            listener.onDrawableStartClick(editText);
                                
                            return true;
                        }
                        if (drawableEnd != null && event.getX() >= (editText.getWidth() - editText.getPaddingRight() - drawableEnd.getBounds().width())) {
                            listener.onDrawableEndClick(editText);
                                
                            return true;
                        }
                    }
                }
                    
                return false;
            }
        });
    }
}