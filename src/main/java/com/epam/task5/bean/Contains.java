package com.epam.task5.bean;

import java.io.Serializable;

/**
 * Created by Evgeny on 20.02.2017.
 */
public class Contains implements Serializable {
    private String ingredient;
    private String number;
    private String cost;

    public Contains() {
    }

    @Override
    public boolean equals(Object oject) {
        if (this == oject) {
            return true;
        }
        if (oject == null || getClass() != oject.getClass()) {
            return false;
        }

        Contains contains = (Contains) oject;

        if (number != null ?
                !number.equals(contains.number) :
                contains.number != null) {
            return false;
        }
        if (cost != null ?
                !cost.equals(contains.cost) :
                contains.cost != null) {
            return false;
        }
        return ingredient != null ?
                ingredient.equals(contains.ingredient) :
                contains.ingredient == null;
    }

    @Override
    public int hashCode() {
        int result = ingredient != null ? ingredient.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contains{" +
                "ingredient='" + ingredient + '\'' +
                ", number='" + number + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getNumber() {
        return number;
    }

    public String getCost() {
        return cost;
    }
}
