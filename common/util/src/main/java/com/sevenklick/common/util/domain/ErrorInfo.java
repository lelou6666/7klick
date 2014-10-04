package com.sevenklick.common.util.domain;

/**
 * Created by pierre.petersson on 23/08/2014.
 */
public class ErrorInfo {

    public final String url;
    public final String technicalMessage;
    public final String userMessage;

    public String getUrl() {
        return url;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public ErrorInfo(String url, String userMessage, String technicalMessage) {
        this.url = url;
        this.technicalMessage = technicalMessage;
        this.userMessage = userMessage;

    }
}