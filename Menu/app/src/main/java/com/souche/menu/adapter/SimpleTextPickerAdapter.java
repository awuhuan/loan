package com.souche.menu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.souche.menu.widget.adapters.AbstractWheelTextAdapter;


/**
 * Created by wangjiguang on 15/8/11.
 */
public class SimpleTextPickerAdapter extends AbstractWheelTextAdapter {

    private Context context;
    private String[] textArr;

    public SimpleTextPickerAdapter(Context context, String[] textArr) {
        super(context);
        this.context = context;
        this.textArr = textArr;
        this.setTextSize(18);
    }

    @Override
    public int getItemsCount() {
        return textArr.length;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return textArr[index];
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        TextView tv = (TextView) super.getItem(index, convertView, parent);
        if (tv != null) {
            tv.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        }
        return tv;
    }

}
