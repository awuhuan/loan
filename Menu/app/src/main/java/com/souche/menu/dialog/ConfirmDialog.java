package com.souche.menu.dialog;

/**
 * Created by wangjiguang on 15/8/10.
 */

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.souche.menu.R;


/**
 * 确认对话框
 *
 * @author Vinda.Z
 * @ClassName ConfirmDialog
 * @QQ 443550101
 * @Email vinda.z@outlook.com
 * @date 2014-4-11下午2:24:54
 */
public class ConfirmDialog extends Dialog {
    private Context mContext;
    private LinearLayout ll_confirm_content;

    /**
     * @param context
     */
    public ConfirmDialog(Context context) {
        this(context, 0, 0);
    }

    /**
     * @param context
     */
    public ConfirmDialog(Context context, int width, int height) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        initView();

    }

    private void initView() {
        setContentView(R.layout.dialog_confirm);
        ll_confirm_content = (LinearLayout) findViewById(R.id.ll_confirm_content);
        // 设置高度宽度。。
    }

    public ConfirmDialog setLeftButton(String label, View.OnClickListener l) {
        TextView tv_left = (TextView) findViewById(R.id.tv_left_action);
        tv_left.setText(label);
        tv_left.setOnClickListener(l);
        tv_left.setVisibility(View.VISIBLE);
        checkDivider();
        return this;
    }

    public ConfirmDialog setLeftButton(int stringResId, View.OnClickListener l) {
        return setLeftButton(mContext.getString(stringResId), l);
    }


    public ConfirmDialog setRightButton(String label, View.OnClickListener l) {
        TextView tv_right = (TextView) findViewById(R.id.tv_right_action);
        tv_right.setText(label);
        tv_right.setOnClickListener(l);
        tv_right.setVisibility(View.VISIBLE);
        checkDivider();
        return this;
    }

    public ConfirmDialog setRightButton(int stringResId, View.OnClickListener l) {
        return setRightButton(mContext.getString(stringResId), l);
    }

    private void checkDivider() {
        TextView tv_right = (TextView) findViewById(R.id.tv_right_action);
        TextView tv_left = (TextView) findViewById(R.id.tv_left_action);
        View divider = findViewById(R.id.divider);
        if (tv_right.getVisibility() == View.VISIBLE
                && tv_left.getVisibility() == View.VISIBLE) {
            divider.setVisibility(View.VISIBLE);
        } else {
            divider.setVisibility(View.GONE);
        }
    }

    /**
     * 设置默认对话框的内容，确认内容confirmContent改变后此方法无效
     *
     * @param msg
     */
    public ConfirmDialog setMessage(String msg) {
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setGravity(Gravity.CENTER_HORIZONTAL);
        if (tv_message != null) {
            tv_message.setText(msg);
        }
        return this;
    }

    public ConfirmDialog setMessageAlignLeft(String msg) {
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setGravity(Gravity.LEFT);
        if (tv_message != null) {
            tv_message.setText(msg);
        }
        return this;
    }

    /**
     * 设置对话框的主体内容
     *
     * @param view
     * @return
     */
    public ConfirmDialog setConfirmContent(View view) {
        ll_confirm_content.removeAllViews();
        ll_confirm_content.addView(view);
        return this;
    }

    /**
     * 设置title
     */
    public void setConfirmTitle(String message) {
        View ll_title = findViewById(R.id.ll_confirm_title);
        TextView tv_title = (TextView) findViewById(R.id.tv_confirm_title);
        ll_title.setVisibility(View.VISIBLE);
        tv_title.setText(message);
    }

}
