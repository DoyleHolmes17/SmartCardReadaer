package com.example.doyle.cardreader;

import android.nfc.Tag;

public class EMoneyCard {
    protected static final String TAG = "CekSaldo";
    protected long mBalance;
    protected String mCardNumber;
    protected Tag mTag;
    protected String mTagId;

    public boolean readCard() {
        return false;
    }

    public String getCardNumber() {
        return this.mCardNumber;
    }

    public long getCardBalance() {
        return this.mBalance;
    }
}
