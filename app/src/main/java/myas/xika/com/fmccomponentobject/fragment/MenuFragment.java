package myas.xika.com.fmccomponentobject.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import myas.xika.com.fmccomponentobject.R;
import myas.xika.com.fmccomponentobject.adapter.FourMajorComponentsAdapter;
import myas.xika.com.fmccomponentobject.bean.FourMajorComponentsBean;
import myas.xika.com.fmccomponentobject.utils.ControlIconSizeUtils;
import myas.xika.com.fmccomponentobject.utils.OvalBitmapUtils;

/**
 * Created by XikaKyc on 2016/9/20.
 */
public class MenuFragment extends Fragment {

    //签到，礼包，设置
    private TextView mCheckin_tv_checkin_text_image, mGift_tv_gift_text_image, mSetting_tv_setting_text_image;
    //四大组件ListView
    private ListView mFour_major_components_lv_list_view;
    //四大组件适配器
    private FourMajorComponentsAdapter mFourMajorComponentsAdapter;
    //四大组件List集合
    private ArrayList<FourMajorComponentsBean> mFourMajorComponentsBeanDatas;
    private ImageView mHead_portrait_iv_user_image;
    //四大组件，图标集合
    private int mLogoIcons[] = new int[]{
            R.drawable.logo_act_bg_selector,
            R.drawable.logo_ser_bg_selector,
            R.drawable.logo_br_bg_selector,
            R.drawable.logo_cp_bg_selector
    };
    //四大组件，名字集合
    private String mLogoNames[] = new String[]{
            "Activity (活动)",
            "Service (服务)",
            "BR (广播接收器)",
            "CP (内容提供者)"
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载菜单布局
        View root = inflater.inflate(R.layout.fmc_xika_sliding_menus_menu_frame, null);
        //初始化
        initView(root);
        //初始化数据
        initData();
        //初始化图片大小
        initIconSize();
        //初始化用户圆形头像
        initUserOvalBitmap();
        //初始化事件
        initEvent();
        //返回 视图
        return root;
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        mFour_major_components_lv_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }

    /**
     * 初始化用户圆形头像
     */
    private void initUserOvalBitmap() {
        Bitmap userHeadBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.person_user_profile);
        Bitmap userHeadOvalBitmap = OvalBitmapUtils.getOvalBitmap(userHeadBitmap);
        mHead_portrait_iv_user_image.setImageBitmap(userHeadOvalBitmap);
    }

    /**
     * 初始化图片大小
     */
    private void initIconSize() {
        //签到，礼包，设置
        ControlIconSizeUtils.menuFrameSystemSetting(getActivity(), mCheckin_tv_checkin_text_image, R.drawable.person_center_system_checkin_ex);
        ControlIconSizeUtils.menuFrameSystemSetting(getActivity(), mGift_tv_gift_text_image, R.drawable.person_center_system_gift_ex);
        ControlIconSizeUtils.menuFrameSystemSetting(getActivity(), mSetting_tv_setting_text_image, R.drawable.person_center_system_setting_ex);
    }

    /**
     * 初始化
     */
    private void initView(View root) {
        //用户头像
        mHead_portrait_iv_user_image = (ImageView) root.findViewById(R.id.head_portrait_iv_user_image);
        //签到，礼包，设置
        mCheckin_tv_checkin_text_image = (TextView) root.findViewById(R.id.checkin_tv_checkin_text_image);
        mGift_tv_gift_text_image = (TextView) root.findViewById(R.id.gift_tv_gift_text_image);
        mSetting_tv_setting_text_image = (TextView) root.findViewById(R.id.setting_tv_setting_text_image);
        //四大组件ListView
        mFour_major_components_lv_list_view = (ListView) root.findViewById(R.id.four_major_components_lv_list_view);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //实例化四大组件的集合
        mFourMajorComponentsBeanDatas = new ArrayList<>();
        //遍历
        for (int i = 0; i < mLogoNames.length; i++) {
            FourMajorComponentsBean bean = new FourMajorComponentsBean();
            bean.logo_icon = mLogoIcons[i];
            bean.logo_name = mLogoNames[i];
            //添加到集合中
            mFourMajorComponentsBeanDatas.add(bean);
        }
        //实例化四大组件的适配器
        mFourMajorComponentsAdapter = new FourMajorComponentsAdapter(getActivity(), mFourMajorComponentsBeanDatas);
        //设置四大组件的适配器
        mFour_major_components_lv_list_view.setAdapter(mFourMajorComponentsAdapter);

    }
}
