package com.example.nyahn_fileexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nyahn_fileexplorer.Activity.FileListActivity;
import com.example.nyahn_fileexplorer.Utils.FileManage;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    LinearLayout llMainStorage;
    LinearLayout llSdcardStorage;

    TextView tvInnerVolume;
    TextView tvSdcardVolume;

    File innerFile;
    File sdcardFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbarTitle();

        init();
        initClick();
        setupPermission();

    }

    // request permission for sdcard
    private void setupPermission() {
        //check for permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        // check for write permission
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }


    public void init() {
        llMainStorage = (LinearLayout)findViewById(R.id.llMainStorage);
        llSdcardStorage = (LinearLayout)findViewById(R.id.llSdcardStorage);
        tvInnerVolume = (TextView) findViewById(R.id.tvInnerVolume);
        tvSdcardVolume = (TextView) findViewById(R.id.tvSdcardVolume);

        // 내부 저장소 저장용량 계산
        innerFile =  new File(Environment.getExternalStorageDirectory().getPath());

        int usuableSpace = (int) Math.round(innerFile.getFreeSpace() / Math.pow(1024, 3));
        int totalSpace = (int) Math.round(innerFile.getTotalSpace() / Math.pow(1024, 3));
        int usedSpace = totalSpace - usuableSpace;

        // TODO : 주석 해제
        tvInnerVolume.setText(String.format("%dGB / %dGB", usedSpace, totalSpace));

        // sdcard 폴더 갖고 오기
        File sdcard = null;
        sdcardFile = new File(Environment.getStorageDirectory().getPath());
        File[] list = sdcardFile.listFiles();

        // sdcard 이름에 (-)이 포함되어 있음.
        for(File el : list){
            if(el.getName().contains("-")){
                sdcard = el;
            }
        }

        if(sdcard != null) {
            usuableSpace = (int) Math.round(sdcardFile.getFreeSpace() / Math.pow(1024, 3));
            totalSpace = (int) Math.round(sdcardFile.getTotalSpace() / Math.pow(1024, 3));
            usedSpace = totalSpace - usuableSpace;
            tvSdcardVolume.setText(String.format("%dGB / %dGB", usedSpace, totalSpace));
        } else {
            // sdcard가 없을 경우 선택 비활성화
            llSdcardStorage.setEnabled(false);
        }

    }
    public void initClick(){
        Intent intent;
        Bundle bundle = new Bundle();
        intent = new Intent(MainActivity.this, FileListActivity.class);

        llMainStorage.setOnClickListener(view ->{
            bundle.putString("STORAGE", innerFile.getPath());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });

        llSdcardStorage.setOnClickListener(v -> {
            bundle.putString("STORAGE", sdcardFile.getPath());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
    }

    private void setToolbarTitle(){
        // toolbar as actionbar
        // setting toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_main_page);
        setSupportActionBar(toolbar);
    }
}