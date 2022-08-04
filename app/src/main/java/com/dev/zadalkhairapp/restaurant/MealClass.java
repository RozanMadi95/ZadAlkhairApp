package com.dev.zadalkhairapp.restaurant;

public class MealClass {
    private String mealname;
    private String avilableitem;
    private String price;
    private String imgurl;
    private String randounid;
    private String resturentid;
    private String paymentoption;
    private String deliveryoption;
    private String ecpirydate;
    private String userId;

    public MealClass(String mealname, String avilableitem, String price, String imgurl, String randounid, String resturentid, String paymentoption, String deliveryoption, String ecpirydate, String userId) {
        this.mealname = mealname;
        this.avilableitem = avilableitem;
        this.price = price;
        this.imgurl = imgurl;
        this.randounid = randounid;
        this.resturentid = resturentid;
        this.paymentoption = paymentoption;
        this.deliveryoption = deliveryoption;
        this.ecpirydate = ecpirydate;
        this.userId = userId;
    }

    public MealClass() {
    }

    public String getMealname() {
        return mealname;
    }

    public void setMealname(String mealname) {
        this.mealname = mealname;
    }

    public String getAvilableitem() {
        return avilableitem;
    }

    public void setAvilableitem(String avilableitem) {
        this.avilableitem = avilableitem;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getRandounid() {
        return randounid;
    }

    public void setRandounid(String randounid) {
        this.randounid = randounid;
    }

    public String getResturentid() {
        return resturentid;
    }

    public void setResturentid(String resturentid) {
        this.resturentid = resturentid;
    }

    public String getPaymentoption() {
        return paymentoption;
    }

    public void setPaymentoption(String paymentoption) {
        this.paymentoption = paymentoption;
    }

    public String getDeliveryoption() {
        return deliveryoption;
    }

    public void setDeliveryoption(String deliveryoption) {
        this.deliveryoption = deliveryoption;
    }

    public String getEcpirydate() {
        return ecpirydate;
    }

    public void setEcpirydate(String ecpirydate) {
        this.ecpirydate = ecpirydate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
