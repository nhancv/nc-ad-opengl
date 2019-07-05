package com.nhancv.opengl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class CircleFrameLayout extends FrameLayout {
    private Path clippingPath;

    public CircleFrameLayout(Context context) {
        this(context, null, 0, 0);
    }

    public CircleFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public CircleFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CircleFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            int radius = Math.min(w, h) / 2;
            clippingPath = new Path();
            clippingPath.addCircle(w / 2, h / 2, radius, Path.Direction.CW);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int count = canvas.save();
        canvas.clipPath(clippingPath);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(count);
    }

}
