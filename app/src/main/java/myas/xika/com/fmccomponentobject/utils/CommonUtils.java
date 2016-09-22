package myas.xika.com.fmccomponentobject.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by XikaKyc on 2016/8/2.
 * CommonUtils 常见的工具类
 */
public class CommonUtils {
    /**
     * Desc : 关流
     *
     * @param closeable
     */
    public static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //引用置为null
            closeable = null;
        }
    }
}
