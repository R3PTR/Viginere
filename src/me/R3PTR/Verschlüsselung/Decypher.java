package me.R3PTR.Verschlüsselung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Decypher {
    public static int getKeyLength(String message) {
        StringBuilder sb = new StringBuilder();
        HashMap<String, List<Integer>> characterPairs = new HashMap<>();
        message = message.toUpperCase().replace(" ", "");
        for (int i = 0; i < message.length() - 1; i++) {
            String pair = message.charAt(i) + "" + message.charAt(i + 1);
            characterPairs.computeIfAbsent(pair, k -> new ArrayList<>());
            characterPairs.get(pair).add(i);
        }
        String highestPair = "";
        int highest = 0;
        for (String s : characterPairs.keySet()) {
            int current = characterPairs.get(s).size();
            if (current > highest) {
                highest = current;
                highestPair = s;
            }
        }
        List<Integer> highestList = characterPairs.get(highestPair);
        List<Integer> gaps = new ArrayList<>();
        int biggestGap = 0;
        for (int i = 0; i < highestList.size() - 1; i++) {
            int gap = highestList.get(i + 1) - highestList.get(i);
            gaps.add(gap);
            biggestGap = Math.max(gap, biggestGap);
        }
        int biggestDivisor = biggestGap;
        int devisions = 0;
        for (int i = biggestGap; i > 2; i--) {
            int currentDevisions = 0;
            for (int g : gaps) {
                if (g % i == 0) {
                    currentDevisions++;
                }
            }
            if (currentDevisions > devisions) {
                devisions = currentDevisions;
                biggestDivisor = i;
            }
        }
        return biggestDivisor;
    }
}