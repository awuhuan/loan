package com.souche.menu.Utils;

import android.util.Log;
import android.view.View;

/**
 * Created by wangjiguang on 15/8/11.
 */
public class CommonUtils {

    private static final int KEY_PREVENT_TS = -20000;

    private static String TAG = "CommonUtils";

    private static long lastClickTime;
    private static int lastClickViewId;
    /**
     * 判断是否异常的双击（300毫秒内不能点击不同控件，500毫秒内不能点击相同控件）
     *
     * @return
     */
    public static boolean isFastDoubleClick(View v) {
        long now = System.currentTimeMillis();
        //检查是否被阻止点击
        if (v.getTag(KEY_PREVENT_TS) != null && v.getTag(KEY_PREVENT_TS) instanceof Long) {
            if ((Long) v.getTag(KEY_PREVENT_TS) > now) {
                Log.d(TAG, "Click prevented before " + v.getTag(KEY_PREVENT_TS));
                return true;
            }
        }
        long interval = now - lastClickTime;
        if (lastClickViewId == v.getId() && interval < 500) {
            Log.d(TAG, "isFastDoubleClick:same view");
            return true;
        } else if (interval < 300) {
            Log.d(TAG, "isFastDoubleClick:not same view");
            return true;
        }
        lastClickViewId = v.getId();
        lastClickTime = now;
        return false;
    }


    /**
     * 阻止点击事件若干毫秒，在使用isFastDoubleClick检查是会判断
     *
     * @param view
     * @param ts
     */
    public static void preventClick(View view, long ts) {
        view.setTag(KEY_PREVENT_TS, System.currentTimeMillis() + ts);
    }
}
