package com.myapplication;

/**
 * Created by ruoxuan.fu on 2018/2/24.
 * <p>
 * Code is far away from bug with WOW protecting.
 */

public class SpanClickableSpan extends ClickableSpanNoUnderLine {

    private String urlString;

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public SpanClickableSpan(int color, OnClickListener onClickListener) {
        super(color, onClickListener);
    }

    public SpanClickableSpan(OnClickListener onClickListener) {
        super(onClickListener);
    }
}
