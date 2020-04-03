package me.R3PTR.Verschlüsselung;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
    public static void sortDic(File dic) {
        try {
            Scanner sc = new Scanner(dic);
            List<String> words = new ArrayList<>();
            while (sc.hasNext()) {
                String next = sc.next();
                next = next.toUpperCase();
                if (checkWord(next)) {
                    System.out.println(next);
                    words.add(next);
                }
            }
            Collections.sort(words);
            StringBuilder sb = new StringBuilder();
            for (String s : words) {
                sb.append(s).append(" ");
            }
            System.out.println(sb.toString());
            FileWriter writer = new FileWriter(dic);
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkWord(String next) {
        String alphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z ";
        for (char c : next.toCharArray()) {
            if (!alphabet.contains(c + " ")) {
                System.out.println(c);
                return false;
            }
        }
        System.out.println(next);
        return true;
    }
}
