package com.souche.menu.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.souche.menu.R;
import com.souche.menu.Utils.DateUtils;
import com.souche.menu.adapter.AdDomain;
import com.souche.menu.model.OrderModel;
import com.souche.menu.queryparam.OrderParam;
import com.souche.menu.view.MonthPickerPopWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BannerActivity extends BaseActicity implements View.OnClickListener {

    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径

    private ViewPager adViewPager;
    private List<ImageView> imageViews;// 滑动的图片集合

    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;

    private int currentItem = 0; // 当前图片的索引号
    // 定义的指示点
    private View dot0;
    private View dot1;

    /**
     * 从车型号得到的年份，用于设置尚持上牌的最早年份
     */
    private int carModelYear = 1943;


    private MonthPickerPopWindow regTimePicker;

    private ScheduledExecutorService scheduledExecutorService;

    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;

    // 轮播banner的数据
    private List<AdDomain> adList;


    OrderModel orderModel;

    OrderParam param = new OrderParam();

    EditText et_carName, et_vin, et_buyerName, et_buyerIdentity;
    TextView tv_orderId, tv_seller,tv_submit,tv_cancel,tv_registTime;
    View rootView;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adViewPager.setCurrentItem(currentItem);
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_user_info);
        // 使用ImageLoader之前初始化
        initImageLoader();

        Intent intent = getIntent();
        orderModel = (OrderModel) intent.getSerializableExtra("orderModel");
        findView();

        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();

        initAdData();

        startAd();
    }


    private void findView() {
        tv_cancel = (TextView)findViewById(R.id.tv_cancel);
        rootView = findViewById(R.id.root_view);
        tv_orderId = (TextView) findViewById(R.id.tv_orderId);
        tv_seller = (TextView) findViewById(R.id.tv_seller);
        et_carName = (EditText) findViewById(R.id.carName);
        et_vin = (EditText) findViewById(R.id.carVin);
        tv_registTime = (TextView) findViewById(R.id.et_regist_time);
        et_buyerName = (EditText) findViewById(R.id.et_buyerName);
        et_buyerIdentity = (EditText) findViewById(R.id.et_identity);

        tv_orderId.setText(String.valueOf(orderModel.getOrderId()));
        tv_seller.setText(orderModel.getSeller());

        tv_submit = (TextView)findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        findViewById(R.id.registTime).setOnClickListener(this);
    }





    @Override
    public void onClick(View view) {
        if(isFastDoubleClick(view)){
            return;
        }
        Integer vid = view.getId();
        if (vid == R.id.tv_cancel) {
            finish();
        }else if(vid==R.id.tv_submit){
            fillBuyerInfoPost();
        }else if(vid==R.id.registTime){
            showRegTimePicker();
        }

    }

    @Override
    protected void parseResponse(JSONObject response,final Context context) throws JSONException {
        showToast(context,"操作成功");
        finishActivity();
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
                                param.setRegistrationTime(DateUtils.getFormatDateTime(c.getTime(), "yyyy-mm"));

                            } else {
                                String ym = y + "年" + m + "月";
                                tv_registTime.setText(ym);
                               param.setRegistrationTime(y + "-" + m);
                            }
                        }
                    }
            );
//            regTimePicker.addBindFoot(noReg, noReg);   未上牌
        }
        regTimePicker.setRange(minYear, 1, maxYear, maxMonth, true);
        regTimePicker.show();
    }





    private void fillBuyerInfoPost(){


        param.setOrderId(String.valueOf(orderModel.getOrderId()));
        param.setBuyerName(et_buyerName.getText().toString());
        param.setIdentityId(et_buyerIdentity.getText().toString());
        param.setModelName(et_carName.getText().toString());
        param.setVinNumbern(et_vin.getText().toString());

        if(checkParamEmpty(param)){
            String url = getString(R.string.baseUrl);
            url += getString(R.string.fillOrderInfo);
            Uri.Builder builder = Uri.parse(url).buildUpon();
            builder.appendQueryParameter("orderId", param.getOrderId());
            builder.appendQueryParameter("buyerName",param.getBuyerName());
            builder.appendQueryParameter("identityId", param.getIdentityId());
            builder.appendQueryParameter("registrationTime", param.getRegistrationTime());
            builder.appendQueryParameter("modelName", param.getModelName());
            builder.appendQueryParameter("vinNumber", param.getVinNumbern());
            sendPost(builder.toString(),true, BannerActivity.this);
        }else {
            showToast(BannerActivity.this,"参数不完整");
        }



    }



    /**
     * 判断机构各个条件是否有空
     *
     * @param param
     * @return
     */
    private boolean checkParamEmpty(OrderParam param) {
        Class cls = param.getClass();
        Field[] fields = cls.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            try {
                if (f.get(param) == null ) {
                    return false;
                }

            } catch (IllegalAccessException e) {

                e.printStackTrace();
                return false;
            }

        }
        return true;
    }








    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(getApplicationContext(),
                        IMAGE_CACHE_PATH);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImageLoader.getInstance().init(config);
    }

    private void initAdData() {
        // 广告数据
        adList = getBannerAd();

        imageViews = new ArrayList<>();

        // 点
        dots = new ArrayList<>();
        dotList = new ArrayList<>();
        dot0 = findViewById(R.id.v_dot0);
        dot1 = findViewById(R.id.v_dot1);

        dots.add(dot0);
        dots.add(dot1);

        adViewPager = (ViewPager) findViewById(R.id.vp);
        adViewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();
    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(this);
            // 异步加载图片
            mImageLoader.displayImage(adList.get(i).getImgUrl(), imageView,
                    options);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 20,
                TimeUnit.SECONDS);
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
    }

    private class MyPageChangeListener implements OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            AdDomain adDomain = adList.get(position);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return adList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            ((ViewPager) container).addView(iv);
            final AdDomain adDomain = adList.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    /**
     * 轮播广播模拟数据
     *
     * @return
     */
    private List<AdDomain> getBannerAd() {
        List<AdDomain> adList = new ArrayList<>();

        AdDomain adDomain = new AdDomain();
        adDomain.setId("108078");
        adDomain.setDate("3月4日");
        adDomain.setTitle("车辆行驶证");
        adDomain.setTopicFrom("车辆行驶证");
        adDomain.setTopic("车辆行驶证");
        adDomain.setImgUrl(orderModel.getCarDrivingLicense());
        adDomain.setAd(false);
        adList.add(adDomain);

        AdDomain adDomain2 = new AdDomain();
        adDomain2.setId("108078");
        adDomain2.setDate("3月5日");
        adDomain2.setTitle("身份证");
        adDomain2.setTopicFrom("身份证");
        adDomain2.setTopic("“身份证”");
        adDomain2.setImgUrl(orderModel.getIdA());
        adDomain2.setAd(false);
        adList.add(adDomain2);


        return adList;
    }

}
