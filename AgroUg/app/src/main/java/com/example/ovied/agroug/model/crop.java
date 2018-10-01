package com.example.ovied.agroug.model;

public class crop
{
    private Integer id;
    private String name;
    private String review;
    private String description;
    private String routeImage;
    private String nameScientific;


    public crop() {
    }

    public crop(Integer id,String name, String review, String description, String routeImage, String nameScientific) {
        this.id=id;
        this.name = name;
        this.review = review;
        this.description = description;
        this.routeImage = routeImage;
        this.nameScientific = nameScientific;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }

    public String getDescription() {
        return description;
    }

    public String getRouteImage() {
        return routeImage;
    }

    public String getNameScientific() {
        return nameScientific;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRouteImage(String routeImage) {
        this.routeImage = routeImage;
    }

    public void setNameScientific(String nameScientific) {
        this.nameScientific = nameScientific;
    }
}
