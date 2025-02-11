package com.hyn.pojo;

public class Result {
    private int code;

    public Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        return new Result(1,"访问成功",null);
    }
    public static Result success(Object data) {
        return new Result(1,"访问成功",data);
    }
    public static Result success(String msg, Object data) {
        return new Result(1,msg,data);
    }
    public static Result error(String msg) {
        return new Result(0,msg,null);
    }
    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private String msg;
    private Object data;
}
