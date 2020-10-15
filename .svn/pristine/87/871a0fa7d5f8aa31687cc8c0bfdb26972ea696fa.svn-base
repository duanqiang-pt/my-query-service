package com.zhongsheng.utils;

import java.lang.reflect.Field;

/**
 * @author zhongsheng
 * @date 2020年10月10日
 * @version 1.0
 */
public class ReflectUtils {
    
    
    /**
     * 通过反射获取到对象的属性值
    * @param targetObj	需要获取值的对象
    * @param fileName		需要获取的属性名
    * @return	获取到的值
     */
    public static <T> Object getFieldValue(T targetObj,String fileName){
        
    	try {
			Field field = targetObj.getClass().getDeclaredField(fileName);
			field.setAccessible(true);
			return field.get(targetObj);
		} catch (Exception e) {
			
		}
        
        return null;
    }

}
