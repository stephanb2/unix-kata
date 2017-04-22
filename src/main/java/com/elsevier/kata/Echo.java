package com.elsevier.kata;

import java.util.Arrays;

public class Echo {

    public static void main(String... args){
        System.out.println(String.join(" ", Arrays.asList(args)));
    }
}
