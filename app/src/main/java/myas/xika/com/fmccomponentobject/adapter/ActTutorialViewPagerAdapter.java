package myas.xika.com.fmccomponentobject.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by XikaKyc on 2016/9/19.
 */
public class ActTutorialViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mActTutorialViewPagerBeanDatas;

    public ActTutorialViewPagerAdapter(Context context, int[] actTutorialViewPagerBeanDatas) {
        mContext = context;
        mActTutorialViewPagerBeanDatas = actTutorialViewPagerBeanDatas;
    }

    @Override
    public int getCount() {
        return mActTutorialViewPagerBeanDatas == null ? 0 : mActTutorialViewPagerBeanDatas.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object; //都是这样写，已经是一种模式了!
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //创建一个ImageView
        ImageView mTutorialPage = new ImageView(mContext);
        //设置Image资源
        mTutorialPage.setImageResource(mActTutorialViewPagerBeanDatas[position]);
        //设置缩放类型，拉伸
        mTutorialPage.setScaleType(ImageView.ScaleType.FIT_XY);
        //把ImageView添加到ViewGroup容器里
        container.addView(mTutorialPage);
        //返回这个Page
        return mTutorialPage;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
