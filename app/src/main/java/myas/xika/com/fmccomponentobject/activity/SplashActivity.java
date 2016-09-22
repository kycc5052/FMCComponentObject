package myas.xika.com.fmccomponentobject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import myas.xika.com.fmccomponentobject.MainActivity;
import myas.xika.com.fmccomponentobject.R;
import myas.xika.com.fmccomponentobject.app.Constants;
import myas.xika.com.fmccomponentobject.utils.SharedPrefsUtils;

public class SplashActivity extends Activity {

    private static final long ANIMATION_DURATION = 2000;
    private ImageView mAct_splash_iv_splash_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initAnimation();
    }

    private void initAnimation() {
        startAnimation();
    }

    private void startAnimation() {
        //动画集合
        AnimationSet animationSet = new AnimationSet(false); //是否加速器
        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(ANIMATION_DURATION);
        animationSet.addAnimation(rotateAnimation);  //添加旋转动画
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(ANIMATION_DURATION);
        animationSet.addAnimation(scaleAnimation);  //添加缩放动画
        //透明动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//从透明到不透明
        alphaAnimation.setDuration(ANIMATION_DURATION);
        animationSet.addAnimation(alphaAnimation);  //添加透明动画
        //设置动画
        mAct_splash_iv_splash_image.setAnimation(animationSet);
        //监听动画结束
        animationSet.setAnimationListener(mSplashAnimationListener);
    }

    private Animation.AnimationListener mSplashAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            boolean mFMC_Guide_Tutorial_Flag = SharedPrefsUtils.getBoolean(SplashActivity.this, Constants.FMC_GUIDE_TUTORIAL);
            if (mFMC_Guide_Tutorial_Flag) {
                navigateToMain();
            } else {
                navigateToTutorial();
            }
        }
    };

    /**
     * 跳转到主界面
     */
    private void navigateToMain() {
        // SystemClock.sleep(1000);
        Intent mMainIntent = new Intent(this, MainActivity.class);
        startActivity(mMainIntent);
        //跳转之后finish
        finish();
    }

    /**
     * 跳转到向导界面
     */
    private void navigateToTutorial() {
        // SystemClock.sleep(1000);
        Intent mTutorialIntent = new Intent(this, TutorialActivity.class);
        startActivity(mTutorialIntent);
        //跳转之后finish
        finish();
    }

    private void initView() {
        mAct_splash_iv_splash_image = (ImageView) findViewById(R.id.act_splash_iv_splash_image);
    }


}
