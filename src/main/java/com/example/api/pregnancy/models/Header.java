package com.example.api.pregnancy.models;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
//@TypeAlias("headers")
public class Header extends MyAuditModel {
    private String title;
    private List<String> description;
    private byte videosCount;
    private byte pdfCount;
    private String imagePath;
    private String gradientColor[];

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public byte getVideosCount() {
        return videosCount;
    }

    public void setVideosCount(byte videosCount) {
        this.videosCount = videosCount;
    }

    public byte getPdfCount() {
        return pdfCount;
    }

    public void setPdfCount(byte pdfCount) {
        this.pdfCount = pdfCount;
    }

    public String[] getGradientColor() {
        return gradientColor;
    }

    public void setGradientColor(String[] gradientColor) {
        this.gradientColor = gradientColor;
    }
}
