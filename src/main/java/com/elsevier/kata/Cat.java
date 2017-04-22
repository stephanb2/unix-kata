package com.elsevier.kata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Cat {
    private static final int EXIT_FAILURE = 1;

    public static void main(String... args) {
        //TODO: handle "-" as stdin
        try {
            for (String fileName : args) {
                outputFileStream(fileName);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(EXIT_FAILURE);
        }
    }

    static void printFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(new File(fileName));
        BufferedReader br = new BufferedReader(fileReader);
        int charInt;
        while ((charInt = br.read()) != -1) {
            System.out.print((char) charInt);
        }
        fileReader.close();
    }

    static void outputFileStream(String fileName) throws IOException {
        FileInputStream inputStream = new FileInputStream(fileName);
        int charInt;
        while ((charInt = inputStream.read()) != -1) {
            System.out.print((char) charInt);
        }
        inputStream.close();
    }
}
