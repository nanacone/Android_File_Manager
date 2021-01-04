package com.example.nyahn_fileexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout llInnerStorage;
    LinearLayout llextraStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbarTitle();

        init();
        setupPermission();
//        initClick();

    }

    // request permission for sdcard
    private void setupPermission() {
        //check for permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //ask for permission
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }
    }
    public void init() {
        llInnerStorage = (LinearLayout)findViewById(R.id.llInnerStorage);
        llextraStorage = (LinearLayout)findViewById(R.id.llExtraStorage);

    }
//    public void initClick(){
//        llextraStorage.setOnClickListener(view ->
//                new Intent(MainActivity.this, DirectoryActivity.class));
//        llInnerStorage.setOnClickListener(view ->
//                new Intent());
//
//    }

    private void setToolbarTitle(){
        // toolbar as actionbar
        // setting toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_main_page);
        setSupportActionBar(toolbar);
    }
}