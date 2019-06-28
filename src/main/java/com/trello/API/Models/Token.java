package com.trello.API.Models;

public class Token {

    public String id;
    public String idMember;

    @Override
    public String toString() {
        return "Token{" +
                "id='" + id + '\'' +
                ", idMember='" + idMember + '\'' +
                '}';
    }
}
