package com.example.doyle.cardreader;

import android.nfc.tech.IsoDep;

import java.io.IOException;

public class MegaCash extends IsoDepCard {
    public static final byte[] MegaCashAPDUSelectDF = new byte[]{(byte) -112, (byte) 90, (byte) 0, (byte) 0, (byte) 3, (byte) 0, (byte) -120, (byte) 0, (byte) 0};
    private static final byte[] MegaCashGetBalance = new byte[]{(byte) -112, (byte) 108, (byte) 0, (byte) 0, (byte) 1, (byte) 2, (byte) 0};
    private static final byte[] MegaCashGetHistory = new byte[]{(byte) -112, (byte) -69, (byte) 0, (byte) 0, (byte) 7, (byte) 3, (byte) 0, (byte) 0, (byte) 0, (byte) 9, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] MegaCashGetNextRecord = new byte[]{(byte) -112, (byte) -81, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] MegaCashGetPAN = new byte[]{(byte) -112, (byte) -67, (byte) 0, (byte) 0, (byte) 7, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 10, (byte) 0, (byte) 0, (byte) 0};

    public MegaCash(IsoDep isoDep) {
        this.mIsoDep = isoDep;
    }

    public boolean readCard() {
        try {
            String hexString = Util.getHexString(this.mIsoDep.transceive(MegaCashGetPAN));
            this.mCardNumber = hexString.substring(4, hexString.length() - 4);
            this.mBalance = (long) hexStringToInteger(Util.getHexString(reverseValue(this.mIsoDep.transceive(MegaCashGetBalance))));
            Util.getHexString(this.mIsoDep.transceive(MegaCashGetHistory));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private byte[] reverseValue(byte[] bArr) {
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        int i = length - 1;
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2] = bArr[i - i2];
        }
        return bArr2;
    }

    public int hexStringToInteger(String str) {
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            i2 = (i2 * 16) + "0123456789ABCDEF".indexOf(str.charAt(i));
            i++;
        }
        return i2;
    }
}
