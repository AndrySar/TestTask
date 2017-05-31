package com.bostongene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andry on 30.05.17.
 */
public class main {

    public static void main(String[] args) {

        try{
            YandexTranslate yat = new YandexTranslate();
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String inputString = "";

            while(true) {
                System.out.println("Enter sentence< q to quit >: ");
                inputString = bufferRead.readLine();

                if(inputString.compareTo("q") == 0) break;

                System.out.println(yat.translate(inputString));
            }
        }catch (Exception e) {
            System.out.println("Error");
        }

    }
}
