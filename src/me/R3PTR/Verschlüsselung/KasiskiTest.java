package me.R3PTR.Verschlüsselung;

import java.util.ArrayList;
import java.util.HashMap;

public class KasiskiTest {

    public static int getKeyLength(String txt) {
        HashMap<String, ArrayList<Integer>> kasiskiTest = kasiskiTest(txt);
        ArrayList<Integer> gaps = new ArrayList<>();
        int biggestGap = 0;
        for (String s : kasiskiTest.keySet()) {
            ArrayList<Integer> pointList = kasiskiTest.get(s);
            for (int i = 1; i < pointList.size(); i++) {
                int gap = pointList.get(i) - pointList.get(i - 1);
                gaps.add(gap);
                biggestGap = Math.max(biggestGap, gap);
            }
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

    public static HashMap<String, ArrayList<Integer>> kasiskiTest(String txt) {
        final int MAX_TIEFE = 200;
        int length = txt.length();
        if (length > MAX_TIEFE) {
            length = MAX_TIEFE;
        }
        HashMap<String, ArrayList<Integer>> res = new HashMap<>();
        String frac;
        int j, k;
        for (int i = 0; i <= length; i++) {
            frac = get3CharAt(txt, i);
            while (res.containsKey(frac) && !frac.equals("$")) {
                i++;
                frac = get3CharAt(txt, i);
            }
            j = i + 1;
            k = 0;
            while (j < length && k != -1) {
                k = txt.indexOf(frac, j);
                if (k != -1) {
                    j = k + 1;
                    if (res.containsKey(frac)) {
                        res.get(frac).add(k);
                    } else {
                        ArrayList<Integer> anz = new ArrayList<>();
                        anz.add(i);
                        anz.add(k);
                        res.put(frac, anz);
                    }
                }
            }
        }
        return res;
    }

    private static String get3CharAt(String txt, int pos) {
        StringBuilder res = new StringBuilder();
        if (txt.length() > pos + 2) {
            res.append(txt.charAt(pos));
            res.append(txt.charAt(pos + 1));
            res.append(txt.charAt(pos + 2));
        } else {
            res.append("$");
        }
        return res.toString();
    }
}