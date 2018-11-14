package com.example.doyle.cardreader.tapcashgo.transit;

public class C0789a {
    private final String f2403a;
    private final String f2404b;

    public C0789a(String str, String str2) {
        this.f2403a = str;
        this.f2404b = str2;
    }

    public String m4187a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f2404b.substring(0, 4));
        stringBuilder.append(" ");
        stringBuilder.append(this.f2404b.substring(4, 8));
        stringBuilder.append(" ");
        stringBuilder.append(this.f2404b.substring(8, 12));
        stringBuilder.append(" ");
        stringBuilder.append(this.f2404b.substring(12, 16));
        return stringBuilder.toString();
    }
}
