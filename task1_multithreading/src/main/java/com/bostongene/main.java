package com.bostongene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andry on 28.05.17.
 */
public class main {
    public static void main(String[] args) {

        SynchronizedContainer sc = new SynchronizedContainer();

        // First thread
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    String inputString = "";

                    while (true) {
                        System.out.println("Enter word number: ");
                        inputString = bufferRead.readLine();

                        if(!inputString.isEmpty())
                            sc.setEnglishNumber(inputString);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();

        // Second thread
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

               while (true) {
                   try {
                       Thread.sleep(5000);
                       System.out.println(sc.getMinEnglishNumber());
                   }catch (InterruptedException e) {
                        e.printStackTrace();
                   }
               }
            }
        });
        thread2.start();


    }
}
