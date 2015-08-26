package com.souche.menu.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.souche.menu.R;
import com.souche.menu.dialog.ConfirmDialog;
import com.souche.menu.model.OrderModel;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


/**
 * Created by jiangs on 14-11-4.
 */
public class DetailActivity extends BaseActicity implements View.OnClickListener {
    private TextView tv_orderId, applyCity, sellerName, tv_sellerPhone, company, carName, carVin,
            contact, contactPhone, price, auditAmount, auditTime, interviewAmount, buyerPhone, buyerName,
            interviewTime, confirmTransferResultTime, evaluation, evaluationMsg;
    private TextView btnOne, btnTwo, btnThree;


    OrderModel orderModel;

//    JsonObjectRequest jsonObjRequest;
//    private RequestQueue mVolleyQueue;
//
//    private final String TAG_REQUEST = "MY_TAG";
//
//
//    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        orderModel = (OrderModel) intent.getSerializableExtra("orderModel");
        mVolleyQueue = Volley.newRequestQueue(this);


        setContentView();

        findView();
        setText();
        findViewById(R.id.tv_cancel).setOnClickListener(this);
    }


    void setContentView() {
        if (getString(R.string.STATUS_INVALID).equals(orderModel.getStatus())) {
            findBtnViewNine();  //失效订单
        } else {
            switch (Integer.valueOf(orderModel.getOrderFlow())) {
                case 200:
                    findBtnViewTwo();
                    break;
                case 300:
                    findBtnViewThree();
                    break;
                case 400:
                    findBtnViewFour();
                    break;
                case 500:
                    findBtnViewFive();
                    break;
                case 600:
                    findBtnViewSix();
                    break;
                case 700:
                    findBtnViewSeven();
                    break;
                case 800:  //贷款成功
                    setContentView(R.layout.eight_detail);
                    break;

            }
        }
    }


    private void findBtnViewTwo() {
        setContentView(R.layout.two_detail);
        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);

        btnOne.setText(R.string.reSendMartal);
        btnTwo.setText(R.string.cancelOrder);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);


    }

    private void findBtnViewThree() {
        setContentView(R.layout.three_detail);
        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);
        btnThree = (TextView) findViewById(R.id.btnThree);

        btnOne.setText(R.string.fillInfo);
        btnTwo.setText(R.string.reSendMartal);
        btnThree.setText(R.string.cancelOrder);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);

    }

    private void findBtnViewFour() {
        setContentView(R.layout.four_detail);
        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);
        btnThree = (TextView) findViewById(R.id.btnThree);

        btnOne.setText(R.string.readyOk);
        btnTwo.setText(R.string.reSendMartal);
        btnThree.setText(R.string.cancelOrder);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
    }

    private void findBtnViewFive() {
        setContentView(R.layout.five_detail);
        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);

        btnOne.setText(R.string.approveResult);
        btnTwo.setText(R.string.cancelOrder);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
    }

    private void findBtnViewSix() {
        setContentView(R.layout.six_detail);
        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);

        btnOne.setText(R.string.interviewResult);
        btnTwo.setText(R.string.cancelOrder);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
    }

    private void findBtnViewSeven() {
        setContentView(R.layout.seven_detail);
        btnOne = (TextView) findViewById(R.id.btnOne);
        btnTwo = (TextView) findViewById(R.id.btnTwo);

        btnOne.setText(R.string.confirmOk);
        btnTwo.setText(R.string.cancelOrder);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);

    }


    private void findBtnViewNine() {
        setContentView(R.layout.nine_detail);
        btnOne = (TextView) findViewById(R.id.btnOne);
        btnOne.setText(R.string.rebackOrder);
        btnOne.setOnClickListener(this);

    }


    private void findView() {
        tv_orderId = (TextView) findViewById(R.id.orderId);
        applyCity = (TextView) findViewById(R.id.applyCity);
        sellerName = (TextView) findViewById(R.id.sellerName);
        tv_sellerPhone = (TextView) findViewById(R.id.sellerPhone);
        company = (TextView) findViewById(R.id.company);
        carName = (TextView) findViewById(R.id.carName);
        carVin = (TextView) findViewById(R.id.carVin);
        buyerPhone = (TextView) findViewById(R.id.buyerPhone);
        buyerName = (TextView) findViewById(R.id.buyerName);
        contact = (TextView) findViewById(R.id.contact);
        contactPhone = (TextView) findViewById(R.id.contactPhone);
        price = (TextView) findViewById(R.id.price);
        auditAmount = (TextView) findViewById(R.id.auditAmount);
        auditTime = (TextView) findViewById(R.id.auditTime);
        interviewAmount = (TextView) findViewById(R.id.interviewAmount);
        interviewTime = (TextView) findViewById(R.id.interviewTime);
        confirmTransferResultTime = (TextView) findViewById(R.id.confirmTransferResultTime);
        evaluation = (TextView) findViewById(R.id.evaluation);
        evaluationMsg = (TextView) findViewById(R.id.evaluationMsg);


    }


    private void setText() {
        if (tv_orderId != null) {
            tv_orderId.setText(String.valueOf(orderModel.getOrderId()));
        }
        if (applyCity != null) {
            applyCity.setText(orderModel.getApplyCity());
        }
        if (sellerName != null) {
            if (orderModel.getSeller().equals(orderModel.getSellerName())) {
                sellerName.setText("");
            } else {
                sellerName.setText(orderModel.getSellerName());
            }

        }
        if (tv_sellerPhone != null) {
            tv_sellerPhone.setText(orderModel.getSeller());
        }
        if (company != null) {
            company.setText(orderModel.getCompanyName());
        }
        if (carName != null) {
            carName.setText(orderModel.getCarName());
        }
        if (buyerName != null) {
            if (orderModel.getBuyerPhone().equals(orderModel.getBuyerName())) {
                buyerName.setText("");
            } else {
                buyerName.setText(orderModel.getBuyerName());
            }
        }
        if (buyerPhone != null) {
            buyerPhone.setText(orderModel.getBuyerPhone());
        }

        if (carName != null) {
            carName.setText(orderModel.getCarName());
        }
        if (carVin != null) {
            carVin.setText(orderModel.getVinNum());
        }
        if (contact != null) {
            contact.setText(orderModel.getContact());
        }
        if (contactPhone != null) {
            contactPhone.setText(orderModel.getContactPhone());
        }
        if (price != null) {
            price.setText(orderModel.getPriceFormat());
        }
        if (auditAmount != null) {
            auditAmount.setText(String.valueOf(orderModel.getAuditAmountFormat()));
        }
        if (auditTime != null) {
            auditTime.setText(orderModel.getApprovalDate());
        }
        if (interviewAmount != null) {
            interviewAmount.setText(String.valueOf(orderModel.getInterviewAmountFormat()));
        }
        if (interviewTime != null) {
            interviewTime.setText(orderModel.getInterviewDate());
        }
        if (confirmTransferResultTime != null) {
            confirmTransferResultTime.setText(orderModel.getConfirmTransferResultTime());
        }
        if (evaluation != null) {
            if(StringUtils.isNotBlank(orderModel.getEvaluation())){
                evaluation.setText(orderModel.getEvaluation() + "星");
            }
        }
        if (evaluationMsg != null) {
            evaluationMsg.setText(orderModel.getEvaluationMsg());
        }
    }


    @Override
    public void onClick(View view) {
        if (isFastDoubleClick(view)) {
            return;
        }
        final int vid = view.getId();
        if (getString(R.string.STATUS_INVALID).equals(orderModel.getStatus())) {
            showNine(view);  //恢复订单
        } else {
            switch (Integer.valueOf(orderModel.getOrderFlow())) {
                case 200:
                    showTwo(view);
                    break;
                case 300:
                    showThree(view);
                    finish();
                    break;
                case 400:
                    showFour(view);
                    break;
                case 500:
                    showFive(view);
                    break;
                case 600:
                    showSix(view);
                    break;
                case 700:
                    showSeven(view);
                    break;
            }
        }

        if (vid == R.id.tv_cancel) {
            finish();
        }

    }

    private void showTwo(View view) {
        Integer vid = view.getId();
        if (vid == R.id.btnOne) {
            showReSendMsg();
        } else if (vid == R.id.btnTwo) {
            showCancelOrder();
        }
    }


    private void showThree(View view) {
        Integer vid = view.getId();
        if (vid == R.id.btnOne) {
            Intent intent = new Intent(DetailActivity.this, BannerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("orderModel", orderModel);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (vid == R.id.btnTwo) {
            showReSendMsg();
        } else if (vid == R.id.btnThree) {
            showCancelOrder();
        }
    }

    private void showFour(View view) {
        Integer vid = view.getId();
        if (vid == R.id.btnOne) {

            showConfirmDialog();


        } else if (vid == R.id.btnTwo) {
            showReSendMsg();
        } else if (vid == R.id.btnThree) {
            showCancelOrder();
        }
    }

    private void showFive(View view) {
        Integer vid = view.getId();
        if (vid == R.id.btnOne) {
            showAuditDialog();
        } else if (vid == R.id.btnTwo) {
            showCancelOrder();
        }
    }

    private void showSix(View view) {
        Integer vid = view.getId();
        if (vid == R.id.btnOne) {
            showInterviewDialog();
        } else if (vid == R.id.btnTwo) {
            showCancelOrder();
        }
    }

    private void showSeven(View view) {
        Integer vid = view.getId();
        if (vid == R.id.btnOne) {
            confirmOver();
        } else if (vid == R.id.btnTwo) {
            showCancelOrder();
        }
    }

    private void showNine(View view) {
        Integer vid = view.getId();
        if (vid == R.id.btnOne) {
            showReBackOrder();
        }
    }

    EditText etAmount;
    TextView interviewDate;
    RadioButton needRadio;
    RadioButton auditNoPass;

    private void showAuditDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View textEntryView = inflater.inflate(
                R.layout.audit, null);

        etAmount = (EditText) textEntryView.findViewById(R.id.auditAmount);
        interviewDate = (TextView) textEntryView.findViewById(R.id.interviewTime);
        needRadio = (RadioButton) textEntryView.findViewById(R.id.need);
        auditNoPass = (RadioButton) textEntryView.findViewById(R.id.auditNoPass);

        needRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (needRadio.isChecked()) {
                    auditNoPass.setChecked(false);
                }
            }
        });

        auditNoPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (auditNoPass.isChecked()) {
                    needRadio.setChecked(false);
                }
            }
        });
        interviewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFastDoubleClick(view)){
                    return;
                }
                onCreateDialog(0).show();
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(textEntryView);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (Integer.valueOf(etAmount.getText().toString()) < 10000) {
                            showToast(DetailActivity.this, "金额太小");
                            return;
                        }
                        auditPassPost(needRadio, auditNoPass, etAmount, interviewDate);
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        builder.show();
    }


    private void auditPassPost(final RadioButton needRadio, RadioButton auditNoPass, final EditText etAmount, final TextView interviewDate) {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.inputApprovalResult);
        Uri.Builder builder = Uri.parse(url).buildUpon();

        builder.appendQueryParameter("orderId", String.valueOf(orderModel.getOrderId()));
        builder.appendQueryParameter("sign", getString(R.string.nobug));


        if (auditNoPass.isChecked()) {
            builder.appendQueryParameter("approResult", getString(R.string.auditNotpass));
        } else {
            builder.appendQueryParameter("approResult", getString(R.string.auditPass));
            if (needRadio.isChecked()) {
                builder.appendQueryParameter("needInterview", getString(R.string.needInterview));
                if (StringUtils.isEmpty(interviewDate.getText().toString())) {
                    showToast(DetailActivity.this, "请输入面签时间");
                    return;
                } else {
                    builder.appendQueryParameter("interviewDate", interviewDate.getText().toString());
                }
            } else {
                builder.appendQueryParameter("needInterview", getString(R.string.noNeedInterview));
            }
            if (StringUtils.isBlank(etAmount.getText().toString())) {
                showToast(DetailActivity.this, "审核金额不能为空");
                return;
            }
            builder.appendQueryParameter("approAmount", etAmount.getText().toString());

        }

        sendPost(builder.toString(), true, this);
    }


    private void showInterviewDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View textEntryView = inflater.inflate(
                R.layout.interview, null);

        final EditText etAmount = (EditText) textEntryView.findViewById(R.id.interviewAmount);

        final RadioButton pass = (RadioButton) textEntryView.findViewById(R.id.interviewPass);
        final RadioButton noPass = (RadioButton) textEntryView.findViewById(R.id.interviewNoPass);

        noPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noPass.isChecked()) {
                    pass.setChecked(false);
                }
            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass.isChecked()) {
                    noPass.setChecked(false);
                }
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(textEntryView);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (Integer.valueOf(etAmount.getText().toString()) < 10000) {
                            showToast(DetailActivity.this, "金额太小");
                            return;
                        }
                        interviewPassPost(etAmount, pass, noPass);

                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        builder.show();
    }


    /**
     * 输入面签结果
     *
     * @param
     */
    private void interviewPassPost(EditText etAmount, RadioButton pass, RadioButton noPass) {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.inputInterviewResult);
        Uri.Builder builder = Uri.parse(url).buildUpon();

        if (noPass.isChecked()) {
            builder.appendQueryParameter("result", getString(R.string.interviewNotpass));
        } else if (pass.isChecked()) {
            builder.appendQueryParameter("result", getString(R.string.interviewpass));

            if (StringUtils.isBlank(etAmount.getText().toString())) {
                showToast(DetailActivity.this, "面签金额不能为空");
                return;
            }
            builder.appendQueryParameter("amount", etAmount.getText().toString());
        } else {
            return;
        }

        builder.appendQueryParameter("orderId", String.valueOf(orderModel.getOrderId()));
        builder.appendQueryParameter("sign", getString(R.string.nobug));
        sendPost(builder.toString(), true, this);
    }


    @Override
    protected Dialog onCreateDialog(int type) {
        Calendar c = Calendar.getInstance();
        Dialog dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                        interviewDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                },
                c.get(Calendar.YEAR), // 传入年份
                c.get(Calendar.MONTH), // 传入月份
                c.get(Calendar.DAY_OF_MONTH) // 传入天数
        );

        return dialog;
    }


    /**
     * 准备好资料
     */
    private void showConfirmDialog() {

        final ConfirmDialog dia = new ConfirmDialog(DetailActivity.this);
        dia.setLeftButton("确定",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmReadyOk();
                        dia.dismiss();
                    }
                }
        ).setRightButton("返回",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dia.dismiss();
                    }
                }
        ).setMessage("买家资料准备完毕");
        dia.show();


    }






    /**
     * 取消／恢复订单当前订单
     */
    private void showConfirmOrder(final String url, final Context context) {
        final ConfirmDialog dia = new ConfirmDialog(this);
        dia.setLeftButton(getResources().getString(R.string.cancel),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dia.dismiss();
                    }
                }
        )
                .setRightButton(getResources().getString(R.string.confirm),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendPost(url, true, context);
                                dia.dismiss();
                            }
                        }
                ).setMessage("确认取消？");

        dia.show();
    }


    /**
     * 恢复当前订单
     */
    private void showReBackOrder() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.rollBackV3Order);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("orderId", String.valueOf(orderModel.getOrderId()));
        showConfirmOrder(builder.toString(), this);
    }


    /**
     * 取消当前订单
     */
    private void showCancelOrder() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.cancelV3Order);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("orderId", String.valueOf(orderModel.getOrderId()));
        showConfirmOrder(builder.toString(), this);
    }


    private void confirmOver() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.confirmOver);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("orderId", String.valueOf(orderModel.getOrderId()));
        builder.appendQueryParameter("transferResult", "yes");
        showConfirmOrder(builder.toString(), this);
    }


    /**
     * 重发资料弹框
     */
    private void showReSendMsg() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View textEntryView = inflater.inflate(
                R.layout.dialoglayout, null);
        final EditText et_phone = (EditText) textEntryView.findViewById(R.id.phone);
        et_phone.setText(orderModel.getBuyerPhone());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(textEntryView);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        resendMsgPost(et_phone.getText().toString());
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        builder.show();
    }

    /**
     * 重发资料发送请求
     */
    private void resendMsgPost(String phone) {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.reSendMsg);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("orderId", String.valueOf(orderModel.getOrderId()));
        builder.appendQueryParameter("phone", phone);
        sendPost(builder.toString(), true, this);
    }


    /**
     * 准备完毕
     */
    private void confirmReadyOk() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.confirmInfoOK);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("orderId", String.valueOf(orderModel.getOrderId()));
        sendPost(builder.toString(), true, this);
    }



    @Override
    protected void parseResponse(JSONObject response,final Context context) throws JSONException {
        showToast(context, "操作成功");
        finishActivity();

    }


}

