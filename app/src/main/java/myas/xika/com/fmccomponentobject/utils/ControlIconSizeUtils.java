package myas.xika.com.fmccomponentobject.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by XikaKyc on 2016/9/14.
 */
public class ControlIconSizeUtils {
    /**
     * 控制图标大小,模版
     * 过时API
     * Drawable drawable = context.getResources().getDrawable(i);
     * 新API  @TargetApi(Build.VERSION_CODES.LOLLIPOP)  21 以上
     * Drawable drawable = context.getResources().getDrawable(i, null);
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void controlIconSize(Context context, Button button, int i, String s) {
        //控制图标大小
        Drawable drawable = context.getResources().getDrawable(i, null);
        //第一0是距左边距离，第二0是距上边距离，后面3，4分别是长宽
        //(int left, int top, int right, int bottom)
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //放哪边
        //(Drawable left, Drawable top, Drawable right, Drawable bottom)
        button.setCompoundDrawables(null, null, null, null);
    }

    /**
     * 控制菜单布局，系统设置图标大小
     */
    public static void menuFrameSystemSetting(Context context, TextView textView, int i) {
        Drawable drawable = context.getResources().getDrawable(i);
        drawable.setBounds(0, 0, 40, 40);
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 控制内容布局，底部切换按钮，图标大小
     */
    public static void ContentFrameBottomSwitchButton(Context context, RadioButton radiobutton, int i) {
        Drawable drawable = context.getResources().getDrawable(i);
        drawable.setBounds(0, 0, 50, 50);
        radiobutton.setCompoundDrawables(null,drawable, null, null);
    }

}
