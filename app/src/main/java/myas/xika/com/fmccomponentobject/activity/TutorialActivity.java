package myas.xika.com.fmccomponentobject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;

import myas.xika.com.fmccomponentobject.MainActivity;
import myas.xika.com.fmccomponentobject.R;
import myas.xika.com.fmccomponentobject.adapter.ActTutorialViewPagerAdapter;
import myas.xika.com.fmccomponentobject.app.Constants;
import myas.xika.com.fmccomponentobject.utils.SharedPrefsUtils;


public class TutorialActivity extends Activity {
    //向导界面ViewPager
    private ViewPager mAct_tutorial_vp_image;
    //向导Image集合
    private int[] mTutorial_Images = new int[]{
            R.drawable.guid_1,
            R.drawable.guid_2,
            R.drawable.guid_3,
            R.drawable.guid_4
    };
    private ActTutorialViewPagerAdapter mActTutorialViewPagerAdapter;

    private CirclePageIndicator mAct_tutorial_cpi_indicator;
    private Button mAct_tutorial_btn_start_experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        initView();
        initEvent();
        initData();
    }

    private void initEvent() {
        //  mAct_tutorial_vp_image.setOnPageChangeListener(mOnPageChangeListener);
        //指示器的监听器
        /**
         *   mAct_tutorial_cpi_indicator.setViewPager(mAct_tutorial_vp_image); 会把我们设置的ViewPager的监听器覆盖，
         *   但是会提供一个设置页面变化接口。
         * */
        mAct_tutorial_cpi_indicator.setOnPageChangeListener(mOnPageChangeListener);
        mAct_tutorial_btn_start_experience.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //跳转到主界面
            navigateToMain();
            //保存布尔值，表示已经看过向导界面
            SharedPrefsUtils.putBoolean(TutorialActivity.this, Constants.FMC_GUIDE_TUTORIAL, true);
        }
    };

    private void navigateToMain() {
        Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == mTutorial_Images.length - 1) {
                mAct_tutorial_btn_start_experience.setVisibility(View.VISIBLE);
            } else {
                mAct_tutorial_btn_start_experience.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initData() {
        mActTutorialViewPagerAdapter = new ActTutorialViewPagerAdapter(TutorialActivity.this, mTutorial_Images);
        mAct_tutorial_vp_image.setAdapter(mActTutorialViewPagerAdapter);

        //关联ViewPage
        mAct_tutorial_cpi_indicator.setViewPager(mAct_tutorial_vp_image);
    }

    private void initView() {
        mAct_tutorial_vp_image = (ViewPager) findViewById(R.id.act_tutorial_vp_image);
        mAct_tutorial_cpi_indicator = (CirclePageIndicator) findViewById(R.id.act_tutorial_cpi_indicator);
        mAct_tutorial_btn_start_experience = (Button) findViewById(R.id.act_tutorial_btn_start_experience);
    }
}
