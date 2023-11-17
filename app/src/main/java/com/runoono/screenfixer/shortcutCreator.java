package com.runoono.screenfixer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class shortcutCreator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortcut_creator);

        RootProvider.EnableRoot();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        EditText xBox = findViewById(R.id.shortcut_x);
        EditText yBox = findViewById(R.id.shortcut_y);
        xBox.setText(width+"");
        yBox.setText(height+"");
        Button b = findViewById(R.id.shortcut_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Intent executionIntent = new Intent(shortcutCreator.this, shortcutLauncher.class);
                String resolution = xBox.getText().toString()+"x"+yBox.getText().toString();
                executionIntent.putExtra("resolution", resolution);
                intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, executionIntent);
                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, String.format("Toggle Mini Mode (%s)", resolution));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}