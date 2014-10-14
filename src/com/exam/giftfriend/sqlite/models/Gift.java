package com.exam.giftfriend.sqlite.models;

public class Gift {

    private int id;
    private String name;
    private int status;
    private String created;
    private String location;

    public Gift() {
    }

    public Gift(String name, int status) {
        this.setName(name);
        this.setStatus(status);
    }

    public Gift(int id, String name, int status, String location) {
        this.setId(id);
        this.setName(name);
        this.setStatus(status);
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreatedAt(String created){
        this.created = created;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getStatus() {
        return this.status;
    }
}
