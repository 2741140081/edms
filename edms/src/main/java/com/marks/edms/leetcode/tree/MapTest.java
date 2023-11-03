package com.marks.edms.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("MOP",0.9709);
        map.put("EUR",8.7073);
        map.put("HKD",1.00);
        map.put("RMB",1.18);
        map.put("USD",7.77);
        map.put("SGD",5.75);
        map.put("MYR",1.895);
        map.put("TWD",0.255);

        String Currency = "RMB";

        Double aDouble = map.get(Currency);
    }
}
