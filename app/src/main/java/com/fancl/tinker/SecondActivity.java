package com.fancl.tinker;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fancl.tinker.library.FixDexUtils;
import com.fancl.tinker.library.utils.Consants;
import com.fancl.tinker.library.utils.FileUtils;
import com.fancl.tinker.utils.ParamSort;

import java.io.File;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


    }

    public void show(View view) {
        ParamSort paramSort = new ParamSort();
        paramSort.math(this);
    }

    public void fix(View view) {
        File sourFile = new File(Environment.getExternalStorageDirectory(), Consants.DEX_NAME);

        File targetFile = new File(getDir(Consants.DEX_DIR, Context.MODE_PRIVATE).getAbsolutePath()
                + File.separator + Consants.DEX_NAME);

        if(targetFile.exists()){
            targetFile.delete();
        }

        FileUtils.copyFile(sourFile, targetFile);

        FixDexUtils.loadFixDex(this);
    }


}
