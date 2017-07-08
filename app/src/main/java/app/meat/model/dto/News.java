package app.meat.model.dto;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;


public class News implements Serializable {
    @PropertyName("TITLE")
    private String title;
    @PropertyName("CATEGORY")
    private String category;
    @PropertyName("MESSAGE")
    private String message;


    public News() {
    }

    public News(String title, String category, String message) {
        this.title = title;
        this.category = category;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
