package com.trello.API.Models;

public class CheckList {
    public CheckList(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public CheckList() {}

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
