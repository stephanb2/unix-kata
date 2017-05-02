package com.elsevier.kata;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public class TextUtilsTest {

    @Test
    public void emptyStreamIsNotText() throws IOException {
        // Given
        StringReader emptyReader = new StringReader("");
        // When
        Boolean isText = TextUtils.isText(emptyReader);
        // Then
        Assert.assertFalse(isText);
    }

    @Test
    public void simpleStringIsText() throws IOException {
        //Given
        StringReader stringReader = new StringReader(
                "There are many variations of passages of Lorem Ipsum available, " +
                        "but the majority have suffered alteration in some form, by injected humour, or randomised words "
                        +
                        "which don't look even slightly believable.");
        // When
        boolean isText = TextUtils.isText(stringReader);
        //Then
        Assert.assertTrue(isText);
        char[] buffer = new char[10];
        int success = stringReader.read(buffer, 0, 10);
        Assert.assertEquals("There are ", new String(buffer));
    }

    @Test
    public void binaryFileIsNotText() throws IOException {
        // Given
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("test.bin");
        Reader reader = new BufferedReader(new InputStreamReader(is));
        // When
        Boolean isText = TextUtils.isText(reader);
        // Then
        Assert.assertFalse(isText);
    }

}