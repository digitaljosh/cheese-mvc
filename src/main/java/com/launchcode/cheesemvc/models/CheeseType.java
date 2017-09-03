package com.launchcode.cheesemvc.models;



public enum CheeseType {

    // three different values that CheeseType can have
    HARD ("Hard"),
    SOFT ("Soft"),
    FAKE ("Fake");

    private final String name;

    // constructor
    CheeseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
