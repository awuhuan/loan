package com.souche.menu.view;

/**
 * Created by wangjiguang on 15/8/11.
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.souche.menu.R;
import com.souche.menu.Utils.CommonUtils;


/**
 * 从底部向上弹出的选择器
 * Created by vinda on 14-11-25.
 */
public class BottomUpSelectWindow extends PopupWindow
        implements View.OnClickListener {
    private final String TAG = "BottomUpSelectWindow";
    private View thisView;
    private View tv_submit;
    private FrameLayout contentPanel;
    private SubmitableView submitableView;
    private View ll_panel;

    public BottomUpSelectWindow(Context context) {
        super(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        thisView = layoutInflater.inflate(R.layout.view_bottom_up_select_window,
                null);
        ll_panel = thisView.findViewById(R.id.ll_panel);
        contentPanel = (FrameLayout) thisView.findViewById(R.id.content);
        this.setContentView(thisView);
        initView();

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        thisView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int height = ll_panel.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height && !CommonUtils.isFastDoubleClick(v)) {
                        CommonUtils.preventClick(v, 1000);
                        dismiss();
                    }
                }
                return true;
            }
        });


    }

    private void initView() {
        tv_submit = thisView.findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(this);
    }

    /**
     * 设置中间内容的view
     *
     * @param view
     */
    public void setContent(SubmitableView view) {
        submitableView = view;
        contentPanel.removeAllViews();
        contentPanel.addView(submitableView.getView());
    }

    /**
     * 左上角的标题（完成按钮左边）
     *
     * @param title
     */
    public void setTitle(String title) {
        TextView tv_title = (TextView) thisView.findViewById(R.id.tv_title);
        tv_title.setText(title);
    }

    @Override
    public void dismiss() {
        //动画
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
        animation.setDuration(200);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ll_panel.setVisibility(View.GONE);
                dismissWindow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ll_panel.startAnimation(animation);

    }

    /**
     * 隐藏整个窗口
     */
    private void dismissWindow() {
        try {
            //由于会在动画后调用，父窗口可能已经关闭，所以这里加try-catch
            super.dismiss();
        } catch (Exception e) {
            Log.e(TAG, "dismissWindow error.", e);
        }
    }

    @Override
    public void onClick(View v) {
        final int vid = v.getId();
        if (vid == R.id.tv_submit) {
            submitableView.submit();

        }
    }

    public void show(View parent) {
        this.showAtLocation(parent, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        ll_panel.setVisibility(View.VISIBLE);
        //动画
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
        animation.setDuration(200);
        ll_panel.startAnimation(animation);
    }
}
