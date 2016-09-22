package myas.xika.com.fmccomponentobject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import myas.xika.com.fmccomponentobject.app.Constants;

/**
 * Created by XikaKyc on 2016/8/3.
 * <p/>
 * SharedPreferences
 * SharedPreferences是Android平台上一个轻量级的存储类，用来保存应用的一些常用配置。
 * SharedPreferences数据的四种操作模式
 * Context.MODE_PRIVATE
 * Context.MODE_APPEND
 * Context.MODE_WORLD_READABLE
 * Context.MODE_WORLD_WRITEABLE
 * Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
 * Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件.
 * Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件.
 * MODE_WORLD_READABLE：表示当前文件可以被其他应用读取.
 * MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入
 * <p/>
 * 用法
 * SharedPreferences 可以用来进行数据的共享，包括应用程序之间，或者同一个应用程序中的不同组件。比如两个activity除了通过Intent传递数据之外，也可以通过SharedPreferences来共享数据。
 * 存
 * Editor sharedata = getSharedPreferences("data", 0).edit();
 * sharedata.putString("item","hello getSharedPreferences");
 * sharedata.commit();
 * 取
 * SharedPreferences sharedata = getSharedPreferences("data", 0);
 * String data = sharedata.getString("item", null);
 * Log.v("cola","data="+data);
 */
public class SharedPrefsUtils {
    //instance实例，  单例模式, 初始化 SharedPreferences 对象
    public static SharedPreferences instance;

    //getInstance 获取实例的方法。单例模式
    public static SharedPreferences getInstance(Context context) {
        //如果对象引用为null
        if (instance == null)
            //实例化这个对象
            instance = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        //返回这个对象的实例
        return instance;
    }

    /**
     * 获取一个Boolean值，默认值是false
     */
    public static boolean getBoolean(Context context, String key) {
        //调用3个参数的方法，设置默认值
        return getBoolean(context, key, false);
    }

    /**
     * 获取一个Boolean值
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        //得到对象的实例
        SharedPreferences sp = getInstance(context);
        //根据key ,获取值，需要一个默认值
        return sp.getBoolean(key, defValue);
    }

    /**
     * 保存一个Boolean值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        //得到对象的实例
        SharedPreferences sp = getInstance(context);
        //获取编辑器
        SharedPreferences.Editor editor = sp.edit();
        //保存 key 和 value
        editor.putBoolean(key, value);
        //提交
        editor.commit();
    }

    /**
     * 获取一个String值，默认值是null
     */
    public static String getString(Context context, String key) {
        //调用3个参数的方法，设置默认值
        return getString(context, key, null);
    }

    /**
     * 获取一个String值
     */
    public static String getString(Context context, String key, String defValue) {
        //得到对象的实例
        SharedPreferences sp = getInstance(context);
        //根据key ,获取值，需要一个默认值
        return sp.getString(key, defValue);
    }

    /**
     * 保存一个String值
     */
    public static void putString(Context context, String key, String value) {
        //得到对象的实例
        SharedPreferences sp = getInstance(context);
        //获取编辑器
        SharedPreferences.Editor editor = sp.edit();
        //保存 key 和 value
        editor.putString(key, value);
        //提交
        editor.commit();
    }
}
