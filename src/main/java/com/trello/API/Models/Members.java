package com.trello.API.Models;

import java.util.Objects;

public class Members {
    public String id;
    public String fullName;
    public String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Members members = (Members) o;
        return Objects.equals(fullName, members.fullName) &&
                Objects.equals(username, members.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, username);
    }

    @Override
    public String toString() {
        return "Members{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
