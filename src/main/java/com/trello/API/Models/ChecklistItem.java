package com.trello.API.Models;

public class ChecklistItem {
    public String name;
    public String id;

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
