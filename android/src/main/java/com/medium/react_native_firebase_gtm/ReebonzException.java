package com.medium.react_native_firebase_gtm;

import android.util.Log;

public class ReebonzException extends Exception {
    private String domain;
    private int code;

    public ReebonzException(String message, int code, String domain) {
        super(message);
        this.setCode(code);
        this.setDomain(domain);
    }

    public ReebonzException(String message, int code, String domain, Throwable cause) {
        super(message, cause);
        this.setCode(code);
        this.setDomain(domain);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}