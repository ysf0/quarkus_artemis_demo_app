package com.demo.dto;

public class Content {

    private String contentName;
    private String contentData;

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentName='" + contentName + '\'' +
                ", contentData='" + contentData + '\'' +
                '}';
    }
}
