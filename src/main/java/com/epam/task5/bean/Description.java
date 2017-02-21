package com.epam.task5.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Evgeny on 20.02.2017.
 */
public class Description implements Serializable {
    private String value;
    private String portion;
    private String cost;
    private List<Contains> containsList;

    public Description(String portion) {
        this.portion = portion;
    }

    public Description() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Description description = (Description) object;

        if (cost != null ?
                !cost.equals(description.cost) :
                description.cost != null) {
            return false;
        }
        if (value != null ?
                !value.equals(description.value) :
                description.value != null) {
            return false;
        }
        if (portion != null ?
                !portion.equals(description.portion) :
                description.portion != null) {
            return false;
        }
        return containsList != null ?
                containsList.equals(description.containsList) :
                description.containsList == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (portion != null ? portion.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (containsList != null ? containsList.hashCode() : 0);
        return result;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public List<Contains> getContainsList() {
        return containsList;
    }

    public void setContainsList(List<Contains> containsList) {
        this.containsList = containsList;
    }
}
