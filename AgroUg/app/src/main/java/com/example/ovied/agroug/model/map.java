package com.example.ovied.agroug.model;

public class map {



    private Integer id;
    private Integer idCrop;
    private Float length;
    private Float latitude;
    private String description;


    public map() {
    }

    public map(Integer id, Integer idCrop, Float length, Float latitude, String description) {
        this.id = id;
        this.idCrop = idCrop;
        this.length = length;
        this.latitude = latitude;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdCrop() {
        return idCrop;
    }

    public Float getLength() {
        return length;
    }

    public Float getLatitude() {
        return latitude;
    }

    public String getDescription() {
        return description;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdCrop(Integer idCrop) {
        this.idCrop = idCrop;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
