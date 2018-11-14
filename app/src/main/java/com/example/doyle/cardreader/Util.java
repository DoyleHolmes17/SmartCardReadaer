package com.example.doyle.cardreader;

public class Util {
    private static final byte[] HEX_CHAR_TABLE = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70};
    private static final String TAG = "CardReader";

    public static boolean validApduResponse(byte[] bArr) {
        int i = 0;
        boolean validd = false;
        byte b = bArr[bArr.length - 2];
        byte b2 = bArr[bArr.length - 1];
        int i2 = (b == (byte) -112 || b == (byte) -111) ? 1 : 0;
        if (b2 == (byte) 0) {
            i = 1;
        }
        if (i2 == 1 && i ==1){
            validd = true;
        }
        return validd;
//        return i2 & i;
    }

    public static String getHexString(byte[] bArr) {
        return getHexString(bArr, bArr.length);
    }

    public static String getHexString(byte[] bArr, int i) {
        byte[] bArr2 = new byte[(i * 2)];
        int i2 = 0;
        int length = bArr.length;
        int i3 = 0;
        int i4 = i3;
        while (i2 < length) {
            byte b = bArr[i2];
            if (i3 >= i) {
                break;
            }
            i3++;
            int i5 = b & 255;
            int i6 = i4 + 1;
            bArr2[i4] = HEX_CHAR_TABLE[i5 >>> 4];
            i4 = i6 + 1;
            bArr2[i6] = HEX_CHAR_TABLE[i5 & 15];
            i2++;
        }
        return new String(bArr2);
    }

    public static byte[] mergeArray(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int length2 = bArr2.length;
        Object obj = new byte[(length + length2)];
        System.arraycopy(bArr, 0, obj, 0, length);
        System.arraycopy(bArr2, 0, obj, length, length2);
        return (byte[]) obj;
//        return obj;
    }

    public static byte[] getHexByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static byte[] getHexByteArray2(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }
}
