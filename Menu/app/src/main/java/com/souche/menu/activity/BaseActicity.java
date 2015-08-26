package com.souche.menu.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.souche.menu.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangjiguang on 15/8/20.
 */
public class BaseActicity extends ActionBarActivity {
    protected final String TAG_REQUEST = "MY_TAG";

    protected JsonObjectRequest jsonObjRequest;
    protected RequestQueue mVolleyQueue;


    private ProgressDialog mProgress;


    private static long lastClickTime;
    private static int lastClickViewId;
    private static final int KEY_PREVENT_TS = -20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVolleyQueue = Volley.newRequestQueue(this);
        lastClickTime = System.currentTimeMillis();
    }


    protected void showProgress(final Context context) {
        mProgress = ProgressDialog.show(context, "", "稍等...");
    }

    protected void stopProgress() {
        if(mProgress!=null)
            mProgress.cancel();
    }


    protected void parseResponse(JSONObject response,final Context context) throws JSONException {

        showToast(context,"操作成功");
    }




    /**
     * d
     * 发送取消／恢复当前订单http请求
     *
     * @param
     */
    protected void sendPost(String url, final boolean show,final Context context) {
        if(show){
            showProgress(this);
        }
        jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("status") && "0".equals(response.get("status"))) {
                        if(show){
                            stopProgress();
                        }
                        parseResponse(response, context);
                    } else if (response.has("msg")) {
                        if(show){
                            stopProgress();
                        }
                        showToast(context,response.get("msg").toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    stopProgress();
//                    showToast(context,"JSON parse error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("loan", error.getMessage());
                stopProgress();
                showToast(context,"网络错误");

            }
        });
        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjRequest.setTag(TAG_REQUEST);
        mVolleyQueue.add(jsonObjRequest);
    }


//    private final static int DATE_DIALOG = 0;
//    private final static int TIME_DIALOG = 1;
//    private Calendar c = null;
//
//    @Override
//    protected Dialog onCreateDialog(int type) {
//        Dialog dialog = null;
//        switch (type) {
//            case DATE_DIALOG:
//                c = Calendar.getInstance();
//                dialog = new DatePickerDialog(
//                        this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
//                                interviewTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
//                            }
//                        },
//                        c.get(Calendar.YEAR), // 传入年份
//                        c.get(Calendar.MONTH), // 传入月份
//                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
//                );
//                break;
//            case TIME_DIALOG:
//                c = Calendar.getInstance();
//                dialog = new TimePickerDialog(
//                        this,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
////                                et.setText("您选择了："+hourOfDay+"时"+minute+"分");
//                            }
//                        },
//                        c.get(Calendar.HOUR_OF_DAY),
//                        c.get(Calendar.MINUTE),
//                        false
//                );
//                break;
//        }
//        return dialog;
//    }
//



    /**
     * 判断是否异常的双击（300毫秒内不能点击不同控件，500毫秒内不能点击相同控件）
     *
     * @return
     */
    public static boolean isFastDoubleClick(View v) {
        long now = System.currentTimeMillis();
        //检查是否被阻止点击
        if (v.getTag(KEY_PREVENT_TS) != null && v.getTag(KEY_PREVENT_TS) instanceof Long) {
            if ((Long) v.getTag(KEY_PREVENT_TS) > now) {
                return true;
            }
        }
        long interval = now - lastClickTime;
        if (lastClickViewId == v.getId() && interval < 500) {
            return true;
        } else if (interval < 300) {
            return true;
        }
        lastClickViewId = v.getId();
        lastClickTime = now;
        return false;
    }


    /**
     * 退出当前界面
     */
    protected void finishActivity() {
        this.finish();
        // 退出动画
        overridePendingTransition(android.R.anim.fade_in, R.anim.abc_slide_out_top);

    }

    protected void showToast(final Context context,final String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
