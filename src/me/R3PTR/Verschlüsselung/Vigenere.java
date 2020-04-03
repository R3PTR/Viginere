package me.R3PTR.Verschlüsselung;

public class Vigenere {

    public static String encrypt(String message, String key) {
        StringBuilder sb = new StringBuilder();
        message = message.toUpperCase().replace(" ", "");
        key = key.toUpperCase().replace(" ", "");
        int keyIndex = 0;
        for (char c : message.toCharArray()) {
            int encryptedCharacter = (int) key.charAt(keyIndex) + ((int) c - 65);
            if (encryptedCharacter > 90) {
                encryptedCharacter -= 26;
            }
            if (keyIndex++ == key.length()) {
                keyIndex = 0;
            }
            sb.append((char) encryptedCharacter);
        }
        return sb.toString();
    }

    public static String decrypt(String message, String key) {
        StringBuilder sb = new StringBuilder();
        message = message.toUpperCase().replace(" ", "");
        key = key.toUpperCase().replace(" ", "");
        int keyIndex = 0;
        for (char c : message.toCharArray()) {
            int decryptedCharacter = (int) c - (int) key.charAt(keyIndex);
            if (decryptedCharacter >= 0) {
                decryptedCharacter += 65;
            } else {
                decryptedCharacter += 91;
            }
            if (keyIndex + 1 == key.length()) {
                keyIndex = 0;
            } else {
                keyIndex++;
            }
            sb.append((char) decryptedCharacter);
        }
        return sb.toString();
    }
}
