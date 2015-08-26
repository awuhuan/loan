package com.souche.menu.Utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangjiguang on 15/8/11.
 */

public class JsonHelper {
    private static String TAG = "JsonConvertHelper";

    /**
     * 提取字符串；由于null会被视为一个字符串，所有要用isNull先判断
     *
     * @param json
     * @param key
     * @param fallback
     * @return
     */
    public static String optString(JSONObject json, String key, String fallback) {
        if (json.isNull(key)) {
            return fallback;
        } else {
            return json.optString(key, fallback);
        }
    }

    public static String optString(JSONObject json, String key) {
        return optString(json, key, null);
    }


    /**
     * 合并两个json，包含名称相同的json子节点，会在子节点内进行合并，不会整个覆盖
     *
     * @param src 源
     * @param tar 目标，内容可能被覆盖
     */
    public static void deepMerge(JSONObject src, JSONObject tar) throws JSONException {
        for (Iterator<String> it = src.keys(); it.hasNext(); ) {
            String key = it.next();
            Object value = src.get(key);
            if (!tar.has(key)) {
                tar.put(key, value);
            } else {
                if (value instanceof JSONObject) {
                    JSONObject valueJson = (JSONObject) value;
                    //递归合并
                    deepMerge(valueJson, tar.getJSONObject(key));
                } else {
                    //覆盖
                    tar.put(key, value);
                }
            }

        }
    }

    public static List parseList(final Context context, JSONArray jsonArray,
                                 final Class<? extends JsonConvertable> clazz) throws Exception {
        int length = jsonArray.length();
        List<Object> items = new ArrayList<Object>(length);
        try {
            for (int i = 0; i < length; i++) {
                JSONObject json = jsonArray.optJSONObject(i);
                JsonConvertable item = clazz.newInstance().fromJson(context, json);
                items.add(item);
            }
        } catch (Exception e) {
            Log.e(TAG, "create Object failed.", e);
            throw e;
        }
        return items;
    }
}

