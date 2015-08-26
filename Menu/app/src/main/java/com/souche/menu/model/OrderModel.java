package com.souche.menu.model;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by wangjiguang on 15/7/27.
 */
public class OrderModel implements Serializable {

    /**
     * applyCityCode : 00545
     * confirmTransferResultTime : 2015-07-24 10:44:25
     * buyerInfoUrl :
     * approveLoanAmount : 20000
     * carInfoUrl :
     * evaluationMsg :
     * orderFlowName : 贷款成功
     * dateCreate : 2015-07-24 10:23:09
     * contact : 许明明
     * evaluation :
     * carName : 宝马
     * orderId : 28
     * interviewDate : 2015-07-24 00:00:00
     * interviewAmount : 43543
     * contactPhone : 15257100971
     * status : 1
     * buyerChooseCompanyId : 488
     * vinNum : 2222222222222222222222
     * sellerName :
     * companyName : 沈阳祺和汽车销售有限公司
     * orderFlow : 800
     * seller : 15700129331
     * buyerName : 别惹程序员
     * applyCity : 大连
     * price : 300000
     * buyerPhone : 15700129331
     * approvalDate : 2015-07-24 10:43:27
     */
    private String applyCityCode;
    private String confirmTransferResultTime;
    private String buyerInfoUrl;
    private int approveLoanAmount;
    private String carInfoUrl;
    private String evaluationMsg;
    private String orderFlowName;
    private String dateCreate;
    private String contact;
    private String evaluation;
    private String carName;
    private int orderId;
    private String interviewDate;
    private int interviewAmount;
    private String contactPhone;
    private String status;
    private int buyerChooseCompanyId;
    private String vinNum;
    private String sellerName;
    private String companyName;
    private String orderFlow;
    private String seller;
    private String buyerName;
    private String applyCity;
    private int price;
    private String buyerPhone;
    private String approvalDate;
    private String carDrivingLicense;
    private String idA;

    public String getCarDrivingLicense() {
        return carDrivingLicense;
    }

    public void setCarDrivingLicense(String carDrivingLicense) {
        this.carDrivingLicense = carDrivingLicense;
    }

    public String getIdA() {
        return idA;
    }

    public void setIdA(String idA) {
        this.idA = idA;
    }

    public void setApplyCityCode(String applyCityCode) {
        this.applyCityCode = applyCityCode;
    }

    public void setConfirmTransferResultTime(String confirmTransferResultTime) {
        this.confirmTransferResultTime = confirmTransferResultTime;
    }

    public void setBuyerInfoUrl(String buyerInfoUrl) {
        this.buyerInfoUrl = buyerInfoUrl;
    }

    public void setApproveLoanAmount(int approveLoanAmount) {
        this.approveLoanAmount = approveLoanAmount;
    }

    public void setCarInfoUrl(String carInfoUrl) {
        this.carInfoUrl = carInfoUrl;
    }

    public void setEvaluationMsg(String evaluationMsg) {
        this.evaluationMsg = evaluationMsg;
    }

    public void setOrderFlowName(String orderFlowName) {
        this.orderFlowName = orderFlowName;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public void setInterviewAmount(int interviewAmount) {
        this.interviewAmount = interviewAmount;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBuyerChooseCompanyId(int buyerChooseCompanyId) {
        this.buyerChooseCompanyId = buyerChooseCompanyId;
    }

    public void setVinNum(String vinNum) {
        this.vinNum = vinNum;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setOrderFlow(String orderFlow) {
        this.orderFlow = orderFlow;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setApplyCity(String applyCity) {
        this.applyCity = applyCity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApplyCityCode() {
        return applyCityCode;
    }

    public String getConfirmTransferResultTime() {
        return confirmTransferResultTime;
    }

    public String getBuyerInfoUrl() {
        return buyerInfoUrl;
    }

    public int getApproveLoanAmount() {
        return approveLoanAmount;
    }

    public String getCarInfoUrl() {
        return carInfoUrl;
    }

    public String getEvaluationMsg() {
        return evaluationMsg;
    }

    public String getOrderFlowName() {
        return orderFlowName;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getContact() {
        return contact;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public String getCarName() {
        return carName;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getInterviewDate() {
        if(StringUtils.isBlank(interviewDate))return "";
        String format = "yyyy-MM-dd";
        SimpleDateFormat dd = new SimpleDateFormat(format);
        try {
            return dd.format(dd.parse(interviewDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getInterviewAmount() {
        return interviewAmount;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getStatus() {
        return status;
    }

    public int getBuyerChooseCompanyId() {
        return buyerChooseCompanyId;
    }

    public String getVinNum() {
        return vinNum;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getOrderFlow() {
        return orderFlow;
    }

    public String getSeller() {
        return seller;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getApplyCity() {
        return applyCity;
    }

    public int getPrice() {
        return price;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public String getApprovalDate() {
        return approvalDate;
    }


    public String getPriceFormat(){
        DecimalFormat df=new DecimalFormat(".00");
        double price = getPrice()/ Double.valueOf(10000);


        return df.format(price);
    }

    public String getAuditAmountFormat(){
        DecimalFormat df=new DecimalFormat(".00");
        double price = getApproveLoanAmount()/ Double.valueOf(10000);


        return df.format(price);
    }

    public String getInterviewAmountFormat(){
        DecimalFormat df=new DecimalFormat(".00");
        double price = getInterviewAmount()/ Double.valueOf(10000);


        return df.format(price);
    }


}
