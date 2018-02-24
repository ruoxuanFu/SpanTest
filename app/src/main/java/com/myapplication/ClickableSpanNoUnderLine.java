package com.myapplication;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

/**
 * Created by ruoxuan.fu on 2018/2/24.
 * <p>
 * Code is far away from bug with WOW protecting.
 */

public class ClickableSpanNoUnderLine extends ClickableSpan {

    private static final String TAG = "ClickableSpan";

    private static final int NO_COLOR = -206;
    private int color;
    private OnClickListener onClickListener;

    public ClickableSpanNoUnderLine(int color, OnClickListener onClickListener) {
        super();
        this.color = color;
        this.onClickListener = onClickListener;
    }

    public ClickableSpanNoUnderLine(OnClickListener onClickListener) {
        this(NO_COLOR, onClickListener);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        // 设置文字颜色
        if (this.color == NO_COLOR) {
            ds.setColor(ds.linkColor);
        } else {
            ds.setColor(this.color);
        }
        ds.clearShadowLayer();
        // 去除下划线
        ds.setUnderlineText(false);
        ds.bgColor = Color.RED;
    }

    @Override
    public void onClick(View widget) {
        if (onClickListener != null) {
            onClickListener.onClick(widget, this);
        } else {
            Log.w(TAG, "listener was null");
        }
    }

    public interface OnClickListener<T extends ClickableSpanNoUnderLine> {
        void onClick(View weight, T span);
    }
}
