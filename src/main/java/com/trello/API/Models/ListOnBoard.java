package com.trello.API.Models;

public class ListOnBoard {

    public String id;
    public String name;
    public Boolean closed;

    @Override
    public String toString() {
        return "ListOnBoard{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", closed=" + closed +
                '}';
    }
}
