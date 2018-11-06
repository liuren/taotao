/**   
 * @Title: ReflectionUtils.java 
 * @Package com.louis.kitty.admin.utils 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lr
 * @date 2018年11月6日 上午11:08:52 
 * @version V1.0.0   
 */
package com.louis.kitty.admin.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** 
 * @ClassName: ReflectionUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lr
 * @date 2018年11月6日 上午11:08:52 
 *  
 */
public class ReflectionUtils {

    /** 
     * @Title: invoke 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param mapper
     * @param @param queryMethodName
     * @param @param args
     * @param @return  参数说明 
     * @return Object    返回类型 
     * @throws 
     */
    public static Object invoke(Object object, String method,  Object... args) {
        Object result =  null;
        Class<? extends Object> clazz = object.getClass();
        Method queryMethod = getMethod(clazz, method, args);
        if(queryMethod != null) {
            try {
                    result = queryMethod.invoke(object, args);
            } catch (IllegalAccessException e) {
                    e.printStackTrace();
            } catch (IllegalArgumentException e) {
                    e.printStackTrace();
            } catch (InvocationTargetException e) {
                    e.printStackTrace();
            }
    } else {
            try {
                    throw new NoSuchMethodException(clazz.getName() + " 类中没有找到 " + method + " 方法。");
            } catch (NoSuchMethodException e) {
                    e.printStackTrace();
            }
    }
        return result;
    }

    /** 
     * @Title: getMethod 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param clazz
     * @param @param method
     * @param @param args
     * @param @return  参数说明 
     * @return Method    返回类型 
     * @throws 
     */
    public static Method getMethod(Class<? extends Object> clazz, String name, Object[] args) {
        Method queryMethod = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                 Class<?>[] parameterTypes = method.getParameterTypes();
                 if (parameterTypes.length == args.length) {
                    boolean isSameMethod = true;
                    for (int i = 0; i < parameterTypes.length; i++) {
                        Object arg = args[i];
                        if (arg == null) {
                            arg = "";
                        }
                        if (!parameterTypes[i].equals(args[i].getClass())) {
                             isSameMethod = false;
                        }
                    }
                    if (isSameMethod) {
                        queryMethod = method;
                        break;
                    }
                } else {

                }
            }
        }
        return queryMethod;
    }

    
}
