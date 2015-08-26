package com.souche.menu.queryparam;

import java.io.Serializable;

/**
 * Created by wangjiguang on 15/7/27.
 */
public class QueryParam implements Serializable {
    private Integer orderId;

    private String carName;

    private String vinNumber;

    private String buyPhone;

    private String sellPhone;

    private Integer orderFlow;

    private String status;

    private String applyCity; //申请城市


    private String timeBegin;

    private String timeEnd;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getBuyPhone() {
        return buyPhone;
    }

    public void setBuyPhone(String buyPhone) {
        this.buyPhone = buyPhone;
    }

    public String getSellPhone() {
        return sellPhone;
    }

    public void setSellPhone(String sellPhone) {
        this.sellPhone = sellPhone;
    }

    public Integer getOrderFlow() {
        return orderFlow;
    }

    public void setOrderFlow(Integer orderFlow) {
        this.orderFlow = orderFlow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyCity() {
        return applyCity;
    }

    public void setApplyCity(String applyCity) {
        this.applyCity = applyCity;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
