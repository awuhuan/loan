package com.souche.menu.view;

import android.content.Context;

import com.souche.menu.Utils.DateUtils;
import com.souche.menu.Utils.Option;
import com.souche.menu.widget.WheelView;

import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangjiguang on 15/8/15.
 */

public class MonthSelectView extends AbstractCascadeWheelSelectView {

    private OnSubmitListener mlistener;
    private Map<Option, Option> bindhead = new LinkedHashMap<>();
    private Map<Option, Option> bindfoot = new LinkedHashMap<>();
    private int minYear;
    private int maxYear;
    private int minMonth = 1;
    private int maxMonth = 12;
    //年份排序
    private boolean orderYearAsc = true;

    @Override
    public void submit() {
        if (isCanSubmit() && mlistener != null) {
            // 选择完成，调用回调方法
            mlistener.onSubmit(getLvOneOption().getValue(), getLvTwoOption().getValue());
        }
    }


    public interface OnSubmitListener {
        void onSubmit(String year, String month);
    }

    public MonthSelectView(Context context) {
        super(context);
        // 默认显示前20年
        maxYear = DateUtils.getYear();
        minYear = maxYear - 19;
        updateView();
    }

    @Override
    protected void onChange(WheelView wheel, int oldValue, int newValue) {
        super.onChange(wheel, oldValue, newValue);
        if (wheel == getLvOneWheel()) {
            //如果滚到foot，则month也滚到对应的
            if (bindfoot.containsKey(getLvOneOption())) {
                Option m = bindfoot.get(getLvOneOption());
                getLvTwoWheel().setCurrentItem(lvTwoOptions.indexOf(m), true);
            }
            updateMonth();
        }
    }


    private void updateView() {
        lvOneOptions.clear();
        //head
        if (!bindhead.isEmpty()) {
            for (Iterator<Option> iterator = bindhead.keySet().iterator(); iterator.hasNext(); ) {
                Option y = iterator.next();
                lvOneOptions.add(y);
            }
        }
        //年数量
        int yearcount = maxYear - minYear + 1;
        //升序每次+1
        int step = orderYearAsc ? 1 : -1;
        int first = orderYearAsc ? minYear : maxYear;
        for (int i = 0; i < yearcount; i++) {
            Option opt = new Option();
            opt.setLabel("" + (first + i * step));
            opt.setValue(opt.getLabel());
            lvOneOptions.add(opt);
        }

        //foot
        if (!bindfoot.isEmpty()) {
            for (Iterator<Option> iterator = bindfoot.keySet().iterator(); iterator.hasNext(); ) {
                Option y = iterator.next();
                lvOneOptions.add(y);
            }
        }
        notifyLvOneDataChanged();
        updateMonth();
    }


    private void updateMonth() {
        lvTwoOptions.clear();
        Option y = getLvOneOption();
        if (bindhead.containsKey(y)) {
            //如果y是head里面的，那么y/m他们必须是对应的
            Option m = bindhead.get(y);
            lvTwoOptions.add(m);
        } else if (bindfoot.containsKey(y)) {
            //如果y是foot里面的，那么y/m他们必须是对应的
            Option m = bindfoot.get(y);
            lvTwoOptions.add(m);
        } else {
            int year = toInt(y.getLabel(), -1);
            int startM = 1;
            int endM = 12;
            if (year == minYear) {
                startM = minMonth;
            }
            if (year == maxYear) {
                endM = maxMonth;
            }
//            //head
//            if (!bindhead.isEmpty()) {
//                for (Iterator<String> iterator = bindhead.keySet().iterator(); iterator.hasNext(); ) {
//                    String yy = iterator.next();
//                    displayMonth.add(bindhead.get(yy));
//                }
//            }
            //月份
            for (int i = startM; i <= endM; i++) {
                Option opt = new Option();
                opt.setLabel("" + i);
                opt.setValue(opt.getLabel());
                lvTwoOptions.add(opt);
            }
            //foot
            if (!bindfoot.isEmpty()) {
                for (Iterator<Option> iterator = bindfoot.values().iterator(); iterator.hasNext(); ) {
                    Option yy = iterator.next();
                    lvTwoOptions.add(yy);
                }
            }
        }
        notifyLvTwoDataChanged();
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
        if (maxYear < minYear) {
            throw new IllegalArgumentException("MaxYear couldn't less then MinYear");
        }
        this.minYear = minYear;
        this.maxYear = maxYear;
        this.minMonth = minMonth;
        this.maxMonth = maxMonth;
        this.orderYearAsc = orderYearAsc;
        updateView();
    }

    /**
     * 添加到底部
     *
     * @param ylabel
     * @param mlabel
     */
    public void addBindFoot(String ylabel, String mlabel) {
        Option yOpt = new Option();
        yOpt.setLabel(ylabel);
        yOpt.setValue(ylabel);

        Option mOpt = new Option();
        mOpt.setLabel(mlabel);
        mOpt.setValue(mlabel);
        bindfoot.put(yOpt, mOpt);
        updateView();
    }

    /**
     * 添加到头部
     *
     * @param ylabel
     * @param mlabel
     */
    public void addBindHead(String ylabel, String mlabel) {
        Option yOpt = new Option();
        yOpt.setLabel(ylabel);
        yOpt.setValue(ylabel);

        Option mOpt = new Option();
        mOpt.setLabel(mlabel);
        mOpt.setValue(mlabel);
        bindhead.put(yOpt, mOpt);
        updateView();
    }

    public void clearBindFoot() {
        bindfoot.clear();
        updateView();
    }

    public void clearBindHead() {
        bindhead.clear();
        updateView();
    }

    @Override
    public boolean isCanSubmit() {
        boolean result = false;
        Option y = getLvOneOption();
        Option m = getLvTwoOption();
        //如果y或者m显示的内容是head/foot里面的，那么他们Label必须是对应的
        if ((bindhead.containsKey(y) || bindhead.containsValue(m))
                && !m.equals(bindhead.get(y))) {
            result = false;
        } else result = !((bindfoot.containsKey(y) || bindfoot.containsValue(m))
                && !m.equals(bindfoot.get(y)));
        return result;
    }

    public void setOnSubmitListener(OnSubmitListener l) {
        this.mlistener = l;
    }



    /**
     * 提供把字符串转为整数
     *
     * @param value 转换的数字
     * @param nint  空备用返回
     * @return 四舍五入后的结果
     */
    public static int toInt(String value, int nint) {
        if (value == null)
            return nint;
        if (!isStandardNumber(value))
            return nint;
        if (value.indexOf(".") != -1) {
            try {
                return (int) Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return nint;
            }
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return nint;
        }
    }


    public static boolean isStandardNumber(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1) {
            if (chars[start] == '0' && chars[start + 1] == 'x') {
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another
        // digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (!allowSigns
                    && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l' || chars[i] == 'L') {
                // not allowing L with an exponent
                return foundDigit && !hasExp;
            }
            // last zhex is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't
        // pass
        return !allowSigns && foundDigit;
    }

}

