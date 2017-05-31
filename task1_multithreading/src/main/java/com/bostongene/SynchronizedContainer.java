package com.bostongene;

import java.util.TreeMap;

/**
 * Created by Andry on 30.05.17.
 *
 *
 * Synchronized Container
 *
 *
 *
 */
public class SynchronizedContainer {

    private TreeMap treeMap = new TreeMap<>();

    private ConverterService converterService = new ConverterService();

    public synchronized void setEnglishNumber(String number) {
        try{
            treeMap.put(converterService.convertToNumber(number), number);
        }catch (IllegalArgumentException e){}
    }

    public synchronized String getMinEnglishNumber() {
        if(!treeMap.isEmpty()) {
            String number = treeMap.firstEntry().getValue().toString();
            treeMap.remove(treeMap.firstKey());
            return number;
        }
        return "Empty";
    }
}
