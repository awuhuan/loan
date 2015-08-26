package com.souche.menu.dialog;
/**
 *
 */

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.souche.menu.R;


/**
 * @author Vinda.Z
 * @ClassName XAlertDialog
 * @QQ 443550101
 * @Email vinda.z@outlook.com
 * @date 2014-4-11下午2:24:54
 */
public class LoadingDialog extends Dialog {
    private Context mContext;

    /**
     * @param context
     */
    public LoadingDialog(Context context) {
        this(context, 0, 0);
    }

    /**
     * @param context
     */
    public LoadingDialog(Context context, int width, int height) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mContext = context;
        initView();
    }

    private void initView() {
        setContentView(R.layout.view_loading_dialog);
    }

    /**
     * 设置对话框内容
     *
     * @param msg
     */
    public LoadingDialog setMessage(String msg) {
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setText(msg);
        return this;
    }
}
