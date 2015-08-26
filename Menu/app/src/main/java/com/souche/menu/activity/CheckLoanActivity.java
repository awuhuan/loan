package com.souche.menu.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.souche.menu.R;
import com.souche.menu.model.AnswerModel;
import com.souche.menu.model.CompanyModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjiguang on 15/8/12.
 */
public class CheckLoanActivity extends BaseActicity implements View.OnClickListener {

    private EditText ev_seller, ev_buyerName, ev_buyerPhone, ev_desc;


    List<CompanyModel> list;

    TextView tv_can_loan, tv_cannot_loan, tv_only_consult;

    AnswerModel answerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_loan);

        Intent intent = getIntent();
        list = (ArrayList<CompanyModel>) intent.getSerializableExtra("list");

        answerModel = (AnswerModel) intent.getSerializableExtra("answerModel");
        findView();
        setClickListen();
        addCompanyView(list);
    }


    private void findView() {
        findViewById(R.id.tv_cancel).setOnClickListener(this);

        ev_seller = (EditText) findViewById(R.id.ev_seller);
        ev_buyerName = (EditText) findViewById(R.id.ev_buyerName);
        ev_buyerPhone = (EditText) findViewById(R.id.ev_buyerPhone);
        ev_desc = (EditText) findViewById(R.id.ev_desc);
        tv_can_loan = (TextView) findViewById(R.id.tv_can_loan);
        tv_cannot_loan = (TextView) findViewById(R.id.tv_cannot_loan);
        tv_only_consult = (TextView) findViewById(R.id.tv_only_consult);


    }

    private void setClickListen() {
        tv_can_loan.setOnClickListener(this);
        tv_cannot_loan.setOnClickListener(this);
        tv_only_consult.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (isFastDoubleClick(view)) {
            return;
        }
        Integer vid = view.getId();
        if (vid == R.id.tv_cancel) {
            finish();
        }
        if (vid == R.id.tv_only_consult) {
            requireOnlyConsult();
        } else if (vid == R.id.tv_cannot_loan) {
            requireCanNotLoan();

        } else if (vid == R.id.tv_can_loan) {
            if (list.size() > 0) {

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.company);
                RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioButton != null) {
                    Integer companyId = radioButton.getId();
                    requireCanLoan(companyId);

                } else {
                    showToast(CheckLoanActivity.this, "请选择贷款机构");
                }
            } else {
                showToast(CheckLoanActivity.this, "没有机构的贷款条件相匹配");
            }

        }
    }


    private void requireCanLoan(Integer companyId) {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.canLoan);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("sign", "nobug");
        builder.appendQueryParameter("orderId", answerModel.getOrderId().toString());
        builder.appendQueryParameter("buyerName", ev_buyerName.getText().toString());
        builder.appendQueryParameter("buyerPhone", ev_buyerPhone.getText().toString());
        builder.appendQueryParameter("qualifications", ev_desc.getText().toString());
        builder.appendQueryParameter("companyId", companyId.toString());
        builder.appendQueryParameter("seller", ev_seller.getText().toString());
        sendPost(builder.toString(), true, this);
    }


    private void requireCanNotLoan() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.cannotLoan);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("sign", "nobug");
        builder.appendQueryParameter("orderId", answerModel.getOrderId().toString());
        builder.appendQueryParameter("buyerName", ev_buyerName.getText().toString());
        builder.appendQueryParameter("buyerPhone", ev_buyerPhone.getText().toString());
        builder.appendQueryParameter("qualifications", ev_desc.getText().toString());
        builder.appendQueryParameter("seller", ev_seller.getText().toString());
        sendPost(builder.toString(), true, this);
    }


    private void requireOnlyConsult() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.onlyConsult);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("sign", "nobug");
        builder.appendQueryParameter("orderId", answerModel.getOrderId().toString());
        builder.appendQueryParameter("buyerName", ev_buyerName.getText().toString());
        builder.appendQueryParameter("buyerPhone", ev_buyerPhone.getText().toString());
        builder.appendQueryParameter("qualifications", ev_desc.getText().toString());
        builder.appendQueryParameter("seller", ev_seller.getText().toString());
        sendPost(builder.toString(), true, this);
    }


    private void addCompanyView(List<CompanyModel> models) {
        RadioGroup company = (RadioGroup) findViewById(R.id.company);
        if (models.size() < 1) {
            tv_can_loan.setEnabled(false);
        }
        for (int i = 0; i < list.size(); i++) {
            RadioButton com = new RadioButton(this);
            com.setOnClickListener(this);
            com.setId(list.get(i).getId());
            com.setText(list.get(i).getName() + "  月还款额" + list.get(i).getMonthlyPayment() + "  总利息" + list.get(i).getTotalInterest());
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            company.addView(com, lp1);
        }
    }


    @Override
    protected void parseResponse(JSONObject response, final Context context) throws JSONException {
        showToast(context, "操作成功");
        finishActivity();
    }


}
