package com.dev.zadalkhairapp.consumer;

public class Consumer {
    private String name ,email,address,password,phoneAll;

    public Consumer() {
    }

    public Consumer(String name, String email, String address, String password, String phoneAll) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phoneAll = phoneAll;
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
