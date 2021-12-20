package com.urise.webapp;

import java.io.File;

public class MainFile {
    public void listFiles(String startDir) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFiles(file.getAbsolutePath());
                } else {
                    System.out.println(file.getName() + " (size in bytes: " + file.length() + ")");
                }
            }
        }
    }

    public static void main(String[] args) {
        MainFile test = new MainFile();

        String startDir = "./src";

        test.listFiles(startDir);
    }
}