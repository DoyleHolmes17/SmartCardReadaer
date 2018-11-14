package com.example.doyle.cardreader.tapcashgo.card.tapcash;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
//import com.google.android.gms.measurement.AppMeasurement.Param;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TAPCASHTransaction implements Parcelable {
    public static final Creator<TAPCASHTransaction> CREATOR = new C07651();
    private final byte f2351a;
    private final int f2352b;
    private final int f2353c;
    private final String f2354d;

    static class C07651 implements Creator<TAPCASHTransaction> {
        C07651() {
        }

        public TAPCASHTransaction createFromParcel(Parcel parcel) {
            return m4093a(parcel);
        }

        public TAPCASHTransaction[] newArray(int i) {
            return m4094a(i);
        }

        public TAPCASHTransaction m4093a(Parcel parcel) {
            return new TAPCASHTransaction(parcel.readByte(), parcel.readInt(), parcel.readInt(), parcel.readString());
        }

        public TAPCASHTransaction[] m4094a(int i) {
            return new TAPCASHTransaction[i];
        }
    }

    public enum C0766a {
        PURCHASE,
        BLACKLIST,
        ATM_TOP_UP,
        CASH_TOP_UP,
        STATEMENT_FEE,
        CARD_UPDATE,
        GRACE_PERIODE_FEE,
        DISABLE_ATU,
        DISABLE_PURSE,
        ATM_REFUND,
        CASH_REFUND,
        REFUND_DISABLE_PURSE,
        OTHERS,
        UNKNOWN
    }

    public int describeContents() {
        return 0;
    }

    public TAPCASHTransaction(byte[] bArr) {
        this.f2351a = bArr[0];
        int i = (((bArr[1] << 16) & 16711680) | ((bArr[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | (bArr[3] & 255);
        if ((bArr[1] & 128) != 0) {
            i |= ViewCompat.MEASURED_STATE_MASK;
        }
        this.f2352b = i;
        this.f2353c = ((((((bArr[4] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[5] << 16) & 16711680)) | ((bArr[6] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | (bArr[7] & 255)) + 788950800) - 57600;
        byte[] bArr2 = new byte[9];
        for (i = 0; i < 8; i++) {
            bArr2[i] = bArr[i + 8];
        }
        bArr2[8] = (byte) 0;
        this.f2354d = new String(bArr2);
    }

    public TAPCASHTransaction(byte b, int i, int i2, String str) {
        this.f2351a = b;
        this.f2352b = i;
        this.f2353c = i2;
        this.f2354d = str;
    }

    public C0766a m4096a() {
        if (this.f2351a == (byte) 1) {
            return C0766a.PURCHASE;
        }
        if (this.f2351a == (byte) 2) {
            return C0766a.BLACKLIST;
        }
        if (this.f2351a == (byte) 3) {
            return C0766a.ATM_TOP_UP;
        }
        if (this.f2351a == (byte) 4) {
            return C0766a.CASH_TOP_UP;
        }
        if (this.f2351a == (byte) 5) {
            return C0766a.STATEMENT_FEE;
        }
        if (this.f2351a == (byte) 6) {
            return C0766a.CARD_UPDATE;
        }
        if (this.f2351a == (byte) 7) {
            return C0766a.GRACE_PERIODE_FEE;
        }
        if (this.f2351a == (byte) 8) {
            return C0766a.DISABLE_ATU;
        }
        if (this.f2351a == (byte) 9) {
            return C0766a.DISABLE_PURSE;
        }
        if (this.f2351a == (byte) 10) {
            return C0766a.ATM_REFUND;
        }
        if (this.f2351a == (byte) 32) {
            return C0766a.CASH_REFUND;
        }
        if (this.f2351a == (byte) 34) {
            return C0766a.REFUND_DISABLE_PURSE;
        }
        if (this.f2351a == (byte) -16) {
            return C0766a.OTHERS;
        }
        return C0766a.UNKNOWN;
    }

    public int m4098b() {
        return this.f2352b;
    }

    public int m4099c() {
        return this.f2353c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f2351a);
        parcel.writeInt(this.f2352b);
        parcel.writeInt(this.f2353c);
        parcel.writeString(this.f2354d);
    }

    public static TAPCASHTransaction m4095a(Element element) {
        return new TAPCASHTransaction(Byte.parseByte("typeee"), Integer.parseInt(element.getAttribute("amount")), Integer.parseInt(element.getAttribute("date")), element.getAttribute("user-data"));
//        return new TAPCASHTransaction(Byte.parseByte(element.getAttribute(Param.TYPE)), Integer.parseInt(element.getAttribute("amount")), Integer.parseInt(element.getAttribute("date")), element.getAttribute("user-data"));
    }

    public Element m4097a(Document document) {
        Element createElement = document.createElement("transaction");
        createElement.setAttribute("typeeeee", Byte.toString(this.f2351a));
        createElement.setAttribute("amount", Integer.toString(this.f2352b));
        createElement.setAttribute("date", Integer.toString(this.f2353c));
        createElement.setAttribute("user-data", this.f2354d);
        return createElement;
    }
}
