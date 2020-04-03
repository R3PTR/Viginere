package me.R3PTR.Verschlüsselung;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Decypher {

    private static List<Integer> keyChars;

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
        System.out.println(highestPair);
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

    public static String dictionaryAttack(String message, File dic) {
        String key = "";
        try {
            Scanner sc = new Scanner(dic);
            int most = 0;
            while (sc.hasNext()) {
                String next = sc.next();
                String decryptedMessage = Vigenere.decrypt(message, next);
                int current = 0;
                for (int i = 0; i < message.length() - 1; i++) {
                    String pair = decryptedMessage.charAt(i) + "" + decryptedMessage.charAt(i + 1);
                    if (pair.equalsIgnoreCase("ER")) {
                        current++;
                    }
                }
                if (current > most) {
                    most = current;
                    key = next;
                }
            }
            System.out.println(most);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String bruteforceAttack(String message, int keyLength) {
        String key = "";
        keyChars = null;
        int most = 0;
        for (int i = 0; i < Math.pow(26, keyLength); i++) {
            String currentKey = getKey(keyLength);
            String decryptedMessage = Vigenere.decrypt(message, currentKey);
            int current = 0;
            for (int i1 = 0; i1 < decryptedMessage.length() - 1; i1++) {
                String pair = decryptedMessage.charAt(i1) + "" + decryptedMessage.charAt(i1 + 1);
                if (pair.equalsIgnoreCase("ER")) {
                    current++;
                }
            }
            if (current > most) {
                most = current;
                key = currentKey;
            }
        }
        return key;
    }

    private static String getKey(int keyLength) {
        StringBuilder sb = new StringBuilder();
        if (keyChars == null) {
            keyChars = new ArrayList<>();
            for (int i = 0; i < keyLength; i++) {
                keyChars.add(65);
            }
        }
        for (int i = keyChars.size() - 1; i >= 0; i--) {
            int current = keyChars.get(i);
            if (current == 90) {
                keyChars.set(i, 65);
            } else {
                keyChars.set(i, current + 1);
                break;
            }
        }
        for (int i : keyChars) {
            sb.append((char) i);
        }
        return sb.toString();
    }
}