package myas.xika.com.fmccomponentobject.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by XikaKyc on 2016/7/31.
 * ClassName: PkgUtils.
 * Function(功能)：包管理工具类.
 *
 * @author XikaKyc
 */
public class PkgUtils {
    /**
     * Desc : 获取当前软件的版本名称
     *
     * @param context
     */
    public static String getVersionName(Context context) {
        //初始化为空
        String versionName = "";
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        try {
            /**
             * String packageName ：包名
             * int flags ：标志位
             * 持有AndroidManifest.xml文件信息
             * 0 获取所有信息
             * */
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //获取版本Name
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //如果未获取到版本Name，设置为： 未知
            versionName = "未知";
            e.printStackTrace();
        }
        //返回 :版本Name
        return versionName;
    }

    /**
     * Desc : 获取当前软件的版本号
     *
     * @param context
     */
    public static int getVersionCode(Context context) {
        //初始化为 -1
        int versionCode = -1;
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        try {
            /**
             * String packageName ：包名
             * int flags ：标志位
             * 持有AndroidManifest.xml文件信息
             * 0 获取所有信息
             * */
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //获取版本Code
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            //如果未获取到版本Code，设置为：-1
            versionCode = -1;
            e.printStackTrace();
        }
        //返回 :版本Code
        return versionCode;
    }

    /**
     * Desc :   获取某一个服务，
     * 当前是否处于活动的状态
     *
     * @param context
     * @param clazz
     */
    public static boolean isServiceRunning(Context context, Class clazz) {
        //获取活动管理器
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取正在运行的服务
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
        //遍历所有正在运行的服务
        for (ActivityManager.RunningServiceInfo info : services) {
            //获取某一个服务
            ComponentName service = info.service;
            //得到ClassName
            String className = service.getClassName();
            //判断ClassName
            if (className.equals(clazz.getName())) {
                return true;
            }
        }
        return false;
    }
}
