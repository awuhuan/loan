package com.souche.menu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.souche.menu.R;
import com.souche.menu.model.OrderModel;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjiguang on 15/7/30.
 */
public class OrderListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    public ImageLoader mImageLoader  = ImageLoader.getInstance();

    public List<OrderModel> mDataList = new ArrayList<>();

    public OrderListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public OrderModel getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.carName = (TextView) convertView.findViewById(R.id.tv_carName);
            holder.orderId = (TextView) convertView.findViewById(R.id.tv_orderId);
            holder.city = (TextView) convertView.findViewById(R.id.tv_apply_city);
            holder.sellerPhone = (TextView) convertView.findViewById(R.id.tv_seller_phone);
            holder.buyerPhone = (TextView)convertView.findViewById(R.id.tv_buyer_phone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderModel orderModel = mDataList.get(position);
        if(StringUtils.isNotBlank(orderModel.getSeller())){
            holder.sellerPhone.setText(orderModel.getSeller());
        }
        if(StringUtils.isNotBlank(orderModel.getBuyerPhone())){
            holder.buyerPhone.setText(orderModel.getBuyerPhone());
        }
        if(StringUtils.isNotBlank(orderModel.getApplyCity())) {
            holder.city.setText(orderModel.getApplyCity());
        }

        if(StringUtils.isNotBlank(orderModel.getCarName())) {
            holder.carName.setText(orderModel.getCarName());
        }

        if (StringUtils.isNotBlank(orderModel.getIdA())) {

            mImageLoader.displayImage(mDataList.get(position).getIdA(), holder.img);
        }

        holder.orderId.setText(String.valueOf(mDataList.get(position).getOrderId()));
        return convertView;
    }


    class ViewHolder {
        TextView orderId;
        TextView sellerPhone;
        TextView buyerPhone;
        TextView city;
        TextView carName;
        ImageView img;
    }
}
