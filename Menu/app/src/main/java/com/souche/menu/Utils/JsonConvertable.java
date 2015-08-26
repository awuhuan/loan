package com.souche.menu.Utils;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by wangjiguang on 15/8/11.
 */
public interface JsonConvertable<T extends JsonConvertable> {
    T fromJson(Context context, JSONObject json);
}
