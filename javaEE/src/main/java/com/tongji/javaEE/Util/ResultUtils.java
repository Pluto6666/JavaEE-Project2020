package com.tongji.javaEE.Util;

public class ResultUtils {
    /**
     * 成功返回
     * @param data
     * @param msg
     * @return
     */
    public static Object success(Object data,String msg){
        Result result = new Result();
        result.setCode(1);
        result.setData(data);
        result.setMessage(msg);
        return result;
    }
    public static Object success(String msg){
        Result result = new Result();
        result.setCode(1);
        result.setMessage(msg);
        return result;
    }
    public static Object success(Object data){
        Result result = new Result();
        result.setCode(1);
        result.setData(data);
        return result;
    }
    public static Object success(){
        Result result = new Result();
        result.setCode(1);
        return result;
    }

    /**
     * 错误返回
     * @return
     */
    public static Object error(){
        Result result = new Result();
        result.setCode(0);
        return result;
    }
    public static Object error(String msg){
        Result result = new Result();
        result.setCode(0);
        result.setMessage(msg);
        return result;
    }

    public static class Result{
        private int code;//返回状态     1为成功，0为失败
        private String message;//返回信息
        private Object data;//返回数据

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

        public Object getData() {
            return data;
        }
        public void setData(Object data) {
            this.data = data;
        }
    }

}
