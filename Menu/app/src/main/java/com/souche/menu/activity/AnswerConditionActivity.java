package com.souche.menu.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.souche.menu.R;
import com.souche.menu.Utils.DateUtils;
import com.souche.menu.model.AnswerModel;
import com.souche.menu.model.CompanyModel;
import com.souche.menu.model.OrderModel;
import com.souche.menu.model.SettleCity;
import com.souche.menu.view.MonthPickerPopWindow;
import com.souche.menu.view.SimpleTextPickerPopWindow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjiguang on 15/8/11.
 */
public class AnswerConditionActivity extends BaseActicity implements View.OnClickListener {

    private TextView tv_submit, tv_loanPeriod, tv_seller, tv_order, tv_mileage, tv_seatCount, tv_isLocal,
            tv_hasHouse, tv_bankFlow, tv_inCome, tv_hasAssure, tv_carUseWay, tv_registTime, tv_carPrice, tv_loanAmount;

    private View rootView, mileage, loanPeriod, seatCount, isLocal, hasHouse, bankFlow, inCome,
            hasAssure, settleCity, carUseWay, carPrice, loanAmount, registTime;

    private Spinner tv_settleCity;

    private SimpleTextPickerPopWindow operationTypePicker;

    AnswerModel answerModel = new AnswerModel();

    OrderModel orderModel;

    private List<String> citys;

    ArrayList<CompanyModel> list = new ArrayList<>();


    private MonthPickerPopWindow regTimePicker;


    /**
     * 从车型号得到的年份，用于设置尚持上牌的最早年份
     */
    private int carModelYear = 1943;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_condition);
        getSettleCityList();
        Intent intent = getIntent();
        orderModel = (OrderModel) intent.getSerializableExtra("orderModel");
        findView();
        setClickListen();
    }


    private void findView() {
        tv_seller = (TextView) findViewById(R.id.tv_seller);
        tv_order = (TextView) findViewById(R.id.tv_order);
        tv_seller.setText("卖家电话：" + orderModel.getSeller());
        tv_order.setText("订单编号：" + orderModel.getOrderId());

        tv_settleCity = (Spinner) findViewById(R.id.tv_settleCity);

        tv_mileage = (TextView) findViewById(R.id.tv_mileage);
        tv_seatCount = (TextView) findViewById(R.id.tv_seatCount);
        tv_isLocal = (TextView) findViewById(R.id.tv_isLocal);
        tv_hasHouse = (TextView) findViewById(R.id.tv_hasHouse);
        tv_bankFlow = (TextView) findViewById(R.id.tv_bankFlow);
        tv_inCome = (TextView) findViewById(R.id.tv_inCome);
        tv_hasAssure = (TextView) findViewById(R.id.tv_hasAssure);

        tv_carUseWay = (TextView) findViewById(R.id.tv_carUseWay);
        tv_loanPeriod = (TextView) findViewById(R.id.tv_loanPeriod);
        tv_carPrice = (TextView) findViewById(R.id.tv_carPrice);
        tv_loanAmount = (TextView) findViewById(R.id.tv_loanAmount);
        tv_registTime = (TextView) findViewById(R.id.tv_registTime);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        rootView = findViewById(R.id.root_view);

        mileage = findViewById(R.id.mileage);
        seatCount = findViewById(R.id.seatCount);
        isLocal = findViewById(R.id.isLocal);
        hasHouse = findViewById(R.id.hasHouse);
        bankFlow = findViewById(R.id.bankFlow);
        inCome = findViewById(R.id.inCome);
        hasAssure = findViewById(R.id.hasAssure);
        settleCity = findViewById(R.id.settleCity);
        carUseWay = findViewById(R.id.carUseWay);
        loanPeriod = findViewById(R.id.loanPeriod);
        registTime = findViewById(R.id.registTime);

    }

    private void setClickListen() {
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        mileage.setOnClickListener(this);
        seatCount.setOnClickListener(this);
        isLocal.setOnClickListener(this);
        hasHouse.setOnClickListener(this);
        bankFlow.setOnClickListener(this);
        inCome.setOnClickListener(this);
        hasAssure.setOnClickListener(this);
        settleCity.setOnClickListener(this);
        carUseWay.setOnClickListener(this);
        loanPeriod.setOnClickListener(this);
        registTime.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

    }
    private ArrayAdapter<String> arr_adapter;


    @Override
    public void onClick(View view) {
        if(isFastDoubleClick(view)){
            return;
        }

        Integer vid = view.getId();
        if (vid == R.id.tv_cancel) {
            finish();
        } else if (vid == R.id.mileage) {
            String[] mileage = getResources().getStringArray(R.array.mileage);
            showPicker(tv_mileage, mileage, 1);

        } else if (vid == R.id.seatCount) {
            String[] seatCount = getResources().getStringArray(R.array.seatCount);
            showPicker(tv_seatCount, seatCount, 2);

        } else if (vid == R.id.isLocal) {
            String[] local = getResources().getStringArray(R.array.isLoacal);
            showPicker(tv_isLocal, local, 3);

        } else if (vid == R.id.hasHouse) {
            String[] hasHouse = getResources().getStringArray(R.array.yesOrNot);
            showPicker(tv_hasHouse, hasHouse, 4);

        } else if (vid == R.id.bankFlow) {
            String[] bankFlow = getResources().getStringArray(R.array.canOrNot);
            showPicker(tv_bankFlow, bankFlow, 5);

        } else if (vid == R.id.inCome) {
            String[] inCome = getResources().getStringArray(R.array.canOrNot);
            showPicker(tv_inCome, inCome, 6);
        } else if (vid == R.id.hasAssure) {
            String[] hasAssure = getResources().getStringArray(R.array.canOrNot);
            showPicker(tv_hasAssure, hasAssure, 7);

        } else if (vid == R.id.carUseWay) {
            String[] useWayParam = getResources().getStringArray(R.array.useWay);
            showPicker(tv_carUseWay, useWayParam, 8);

        } else if (vid == R.id.loanPeriod) {
            String[] period = getResources().getStringArray(R.array.loanPeriod);
            showPicker(tv_loanPeriod, period, 9);
        } else if (vid == R.id.settleCity) {
            if (citys == null || citys.size() < 1) {
                return;
            }




        } else if (vid == R.id.registTime) {
            showRegTimePicker();
        } else if (vid == R.id.tv_submit) {
            answerModel.setOrderId(orderModel.getOrderId());
            answerModel.setCarPrice(tv_carPrice.getText().toString());
            answerModel.setLoanAmount(tv_loanAmount.getText().toString());
            answerModel.setSettleCity(tv_settleCity.getSelectedItem().toString());
            if (checkParamEmpty(answerModel)) {
                getCanLoanCompanyList();
            } else {
                showToast(this, "请填写完整贷款条件");
            }


        }
    }



    private void getCanLoanCompanyList() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.getCanLoanCompanyList);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("sign", "nobug");
        builder.appendQueryParameter("orderId", answerModel.getOrderId().toString());
        builder.appendQueryParameter("settleCity", answerModel.getSettleCity());
        builder.appendQueryParameter("carRegistTime", answerModel.getCarRegistTime());
        builder.appendQueryParameter("carMileage", answerModel.getCarMileage());
        builder.appendQueryParameter("seatCount", answerModel.getSeatCount());
        builder.appendQueryParameter("carUseWay", answerModel.getCarUseWay());
        builder.appendQueryParameter("carPrice", answerModel.getCarPrice());
        builder.appendQueryParameter("loanAmount", answerModel.getLoanAmount());
        builder.appendQueryParameter("loanPeriod", answerModel.getLoanPeriod());
        builder.appendQueryParameter("isAboriginal", answerModel.getIsAboriginal());
        builder.appendQueryParameter("hasHouse", answerModel.getHasHouse());
        builder.appendQueryParameter("hasBankFlow", answerModel.getHasBankFlow());
        builder.appendQueryParameter("canShowIncome", answerModel.getCanShowIncome());
        builder.appendQueryParameter("hasPeopleAssure", answerModel.getHasPeopleAssure());

        jsonObjRequest = new JsonObjectRequest(Request.Method.POST, builder.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    parseCanLoanCompanyResponse(response);
                    stopProgress();
                } catch (Exception e) {
                    e.printStackTrace();
                    showToast(AnswerConditionActivity.this,"JSON parse error");
                    stopProgress();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                stopProgress();
                Log.w("loan", error.getMessage());
                showToast(AnswerConditionActivity.this,"网络错误");
            }
        });
        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjRequest.setTag(TAG_REQUEST);
        mVolleyQueue.add(jsonObjRequest);
    }



    private void parseCanLoanCompanyResponse(JSONObject response) throws JSONException {
        if (response.has("status") && "0".equals(response.get("status"))) {
            try {
                JSONObject photos = response.getJSONObject("data");
                JSONArray jsonArray = photos.getJSONArray("companyList");

                if(jsonArray.length()>0) {
                    for (int index = 0; index < jsonArray.length(); index++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(index);
                        Gson gson = new Gson();
                        CompanyModel companyModel = gson.fromJson(jsonObj.toString(), CompanyModel.class);
                        list.add(companyModel);
                    }
                }

                Intent intent = new Intent(AnswerConditionActivity.this, CheckLoanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", list);
                bundle.putSerializable("answerModel", answerModel);
                intent.putExtras(bundle);
                startActivity(intent);
                finishActivity();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showToast(AnswerConditionActivity.this,"提交失败");
        }

    }




    /**
     * 上牌时间选择
     */
    private void showRegTimePicker() {
        final String noReg = getString(R.string.no_reg);
        //根据条件设置时间选择器的最小时间和最大时间
        int minYear;
        //显示最大年份
        int maxYear;
        //显示最大年份的月数
        int maxMonth;
        if (carModelYear != 1943) {
            //显示最小年份
            minYear = carModelYear - 2;
            //显示最大年份
            maxYear = carModelYear + 4;
            //显示最大年份的月数
            maxMonth = 12;
            if (maxYear > DateUtils.getYear()) {
                maxYear = DateUtils.getYear();
                maxMonth = DateUtils.getMonth();
            }
        } else {
            minYear = DateUtils.getYear() - 19;
            maxYear = DateUtils.getYear();
            maxMonth = DateUtils.getMonth();
        }

        if (regTimePicker == null) {
            regTimePicker = new MonthPickerPopWindow(rootView,
                    new MonthPickerPopWindow.OnPickedListener() {
                        @Override
                        public void onPicked(String y, String m) {
                            //未上牌显示“未上牌”，否则显示xxxx年xx月
                            if (y.equals(m) && y.equals(getString(R.string.un_plated))) {
                                tv_registTime.setText(y);
                                Calendar c = Calendar.getInstance();
                                c.set(1970, 0, 1, 0, 0, 0);
                                answerModel.setCarRegistTime(DateUtils.getFormatDateTime(c.getTime(), "yyyy-mm"));
                            } else {
                                String ym = y + "年" + m + "月";
                                tv_registTime.setText(ym);
                                answerModel.setCarRegistTime(y + "-" + m);
                            }
                        }
                    }
            );
//            regTimePicker.addBindFoot(noReg, noReg);   未上牌
        }
        regTimePicker.setRange(minYear, 1, maxYear, maxMonth, true);
        regTimePicker.show();
    }


    private void showPicker(final TextView tv, final String[] arr, final Integer indexParam) {
        operationTypePicker = new SimpleTextPickerPopWindow(rootView, arr,
                new SimpleTextPickerPopWindow.OnPickedListener() {
                    @Override
                    public void onPicked(int id, String label) {
                        tv.setText(label);
                        setAnswerModelInfo(id, indexParam);
                    }
                }
        );
        operationTypePicker.show();
    }


    private void setAnswerModelInfo(Integer index, Integer indexParam) {
        String[] param = getResources().getStringArray(R.array.yesOrNo);
        switch (indexParam) {
            case 1:
                String[] mileageParam = getResources().getStringArray(R.array.mileageParam);
                answerModel.setCarMileage(mileageParam[index]);
                break;
            case 2:
                String[] seatCountParam = getResources().getStringArray(R.array.seatCountParam);
                answerModel.setSeatCount(seatCountParam[index]);
                break;
            case 3:
                answerModel.setIsAboriginal(param[index]);
                break;
            case 4:
                answerModel.setHasHouse(param[index]);
                break;
            case 5:
                answerModel.setHasBankFlow(param[index]);
                break;
            case 6:
                answerModel.setCanShowIncome(param[index]);
                break;
            case 7:
                answerModel.setHasPeopleAssure(param[index]);
                break;
            case 8:
                String[] oneOrTwoParam = getResources().getStringArray(R.array.oneOrTwo);
                answerModel.setCarUseWay(oneOrTwoParam[index]);
                break;
            case 9:
                String[] loanPeriodParam = getResources().getStringArray(R.array.loanPeriodParam);
                answerModel.setLoanPeriod(loanPeriodParam[index]);
                break;

        }
    }


    @Override
    protected void parseResponse(JSONObject response, final Context context) throws JSONException {
        if (response.has("status") && "0".equals(response.get("status"))) {
            try {
                JSONArray jsonArray = response.getJSONArray("data");
                citys = new ArrayList<>();
                for (int index = 0; index < jsonArray.length(); index++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(index);
                    Gson gson = new Gson();
                    SettleCity city = gson.fromJson(jsonObj.toString(), SettleCity.class);
                    citys.add(city.getCityName());
                }

                //适配器
                arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citys);
                //设置样式
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                tv_settleCity.setAdapter(arr_adapter);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void getSettleCityList() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.getCityList);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("sign", "nobug");
        builder.appendQueryParameter("type", "regist");
        sendPost(builder.toString(),true, this);
    }


    /**
     * 判断机构各个条件是否有空
     *
     * @param answerModel
     * @return
     */
    private boolean checkParamEmpty(AnswerModel answerModel) {
        Class cls = answerModel.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<String, String> noCheckMap = new HashMap();
        noCheckMap.put("dateCreate", "");
        noCheckMap.put("dateUpdate", "");
        noCheckMap.put("dateDelete", "");

        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            if (noCheckMap.containsKey(f.getName())) {
                continue;
            }
            try {
                if (f.get(answerModel) == null) {
                    return false;
                }

            } catch (IllegalAccessException e) {

                e.printStackTrace();
                return false;
            }

        }
        return true;
    }


}
