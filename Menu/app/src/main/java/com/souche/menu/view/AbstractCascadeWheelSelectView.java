package com.souche.menu.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.souche.menu.R;
import com.souche.menu.Utils.Option;
import com.souche.menu.adapter.SimpleOptionWheelAdapter;
import com.souche.menu.widget.OnWheelChangedListener;
import com.souche.menu.widget.WheelView;
import com.souche.menu.widget.adapters.AbstractWheelAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjiguang on 15/8/15.
 */

public abstract class AbstractCascadeWheelSelectView implements SubmitableView {
    protected Context mContext;
    private View view;

    private WheelView wv_one;
    private WheelView wv_two;

    private OnChangeListener mOnChangeListener;

    protected final List<Option> lvOneOptions = new ArrayList<>();
    protected final List<Option> lvTwoOptions = new ArrayList<>();

    private AbstractWheelAdapter mLvOneAdapter;
    private AbstractWheelAdapter mLvTwoAdapter;

    public AbstractCascadeWheelSelectView(Context context) {
        this.mContext = context;
        initView();
    }

    protected WheelView getLvOneWheel() {
        return wv_one;
    }

    protected WheelView getLvTwoWheel() {
        return wv_two;
    }


    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.view_double_wheel, null);

        wv_one = (WheelView) view.findViewById(R.id.wv_start);
        wv_two = (WheelView) view.findViewById(R.id.wv_end);

        notifyLvOneDataChanged();
        notifyLvTwoDataChanged();

        wv_one.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                onChange(wv_one, oldValue, newValue);
                if (mOnChangeListener != null) {
                    mOnChangeListener.onChange(wv_one, oldValue, newValue);
                }
            }
        });

        wv_two.addChangingListener(new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                onChange(wv_two, oldValue, newValue);
                if (mOnChangeListener != null) {
                    mOnChangeListener.onChange(wv_two, oldValue, newValue);
                }
            }
        });
    }

    /**
     * 获取一级adapter，子类可重写此方法，自定义视图
     *
     * @return
     */
    protected AbstractWheelAdapter getLvOneAdapter() {
        return new SimpleOptionWheelAdapter(
                mContext, lvOneOptions);
    }

    /**
     * 获取二级adapter，子类可重写此方法，自定义视图
     *
     * @return
     */
    protected AbstractWheelAdapter getLvTwoAdapter() {
        return new SimpleOptionWheelAdapter(
                mContext, lvTwoOptions);
    }

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

    protected void onChange(WheelView wheel, int oldValue, int newValue) {

    }

    public interface OnChangeListener {
        void onChange(WheelView wheel, int oldValue, int newValue);
    }


    public void setOnChangeListener(OnChangeListener l) {
        this.mOnChangeListener = l;
    }

    /**
     * 获取一级选中项
     *
     * @return
     */
    public Option getLvOneOption() {
        return lvOneOptions.get(wv_one.getCurrentItem());
    }

    /**
     * 获取二级选中项
     *
     * @return
     */
    public Option getLvTwoOption() {
        return lvTwoOptions.get(wv_two.getCurrentItem());
    }

    protected void notifyLvOneDataChanged() {
        //TODO 重构这里，不要每次都创建新的adapter
        mLvOneAdapter = getLvOneAdapter();
        wv_one.setViewAdapter(mLvOneAdapter);
        wv_one.setCurrentItem(0);
        //重新定位
        if (wv_one.getCurrentItem() >= mLvOneAdapter.getItemsCount()) {
            if (mLvOneAdapter.getItemsCount() > 0) {
                wv_one.setCurrentItem(mLvOneAdapter.getItemsCount() - 1);
            } else {
                wv_one.setCurrentItem(0);
            }
        }
    }

    protected void notifyLvTwoDataChanged() {
        //TODO 重构这里，不要每次都创建新的adapter
        mLvTwoAdapter = getLvTwoAdapter();
        wv_two.setViewAdapter(mLvTwoAdapter);
        //重新定位
        if (wv_two.getCurrentItem() >= mLvTwoAdapter.getItemsCount()) {
            if (mLvTwoAdapter.getItemsCount() > 0) {
                wv_two.setCurrentItem(mLvTwoAdapter.getItemsCount() - 1);
            } else {
                wv_two.setCurrentItem(0);
            }
        }
    }
}
