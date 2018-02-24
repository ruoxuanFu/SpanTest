package com.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BulletSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mText = findViewById(R.id.text);
        String msg = "这是一段有背景的文字\n" +
                "你猜猜背景是什么\n" +
                "对\n" +
                "你了解的越多，就越糊涂\n" +
                "不要问我为什么\n" +
                "I DO NOT KNOW";
        SpannableString spannableString = new SpannableString(msg);
        //开头带圆点
        BulletSpan bulletSpan = new BulletSpan(15, Color.BLACK);
        //下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        //设置文字字体，三种字体，monospace，serif，sans-serif
        TypefaceSpan typefaceSpan = new TypefaceSpan("serif");
        //设置文字上标SuperscriptSpan(还有下标SubscriptSpan)
        Parcel parcel = Parcel.obtain();
        parcel.writeInt(6);
        SuperscriptSpan superscriptSpan = new SuperscriptSpan(parcel);
        parcel.recycle();
        SpannableString sixPositionString = new SpannableString("Save6");
        //确定上标的位置
        int sixPosition = sixPositionString.toString().indexOf("6");
        sixPositionString.setSpan(superscriptSpan, sixPosition, sixPosition + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        //ClickableSpan 文字可点击。
        msg = "我是臭不要脸的百度";
        SpannableString spannableStringUrl = new SpannableString(msg);
        SpanClickableSpan spanClickableSpan = new SpanClickableSpan(0xff004081, new ClickableSpanNoUnderLine.OnClickListener<SpanClickableSpan>() {
            @Override
            public void onClick(View weight, SpanClickableSpan span) {
                String urlString = span.getUrlString();
                if (TextUtils.isEmpty(urlString)) {
                    return;
                }
                Log.d("Fmsg", "urlString = " + urlString);
                Uri uri = Uri.parse(urlString);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.putExtra(Browser.EXTRA_APPLICATION_ID, getPackageName());
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Log.w("URLSpan", "Activity was not found for intent, " + intent.toString());
                }
            }
        });
        spanClickableSpan.setUrlString("https://www.baidu.com/");
        spannableStringUrl.setSpan(spanClickableSpan, 0, msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mText.setText(spannableStringUrl);
        // 在单击链接时凡是有要执行的动作，都必须设置MovementMethod对象
        mText.setMovementMethod(LinkMovementMethod.getInstance());
        // 设置点击后的颜色，这里涉及到ClickableSpan的点击背景
        mText.setHighlightColor(0xfffff000);
    }
}
