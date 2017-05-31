package com.bostongene;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andry on 30.05.17.
 *
 *
 * English Number Converter
 */
public class ConverterService  {

    private List<String> single = Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private List<String> teens = Arrays.asList("ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen");
    private List<String> tens = Arrays.asList("twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety");
    private List<String> powers = Arrays.asList("", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion");

    public ConverterService() {

    }

    public int convertToNumber(String words) throws IllegalArgumentException{

        String[] arr = words.split(" ");

        for(String word : arr){
            if(!single.contains(word) && !teens.contains(word) && !tens.contains(word) && !powers.contains(word) && word.compareTo("hundred") != 0) {
                throw new IllegalArgumentException();
            }
        }

        if( words.isEmpty() ) {
            return 0;
        }

        words = words.trim();
        words += " ";
        int number = 0;
        int index = 0;

        for(int i = powers.size() - 1; i >= 0; i--) {
            String thisPower = powers.get(i);

            if(powers.get(i).compareTo("") != 0) {
                index = words.indexOf(powers.get(i) + " ");


                if( index >= 0 && words.charAt(index + powers.get(i).length()) == ' ') {
                    int count = convertToNumber(words.substring(0, index));
                    number += count *  Math.pow(1000, i);
                    words = words.substring(index, words.length());
                }
            }
        }

        index = words.indexOf("hundred");

        if( index >= 0 && words.charAt(index + "hundred".length()) == ' ') {
            int count = convertToNumber(words.substring(0, index));
            number += count *  100;
            words = words.substring(index, words.length());
        }


        for(int i = tens.size() - 1; i >= 0; i--) {
            String thisPower = tens.get(i);

            if(tens.get(i).compareTo("") != 0) {
                index = words.indexOf(tens.get(i)  + " ");


                if( index >= 0 && words.charAt(index + tens.get(i).length()) == ' ') {
                    number += ((i + 2) * 10);
                    words = words.substring(index, words.length());
                }
            }
        }

        for(int i = teens.size() - 1; i >= 0; i--) {
            String thisPower = teens.get(i);

            if(teens.get(i).compareTo("") != 0) {
                index = words.indexOf(teens.get(i) + " ");


                if( index >= 0 && words.charAt(index + teens.get(i).length()) == ' ') {
                    number += (i + 10);
                    words = words.substring(index, words.length());
                }
            }
        }

        for(int i = single.size()- 1; i >= 0; i--) {
            String thisPower = single.get(i);

            if(single.get(i).compareTo("") != 0) {
                index = words.indexOf(single.get(i) + " ");


                if( index >= 0 && words.charAt(index + single.get(i).length()) == ' ') {
                    number += i;
                    words = words.substring(index, words.length());
                }
            }
        }


        return number;
    }
}
