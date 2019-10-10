# tinker_sample
手写热更新



通过dex文件插桩合并原理


将自有的类加载器中的dexElements 和系统的dexElements 合并 设置自有的优先加载

将合并后的新数组新的dexElements 通过反射原理 赋值给系统的pathList的 
