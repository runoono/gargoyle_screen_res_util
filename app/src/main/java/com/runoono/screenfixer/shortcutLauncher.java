package com.runoono.screenfixer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class shortcutLauncher extends Activity {

    private ExecutorService queue = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shortcut_launcher);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("resolution")) {
            String resolution = intent.getStringExtra("resolution");
            Log.i("TAG", "onCreate: "+resolution);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            Log.i("TAG", "onCreate: "+width+"x"+height);
            if((width+"x"+height).equals(resolution))
                RootProvider.RunAsRoot("wm size reset &");
            else
                RootProvider.RunAsRoot("wm size "+resolution+" &");
        }
        finish();
    }
}