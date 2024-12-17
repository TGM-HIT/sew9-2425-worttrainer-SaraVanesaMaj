package org.example.model;

public class WortEintrag {
    private String wort;
    private String url;

    public WortEintrag(String wort, String url) {
        if (wort == null || url == null || !url.startsWith("http")) {
            throw new IllegalArgumentException("Ungültiges Wort oder URL");
        }
        this.wort = wort;
        this.url = url;
    }

    public String getWort() {
        return wort;
    }

    public String getUrl() {
        return url;
    }
}