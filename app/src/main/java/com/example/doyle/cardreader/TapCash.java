package com.example.doyle.cardreader;

import android.nfc.tech.IsoDep;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;

import com.example.doyle.cardreader.tapcashgo.card.tapcash.TAPCASHTransaction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TapCash extends IsoDepCard {
    public static final byte[] TapCashAPDUSelectDF = new byte[]{(byte) 0, (byte) -92, (byte) 4, (byte) 0, (byte) 8, (byte) -96, (byte) 0, (byte) 66, (byte) 78, (byte) 73, (byte) 16, (byte) 0, (byte) 1};
    private byte[] commandAPDUSelectDF_TapCash2 = new byte[]{(byte) 0, (byte) -92, (byte) 4, (byte) 0, (byte) 8, (byte) -96, (byte) 0, (byte) 66, (byte) 78, (byte) 73, (byte) -103, (byte) -103, (byte) -103};
    int f2316a;
    byte f2317b;
    byte f2318c;
    int f2319d;
    int f2320e;
    byte[] f2321f;
    byte[] f2322g;
    int f2323h;
    int f2324i;
    int f2325j;
    byte[] f2326k;
    byte f2327l;
    byte f2328m;
    int f2329n;
    int f2330o;
    TAPCASHTransaction f2331p;
    byte[] f2332q;
    byte f2333r;
    boolean f2334s;
    String f2335t;

    public TapCash(IsoDep isoDep) {
        this.mIsoDep = isoDep;
    }

    public boolean readCard() {
        try {
            byte[] transceive = this.mIsoDep.transceive(this.commandAPDUSelectDF_TapCash2);
            byte b = transceive[transceive.length - 1];
            transceive = m4100a((byte) 50, (byte) 3, (byte) 0, (byte) 0, null);
            int i = (((transceive[2] << 16) & 16711680) | ((transceive[3] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | (transceive[4] & 255);
            if ((transceive[2] & 128) != 0) {
                i |= ViewCompat.MEASURED_STATE_MASK;
            }
            this.mBalance = (long) i;
            this.f2321f = new byte[8];
            for (int i2 = 0; i2 < this.f2321f.length; i2++) {
                this.f2321f[i2] = transceive[i2 + 8];
            }
            this.mCardNumber = Util.getHexString(this.f2321f);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private byte[] m4100a(byte b, byte b2, byte b3, byte b4, byte[] bArr) {
        try {
            return this.mIsoDep.transceive(m4103b(b, b2, b3, b4, bArr));
        } catch (IOException unused) {
            return null;
        }
    }

    private byte[] m4103b(byte b, byte b2, byte b3, byte b4, byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(-112);
        byteArrayOutputStream.write(b);
        byteArrayOutputStream.write(b2);
        byteArrayOutputStream.write(b3);
        byteArrayOutputStream.write(b4);
        if (bArr != null) {
            try {
                byteArrayOutputStream.write(bArr);
            } catch (IOException unused) {
                return byteArrayOutputStream.toByteArray();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void explodeCard(int i, byte[] bArr) {
        int i2;
        int i3 = 0;
        if (bArr == null) {
            bArr = new byte[128];
            this.f2334s = false;
            this.f2335t = "";
        } else {
            this.f2334s = true;
            this.f2335t = "";
        }
        this.f2316a = i;
        this.f2317b = bArr[0];
        this.f2318c = bArr[1];
        int i4 = (((bArr[2] << 16) & 16711680) | ((bArr[3] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | (bArr[4] & 255);
        if ((bArr[2] & 128) != 0) {
            i4 |= ViewCompat.MEASURED_STATE_MASK;
        }
        this.f2319d = i4;
        i4 = (((bArr[5] << 16) & 16711680) | ((bArr[6] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | (bArr[7] & 255);
        if ((bArr[5] & 128) != 0) {
            i4 |= ViewCompat.MEASURED_STATE_MASK;
        }
        this.f2320e = i4;
        this.f2321f = new byte[8];
        for (i = 0; i < this.f2321f.length; i++) {
            this.f2321f[i] = bArr[i + 8];
        }
        this.f2322g = new byte[8];
        for (i = 0; i < this.f2322g.length; i++) {
            this.f2322g[i] = bArr[i + 16];
        }
        this.f2323h = ((((bArr[24] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((bArr[25] << 0) & 255)) * 86400) + 788947200;
        this.f2324i = 788947200 + (86400 * (((bArr[26] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((bArr[27] << 0) & 255)));
        this.f2325j = ((((bArr[28] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[29] << 16) & 16711680)) | ((bArr[30] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((bArr[31] << 0) & 255);
        this.f2326k = new byte[8];
        for (i2 = 0; i2 < 8; i2++) {
            this.f2326k[i2] = bArr[i2 + 32];
        }
        this.f2327l = bArr[40];
        this.f2328m = bArr[71];
        this.f2329n = bArr[41] & 255;
        this.f2330o = ((((bArr[42] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[43] << 16) & 16711680)) | ((bArr[44] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((bArr[45] << 0) & 255);
        byte[] bArr2 = new byte[16];
        for (i2 = 0; i2 < bArr2.length; i2++) {
            bArr2[i2] = bArr[i2 + 46];
        }
        this.f2331p = new TAPCASHTransaction(bArr2);
        this.f2332q = new byte[this.f2329n];
        while (i3 < this.f2332q.length) {
            this.f2332q[i3] = bArr[i3 + 62];
            i3++;
        }
        this.f2333r = bArr[this.f2329n + 62];
    }
}
