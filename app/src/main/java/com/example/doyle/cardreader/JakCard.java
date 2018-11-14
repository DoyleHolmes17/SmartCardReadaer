package com.example.doyle.cardreader;

import android.nfc.tech.MifareClassic;

public class JakCard extends MifareClassicCard {
    private static final String TAG = "CekSaldo";

    public JakCard(MifareClassic mifareClassic) {
        this.mMifareClassic = mifareClassic;
    }
}
