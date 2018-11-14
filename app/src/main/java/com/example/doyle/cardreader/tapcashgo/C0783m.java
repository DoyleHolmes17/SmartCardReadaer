package com.example.doyle.cardreader.tapcashgo;

public class C0783m {
    public static String m4140a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
            str = stringBuilder.toString();
        }
        return str;
    }

    public static String m4141a(byte[] bArr, String str) {
        try {
            return m4140a(bArr);
        } catch (Exception unused) {
            return str;
        }
    }

    public static byte[] m4144a(String str) {
        if (str.length() % 2 != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Bad input string: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
