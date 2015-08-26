package com.souche.menu.view;

/**
 * Created by wangjiguang on 15/8/11.
 */

import android.view.View;

/**
 * 可提交的view
 * Created by vinda on 14/11/25.
 */
public interface SubmitableView {
    /**
     * 提交
     */
    void submit();

    /**
     * 获取视图
     *
     * @return
     */
    View getView();

    /**
     * 能否提交
     *
     * @return
     */
    boolean isCanSubmit();

    void onShow();

    void onHide();
}

