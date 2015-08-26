package com.souche.menu.model;

import java.io.Serializable;

/**
 * Created by wangjiguang on 15/8/13.
 */
public class AnswerModel implements Serializable {

    private Integer orderId;

    String settleCity;//可落户城市
    String carRegistTime;      //上牌时间
    String carMileage;//车辆行驶里程
    String seatCount;//车辆类别（座位数）
    String carUseWay; //车辆用途
    String carPrice;//车辆成交价
    String loanAmount;//拟贷款金额
    String loanPeriod;//贷款期数
    String isAboriginal;//是否本省户口
    String hasHouse;  //是否有房产
    String hasBankFlow;//能否提供近6个月银行收入流水
    String canShowIncome; //【无收入流水】能否提供收入或工作证明
    String hasPeopleAssure;//【无房且无流水】能否提供本地人担保（有房产、收入流水良好）

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getSettleCity() {
        return settleCity;
    }

    public void setSettleCity(String settleCity) {
        this.settleCity = settleCity;
    }

    public String getCarRegistTime() {
        return carRegistTime;
    }

    public void setCarRegistTime(String carRegistTime) {
        this.carRegistTime = carRegistTime;
    }

    public String getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(String carMileage) {
        this.carMileage = carMileage;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getCarUseWay() {
        return carUseWay;
    }

    public void setCarUseWay(String carUseWay) {
        this.carUseWay = carUseWay;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public String getIsAboriginal() {
        return isAboriginal;
    }

    public void setIsAboriginal(String isAboriginal) {
        this.isAboriginal = isAboriginal;
    }

    public String getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(String hasHouse) {
        this.hasHouse = hasHouse;
    }

    public String getHasBankFlow() {
        return hasBankFlow;
    }

    public void setHasBankFlow(String hasBankFlow) {
        this.hasBankFlow = hasBankFlow;
    }

    public String getCanShowIncome() {
        return canShowIncome;
    }

    public void setCanShowIncome(String canShowIncome) {
        this.canShowIncome = canShowIncome;
    }

    public String getHasPeopleAssure() {
        return hasPeopleAssure;
    }

    public void setHasPeopleAssure(String hasPeopleAssure) {
        this.hasPeopleAssure = hasPeopleAssure;
    }
}
