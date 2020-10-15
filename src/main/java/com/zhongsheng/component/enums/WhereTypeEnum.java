package com.zhongsheng.component.enums;

/**
 * where子句的要执行的条件类型枚举
 * @version 1.0
 */
public enum WhereTypeEnum {
    
    /*  相当于SQL的fieldName = value  */
    EQUAL_TO("=",""),
    /*  相当于SQL的fieldName <> value  */
    NOT_EQUAL_TO("<>",""),
    /*  相当于SQL的fieldName like value  */
    LIKE("like",""),
    /*  相当于SQL的fieldName not like value  */
    NOT_LIKE("not like",""),
    /*  相当于SQL的fieldName between value1 and value2  */
    BETWEEN("between",""),
    /*  相当于SQL的fieldName not between value1 and value2  */
    NOT_BETWEEN("",""),
    /*  相当于SQL的fieldName is null  */
    IS_NULL("is null",""),
    /*  相当于SQL的fieldName is not null  */
    IS_NOT_NULL("is not null",""),
    /*  相当于SQL的fieldName > value  */
    GREATER_THAN(">",""),
    /*  相当于SQL的fieldName >= value  */
    GREATER_THAN_OR_EQUAL_TO(">=",""),
    /*  相当于SQL的fieldName < value  */
    LESS_THAN("<",""),
    /*  相当于SQL的fieldName <= value  */
    LESS_THAN_OR_EQUAL_TO("<=",""),
    /*  相当于SQL的fieldName in value  */
    IN("in",""),
    /*  相当于SQL的fieldName not in value  */
    NOT_IN("not in",""),
    /*  相当于SQL的fieldName exsit value  */
    EXSITS("exsits",""),
    /*  相当于SQL的fieldName not exsit value  */
    NOT_EXSITS("not exsits","")
    
    ;
    
    /*
     * 在SQL中的代码形式
     */
    @SuppressWarnings("unused")
    private String sqlCode;
    
    /*
     * 描述
     */
    @SuppressWarnings("unused")
    private String describe;

    private WhereTypeEnum(String sqlCode, String describe) {
        this.sqlCode = sqlCode;
        this.describe = describe;
    }
    
    
    
    

}
