package com.example.doyle.cardreader.tapcashgo.card.tapcash;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TAPCASHHistory implements Parcelable {
    public static final Creator<TAPCASHHistory> CREATOR = new C07631();
    private static TAPCASHTransaction[] f2311a = new TAPCASHTransaction[0];
    private int f2312b;
    private TAPCASHTransaction[] f2313c;
    private final boolean f2314d;
    private final String f2315e;

    static class C07631 implements Creator<TAPCASHHistory> {
        C07631() {
        }

        public TAPCASHHistory createFromParcel(Parcel parcel) {
            return m4069a(parcel);
        }

        public TAPCASHHistory[] newArray(int i) {
            return m4070a(i);
        }

        public TAPCASHHistory m4069a(Parcel parcel) {
            int readInt = parcel.readInt();
            if (parcel.readInt() != 1) {
                return new TAPCASHHistory(readInt, parcel.readString());
            }
            TAPCASHTransaction[] tAPCASHTransactionArr = new TAPCASHTransaction[parcel.readInt()];
            for (int i = 0; i < tAPCASHTransactionArr.length; i++) {
                tAPCASHTransactionArr[i] = (TAPCASHTransaction) parcel.readParcelable(TAPCASHTransaction.class.getClassLoader());
            }
            return new TAPCASHHistory(readInt, tAPCASHTransactionArr);
        }

        public TAPCASHHistory[] m4070a(int i) {
            return new TAPCASHHistory[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public TAPCASHHistory(int i, byte[] bArr) {
        this.f2312b = i;
        if (bArr != null) {
            this.f2314d = true;
            this.f2315e = "";
            this.f2313c = new TAPCASHTransaction[(bArr.length / 16)];
            for (int i2 = 0; i2 < bArr.length; i2 += 16) {
                byte[] bArr2 = new byte[16];
                for (int i3 = 0; i3 < bArr2.length; i3++) {
                    bArr2[i3] = bArr[i2 + i3];
                }
                this.f2313c[i2 / bArr2.length] = new TAPCASHTransaction(bArr2);
            }
            return;
        }
        this.f2314d = false;
        this.f2315e = "";
        this.f2313c = f2311a;
    }

    public TAPCASHHistory(int i, String str) {
        this.f2312b = i;
        this.f2315e = str;
        this.f2314d = false;
    }

    public TAPCASHHistory(int i, TAPCASHTransaction[] tAPCASHTransactionArr) {
        this.f2313c = tAPCASHTransactionArr;
        this.f2312b = i;
        this.f2314d = true;
        this.f2315e = "";
    }

    public TAPCASHTransaction[] m4073a() {
        return this.f2313c;
    }

    public boolean m4074b() {
        return this.f2314d;
    }

    public String m4075c() {
        return this.f2315e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2312b);
        int i2 = 0;
        if (this.f2314d) {
            parcel.writeInt(1);
            parcel.writeInt(this.f2313c.length);
            while (i2 < this.f2313c.length) {
                parcel.writeParcelable(this.f2313c[i2], i);
                i2++;
            }
            return;
        }
        parcel.writeInt(0);
        parcel.writeString(this.f2315e);
    }

    public static TAPCASHHistory m4071a(Element element) {
        int parseInt = Integer.parseInt(element.getAttribute("id"));
        if (element.getAttribute("valid").equals("false")) {
            return new TAPCASHHistory(parseInt, element.getAttribute("error"));
        }
        NodeList elementsByTagName = element.getElementsByTagName("transaction");
        TAPCASHTransaction[] tAPCASHTransactionArr = new TAPCASHTransaction[elementsByTagName.getLength()];
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            tAPCASHTransactionArr[i] = TAPCASHTransaction.m4095a((Element) elementsByTagName.item(i));
        }
        return new TAPCASHHistory(parseInt, tAPCASHTransactionArr);
    }

    public Element m4072a(Document document) {
        Element createElement = document.createElement("history");
        createElement.setAttribute("id", Integer.toString(this.f2312b));
        if (this.f2314d) {
            createElement.setAttribute("valid", "true");
            for (TAPCASHTransaction m4097a : this.f2313c) {
                createElement.appendChild(m4097a.m4097a(document));
            }
        } else {
            createElement.setAttribute("valid", "false");
            createElement.setAttribute("error", m4075c());
        }
        return createElement;
    }
}
