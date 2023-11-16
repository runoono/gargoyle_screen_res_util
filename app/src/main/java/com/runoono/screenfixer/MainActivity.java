package com.runoono.screenfixer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        EditText xBox = findViewById(R.id.main_x_res);
        EditText yBox = findViewById(R.id.main_y_res);

        {
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            xBox.setText(width+"");
            yBox.setText(height+"");
        }


        Button set_res_button =findViewById(R.id.main_resolution_button);
        Button res_sys_button =findViewById(R.id.main_reset_button);
        Button res_res_button  =findViewById(R.id.reset_button);

        set_res_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootProvider.RunAsRoot("wm size "+xBox.getText().toString()+"x"+yBox.getText().toString());
            }
        });
        res_sys_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootProvider.RunAsRoot("killall com.android.systemui");
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;
                xBox.setText(width+"");
                yBox.setText(height+"");
            }
        });

        res_res_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootProvider.RunAsRoot("wm size reset");
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;
                xBox.setText(width+"");
                yBox.setText(height+"");
            }
        });
    }
}