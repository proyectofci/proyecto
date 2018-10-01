package com.example.ovied.agroug.model;

public class treatment
{
    private Integer id;
    private String name;
    private String phytosanitaryAction;
    private String applicationDose;
    private String applicationMode;
    private String amountDose;

    public treatment() {
    }

    public treatment(Integer id, String name, String phytosanitaryAction, String applicationDose, String applicationMode, String amountDose) {
        this.id = id;
        this.name = name;
        this.phytosanitaryAction = phytosanitaryAction;
        this.applicationDose = applicationDose;
        this.applicationMode = applicationMode;
        this.amountDose = amountDose;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhytosanitaryAction() {
        return phytosanitaryAction;
    }

    public String getApplicationDose() {
        return applicationDose;
    }

    public String getApplicationMode() {
        return applicationMode;
    }

    public String getAmountDose() {
        return amountDose;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhytosanitaryAction(String phytosanitaryAction) {
        this.phytosanitaryAction = phytosanitaryAction;
    }

    public void setApplicationDose(String applicationDose) {
        this.applicationDose = applicationDose;
    }

    public void setApplicationMode(String applicationMode) {
        this.applicationMode = applicationMode;
    }

    public void setAmountDose(String amountDose) {
        this.amountDose = amountDose;
    }
}
