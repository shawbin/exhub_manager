package io.exhub.exhub_manager.common;

/**
 * 响应返回统一格式
 * @date 2018/7/25
 * @author
 */
public class ServerResponse<T> {

    /**
     * 状态码，与HTTP响应码一致
     */
    private int status;
    /**
     * 简讯
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(int status, T data) {
        return new ServerResponse<T>(status, data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int status, String msg) {
        return new ServerResponse<T>(status, msg);
    }


}
