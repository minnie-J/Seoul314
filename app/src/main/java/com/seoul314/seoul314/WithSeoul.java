package com.seoul314.seoul314;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WithSeoul {

    private String title;
    private String createUser;
    private int personCount;
    private int distance;
    private int time;
    private List<String> user;
    private List<String> readyUser;
    private Map<String, Integer> userDistance;
    private String roomState;
    private Date endTime;


    public WithSeoul(){}

    public WithSeoul(String title, String createUser, int personCount, int distance, int time, List<String> user, List<String> readyUser, Map<String, Integer> userDistance, String roomState, Date endTime) {
        this.title = title;
        this.createUser = createUser;
        this.personCount = personCount;
        this.distance = distance;
        this.time = time;
        this.user = user;
        this.readyUser = readyUser;
        this.userDistance = userDistance;
        this.roomState = roomState;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }

    public List<String> getReadyUser() {
        return readyUser;
    }

    public void setReadyUser(List<String> readyUser) {
        this.readyUser = readyUser;
    }

    public Map<String, Integer> getUserDistance() {
        return userDistance;
    }

    public void setUserDistance(Map<String, Integer> userDistance) {
        this.userDistance = userDistance;
    }

    public String getRoomState() {
        return roomState;
    }

    public void setRoomState(String roomState) {
        this.roomState = roomState;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
