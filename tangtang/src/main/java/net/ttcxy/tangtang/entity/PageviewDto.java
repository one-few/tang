package net.ttcxy.tangtang.entity;


import java.util.Date;


public class PageviewDto {

    private String userId;

    private String dataId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}