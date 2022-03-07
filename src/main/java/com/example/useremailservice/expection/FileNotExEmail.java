package com.example.useremailservice.expection;

import java.io.FileNotFoundException;

public class FileNotExEmail extends FileNotFoundException {


    public FileNotExEmail(String msg) {
        super(msg);
    }
}


