package com.fz.admin.core;

import java.io.Serializable;

public class APIResultModel<T> implements Serializable {
    private static final long serialVersionUID = 3765720967319047788L;
    private T data;
    private String message = "";
    private String code = "";

    public void setCode(String code) {
        this.code = code;
    }

    public APIResultModel() {
    }

    public APIResultModel(String message) {
        this.message = message;
    }

    public APIResultModel(T data) {
        this.data = data;
    }

    public APIResultModel(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setError(String message) {
        this.message = message;
    }

    public void setSuccess(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String value) {
        this.message = value;
    }
}
