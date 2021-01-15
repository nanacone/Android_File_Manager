package com.example.nyahn_fileexplorer.Interface;

import com.example.nyahn_fileexplorer.Models.Mode;

import java.io.File;

public interface OnItemClick {
//    void onBackClick(File clickedFile);

    // FileListAdapter.java
    File onGetParentFile();
    void onSetParentFile(File file);
    void onSetFileList(File file);
    void onShowBottomLayout();
    Mode onGetMode();
    void onSetMode(Mode mode);
    void onAddDirectoryList(File addFile);
    void onBackDirectoryList(int clickedPosition);

    // DirectoryListAdapter.java
}