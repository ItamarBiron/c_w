package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String s = "143";
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        List<Object> stringChars =Arrays.asList(chars);
        Collections.reverse(stringChars);
        s = new String(stringChars);
        System.out.println(s);
    }
}
