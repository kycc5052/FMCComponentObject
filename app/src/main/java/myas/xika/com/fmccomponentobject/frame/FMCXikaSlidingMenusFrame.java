package myas.xika.com.fmccomponentobject.frame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by XikaKyc on 2016/9/14.
 * 滑动菜单框架
 * <p/>
 * 设置单位，1000 表示每秒多少像素（pix/second),1代表每微秒多少像素（pix/millisecond)。
 * 从左向右划返回正数，从右向左划返回负数
 * 从上往下划返回正数，从下往上划返回负数
 * <p/>
 * 知识点一：  关于scrollTo()和scrollBy()以及偏移坐标的设置/取值问题
 * 我们掌握了scrollTo()和scrollBy()方法的作用，这两个方法的主要作用是将View/ViewGroup移至指定的坐标中，并且将偏移量保存起来。
 * <p/>
 * 另外：
 * mScrollX 代表X轴方向的偏移坐标
 * mScrollY 代表Y轴方向的偏移坐标
 * <p/>
 * 于是，在任何时刻我们都可以获取该View/ViewGroup的偏移位置了，即调用getScrollX()方法和getScrollY()方法
 * <p/>
 * 知识点二： Scroller类的介绍
 * 在初次看Launcher滑屏的时候，我就对Scroller类的学习感到非常蛋疼，完全失去了继续研究的欲望。如今，没得办法，
 * 得重新看Launcher模块，基本上将Launcher大部分类以及功能给掌握了。当然，也花了一天时间来学习Launcher里的滑屏实现，
 * 基本上业是拨开云雾见真知了。
 * <p/>
 * 我们知道想把一个View偏移至指定坐标(x,y)处，利用scrollTo()方法直接调用就OK了，但我们不能忽视的是，该方法本身
 * 来的的副作用：非常迅速的将View/ViewGroup偏移至目标点，而没有对这个偏移过程有任何控制，对用户而言可能是不太
 * 友好的。于是，基于这种偏移控制，Scroller类被设计出来了，该类的主要作用是为偏移过程制定一定的控制流程(后面我们会
 * 知道的更多)，从而使偏移更流畅，更完美。
 * <p/>
 * 可能上面说的比较悬乎，道理也没有讲透。下面我就根据特定情景帮助大家分析下：
 * 情景： 从上海如何到武汉？
 * 普通的人可能会想，so easy : 飞机、轮船、11路公交车...
 * 文艺的人可能会想,  小 case ： 时空忍术(火影的招数)、翻个筋斗(孙大圣的招数)...
 * <p/>
 * 不管怎么样，我们想出来的套路可能有两种：
 * 1、有个时间控制过程才能抵达(缓慢的前进)                              -----     对应于Scroller的作用
 * 假设做火车,这个过程可能包括: 火车速率，花费周期等；
 * 2、瞬间抵达(超神太快了，都眩晕了，用户体验不太好)                     ------   对应于scrollTo()的作用
 * <p/>
 * 模拟Scroller类的实现功能：
 * 假设从上海做动车到武汉需要10个小时，行进距离为1000km ，火车速率200/h 。采用第一种时间控制方法到达武汉的
 * 整个配合过程可能如下:
 * 我们每隔一段时间(例如1小时)，计算火车应该行进的距离，然后调用scrollTo()方法，行进至该处。10小时过完后，
 * 我们也就达到了目的地了。
 * 相信大家心里应该有个感觉了。我们就分析下源码里去看看Scroller类的相关方法.
 * private int mStartX;    //起始坐标点 ,  X轴方向
 * private int mStartY;    //起始坐标点 ,  Y轴方向
 * private int mCurrX;     //当前坐标点  X轴， 即调用startScroll函数后,经过一定时间所达到的值
 * private int mCurrY;     //当前坐标点  Y轴， 即调用startScroll函数后,经过一定时间所达到的值
 * private float mDeltaX;  //应该继续滑动的距离， X轴方向
 * private float mDeltaY;  //应该继续滑动的距离， Y轴方向
 * private boolean mFinished;  //是否已经完成本次滑动操作， 如果完成则为 true
 * //构造函数
 * public Scroller(Context context) {
 * this(context, null);
 * }
 * //是否已经完成本次滑动操作
 * public final boolean isFinished() {
 * return mFinished;
 * }
 * //强制结束本次滑屏操作
 * public final void forceFinished(boolean finished) {
 * mFinished = finished;
 * }
 * //获取当前坐标点  X轴
 * public final int getCurrX() {
 * return mCurrX;
 * }
 * //根据当前已经消逝的时间计算当前的坐标点，保存在mCurrX和mCurrY值中
 * public boolean computeScrollOffset() {
 * if (mFinished) {  //已经完成了本次动画控制，直接返回为false
 * return false;
 * }
 * int timePassed = (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);
 * if (timePassed < mDuration) {
 * switch (mMode) {
 * case SCROLL_MODE:
 * float x = (float)timePassed * mDurationReciprocal;
 * ...
 * mCurrX = mStartX + Math.round(x * mDeltaX);
 * mCurrY = mStartY + Math.round(x * mDeltaY);
 * break;
 * ...
 * }
 * else {
 * mCurrX = mFinalX;
 * mCurrY = mFinalY;
 * mFinished = true;
 * }
 * return true;
 * }
 * //开始一个动画控制，由(startX , startY)在duration时间内前进(dx,dy)个单位，即到达坐标为(startX+dx , startY+dy)出
 * public void startScroll(int startX, int startY, int dx, int dy, int duration) {
 * mFinished = false;
 * mDuration = duration;
 * mStartTime = AnimationUtils.currentAnimationTimeMillis();
 * mStartX = startX;       mStartY = startY;
 * mFinalX = startX + dx;  mFinalY = startY + dy;
 * mDeltaX = dx;            mDeltaY = dy;
 * ...
 * }
 * }
 * 其中比较重要的两个方法为：
 * public boolean computeScrollOffset()
 * 函数功能说明：根据当前已经消逝的时间计算当前的坐标点，保存在mCurrX和mCurrY值中
 * public void startScroll(int startX, int startY, int dx, int dy, int duration)
 * 函数功能说明：开始一个动画控制，由(startX , startY)在duration时间内前进(dx,dy)个单位，到达坐标为
 * (startX+dx , startY+dy)处。
 * <p/>
 * PS ： 强烈建议大家看看该类的源码，便于后续理解。
 * <p/>
 * 知识点二： computeScroll(）方法介绍
 * 为了易于控制滑屏控制，Android框架提供了 computeScroll()方法去控制这个流程。在绘制View时，会在draw()过程调用该
 * 方法。因此， 再配合使用Scroller实例，我们就可以获得当前应该的偏移坐标，手动使View/ViewGroup偏移至该处。
 * computeScroll()方法原型如下，该方法位于ViewGroup.java类中
 * <p/>
 * 由父视图调用用来请求子视图根据偏移值 mScrollX,mScrollY重新绘制
 * public void computeScroll() { //空方法 ，自定义ViewGroup必须实现方法体
 * }
 * 为了实现偏移控制，一般自定义View/ViewGroup都需要重载该方法 。
 * 其调用过程位于View绘制流程draw()过程中，如下：
 * <p/>
 * Demo说明:
 * 我们简单的复用了之前写的一个自定义ViewGroup，与以前一次有区别的是，我们没有调用scrollTo()方法去进行瞬间
 * 偏移。 本次做法如下：
 * 第一、调用Scroller实例去产生一个偏移控制(对应于startScroll()方法)
 * 第二、手动调用invalidate()方法去重新绘制，剩下的就是在 computeScroll()里根据当前已经逝去的时间，获取当前
 * 第三、当前应该偏移的坐标，调用scrollBy()方法去缓慢移动至该坐标处。
 */
public class FMCXikaSlidingMenusFrame extends ViewGroup {
    //TAG
    private static final String TAG = "FMCXikaSlidingMenusFrame";
    //菜单,内容
    private View mContentFrameChild, mMenuFrameChild;
    //菜单Flag,内容Flag
    private static final int Menu_Frame_Child = 0;
    private static final int Content_Frame_Child = 1;
    //布局 左上右下
    private int left, top, right, bottom;
    //按下 X,Y
    private float mDownX, mDownY;
    //Scroller类 滑屏
    private Scroller mScroller;

    /**
     * 构造函数
     * 调用自己有参构造
     */
    public FMCXikaSlidingMenusFrame(Context context) {
        this(context, null);
    }

    /**
     * 有参构造函数
     */
    public FMCXikaSlidingMenusFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context); //实例化Scroller类 滑屏对象
    }

    /**
     * 测量函数
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec); //测量孩子
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 布局函数
     */
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //布局孩子
        mMenuFrameChild = getChildAt(Menu_Frame_Child);
        mContentFrameChild = getChildAt(Content_Frame_Child);
        //布局参数
        left = -mMenuFrameChild.getMeasuredWidth();
        top = 0;
        right = 0;
        bottom = mMenuFrameChild.getMeasuredHeight();
        //布局菜单孩子
        mMenuFrameChild.layout(left, top, right, bottom);

        //布局参数
        left = 0;
        top = 0;
        right = mContentFrameChild.getMeasuredWidth();
        bottom = mContentFrameChild.getMeasuredHeight();
        //布局内容孩子
        mContentFrameChild.layout(left, top, right, bottom);
    }

    /**
     * 触摸事件
     */
    @SuppressLint("LongLogTag")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判断动作
        switch (event.getAction()) {
            //按下动作
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();  //获取按下X，Y 点
                mDownY = event.getY();
                break;
            //抬起动作
            case MotionEvent.ACTION_UP:
                float thresholdMenu = -mMenuFrameChild.getWidth() / 2; //获取菜单临界值
                //判断是否少于临界值
                if (getScrollX() < thresholdMenu) {
                    int startX = getScrollX(); //开始的X
                    int endX = -mMenuFrameChild.getWidth();//结束的X
                    int dx = endX - startX;//计算X，偏移量
                    //平滑滚动函数(开始X，开始Y，X的偏移量,Y的偏移量，时长)
                    smoothnessScroller(startX, 0, dx, 0, 500);
                } else {
                    int startX = getScrollX();//开始的X
                    int endX = 0;//结束的X
                    int dx = endX - startX;//计算X，偏移量
                    //平滑滚动函数(开始X，开始Y，X的偏移量,Y的偏移量，时长)
                    smoothnessScroller(startX, 0, dx, 0, 500);
                }
                break;
            //移动动作
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();//移动 X
                float diffX = mDownX - moveX; //滚动偏移量
                float finalX = getScrollX() + diffX;//最终的滚动偏移量

                if (finalX > 0) {
                    scrollTo(0, 0);//滚动
                    return true;
                } else if (finalX < -mMenuFrameChild.getMeasuredWidth()) {
                    scrollTo(-mMenuFrameChild.getMeasuredWidth(), 0);
                    return true;
                }
                scrollBy((int) diffX, 0);//滚动
                //重新赋值按下X
                mDownX = moveX;
                break;

        }
        return true; //消费事件
    }

    /**
     * onScrollChanged方法有四个参数
     * 第一个参数为变化后的X轴位置 l
     * 第二个参数为变化后的Y轴位置 t
     * 第三个参数为原先的X轴位置 oldl
     * 第四个参数为原先的Y轴位置 oldt
     * <p/>
     * float scale = l * 1.0f / mContentFrameChild.getWidth();
     * 缩放
     * rightScale 右边缩放
     * float rightScale = 1.0f + scale * 0.1f;
     * 前面的 1.0f 右边返回时恢复正常大下， 0.5f 右边返回时恢复一半大小。
     * 中间的 scale 缩放
     * 后面的 0.1f 右边距离上边距和下边距的间距，值越大，间距越大。
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        float scale = l * 1.0f / mContentFrameChild.getWidth();
        float rightScale = 1.0f + scale * 0.1f;
        mContentFrameChild.setScaleY(rightScale);
    }

    /**
     * 平滑滚动函数
     */
    private void smoothnessScroller(int startX, int startY, int dx, int dy, int duration) {
        //开始一个动画控制，由(startX , startY)在duration时间内前进(dx,dy)个单位，即到达坐标为(startX+dx , startY+dy)处。
        mScroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();//触发重新绘制
    }


    /**
     * 估算滚动偏移量函数
     */
    @Override
    public void computeScroll() {
        //计算新的滚动偏移量
        if (mScroller.computeScrollOffset()) { //如果mScroller没有调用startScroll，这里将会返回false。
            //因为调用computeScroll函数的是MyLinearLayout实例，
            //所以调用scrollTo移动的将是该实例的孩子，也就是MyButton实例
            scrollTo(mScroller.getCurrX(), 0);
            //继续让系统重绘
            invalidate();//触发重新绘制
        }
    }
}
