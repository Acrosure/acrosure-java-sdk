package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class File {
    private String title;
    private String url;
    private String signedUrl;

    @JsonCreator
    public File(
            @JsonProperty("title") String title,
            @JsonProperty("url") String url,
            @JsonProperty("signed_url") String signedUrl) {
        this.title = title;
        this.url = url;
        this.signedUrl = signedUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSignedUrl() {
        return signedUrl;
    }

    @Override
    public String toString() {
        return "File{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", signedUrl='" + signedUrl + '\'' +
                '}';
    }
}
