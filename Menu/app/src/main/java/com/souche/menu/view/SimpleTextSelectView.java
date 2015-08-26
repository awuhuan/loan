package com.souche.menu.view;

/**
 * Created by wangjiguang on 15/8/11.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.souche.menu.R;
import com.souche.menu.Utils.Option;
import com.souche.menu.adapter.SimpleTextPickerAdapter;
import com.souche.menu.widget.WheelView;


/**
 * 简单文本范围拾取
 * <p/>
 * Created by vinda on 14-12-1.
 */
public class SimpleTextSelectView implements SubmitableView {

    private OnSubmitListener mlistener;

    private Context mContext;
    private View view;

    private WheelView wheelview;
    private Option[] options;

    @Override
    public void submit() {
        if (mlistener != null) {
            int id = wheelview.getCurrentItem();
            // 选择完成，调用回调方法
            mlistener.onSubmit(id, options[id]);
        }
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public boolean isCanSubmit() {
        return true;
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onHide() {
    }

    public interface OnSubmitListener {
        void onSubmit(int id, Option opt);
    }

    public SimpleTextSelectView(Context context, final Option options[]) {
        this.options = options;
        this.mContext = context;

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.view_single_wheel, null);
        wheelview = (WheelView) view.findViewById(R.id.wheelview);
        wheelview.setZoomCenter(true);
        String[] optlabel = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            optlabel[i] = options[i].getLabel();
        }
        SimpleTextPickerAdapter adapter = new SimpleTextPickerAdapter(mContext,
                optlabel);
        wheelview.setViewAdapter(adapter);
    }

    public void setCurrentItem(int pos) {
        wheelview.setCurrentItem(pos);
    }

    public void setOnSubmitListener(OnSubmitListener l) {
        this.mlistener = l;
    }
}
