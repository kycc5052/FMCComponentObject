package myas.xika.com.fmccomponentobject.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import myas.xika.com.fmccomponentobject.R;
import myas.xika.com.fmccomponentobject.utils.ControlIconSizeUtils;
import myas.xika.com.fmccomponentobject.utils.OvalBitmapUtils;
import myas.xika.com.fmccomponentobject.widget.TabPage;

/**
 * Created by XikaKyc on 2016/9/20.
 */
public class ContentFragment extends Fragment {
    //内容布局，RadioGroup 底部切换按钮容器
    private RadioGroup mContent_frame_rg_bottom_switch_button;
    //内容布局，FrameLayout 中间FrameLayout 布局容器
    private FrameLayout mContent_frame_fl_middle_content_area;
    //内容布局，RadioGroup 底部切换按钮容器里面的 RadioButton
    //资讯，基础，界面，设备，存储
    private RadioButton mBottom_switch_button_rb_news_text;
    private RadioButton mBottom_switch_button_rb_base_text;
    private RadioButton mBottom_switch_button_rb_gui_text;
    private RadioButton mBottom_switch_button_rb_device_text;
    private RadioButton mBottom_switch_button_rb_save_text;

    //顶部标识栏，组件头像
    private ImageView mTop_logo_bar_iv_border_image;
    //顶部标识栏，组件Name
    private TextView mTop_logo_bar_tv_component_name_text;
    //缓存TabPage :SparseArray看做一个整型和对象的映射的hashMap,但是SparseArray查找效率更高
    private SparseArray<TabPage> mTabPageSparseArrayCache;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载内容布局
        View root = inflater.inflate(R.layout.fmc_xika_sliding_menus_content_frame, null);
        //实例化缓存TabPage对象
        mTabPageSparseArrayCache = new SparseArray<>();
        //初始化
        initView(root);
        //初始化图片大小
        initIconSize();
        //初始化组件圆形头像
        initComponentsOvalBitmap();
        //初始化事件
        initEvent();
        //返回 视图
        return root;
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        //内容布局，底部切换按钮，CheckedChange 改变事件
        mContent_frame_rg_bottom_switch_button.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    /**
     * new 一个OnCheckedChangeListener改变事件的对象
     */
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        /**
         * CheckedChanged 改变事件的处理
         * */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //先声明一个TabPage
            TabPage page = null;
            //先从缓存里面查找有没有创建对应checkedId的TabPage
            //如果有的话，直接从缓存里面获取
            if (mTabPageSparseArrayCache.get(checkedId) != null) {
                page = mTabPageSparseArrayCache.get(checkedId);
            } else {
                //如果没有，则创建新的TabPage
                page = new TabPage(getActivity());
                //根据checkedId来设置Name
                switch (checkedId) {
                    case R.id.bottom_switch_button_rb_news_text:
                        setTitleName("资讯");
                        break;
                    case R.id.bottom_switch_button_rb_base_text:
                        setTitleName("基础");
                        break;
                    case R.id.bottom_switch_button_rb_gui_text:
                        setTitleName("界面");
                        break;
                    case R.id.bottom_switch_button_rb_device_text:
                        setTitleName("设备");
                        break;
                    case R.id.bottom_switch_button_rb_save_text:
                        setTitleName("存储");
                        break;
                }
                //创建一个新的TabPage需要缓存起来
                mTabPageSparseArrayCache.put(checkedId, page);
            }
            //FrameLayout里面只显示一个TabPage
            //在添加新的TabPage之前，将之前TabPage全部移除
            mContent_frame_fl_middle_content_area.removeAllViews();
            //将TabPage添加到FrameLayout里面
            mContent_frame_fl_middle_content_area.addView(page);
        }
    };

    /**
     * 设置 标题Name
     */
    public void setTitleName(String str) {
        //顶部Logo 组件Name
        mTop_logo_bar_tv_component_name_text.setText(str);
    }

    /**
     * 初始化
     */
    private void initView(View root) {
        //底部切换按钮RadioGroup容器
        mContent_frame_rg_bottom_switch_button = (RadioGroup) root.findViewById(R.id.content_frame_rg_bottom_switch_button);
        //内容布局（FrameLayout内容区）
        mContent_frame_fl_middle_content_area = (FrameLayout) root.findViewById(R.id.content_frame_fl_middle_content_area);

        //底部切换按钮，资讯，基础，界面，设备，存储
        mBottom_switch_button_rb_news_text = (RadioButton) root.findViewById(R.id.bottom_switch_button_rb_news_text);
        mBottom_switch_button_rb_base_text = (RadioButton) root.findViewById(R.id.bottom_switch_button_rb_base_text);
        mBottom_switch_button_rb_gui_text = (RadioButton) root.findViewById(R.id.bottom_switch_button_rb_gui_text);
        mBottom_switch_button_rb_device_text = (RadioButton) root.findViewById(R.id.bottom_switch_button_rb_device_text);
        mBottom_switch_button_rb_save_text = (RadioButton) root.findViewById(R.id.bottom_switch_button_rb_save_text);

        //顶部标识栏，组件头像
        mTop_logo_bar_iv_border_image = (ImageView) root.findViewById(R.id.top_logo_bar_iv_border_image);
        //顶部标识栏，组件Name
        mTop_logo_bar_tv_component_name_text = (TextView) root.findViewById(R.id.top_logo_bar_tv_component_name_text);

    }


    /**
     * 初始化组件圆形头像
     */
    private void initComponentsOvalBitmap() {
        Bitmap topLogoBarBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.top_logo_bar_act_bitmap);
        Bitmap topLogoBarOvalBitmap = OvalBitmapUtils.getOvalBitmap(topLogoBarBitmap);
        mTop_logo_bar_iv_border_image.setImageBitmap(topLogoBarOvalBitmap);
    }

    /**
     * 初始化图片大小
     */
    private void initIconSize() {
        //资讯，基础，界面，设备，存储
        ControlIconSizeUtils.ContentFrameBottomSwitchButton(getActivity(), mBottom_switch_button_rb_news_text, R.drawable.tab_fmc_news_bg_selector);
        ControlIconSizeUtils.ContentFrameBottomSwitchButton(getActivity(), mBottom_switch_button_rb_base_text, R.drawable.tab_fmc_base_bg_selector);
        ControlIconSizeUtils.ContentFrameBottomSwitchButton(getActivity(), mBottom_switch_button_rb_gui_text, R.drawable.tab_fmc_gui_bg_selector);
        ControlIconSizeUtils.ContentFrameBottomSwitchButton(getActivity(), mBottom_switch_button_rb_device_text, R.drawable.tab_fmc_device_bg_selector);
        ControlIconSizeUtils.ContentFrameBottomSwitchButton(getActivity(), mBottom_switch_button_rb_save_text, R.drawable.tab_fmc_save_bg_selector);
    }

}
