package com.example.myapplication.model.pelelang;

import java.util.List;

public class GetProductListResponse {
    private int code;
    private List<KatalogPelelangModel> data;
    private String message;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<KatalogPelelangModel> getData() {
        return data;
    }

    public void setData(List<KatalogPelelangModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
