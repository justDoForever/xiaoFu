package com.xiaofu.domain.response;

import com.xiaofu.domain.enums.ResponseEnum;

/**
 * ResponseEntity.
 *
 * @author Yang-o_o 2020-08-24 15:44
 */
public class ResultInfo<T> {

    private int code;

    private String message;

    private T data;

    public ResultInfo(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public ResultInfo(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
