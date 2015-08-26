package com.souche.menu.view;

import android.view.View;

/**
 * Created by wangjiguang on 15/8/15.
 */

public class MonthPickerPopWindow extends BottomUpSelectWindow {


    private MonthSelectView monthSelectView;
    private View parentView;
    /**
     * @author Vinda.Z
     * @ClassName OnPickedListener
     * @QQ 443550101
     * @Email vinda.z@outlook.com
     * @date 2014-4-28下午4:18:58
     */
    public interface OnPickedListener {
        void onPicked(String year, String month);
    }

    public MonthPickerPopWindow(View parentView, final OnPickedListener listener) {
        super(parentView.getContext());
        this.parentView = parentView;
        monthSelectView = new MonthSelectView(parentView.getContext());
        monthSelectView.setOnSubmitListener(new MonthSelectView.OnSubmitListener() {
            @Override
            public void onSubmit(String year, String month) {
                if (listener != null) {
                    listener.onPicked(year, month);
                }
                MonthPickerPopWindow.this.dismiss();
            }
        });
        setContent(monthSelectView);
    }


    /**
     * 设置时间范围
     *
     * @param minYear
     * @param minMonth
     * @param maxYear
     * @param maxMonth
     * @param orderYearAsc
     */
    public void setRange(int minYear, int minMonth, int maxYear, int maxMonth, boolean orderYearAsc) {
        monthSelectView.setRange(minYear, minMonth, maxYear, maxMonth, orderYearAsc);
    }

    /**
     * 添加到底部
     *
     * @param ylabel
     * @param mlabel
     */
    public void addBindFoot(String ylabel, String mlabel) {
        monthSelectView.addBindFoot(ylabel, mlabel);
    }

    /**
     * 添加到头部
     *
     * @param ylabel
     * @param mlabel
     */
    public void addBindHead(String ylabel, String mlabel) {
        monthSelectView.addBindHead(ylabel, mlabel);
    }

    public void clearBindFoot() {
        monthSelectView.clearBindFoot();
    }

    public void clearBindHead() {
        monthSelectView.clearBindHead();
    }

    public void show() {
        super.show(parentView);
    }
}

