package com.souche.menu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.souche.menu.Utils.Option;
import com.souche.menu.widget.adapters.AbstractWheelTextAdapter;

import java.util.List;

/**
 * Created by wangjiguang on 15/8/15.
 */

public class SimpleOptionWheelAdapter extends AbstractWheelTextAdapter {

    private Context context;
    private List<Option> optionList;

    public SimpleOptionWheelAdapter(Context context, List<Option> optionList) {
        super(context);
        this.context = context;
        this.optionList = optionList;
        this.setTextSize(18);
    }

    @Override
    public int getItemsCount() {
        return optionList.size();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return optionList.get(index).getLabel();
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