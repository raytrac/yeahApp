package com.example.catering;

public class Model {
    private String name;
    private String number;
    private boolean selected;

    public Model(String name, String number) {
        this.name = name;
        this.number = number;
        selected = false;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
