package com.example.doyle.cardreader;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.cardemulation.CardEmulation;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MainActivity extends AppCompatActivity {

    private TextView saldokartu, jeniskartu;
    //    private NfcBroacastReceiver mNfcBroadNfcBroacastReceiver;
    private String[][] techList = null;
    private CardEmulation cardEmulation;
    private NfcAdapter mNfcAdapter;
    private DecimalFormat decimalFormat;
    private DecimalFormatSymbols decimalFormatSymbols;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private String p1 = Manifest.permission.WRITE_EXTERNAL_STORAGE, p2 = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int REQUEST_WRITE_EXTERNAL = 0;
    private static final int REQUEST_READ_EXTERNAAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saldokartu = (TextView) findViewById(R.id.saldokartu);
        jeniskartu = (TextView) findViewById(R.id.jeniskartu);

        decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("Rp ");
        decimalFormatSymbols.setMonetaryDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        String[][] strArr = new String[1][];
        strArr[0] = new String[]{IsoDep.class.getName(), NfcA.class.getName()};
        techList = strArr;
    }

    private String[] getTagInfo(Tag tag) {
//        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String prefix = "android.nfc.tech.";
        String[] info = new String[2];

        // UID
        byte[] uid = tag.getId();
        info[0] = "UID In Hex: " + Util.getHexString(uid) + "\n";
//        info[0] = "UID In Hex: " + Utils.convertByteArrayToHexString(uid) + "\n" +
//                "UID In Dec: " + Utils.convertByteArrayToDecimal(uid) + "\n\n";

        // Tech List
        String[] techList = tag.getTechList();
        String techListConcat = "Technologies: ";
        for(int i = 0; i < techList.length; i++) {
            techListConcat += techList[i].substring(prefix.length()) + ",";
        }
        info[0] += techListConcat.substring(0, techListConcat.length() - 1) + "\n\n";

        // Mifare Classic/UltraLight Info
        info[0] += "Card Type: ";
        String type = "Unknown";
        for(int i = 0; i < techList.length; i++) {
            if(techList[i].equals(MifareClassic.class.getName())) {
                info[1] = "Mifare Classic";
                MifareClassic mifareClassicTag = MifareClassic.get(tag);

                // Type Info
                switch (mifareClassicTag.getType()) {
                    case MifareClassic.TYPE_CLASSIC:
                        type = "Classic";
                        break;
                    case MifareClassic.TYPE_PLUS:
                        type = "Plus";
                        break;
                    case MifareClassic.TYPE_PRO:
                        type = "Pro";
                        break;
                }
                info[0] += "Mifare " + type + "\n";

                // Size Info
                info[0] += "Size: " + mifareClassicTag.getSize() + " bytes \n" +
                        "Sector Count: " + mifareClassicTag.getSectorCount() + "\n" +
                        "Block Count: " + mifareClassicTag.getBlockCount() + "\n";
            } else if(techList[i].equals(MifareUltralight.class.getName())) {
                info[1] = "Mifare UltraLight";
                MifareUltralight mifareUlTag = MifareUltralight.get(tag);

                // Type Info
                switch (mifareUlTag.getType()) {
                    case MifareUltralight.TYPE_ULTRALIGHT:
                        type = "Ultralight";
                        break;
                    case MifareUltralight.TYPE_ULTRALIGHT_C:
                        type = "Ultralight C";
                        break;
                }
                info[0] += "Mifare " + type + "\n";
            } else if(techList[i].equals(IsoDep.class.getName())) {
                info[1] = "IsoDep";
                IsoDep isoDepTag = IsoDep.get(tag);
                info[0] += "IsoDep \n";
            } else if(techList[i].equals(Ndef.class.getName())) {
                Ndef ndefTag = Ndef.get(tag);
                info[0] += "Is Writable: " + ndefTag.isWritable() + "\n" +
                        "Can Make ReadOnly: " + ndefTag.canMakeReadOnly() + "\n";
            } else if(techList[i].equals(NdefFormatable.class.getName())) {
                NdefFormatable ndefFormatableTag = NdefFormatable.get(tag);
                info[0] += "NdefFormatable: " + ndefFormatableTag.toString() + "\n";
            }
        }
        jeniskartu.setText(info[0] + info[1]);
        return info;
    }

    protected void onPause() {
        super.onPause();
        getIntent().removeExtra("android.nfc.extra.TAG");
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
            mNfcAdapter.disableReaderMode(this);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        agg(intent);
    }

    private void agg(Intent intent) {
        String action = intent.getAction();
        if ("android.nfc.action.TAG_DISCOVERED".equals(action) || "android.nfc.action.TECH_DISCOVERED".equals(action)) {
            Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
            if (tag != null) {
                coba(tag);
                getTagInfo(tag);

            }
        }
    }

    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilterArr = new IntentFilter[0];
        if (mNfcAdapter == null) {
            return;
        }
        if (mNfcAdapter.isEnabled()) {
            mNfcAdapter.enableForegroundDispatch(this, activity, intentFilterArr, (String[][]) null);
        } else {
            Toast.makeText(this, "NFC tidak aktif. Silakan Aktifkan NFC.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mNfcBroadNfcBroacastReceiver);
    }

    protected void coba(Tag tag) {
        IsoDep isoDep = IsoDep.get(tag);
        byte[] response = null, response1 = null, response2 = null, response3 = null;
        if (isoDep != null) {
            try {
                Log.e("konek", "ok");
                isoDep.connect();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ora konek", e + "");
            }

            try {
                if (Util.validApduResponse(isoDep.transceive(Brizzi.BrizziAPDUSelectDF))) {
                    Brizzi brizzi = new Brizzi(isoDep);
                    Log.e("ini bri", "ok");
                    if (brizzi.readCard().booleanValue()) {
                        Log.e("read card", brizzi.readCard().booleanValue() + "");
                        if (brizzi.b().booleanValue()) {

                            saldokartu.setText("Nomor Kartu Brizzi : " + brizzi.getCardNumber() +
                                    "\nSaldo Kartu : " + decimalFormat.format(brizzi.getBalance()));
                        } else {
                            Log.e("gagal card dalam if", "ok");
                        }
                    } else {
                        Log.e("gagal card", "ok");
                    }
                } else if (Util.validApduResponse(isoDep.transceive(EMoney.EMoneyAPDUSelectDF))) {
                    EMoney eMoney = new EMoney(isoDep);
                    if (eMoney.readCard()) {
                        saldokartu.setText("Nomor Kartu e-Money : " + eMoney.getCardNumber() +
                                "\nSaldo Kartu : " + decimalFormat.format(eMoney.getCardBalance()));
                    } else {
                        Log.e("gagal card dalam if", "ok");
                    }
            } else if (Util.validApduResponse(isoDep.transceive(MegaCash.MegaCashAPDUSelectDF))) {
                MegaCash megaCash = new MegaCash(isoDep);
                if (megaCash.readCard()) {
                    saldokartu.setText("Nomor Kartu Mega Cash : " + megaCash.getCardNumber() +
                            "\nSaldo Kartu : " + decimalFormat.format(megaCash.getCardBalance()));
                } else {
                    Log.e("gagal card dalam if", "ok");
                }
            } else if (Util.validApduResponse(isoDep.transceive(TapCash.TapCashAPDUSelectDF))) {
                    TapCash tapCash = new TapCash(isoDep);
                if (tapCash.readCard()) {
                    saldokartu.setText("Nomor Kartu TapCash : " + tapCash.getCardNumber() +
                            "\nSaldo Kartu : " + decimalFormat.format(tapCash.getCardBalance()));
                } else {
                    Log.e("gagal card dalam if", "ok");
                }
//            } else if (Util.validApduResponse(response3)) {
//                File file = new File(MainLockActivity.this.getCacheDir(), "s.dex");
//                if (file.exists()) {
//                    file.delete();
//                }
//                AssetManager assets = MainLockActivity.this.getAssets();
//                InputStream open = null;
//                try {
//                    open = assets != null ? assets.open("s.txt") : null;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                OutputStream bufferedOutputStream = null;
//                try {
//                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                byte[] bArr = new byte[8192];
//                try {
//                    bArr = i.a(i.a(open));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.e("baaaaarrrrr", bArr.length + "");
//                try {
//                    bufferedOutputStream.write(bArr, 0, bArr.length);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    bufferedOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    open.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                MainLockActivity.this.getApplicationContext();
//                File dir = MainLockActivity.this.getDir("outdex", 0);
//                StringBuilder stringBuilder2 = new StringBuilder();
//                try {
//                    stringBuilder2.append(dir.getCanonicalPath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                stringBuilder2.append("/s.dex");
//                File file2 = new File(stringBuilder2.toString());
//                Log.e("file soko dir", file2 + "");
//                Log.e("string builder", stringBuilder2.toString());
//                if (file2.exists()) {
//                    Log.e("eksis", "ok");
//                    file2.delete();
//                }
//                a = new h().arr(new SClassLoader(new DexClassLoader(file.getAbsolutePath(), dir.getAbsolutePath(), null, MainLockActivity.this.getClassLoader())), new FlazzCardReader(isoDep), true);
//                Log.e("asdasasdasdasdd", file.getAbsolutePath() + "");
//                Log.e("asdasasdasdasdd123123", dir.getAbsolutePath() + "");
//                Log.e("asdasd", a.a() + "");
//                if (!a.a()) {
//                    if (isoDep.isConnected()) {
//                        try {
//                            isoDep.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } else {
//                    saldokartu.setText("Nomor Kartu Flazz : " + a.b() +
//                            "\nSaldo Kartu : " + decimalFormat.format(a.c()));
////                    this.mNfcCardReaderListener.onFlazzCardFound(a.b(), a.c());
//                }
                } else {
                    saldokartu.setText("Kartu belum disupport.\nSilahkan hubungi PT. Aino Indonesia");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ora konek1", e + "");
            }

            // TODO: send further APDU commands according to the protocol specification
            //response = isoDep.transceive(APDU);

            try {
                Log.e("konek2", "ok");
                isoDep.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("ora konek2", e + "");
            }
        }
    }
}
