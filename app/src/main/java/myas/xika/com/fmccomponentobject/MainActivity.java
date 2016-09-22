package myas.xika.com.fmccomponentobject;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import myas.xika.com.fmccomponentobject.fragment.ContentFragment;
import myas.xika.com.fmccomponentobject.fragment.MenuFragment;

public class MainActivity extends SlidingFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLeftMenu(); //设置左边菜单布局
        initSlidingMenu();//定制侧滑菜单
        initContent();//设置中间内容布局
    }

    /**
     * 设置内容布局
     */
    private void initContent() {
        setContentView(R.layout.fmc_content_frame_layout); //设置内容布局
        FragmentTransaction c = this.getSupportFragmentManager().beginTransaction();
        c.replace(R.id.content_frame, new ContentFragment());
        c.commit();
    }

    /**
     * 定制侧滑菜单
     */
    private void initSlidingMenu() {
        SlidingMenu sm = getSlidingMenu(); //定制侧滑菜单
        //sm.setShadowWidthRes(R.dimen.shadow_width); //设置阴影的宽度
        //sm.setShadowDrawable(R.drawable.shadow);     //设置阴影的drawable
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset); //拉出侧滑菜单后右边内容区域的宽度
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置拉出侧滑菜单的模式
        getSlidingMenu().setMode(SlidingMenu.LEFT);//设置左可以拉出侧滑菜单
        sm.setFadeDegree(0.0f); //设置背景阴影
        getSlidingMenu().setBehindScrollScale(1);//设置跟随滑动
    }

    /**
     * 设置左边菜单布局
     */
    private void initLeftMenu() {
        setBehindContentView(R.layout.fmc_menu_frame_layout); //设置左边菜单布局
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        t.replace(R.id.menu_frame, new MenuFragment());
        t.commit();
    }
}

