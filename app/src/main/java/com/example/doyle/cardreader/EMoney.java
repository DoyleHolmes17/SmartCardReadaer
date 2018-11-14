package com.example.doyle.cardreader;

import android.nfc.tech.IsoDep;

import java.io.IOException;

public class EMoney extends IsoDepCard {
    public static final byte[] EMoneyAPDUSelectDF = new byte[]{(byte) 0, (byte) -92, (byte) 4, (byte) 0, (byte) 8, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 1};
    private static final byte[] EMoneyAPDUforBalance = new byte[]{(byte) 0, (byte) -75, (byte) 0, (byte) 0, (byte) 10};
    private static final byte[] EMoneyAPDUforNumber = new byte[]{(byte) 0, (byte) -77, (byte) 0, (byte) 0, (byte) 63};

    public EMoney(IsoDep isoDep) {
        this.mIsoDep = isoDep;
    }

    public boolean readCard() {
        try {
            byte[] transceive = this.mIsoDep.transceive(EMoneyAPDUforNumber);
            if (!Util.validApduResponse(transceive)) {
                return false;
            }
            this.mCardNumber = Util.getHexString(transceive, 8);
            transceive = this.mIsoDep.transceive(EMoneyAPDUforBalance);
            if (transceive.length < 4) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                this.mBalance += (((long) transceive[i]) & 255) << (i * 8);
            }
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
