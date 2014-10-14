package com.exam.giftfriend.sqlite.models;

public class Friend {

    private int id;
    private String name;

    public Friend() {
    }

    public Friend(String name) {
        this.setName(name);
    }

    public Friend(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}

