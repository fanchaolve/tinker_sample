package com.fancl.tinker.library;

import android.content.Context;

import com.fancl.tinker.library.utils.ArrayUtils;
import com.fancl.tinker.library.utils.Consants;
import com.fancl.tinker.library.utils.ReflectUtils;

import java.io.File;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

//修复工具
public class FixDexUtils {

    private static HashSet<File> loadDex = new HashSet<>();

    static {
        loadDex.clear();
    }

    public static void loadFixDex(Context context) {
        File fileDir = context.getDir(Consants.DEX_DIR, Context.MODE_PRIVATE);
        File[] files = fileDir.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(Consants.DEX_SUFFIX)
                    && !file.getName().equalsIgnoreCase("classes.dex")) {
                loadDex.add(file);
            }
        }

        createDexClassLoader(context, fileDir);
    }

    private static void createDexClassLoader(Context context, File fileDir) {

        String optimizeDir = fileDir.getAbsolutePath() + File.separator + "opt_dex";
        File fopt = new File(optimizeDir);
        if (!fopt.exists()) {
            fopt.mkdirs();
        }
        for (File dex : loadDex) {

            DexClassLoader dexClassLoader = new DexClassLoader(
                    dex.getAbsolutePath(), optimizeDir, null, context.getClassLoader());
            hotFix(dexClassLoader, context);
        }
    }

    private static void hotFix(DexClassLoader dexClassLoader, Context context) {
        //获取系统的pathclassloader
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();


        //获取自有的dexElements数组
        try {
            Object myElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(dexClassLoader));
            //获取系统的dexElements数组
            Object sysElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathClassLoader));
            //合并 生成新的dexElements
            Object newElements = ArrayUtils.combineArray(myElements, sysElements);
            //获取系统的pathlist属性
            Object sysPathlist = ReflectUtils.getPathList(pathClassLoader);

            ReflectUtils.setField(sysPathlist, sysPathlist.getClass(), newElements);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
