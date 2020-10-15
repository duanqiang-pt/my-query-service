package com.zhongsheng.utils;

import java.io.IOException;
import java.util.Properties;


/**
 * 文件操作工具类
 * @author zhongsheng
 * @date 2020年8月11日
 * @version 1.0
 */
public class FileUitls {
    
    
    /**
     * 获取指定路径下的properties文件数据
     * 到Properties对象中。
     * @param propsPath properties文件所在路径（类的根路径为“/”）
     * @return  带有数据的Properties对象
     */
    public static Properties getHasDataProperties(String propsPath) {
    	if(propsPath==null || !propsPath.startsWith("/")){
    		 throw new IllegalArgumentException("the properties path is illegal !");
    	}
        Properties props=new Properties();
        try {
            props.load(FileUitls.class.getResourceAsStream(propsPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }
    

}
