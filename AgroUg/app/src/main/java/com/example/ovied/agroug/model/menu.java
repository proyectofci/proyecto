package com.example.ovied.agroug.model;

public class menu
{
    private Integer  id;
    private String name;

    public menu() {
    }

    public menu(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
