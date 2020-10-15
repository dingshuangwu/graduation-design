package dingshuangwu.graduation.graduationo2o.pojo;

import java.io.Serializable;

/**
 * 请求响应
 *
 * @param <T>
 * @author dingshuangwu
 * @date 2019-12-24
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 2xx Success 3xx Redirection 4xx Client Error 5xx Server Error
     */
    protected int code;

    protected String message;

    protected T data;

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

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <E> Result<E> success() {
        return new Result<E>(200);
    }

    public static <E> Result<E> success(String message) {
        return new Result<E>(200, message);
    }

    public static <E> Result<E> success(E data) {
        return new Result<E>(200, data);
    }

    public static <E> Result<E> success(String message, E data) {
        return new Result<E>(200, message, data);
    }

    public static <E> Result<E> error(E data) {
        return new Result<E>(400, data);
    }

    public static <E> Result<E> error(String message, E data) {
        return new Result<E>(400, message, data);
    }

    public static <E> Result<E> error(String message) {
        return new Result<E>(400, message);
    }

    public Result() {
    }
}
