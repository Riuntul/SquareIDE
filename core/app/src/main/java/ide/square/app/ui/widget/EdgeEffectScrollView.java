package ide.square.app.ui.widget;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EdgeEffect;
import android.widget.ScrollView;

public class EdgeEffectScrollView extends ScrollView {
    public static abstract class EdgeEffectFactory {
        public static final int EDGE_TOP = 0;
        public static final int EDGE_BOTTOM = 1;

        public abstract EdgeEffect createEdgeEffect(Context context, int edge);
    }

    private EdgeEffect mEdgeEffectTop;
    private EdgeEffect mEdgeEffectBottom;
    private EdgeEffectFactory mEdgeEffectFactory;
    
    private float mLastY;
    
	public EdgeEffectScrollView(Context context) {
        this(context, null);
    }
    
    public EdgeEffectScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.scrollViewStyle);
    }
    
    public EdgeEffectScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    
    private void init(Context context) {
        if (mEdgeEffectFactory == null) {
            mEdgeEffectFactory = new EdgeEffectFactory() {
                
                @Override
                public EdgeEffect createEdgeEffect(Context context, int edge) {
                    return new EdgeEffect(context);
                }
            };
        }
        mEdgeEffectTop = mEdgeEffectFactory.createEdgeEffect(context, EdgeEffectFactory.EDGE_TOP);
        mEdgeEffectBottom = mEdgeEffectFactory.createEdgeEffect(context, EdgeEffectFactory.EDGE_BOTTOM);

        setOverScrollMode(OVER_SCROLL_NEVER);
    }
    
    public void setEdgeEffectFactory(EdgeEffectFactory factory) {
        mEdgeEffectFactory = factory;
        mEdgeEffectTop = mEdgeEffectFactory.createEdgeEffect(getContext(), EdgeEffectFactory.EDGE_TOP);
        mEdgeEffectBottom = mEdgeEffectFactory.createEdgeEffect(getContext(), EdgeEffectFactory.EDGE_BOTTOM);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN -> {
                mLastY = event.getY();
                break;
            } case MotionEvent.ACTION_UP -> {
                if (!mEdgeEffectTop.isFinished()) {
                    mEdgeEffectTop.onRelease();
                }
                
                if (!mEdgeEffectBottom.isFinished()) {
                    mEdgeEffectBottom.onRelease();
                }
                
                invalidate();
                
                break;
            } case MotionEvent.ACTION_MOVE -> {
                float deltaY = mLastY - event.getY();
                mLastY = event.getY();
                
                if (!canScrollVertically(-1) && deltaY < 0) {
                    float dpt = Math.max(0, Math.min(event.getX() / getWidth(), 1));
                    mEdgeEffectTop.onPull(Math.abs(deltaY) / getHeight(), dpt);
                    invalidate();
                }
                
                else if (!canScrollVertically(1) && deltaY > 0) {
                    float dpt = Math.max(0, Math.min(1 - (event.getX() / getWidth()), 1));
                    mEdgeEffectBottom.onPull(deltaY / getHeight(), dpt);
                    invalidate();
                }
                
                break;
            }
        }
        return super.onTouchEvent(event);
    }
    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean needsInvalidate = false;

        if (mEdgeEffectTop != null && !mEdgeEffectTop.isFinished()) {
            int saveCount = canvas.save();
            //canvas.translate(100, -getScrollY());
            mEdgeEffectTop.setSize(getWidth(), getHeight());
            if (mEdgeEffectTop.draw(canvas)) {
                needsInvalidate = true;
            }
            canvas.restoreToCount(saveCount);
        }

        if (mEdgeEffectBottom != null && !mEdgeEffectBottom.isFinished()) {
            int saveCount = canvas.save();
            //canvas.translate(100, getScrollY());
            canvas.rotate(180, getWidth() / 2f, 0);
            mEdgeEffectBottom.setSize(getWidth(), getHeight());
            if (mEdgeEffectBottom.draw(canvas)) {
                needsInvalidate = true;
            }
            canvas.restoreToCount(saveCount);
        }

        if (needsInvalidate) {
            postInvalidateOnAnimation();
        }
    }
}