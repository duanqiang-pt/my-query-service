package com.zhongsheng.exception;

/**
 * 查询参数出现的异常
 * @version 1.0
 */
public class QueryParamIllgalException extends SqlQueryException {

    private static final long serialVersionUID = 8118519778343393267L;

    public QueryParamIllgalException() {
        super();
    }

    public QueryParamIllgalException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public QueryParamIllgalException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public QueryParamIllgalException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public QueryParamIllgalException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    
    
    

}
