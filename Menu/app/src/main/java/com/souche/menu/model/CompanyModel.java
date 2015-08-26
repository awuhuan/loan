package com.souche.menu.model;

import java.io.Serializable;

/**
 * Created by wangjiguang on 15/8/12.
 */
public class CompanyModel implements Serializable {

    /**
     * id : 331
     * logo : http://img.souche.com/loanbuy/476EC1BE3C3F4225E733EB00E9882860.jpg
     * name : 有利网
     * monthlyPayment : 2513
     * totalInterest : 10304
     * passRate :
     */
    private int id;
    private String logo;
    private String name;
    private int monthlyPayment;
    private int totalInterest;
    private String passRate;

    public void setId(int id) {
        this.id = id;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonthlyPayment(int monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setTotalInterest(int totalInterest) {
        this.totalInterest = totalInterest;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public int getId() {
        return id;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public int getMonthlyPayment() {
        return monthlyPayment;
    }

    public int getTotalInterest() {
        return totalInterest;
    }

    public String getPassRate() {
        return passRate;
    }
}
