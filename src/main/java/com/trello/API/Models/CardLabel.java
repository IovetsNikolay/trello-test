package com.trello.API.Models;

import java.util.Objects;

public class CardLabel {

    public String name;
    public String id;
    public String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardLabel cardLabel = (CardLabel) o;
        return Objects.equals(id, cardLabel.id) &&
                Objects.equals(color, cardLabel.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color);
    }

    @Override
    public String toString() {
        return "CardLabel{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
