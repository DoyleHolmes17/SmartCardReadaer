package com.example.doyle.cardreader;

import android.nfc.tech.IsoDep;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import org.simalliance.openmobileapi.util.ISO7816;

public class Brizzi {
    private static final byte[] APDU_B = new byte[]{(byte) -112, (byte) 90, (byte) 0, (byte) 0, (byte) 3, (byte) 2, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] APDU_C = new byte[]{(byte) -112, (byte) 90, (byte) 0, (byte) 0, (byte) 3, (byte) 3, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] APDU_D = new byte[]{(byte) -112, (byte) -67, (byte) 0, (byte) 0, (byte) 7, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 23, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] APDU_E = new byte[]{(byte) -112, (byte) -67, (byte) 0, (byte) 0, (byte) 7, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 32, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] APDU_H = new byte[]{(byte) -112, (byte) 108, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 0};
    private static final byte[] APDU_I = new byte[]{(byte) -112, (byte) -67, (byte) 0, (byte) 0, (byte) 7, (byte) 3, (byte) 0, (byte) 0, (byte) 0, (byte) 7, (byte) 0, (byte) 0, (byte) 0};
    public static final byte[] BrizziAPDUSelectDF = new byte[]{(byte) -112, (byte) 90, (byte) 0, (byte) 0, (byte) 3, (byte) 1, (byte) 0, (byte) 0, (byte) 0};
    private static final String TAG = "CekSaldo";
    private static final byte[] f = new byte[]{(byte) -112, (byte) 10, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 0};
    private static final byte[] g = new byte[]{(byte) -112, (byte) 10, (byte) 0, (byte) 0, (byte) 1, (byte) 1, (byte) 0};
    private static final byte[] j = new byte[]{(byte) -112, (byte) -69, (byte) 0, (byte) 0, (byte) 7, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    private String bp;
    private ku l;
    private String mCardNumber;
    private String mCardStatus;
    private IsoDep mIsoDep;
    private String mIssueBranch;
    private String mIssueDate;
    private String mTagId;
    private String r;
    private String s;
    private String u;
    private String v = "";
    private Integer w;

    public Brizzi(IsoDep isoDep) {
        this.mIsoDep = isoDep;
        this.mTagId = Util.getHexString(isoDep.getTag().getId());
        this.l = new ku();
    }

    public static byte[] hexStringToByteArray(String str) {
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

    public Boolean a() {
        IOException e;
        Boolean valueOf = Boolean.valueOf(false);
        Boolean valueOf2;
        try {
            String hexString = Util.getHexString(this.mIsoDep.transceive(APDU_D));
            if (hexString.substring(hexString.length() - 4, hexString.length()).equalsIgnoreCase("9100")) {
                valueOf2 = Boolean.valueOf(true);
                try {
                    this.mCardNumber = hexString.substring(6, 22);
                    this.mIssueDate = hexString.substring(22, 28);
                    this.mIssueBranch = hexString.substring(34, 38);
                    this.mCardStatus = Util.getHexString(this.mIsoDep.transceive(APDU_E)).substring(6, 10);
                    valueOf = valueOf2;
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    return valueOf2;
                }
            }
            return valueOf;
        } catch (IOException e3) {
            valueOf2 = valueOf;
            e = e3;
            e.printStackTrace();
            return valueOf2;
        }
    }

    public Boolean readCard() {
        IOException e;
        Boolean valueOf = Boolean.valueOf(false);
        Boolean valueOf2;
        try {
            byte[] transceive = this.mIsoDep.transceive(APDU_D);
            String hexString = Util.getHexString(transceive);
            if (Util.validApduResponse(transceive)) {
                valueOf2 = Boolean.valueOf(true);
                try {
                    this.mCardNumber = hexString.substring(6, 22);
                    this.mIssueDate = hexString.substring(22, 28);
                    this.mIssueBranch = hexString.substring(34, 38);
                    this.mCardStatus = Util.getHexString(this.mIsoDep.transceive(APDU_E)).substring(6, 10);
                    valueOf = valueOf2;
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    return valueOf2;
                }
            }
            return valueOf;
        } catch (IOException e3) {
            IOException iOException = e3;
            valueOf2 = valueOf;
            e = iOException;
            e.printStackTrace();
            return valueOf2;
        }
    }

    public void setTagId(byte[] bArr) {
        this.mTagId = Util.getHexString(bArr);
    }

    public Boolean b() {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            Boolean valueOf2;
            this.mIsoDep.transceive(APDU_C);
            String hexString = Util.getHexString(this.mIsoDep.transceive(f));
            hexString.substring(hexString.length() - 4, hexString.length());
            if (hexString.substring(hexString.length() - 4, hexString.length()).equalsIgnoreCase("91AF")) {
                this.s = hexString.substring(0, hexString.length() - 4);
                ku kuVar = this.l;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.mCardNumber);
                stringBuilder.append(this.mTagId);
                stringBuilder.append("FF");
                kuVar.a(stringBuilder.toString(), "0000030080000000", this.s);
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("90AF000010");
                stringBuilder2.append(this.l.a().substring(0, 32));
                stringBuilder2.append("00");
                hexStringToByteArray(stringBuilder2.toString());
                kuVar = this.l;
                IsoDep isoDep = this.mIsoDep;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("90AF000010");
                stringBuilder3.append(this.l.a().substring(0, 32));
                stringBuilder3.append("00");
                String a = kuVar.a(isoDep.transceive(hexStringToByteArray(stringBuilder3.toString())));
                if (a.substring(a.length() - 4, a.length()).equalsIgnoreCase("9100")) {
                    valueOf2 = Boolean.valueOf(true);
                    return valueOf2;
                }
            }
            valueOf2 = valueOf;
            return valueOf2;
        } catch (IOException e) {
            e.printStackTrace();
            return valueOf;
        } catch (Exception unused) {
            return valueOf;
        }
    }

    public String b(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i += 2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(str.substring((str.length() - i) - 2, str.length() - i));
            str2 = stringBuilder.toString();
        }
        return str2.toUpperCase();
    }

    public String getCardNumber() {
        return this.mCardNumber;
    }

    public String d() {
        return this.mCardStatus.equals("6161") ? g().booleanValue() ? "PASSIVE" : "ACTIVE" : (this.mCardStatus.equals("636C") || this.mCardStatus.equals("434C")) ? "CLOSED" : "";
    }

    public String e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mIssueDate.substring(0, 2));
        stringBuilder.append("/");
        stringBuilder.append(this.mIssueDate.substring(2, 4));
        stringBuilder.append("/");
        stringBuilder.append(this.mIssueDate.substring(4, 6));
        return stringBuilder.toString();
    }

    public String f() {
        return this.mIssueBranch;
    }

    public Boolean g() {
        Date date = new Date();
        try {
            Date parse = new SimpleDateFormat("yyMMdd").parse(this.bp);
            Boolean valueOf = Boolean.valueOf(true);
            if ((date.getTime() - parse.getTime()) / 86400000 < 365) {
                valueOf = Boolean.valueOf(false);
            }
            if (this.bp.equals("000000")) {
                valueOf = Boolean.valueOf(false);
            }
            return valueOf;
        } catch (ParseException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    public String h() {
        try {
            this.bp = Util.getHexString(this.mIsoDep.transceive(APDU_I)).substring(0, 6);
            String hexString = Util.getHexString(this.mIsoDep.transceive(APDU_H));
            this.u = hexString.substring(0, 6);
            this.w = Integer.valueOf(Integer.parseInt(b(hexString.substring(0, 8)), 16));
            this.r = String.format("%,.2f", new Object[]{Double.valueOf((double) this.w.intValue())});
            return this.r;
        } catch (IOException unused) {
            return null;
        }
    }

    public long getBalance() {
        try {
            this.bp = Util.getHexString(this.mIsoDep.transceive(APDU_I)).substring(0, 6);
            String hexString = Util.getHexString(this.mIsoDep.transceive(APDU_H));
            this.u = hexString.substring(0, 6);
            this.w = Integer.valueOf(Integer.parseInt(b(hexString.substring(0, 8)), 16));
            return Long.valueOf(Long.parseLong(b(hexString.substring(0, 8)), 16)).longValue();
        } catch (IOException unused) {
            return 0;
        }
    }
}
