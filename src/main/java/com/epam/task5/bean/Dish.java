package com.epam.task5.bean;

import java.io.Serializable;

/**
 * Created by Yauheni_Tsitsenkou on 2/20/2017.
 */
public class Dish implements Serializable {
    private String id;
    private String photoUrl;
    private String title;
    private Description description;

    public Dish(String id, String photoUrl, String title, Description description) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.title = title;
        this.description = description;
    }

    public Dish() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Dish dish = (Dish) object;

        if (id != null ?
                !id.equals(dish.id) :
                dish.id != null) {
            return false;
        }
        if (photoUrl != null ?
                !photoUrl.equals(dish.photoUrl) :
                dish.photoUrl != null) {
            return false;
        }
        if (title != null ?
                !title.equals(dish.title) :
                dish.title != null) {
            return false;
        }
        return description != null ?
                description.equals(dish.description) :
                dish.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (photoUrl != null ? photoUrl.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", title='" + title + '\'' +
                ", description=" + description +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}
