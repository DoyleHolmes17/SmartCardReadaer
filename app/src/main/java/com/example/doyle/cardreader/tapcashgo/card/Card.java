package com.example.doyle.cardreader.tapcashgo.card;

import android.nfc.Tag;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import tstudio.card.lib.bni.tapcashgo.transit.C0789a;

public abstract class Card implements Parcelable {
    private byte[] f2307a;
    private Date f2308b;

    public enum C0761a {
        TAPCASH(0);
        
        private int f2306b;

        private C0761a(int i) {
            this.f2306b = i;
        }

        public int m4050a() {
            return this.f2306b;
        }

        public String toString() {
            return this.f2306b != 0 ? "Unknown" : "TAPCASH";
        }
    }

    public static Card m4051a(String str) {
        return null;
    }

    public static Card m4052a(byte[] bArr, Tag tag, boolean z, String str) {
        return null;
    }

    public final int describeContents() {
        return 0;
    }

    public abstract C0761a mo712a();

//    public abstract C0789a mo713d();

    protected Card(byte[] bArr, Date date) {
        this.f2307a = bArr;
        this.f2308b = date;
    }

    public byte[] m4054b() {
        return this.f2307a;
    }

    public Date m4055c() {
        return this.f2308b;
    }

//    public Element mo715f() {
//        try {
//            Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//            newDocument.appendChild(newDocument.createElement("card"));
//            return newDocument.getDocumentElement();
//        } catch (Throwable th) {
//            RuntimeException runtimeException = new RuntimeException(th);
//        }
//    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2307a.length);
        parcel.writeByteArray(this.f2307a);
        parcel.writeLong(this.f2308b.getTime());
    }
}
