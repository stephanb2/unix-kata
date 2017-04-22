package com.elsevier.kata;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FalseIT {

    @Test
    public void trueWithoutArgsExitsWithSuccess() throws IOException {
        Process proc = Runtime.getRuntime().exec("java -cp target/unix-kata.jar com.elsevier.kata.True");
        BufferedReader stdOut = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdErr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        Assert.assertEquals(0, bufferToString(stdErr).length());
        Assert.assertEquals(0, bufferToString(stdOut).length());
        Assert.assertEquals(PosixConstants.EXIT_FAILURE, proc.exitValue());
    }

    @Test
    public void trueWithArgsExitsWithSuccess() throws IOException {
        Process proc = Runtime.getRuntime().exec("java -cp target/unix-kata.jar com.elsevier.kata.True foo");
        BufferedReader stdOut = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdErr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        Assert.assertTrue(bufferToString(stdErr).isEmpty());
        Assert.assertTrue(bufferToString(stdOut).isEmpty());
        Assert.assertEquals(PosixConstants.EXIT_FAILURE, proc.exitValue());
    }

    private String bufferToString(BufferedReader reader) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}