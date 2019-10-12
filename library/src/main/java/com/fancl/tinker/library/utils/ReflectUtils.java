package com.fancl.tinker.library.utils;

import java.lang.reflect.Field;

//反射工具类
public class ReflectUtils {

    private static Object getField(Object obj, Class<?> clazz, String field)
    throws NoSuchFieldException,IllegalAccessException{

        Field localField = clazz.getDeclaredField(field);
        localField.setAccessible(true);
        return  localField.get(obj);
    }

    public static  void setField(Object obj,Class<?> clazz,Object vlaue)
    throws NoSuchFieldException,IllegalAccessException{

        Field field =clazz.getDeclaredField("dexElements");
        field.setAccessible(true);
        field.set(obj,vlaue);
    }

    public  static Object getPathList(Object baseDexClassLoader)
    throws ClassNotFoundException,NoSuchFieldException,IllegalAccessException{
        return getField(baseDexClassLoader,Class.forName("dalvik.system.BaseDexClassLoader"),"pathList");
    }

    public  static Object getDexElements(Object paramObject)
            throws ClassNotFoundException,NoSuchFieldException,IllegalAccessException{
        return  getField(paramObject,paramObject.getClass(),"dexElements");
    }
}
