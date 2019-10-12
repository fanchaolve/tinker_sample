package com.fancl.tinker.library.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtils {


    public static void copyFile(File sourceFile, File targetFile) {

        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = bufferedInputStream.read(b)) != -1) {
                bufferedOutputStream.write(b, 0, len);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
            fileOutputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }

    }
}
