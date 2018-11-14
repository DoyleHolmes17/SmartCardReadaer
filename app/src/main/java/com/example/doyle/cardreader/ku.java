package com.example.doyle.cardreader;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ku {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e = "";
    private String f = "";

    private String b() {
        return "0000000000000000";
    }

    public static byte[] stringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        int i = 0;
        while (i < length - 1) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) (Integer.parseInt(str.substring(i, i2), 16) & 255);
            i = i2;
        }
        return bArr;
    }

    private String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.substring(2, 16));
        stringBuilder.append(str.substring(0, 2));
        return stringBuilder.toString();
    }

    private String c() {
        try {
            return a(a(stringToByteArray("8DC0DC40FE1DC582CF7099E2AACFBC10"), "C152153D5807784C721A433B5B59636D", b())).substring(0, 32);
        } catch (Exception unused) {
            return "";
        }
    }

    private String d() {
        try {
            return a(a(stringToByteArray("3C37029CA595FE4E7E62FCB2F7909B2C"), "C152153D5807784C721A433B5B59636D", b())).substring(0, 32);
        } catch (Exception unused) {
            return "";
        }
    }

    private void e() {
        try {
            this.b = a(b(this.a, c(), b())).substring(0, 32);
        } catch (Exception unused) {
        }
    }

    private void f() {
        try {
            this.e = a(b(this.b, d(), this.c));
        } catch (Exception unused) {
        }
    }

    public String a() {
        try {
            String b = b(a(a(stringToByteArray(a(c(this.d, this.e))), stringToByteArray(b()))));
            String a = a(c(a(a(stringToByteArray("1122334455667788"), stringToByteArray(this.d))), this.e));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a);
            stringBuilder.append(a(c(a(a(stringToByteArray(b), stringToByteArray(a))), this.e)));
            return stringBuilder.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public String a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            StringBuilder stringBuilder;
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("0");
                stringBuilder.append(toHexString);
                toHexString = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(toHexString);
            str = stringBuilder.toString();
        }
        return str.toUpperCase();
    }

    public void a(String str, String str2, String str3) {
        this.a = str;
        this.c = str2;
        this.d = str3;
        e();
        f();
    }

    public byte[] a(String str, String str2) {
        Key secretKeySpec = new SecretKeySpec(stringToByteArray(str2), "DES");
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/Nopadding");
            instance.init(1, secretKeySpec);
            return instance.doFinal(stringToByteArray(str));
        } catch (Exception unused) {
            return null;
        }
    }

    public byte[] a(byte[] bArr, String str, String str2) {
        byte[] stringToByteArray;
        StringBuilder stringBuilder;
        if (str.length() == 48) {
            stringToByteArray = stringToByteArray(str);
        } else if (str.length() == 32) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str.substring(0, 16));
            stringToByteArray = stringToByteArray(stringBuilder.toString());
        } else if (str.length() == 16) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str);
            stringBuilder.append(str);
            stringToByteArray = stringToByteArray(stringBuilder.toString());
        } else {
            stringToByteArray = stringToByteArray("00000000000000000000000000000000");
        }
        Key secretKeySpec = new SecretKeySpec(stringToByteArray, "DESede");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(stringToByteArray(str2));
        try {
            Cipher instance = Cipher.getInstance("DESede/CBC/Nopadding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        if (bArr2.length == 0) {
            throw new IllegalArgumentException("empty security key");
        }
        int i = 0;
        int i2 = i;
        while (i < bArr.length) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i2]);
            i2++;
            if (i2 >= bArr2.length) {
                i2 = 0;
            }
            i++;
        }
        return bArr3;
    }

    public byte[] b(String str, String str2) {
        Key secretKeySpec = new SecretKeySpec(stringToByteArray(str2), "DES");
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/Nopadding");
            instance.init(2, secretKeySpec);
            return instance.doFinal(stringToByteArray(str));
        } catch (Exception unused) {
            return null;
        }
    }

    public byte[] b(String str, String str2, String str3) {
        byte[] stringToByteArray;
        StringBuilder stringBuilder;
        if (str2.length() == 48) {
            stringToByteArray = stringToByteArray(str2);
        } else if (str2.length() == 32) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(str2.substring(0, 16));
            stringToByteArray = stringToByteArray(stringBuilder.toString());
        } else if (str2.length() == 16) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(str2);
            stringBuilder.append(str2);
            stringToByteArray = stringToByteArray(stringBuilder.toString());
        } else {
            stringToByteArray = stringToByteArray("00000000000000000000000000000000");
        }
        Key secretKeySpec = new SecretKeySpec(stringToByteArray, "DESede");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(stringToByteArray(str3));
        try {
            Cipher instance = Cipher.getInstance("DESede/CBC/Nopadding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return instance.doFinal(stringToByteArray(str));
        } catch (Exception unused) {
            return null;
        }
    }

    public byte[] c(String str, String str2) {
        String substring = str2.substring(0, 16);
        try {
            return stringToByteArray(a(b(a(a(a(b(str, substring)), str2.substring(16, 32))), substring)));
        } catch (Exception unused) {
            return stringToByteArray("");
        }
    }
}
