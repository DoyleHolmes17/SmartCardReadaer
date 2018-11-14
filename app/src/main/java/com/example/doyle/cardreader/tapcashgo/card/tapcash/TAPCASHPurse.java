package com.example.doyle.cardreader.tapcashgo.card.tapcash;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import tstudio.card.lib.bni.tapcashgo.C0783m;

public class TAPCASHPurse implements Parcelable {
    public static final Creator<TAPCASHPurse> CREATOR = new C07641();
    private final int f2316a;
    private final byte f2317b;
    private final byte f2318c;
    private final int f2319d;
    private final int f2320e;
    private final byte[] f2321f;
    private final byte[] f2322g;
    private final int f2323h;
    private final int f2324i;
    private final int f2325j;
    private final byte[] f2326k;
    private final byte f2327l;
    private final byte f2328m;
    private final int f2329n;
    private final int f2330o;
    private final TAPCASHTransaction f2331p;
    private final byte[] f2332q;
    private final byte f2333r;
    private final boolean f2334s;
    private final String f2335t;

    static class C07641 implements Creator<TAPCASHPurse> {
        C07641() {
        }

        public TAPCASHPurse createFromParcel(Parcel parcel) {
            return m4076a(parcel);
        }

        public TAPCASHPurse[] newArray(int i) {
            return m4077a(i);
        }

        public TAPCASHPurse m4076a(Parcel parcel) {
            Parcel parcel2 = parcel;
            int readInt = parcel.readInt();
            if (parcel.readInt() == 0) {
                return new TAPCASHPurse(readInt, parcel.readString());
            }
            byte readByte = parcel.readByte();
            byte readByte2 = parcel.readByte();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            byte[] bArr = new byte[parcel.readInt()];
            parcel2.readByteArray(bArr);
            byte[] bArr2 = new byte[parcel.readInt()];
            parcel2.readByteArray(bArr2);
            int readInt4 = parcel.readInt();
            int readInt5 = parcel.readInt();
            int readInt6 = parcel.readInt();
            byte[] bArr3 = new byte[parcel.readInt()];
            parcel2.readByteArray(bArr3);
            byte readByte3 = parcel.readByte();
            byte readByte4 = parcel.readByte();
            int readInt7 = parcel.readInt();
            int readInt8 = parcel.readInt();
            TAPCASHTransaction tAPCASHTransaction = (TAPCASHTransaction) parcel2.readParcelable(TAPCASHTransaction.class.getClassLoader());
            byte[] bArr4 = new byte[parcel.readInt()];
            parcel2.readByteArray(bArr4);
            byte[] bArr5 = bArr4;
            return new TAPCASHPurse(readInt, readByte, readByte2, readInt2, readInt3, bArr, bArr2, readInt4, readInt5, readInt6, bArr3, readByte3, readByte4, readInt7, readInt8, tAPCASHTransaction, bArr5, parcel.readByte());
        }

        public TAPCASHPurse[] m4077a(int i) {
            return new TAPCASHPurse[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public TAPCASHPurse(int i, byte b, byte b2, int i2, int i3, byte[] bArr, byte[] bArr2, int i4, int i5, int i6, byte[] bArr3, byte b3, byte b4, int i7, int i8, TAPCASHTransaction tAPCASHTransaction, byte[] bArr4, byte b5) {
        this.f2316a = i;
        this.f2317b = b;
        this.f2318c = b2;
        this.f2319d = i2;
        this.f2320e = i3;
        this.f2321f = bArr;
        this.f2322g = bArr2;
        this.f2323h = i4;
        this.f2324i = i5;
        this.f2325j = i6;
        this.f2326k = bArr3;
        this.f2327l = b3;
        this.f2328m = b4;
        this.f2329n = i7;
        this.f2330o = i8;
        this.f2331p = tAPCASHTransaction;
        this.f2332q = bArr4;
        this.f2333r = b5;
        this.f2334s = true;
        this.f2335t = "";
    }

    public TAPCASHPurse(int i, String str) {
        this.f2316a = i;
        this.f2317b = (byte) 0;
        this.f2318c = (byte) 0;
        this.f2319d = 0;
        this.f2320e = 0;
        this.f2321f = null;
        this.f2322g = null;
        this.f2323h = 0;
        this.f2324i = 0;
        this.f2325j = 0;
        this.f2326k = null;
        this.f2327l = (byte) 0;
        this.f2328m = (byte) 0;
        this.f2329n = 0;
        this.f2330o = 0;
        this.f2331p = null;
        this.f2332q = null;
        this.f2333r = (byte) 0;
        this.f2334s = false;
        this.f2335t = str;
    }

    public TAPCASHPurse(int i, byte[] bArr) {
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

    public int m4079a() {
        return this.f2316a;
    }

    public byte m4081b() {
        return this.f2318c;
    }

    public int m4082c() {
        return this.f2319d;
    }

    public int m4083d() {
        return this.f2320e;
    }

    public byte[] m4084e() {
        return this.f2321f;
    }

    public byte[] m4085f() {
        return this.f2322g;
    }

    public int m4086g() {
        return this.f2323h;
    }

    public int m4087h() {
        return this.f2324i;
    }

    public byte m4088i() {
        return this.f2327l;
    }

    public byte m4089j() {
        return this.f2328m;
    }

    public int m4090k() {
        return this.f2330o;
    }

    public boolean m4091l() {
        return this.f2334s;
    }

    public String m4092m() {
        return this.f2335t;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2316a);
        if (this.f2334s) {
            parcel.writeInt(1);
            parcel.writeByte(this.f2317b);
            parcel.writeByte(this.f2318c);
            parcel.writeInt(this.f2319d);
            parcel.writeInt(this.f2320e);
            parcel.writeInt(this.f2321f.length);
            parcel.writeByteArray(this.f2321f);
            parcel.writeInt(this.f2322g.length);
            parcel.writeByteArray(this.f2322g);
            parcel.writeInt(this.f2323h);
            parcel.writeInt(this.f2324i);
            parcel.writeInt(this.f2325j);
            parcel.writeInt(this.f2326k.length);
            parcel.writeByteArray(this.f2326k);
            parcel.writeByte(this.f2327l);
            parcel.writeByte(this.f2328m);
            parcel.writeInt(this.f2329n);
            parcel.writeInt(this.f2330o);
            parcel.writeParcelable(this.f2331p, i);
            parcel.writeInt(this.f2332q.length);
            parcel.writeByteArray(this.f2332q);
            parcel.writeByte(this.f2333r);
            return;
        }
        parcel.writeInt(0);
        parcel.writeString(this.f2335t);
    }

//    public static TAPCASHPurse m4078a(Element element) {
//        Element element2 = element;
//        int parseInt = Integer.parseInt(element2.getAttribute("id"));
//        if (element2.getAttribute("valid").equals("false")) {
//            return new TAPCASHPurse(parseInt, element2.getAttribute("error"));
//        }
//        return new TAPCASHPurse(parseInt, Byte.parseByte(element2.getAttribute("cepas-version")), Byte.parseByte(element2.getAttribute("purse-status")), Integer.parseInt(element2.getAttribute("purse-balance")), Integer.parseInt(element2.getAttribute("auto-load-amount")), C0783m.m4144a(element2.getAttribute("can")), C0783m.m4144a(element2.getAttribute("csn")), Integer.parseInt(element2.getAttribute("purse-expiry-date")), Integer.parseInt(element2.getAttribute("purse-creation-date")), Integer.parseInt(element2.getAttribute("last-credit-transaction-trp")), C0783m.m4144a(element2.getAttribute("last-credit-transaction-header")), Byte.parseByte(element2.getAttribute("logfile-record-count")), Byte.parseByte(element2.getAttribute("key-set")), Integer.parseInt(element2.getAttribute("issuer-data-length")), Integer.parseInt(element2.getAttribute("last-transaction-trp")), TAPCASHTransaction.m4095a((Element) element2.getElementsByTagName("transaction").item(0)), C0783m.m4144a(element2.getAttribute("issuer-specific-data")), Byte.parseByte(element2.getAttribute("last-transaction-debit-options")));
//    }

    public Element m4080a(Document document) {
        Element createElement = document.createElement("purse");
        if (this.f2334s) {
            createElement.setAttribute("valid", "true");
            createElement.setAttribute("id", Integer.toString(this.f2316a));
            createElement.setAttribute("cepas-version", Byte.toString(this.f2317b));
            createElement.setAttribute("purse-status", Byte.toString(this.f2318c));
            createElement.setAttribute("purse-balance", Integer.toString(this.f2319d));
            createElement.setAttribute("auto-load-amount", Integer.toString(this.f2320e));
//            createElement.setAttribute("can", C0783m.m4140a(this.f2321f));
//            createElement.setAttribute("csn", C0783m.m4140a(this.f2322g));
            createElement.setAttribute("purse-creation-date", Integer.toString(this.f2324i));
            createElement.setAttribute("purse-expiry-date", Integer.toString(this.f2323h));
            createElement.setAttribute("last-credit-transaction-trp", Integer.toString(this.f2325j));
//            createElement.setAttribute("last-credit-transaction-header", C0783m.m4140a(this.f2326k));
            createElement.setAttribute("logfile-record-count", Byte.toString(this.f2327l));
            createElement.setAttribute("key-set", Byte.toString(this.f2328m));
            createElement.setAttribute("issuer-data-length", Integer.toString(this.f2329n));
            createElement.setAttribute("last-transaction-trp", Integer.toString(this.f2330o));
//            createElement.setAttribute("issuer-specific-data", C0783m.m4140a(this.f2332q));
            createElement.setAttribute("last-transaction-debit-options", Byte.toString(this.f2333r));
            createElement.appendChild(this.f2331p.m4097a(document));
        } else {
            createElement.setAttribute("id", Integer.toString(this.f2316a));
            createElement.setAttribute("valid", "false");
            createElement.setAttribute("error", m4092m());
        }
        return createElement;
    }
}
