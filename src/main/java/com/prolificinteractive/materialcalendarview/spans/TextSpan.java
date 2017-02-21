package com.prolificinteractive.materialcalendarview.spans;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/**
 * @author Administrator
 * @version 2017/2/20 0020
 */

public class TextSpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas canvas, Paint paint, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        int oldColor = paint.getColor();
        paint.setColor(Color.BLACK);
        canvas.drawText("数学", (left + right) / 2, top - 10, paint);
        paint.setColor(oldColor);
    }
}
