package com.souche.menu.Utils;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by wangjiguang on 15/8/11.
 */

public class Option implements JsonConvertable {
    //以下为必填字段
    protected String value;
    protected String label;
    //以下为辅助字段
    protected String iconUrl;
    protected String selectedIconUrl;
    protected String type;//option类型
    protected String summary;
    protected String keyword;//用于筛选的关键字，不会显示

    @Override
    public Option fromJson(Context context, JSONObject json) {
        value = JsonHelper.optString(json, "id");
        label = JsonHelper.optString(json, "name");
        return this;
    }

    public Option(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public Option() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSelectedIconUrl() {
        return selectedIconUrl;
    }

    public void setSelectedIconUrl(String selectedIconUrl) {
        this.selectedIconUrl = selectedIconUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
