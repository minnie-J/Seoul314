package com.seoul314.seoul314;

import java.util.List;

public class User {

    private String userId;
    private String userPwd;
    private String name;
    private List<Double> location;
    private int SuccessWithRunNum;
    private String picture;
    private int soloDistance;
    private int withDistance;
    private int relayDistance;
    private int withSuccessCount;
    private int withFirstCount;

    public User() {}

    public User(String userId, String userPwd, String name, List<Double> location, int successWithRunNum, String picture, int soloDistance, int withDistance, int relayDistance, int withSuccessCount, int withFirstCount) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.name = name;
        this.location = location;
        SuccessWithRunNum = successWithRunNum;
        this.picture = picture;
        this.soloDistance = soloDistance;
        this.withDistance = withDistance;
        this.relayDistance = relayDistance;
        this.withSuccessCount = withSuccessCount;
        this.withFirstCount = withFirstCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public int getSuccessWithRunNum() {
        return SuccessWithRunNum;
    }

    public void setSuccessWithRunNum(int successWithRunNum) {
        SuccessWithRunNum = successWithRunNum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getSoloDistance() {
        return soloDistance;
    }

    public void setSoloDistance(int soloDistance) {
        this.soloDistance = soloDistance;
    }

    public int getWithDistance() {
        return withDistance;
    }

    public void setWithDistance(int withDistance) {
        this.withDistance = withDistance;
    }

    public int getRelayDistance() {
        return relayDistance;
    }

    public void setRelayDistance(int relayDistance) {
        this.relayDistance = relayDistance;
    }

    public int getWithSuccessCount() {
        return withSuccessCount;
    }

    public void setWithSuccessCount(int withSuccessCount) {
        this.withSuccessCount = withSuccessCount;
    }

    public int getWithFirstCount() {
        return withFirstCount;
    }

    public void setWithFirstCount(int withFirstCount) {
        this.withFirstCount = withFirstCount;
    }
}
