package com.example.doyle.cardreader;

import android.nfc.tech.MifareClassic;
import android.util.Log;
import java.io.IOException;

public class MifareClassicCard extends EMoneyCard {
    public static final byte[] DEFECTO_KEY = new byte[]{(byte) -5, (byte) 18, (byte) -1, (byte) 0, (byte) 15, (byte) 1};
    public static final int KEY_TYPE_A = 0;
    public static final int KEY_TYPE_B = 1;
    public static final int MIFARE_CONNECTION_SUCCESS = 0;
    public static final int MIFARE_ERROR = -2;
    public static final int MIFARE_TAG_NULL = -3;
    public static final byte[] STANDAR_KEY = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
    private static final String TAG = "CekSaldo";
    public static final int TAG_NO_COMPATIBLE = -1;
    public byte[] buffer = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    protected MifareClassic mMifareClassic;

    public boolean readCard() {
        return false;
    }

    public MifareClassicCard() {
        Log.d("CekSaldo", "MifareManager default constructor");
        this.mMifareClassic = null;
        this.mTag = null;
        this.mTagId = null;
        this.mCardNumber = null;
        this.mBalance = 0;
    }

    public byte[] idTag() {
        return this.mTag.getId();
    }

    public int connectTag() {
        if (this.mTag == null) {
            return -3;
        }
        String[] techList = this.mTag.getTechList();
        int i = 0;
        while (i < techList.length && !techList[i].equals(MifareClassic.class.getName())) {
            i++;
        }
        if (i >= techList.length) {
            return -1;
        }
        this.mMifareClassic = MifareClassic.get(this.mTag);
        try {
            this.mMifareClassic.connect();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        }
    }

    public void disconnectTag() {
        try {
            this.mMifareClassic.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authentificationKey(byte[] bArr, int i, int i2) {
        switch (i) {
            case 0:
                try {
                    if (this.mMifareClassic.authenticateSectorWithKeyA(i2, bArr)) {
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            case 1:
                try {
                    if (this.mMifareClassic.authenticateSectorWithKeyB(i2, bArr)) {
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
//            case 1:
//                if (this.mMifareClassic.authenticateSectorWithKeyB(i2, bArr)) {
//                    return true;
//                }
//                break;
        }
        return false;
    }

    public byte[] readMifareTagBlock(int i, int i2) {
        try {
            return this.mMifareClassic.readBlock(i2 + (i * 4));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean writeMifareTag(int i, int i2, byte[] bArr) {
        try {
            this.mMifareClassic.writeBlock(i2 + (i * 4), bArr);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte[] testApdu() {
        Object obj = new byte[]{(byte) -1, (byte) -80, (byte) 0, (byte) 4, (byte) 0, (byte) 0};
        try {
            return this.mMifareClassic.transceive(new byte[]{(byte) 48, (byte) 8});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
