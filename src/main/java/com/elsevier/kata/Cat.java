package com.elsevier.kata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    static void outputFileStream(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(fileName);
        int byteInt;
        while ((byteInt = inputStream.read()) != -1) {
            System.out.write(byteInt);
        }
        System.out.flush();
        inputStream.close();
    }
}
