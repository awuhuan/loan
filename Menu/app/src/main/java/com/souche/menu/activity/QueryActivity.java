package com.souche.menu.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.souche.menu.MainActivity;
import com.souche.menu.R;
import com.souche.menu.queryparam.QueryParam;

import org.apache.commons.lang.StringUtils;

import java.util.Calendar;


public class QueryActivity extends BaseActicity implements View.OnClickListener{

    private TextView submitQuery,queryDateStart,queryDateEnd;
    private View dateStart,dateEnd;
    private String title;

    private EditText queryOrderId,queryCar,queryBuyerPhone,querySellerPhone,queryApplyCity,queryVin;
    QueryParam queryParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        findView();
        setClickListen();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        queryParam=(QueryParam)intent.getSerializableExtra("queryParam");

    }


    private void findView(){
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        queryOrderId = (EditText)findViewById(R.id.queryOrderId);
        queryCar = (EditText)findViewById(R.id.queryCar);
        queryVin = (EditText)findViewById(R.id.queryVin);
        queryApplyCity = (EditText)findViewById(R.id.queryApplyCity);
        queryBuyerPhone = (EditText)findViewById(R.id.queryBuyerPhone);
        querySellerPhone = (EditText)findViewById(R.id.querySellerPhone);
        queryDateStart = (TextView)findViewById(R.id.queryDateStart);
        queryDateEnd = (TextView)findViewById(R.id.queryDateEnd);
        submitQuery = (TextView) findViewById(R.id.submitQuery);
        dateStart = findViewById(R.id.dateStart);
        dateEnd = findViewById(R.id.dateEnd);
    }


    private void setClickListen(){
        submitQuery.setOnClickListener(this);
        dateStart.setOnClickListener(this);
        dateEnd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(isFastDoubleClick(view)){
            return;
        }
       Integer vid = view.getId();
        if(vid==R.id.tv_cancel){
            finish();
        }else if(vid==R.id.dateStart){
            onCreateDialog(R.id.dateStart).show();
        }else if(vid==R.id.dateEnd){
            onCreateDialog(R.id.dateEnd).show();
        }else if(vid==R.id.submitQuery){
            Intent intent = new Intent(QueryActivity.this, MainActivity.class);
            intent.putExtra("title", title);
            Bundle bundle = new Bundle();
            setQueryParam(queryParam);
            bundle.putSerializable("queryParam", queryParam);
            intent.putExtras(bundle);
            startActivity(intent);
            finishActivity();
        }
    }


    private void setDateText(int rid,String time){
        switch (rid){
            case R.id.dateStart:
                queryDateStart.setText(time);
                break;
            case R.id.dateEnd:
                queryDateEnd.setText(time);
                break;
        }
    }



    @Override
    protected Dialog onCreateDialog(final int rid) {
        Calendar c = Calendar.getInstance();
        Dialog dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                        setDateText(rid,year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                },
                c.get(Calendar.YEAR), // 传入年份
                c.get(Calendar.MONTH), // 传入月份
                c.get(Calendar.DAY_OF_MONTH) // 传入天数
        );

        return dialog;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_query, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




private QueryParam setQueryParam(QueryParam queryParam){
    String vin =queryCar.getText().toString().trim();
    String carName = queryCar.getText().toString().trim();
    String buyerPhone = queryBuyerPhone.getText().toString().trim();
    String sellerPhone = querySellerPhone.getText().toString().trim();
    String city = queryApplyCity.getText().toString().trim();
    String orderId = queryOrderId.getText().toString().trim();
    String startDate = queryDateStart.getText().toString().trim();
    String endDate = queryDateEnd.getText().toString().trim();
    if(StringUtils.isNotBlank(vin)){
        queryParam.setVinNumber(vin);
    }
    if(StringUtils.isNotBlank(orderId)){
        queryParam.setOrderId(Integer.valueOf(orderId));
    }
    if(StringUtils.isNotBlank(carName)){
        queryParam.setCarName(carName);
    }
    if(StringUtils.isNotBlank(buyerPhone)){
        queryParam.setBuyPhone(buyerPhone);
    }
    if(StringUtils.isNotBlank(sellerPhone)){
        queryParam.setSellPhone(sellerPhone);
    }
    if(StringUtils.isNotBlank(city)){
        queryParam.setApplyCity(city);
    }
    if(StringUtils.isNotBlank(startDate)){
        queryParam.setTimeBegin(startDate);
    }
    if(StringUtils.isNotBlank(endDate)){
        queryParam.setTimeEnd(endDate);
    }
    return queryParam;
}


}
