package com.project;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 9:25 AM 2019/4/20
 **/
public class DoOrderResponse {
    private Object data;
    private String code;
    private String memo;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
