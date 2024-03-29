package com.ds.starter.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ds
 */
@ConfigurationProperties(prefix = "ds.swagger")
public class SwaggerProperties {

    /**
     * 是否开启swagger
     */
    private boolean enable = true;

    private String title = "";

    private String description = "";

    private String host = "localhost";

    private String version = "1.0.0";

    private String basePackage = "";

    private String author = "";

    private String url = "";

    private String email = "";

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
