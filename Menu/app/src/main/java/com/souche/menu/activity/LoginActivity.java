package com.souche.menu.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.souche.menu.MainActivity;
import com.souche.menu.R;

import org.json.JSONObject;

/**
 * Created by wangjiguang on 15/8/9.
 */
public class LoginActivity extends BaseActicity implements View.OnClickListener {

    TextView submit, tv_username, tv_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        submit = (TextView) findViewById(R.id.tv_submit);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_pwd = (TextView) findViewById(R.id.tv_pwd);
        submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (isFastDoubleClick(view)) {
            return;
        }
        if (vid == R.id.tv_submit) {
            //将输入法隐藏，tv_pwd 代表密码输入框
            InputMethodManager imm =(InputMethodManager)getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(tv_pwd.getWindowToken(), 0);
            login();// 去执行的具体操作
        }
    }


    /**
     * 准备完毕
     */
    private void login() {
        String url = getString(R.string.baseUrl);
        url += getString(R.string.login);
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("username", tv_username.getText().toString());
        builder.appendQueryParameter("pwd", tv_pwd.getText().toString());
        sendPost(builder.toString(),true, LoginActivity.this);
    }


    @Override
    protected void parseResponse(JSONObject response, final Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finishActivity();
    }


}
