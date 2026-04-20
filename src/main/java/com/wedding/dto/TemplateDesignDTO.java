package com.wedding.dto;

import java.util.List;

public class TemplateDesignDTO {

    private String templateId;
    private String backgroundUrl;
    private List<TextElement> texts;
    private List<ImageElement> images;

    public static class TextElement {
        private String content;
        private int x;
        private int y;
        private String font;
        private String color;
        private int size;

        // Getters and setters
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public int getX() { return x; }
        public void setX(int x) { this.x = x; }
        public int getY() { return y; }
        public void setY(int y) { this.y = y; }
        public String getFont() { return font; }
        public void setFont(String font) { this.font = font; }
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
        public int getSize() { return size; }
        public void setSize(int size) { this.size = size; }
    }

    public static class ImageElement {
        private String url;
        private int x;
        private int y;
        private int width;
        private int height;

        // Getters and setters
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public int getX() { return x; }
        public void setX(int x) { this.x = x; }
        public int getY() { return y; }
        public void setY(int y) { this.y = y; }
        public int getWidth() { return width; }
        public void setWidth(int width) { this.width = width; }
        public int getHeight() { return height; }
        public void setHeight(int height) { this.height = height; }
    }

    // Getters and setters
    public String getTemplateId() { return templateId; }
    public void setTemplateId(String templateId) { this.templateId = templateId; }
    public String getBackgroundUrl() { return backgroundUrl; }
    public void setBackgroundUrl(String backgroundUrl) { this.backgroundUrl = backgroundUrl; }
    public List<TextElement> getTexts() { return texts; }
    public void setTexts(List<TextElement> texts) { this.texts = texts; }
    public List<ImageElement> getImages() { return images; }
    public void setImages(List<ImageElement> images) { this.images = images; }
}
