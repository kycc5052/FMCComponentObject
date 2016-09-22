package myas.xika.com.fmccomponentobject.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import myas.xika.com.fmccomponentobject.R;

/**
 * Created by XikaKyc on 2016/9/21.
 */
public class TabPage extends RelativeLayout {
    public TabPage(Context context) {
        this(context, null);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_tab_page, this);
    }

    public TabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
