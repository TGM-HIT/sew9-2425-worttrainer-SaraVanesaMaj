package org.example.model;

public class WortEintrag {
    private String wort;
    private String url;

    public WortEintrag(String wort, String url) {
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
