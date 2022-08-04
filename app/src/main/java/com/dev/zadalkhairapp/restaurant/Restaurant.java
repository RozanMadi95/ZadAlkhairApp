package com.dev.zadalkhairapp.restaurant;

public class Restaurant {
    private String name ,email,address,password,phoneAll, image,times;
    public Restaurant() {
    }

    public Restaurant(String name, String email, String address, String password, String phoneAll, String image, String times) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phoneAll = phoneAll;
        this.image = image;
        this.times = times;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneAll() {
        return phoneAll;
    }

    public void setPhoneAll(String phoneAll) {
        this.phoneAll = phoneAll;
    }
}



