package com.sg.exception;

import com.sg.bean.RestAPIResult;

/**
 * 异常处理基础类
 */
public abstract class BaseException extends Exception {

    private static final long serialVersionUID = 1L;

    protected int errorCode;

    protected RestAPIResult dataResult;

    protected BaseException() {
        super();
    }

    protected BaseException(String errorMsg) {
        super(errorMsg);
    }

    protected BaseException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    protected BaseException(int errorCode, String errorMsg, RestAPIResult dataResult) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }

    protected BaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    protected BaseException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
    }

    protected BaseException(int errorCode, String errorMsg, RestAPIResult dataResult, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public RestAPIResult getDataResult() {
        return dataResult;
    }

    public void setDataResult(RestAPIResult dataResult) {
        this.dataResult = dataResult;
    }
}
