package com.example.ovied.agroug.model;

public class affection
{
    private Integer id;
    private String name;
    private String description;
    private String nameScientific;
    private String routeImage;
    private String navegation;

    public affection() {
    }

    public affection(Integer id, String name, String nameScientific, String routeImage, String description,String navegation) {
        this.id = id;
        this.name = name;
        this.nameScientific = nameScientific;
        this.routeImage = routeImage;
        this.description= description;
        this.navegation= navegation;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameScientific() {
        return nameScientific;
    }

    public String getRouteImage() {
        return routeImage;
    }

    public String getDescription() {
        return description;
    }

    public String getNavegation() {
        return navegation;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameScientific(String nameScientific) {
        this.nameScientific = nameScientific;
    }

    public void setRouteImage(String routeImage) {
        this.routeImage = routeImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  void setNavegation(String navegation) {
        this.navegation = navegation;
    }
}
