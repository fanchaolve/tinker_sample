package com.fancl.tinker.library.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayUtils {


    public static Object combineArray(Object arrayLhs, Object arrayRhs) {

        Class<?> loadClazz = arrayLhs.getClass().getComponentType();
        //数组长度
        int i = Array.getLength(arrayLhs);
        int j = i + Array.getLength(arrayRhs);
        Object result = Array.newInstance(loadClazz, j);
        for (int k = 0; k < j; ++k) {

            if (k < i) {
                Array.set(result, k, Array.get(arrayLhs, k));
            } else {
                Array.set(result, k, Array.get(arrayRhs, k - i));
            }
        }
        return result;
    }
}
