/**
 *
 */
package com.souche.menu.view;

import android.view.View;

import com.souche.menu.Utils.Option;


/**
 * 简单文本范围拾取
 *
 * @author Vinda.Z
 * @ClassName SimpleTextPickerPopWindow
 * @QQ 443550101
 * @Email vinda.z@outlook.com
 * @date 2014-3-26下午3:37:13
 */
public class SimpleTextPickerPopWindow extends BottomUpSelectWindow {

    private OnPickedListener mlistener;
    private View parentView;

    /**
     * 区域选定后的回调借口
     *
     * @author Vinda.Z
     * @ClassName OnPickedListener
     * @QQ 443550101
     * @Email vinda.z@outlook.com
     * @date 2014-3-24下午1:59:16
     */
    public interface OnPickedListener {
        void onPicked(int id, String label);
    }

    public SimpleTextPickerPopWindow(View parentView, final String options[],
                                     OnPickedListener listener) {
        super(parentView.getContext());
        this.parentView = parentView;
        this.mlistener = listener;
        Option[] opts = new Option[options.length];
        for (int i = 0; i < options.length; i++) {
            opts[i] = new Option();
            opts[i].setValue("");
            opts[i].setLabel(options[i]);
        }
        SimpleTextSelectView sv = new SimpleTextSelectView(parentView.getContext(), opts);
        this.setContent(sv);
        sv.setOnSubmitListener(new SimpleTextSelectView.OnSubmitListener() {
            @Override
            public void onSubmit(int id, Option opt) {
                if (mlistener != null) {
                    // 选择完成，调用回调方法
                    mlistener.onPicked(id, opt.getLabel());
                    // 完成后隐藏窗口
                    SimpleTextPickerPopWindow.this.dismiss();
                }
            }
        });
    }

    @Deprecated
    public void setCurrentItem(int pos) {
    }

    public void show() {
        super.show(parentView);
    }
}
