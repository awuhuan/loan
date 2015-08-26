package com.souche.menu.queryparam;

/**
 * Created by wangjiguang on 15/8/19.
 */
public class OrderParam {


    String orderId;
    String buyerName;
    String identityId;
    String registrationTime;
    String modelName;
    String vinNumbern;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVinNumbern() {
        return vinNumbern;
    }

    public void setVinNumbern(String vinNumbern) {
        this.vinNumbern = vinNumbern;
    }
}