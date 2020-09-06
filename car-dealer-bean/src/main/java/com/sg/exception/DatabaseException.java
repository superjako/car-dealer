package com.sg.exception;

import com.sg.bean.RestAPIResult;

/**
 * 数据库异常类
 */
public class DatabaseException extends BaseException {

    private static final long serialVersionUID = 1L;

    public DatabaseException() {
        super();
    }

    public DatabaseException(String errorMsg) {
        super(errorMsg);
    }

    public DatabaseException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public DatabaseException(int errorCode, String errorMsg, RestAPIResult dataResult) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }

    public DatabaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public DatabaseException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
    }

    public DatabaseException(int errorCode, String errorMsg, RestAPIResult dataResult, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }
}

