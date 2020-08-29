package com.xiaofu.domain.enums;

/**
 * @author Yang-o_o 2020-08-24 14:10
 */
public enum ResponseEnum {
    /**
     * 成功.
     */
    SUCCESS(10000, "成功"),

    /**
     * 文件上传失败
     */
    FILE_UPLOAD_ERROR(100001, "文件上传失败"),

    /**
     * 自定义异常.
     */
    CUSTOM_ERROR(10098, "自定义异常"),
    /**
     * 系统异常.
     */
    SYSTEM_ERROR(10099, "系统异常");


    private int code;

    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = this.message;
    }
}
