package com.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Josh Markus
 */

public class Cheese {

    @NotNull // specifies this field not be null when validating
    @Size(min=3, max=15) // controls name size when validating
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;


    private CheeseType type;

    private int cheeseId;
    public static int nextId = 1;


    public Cheese(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public Cheese() {
        cheeseId = nextId;
        // nextId++;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CheeseType getType() { return type; }

    public void setType(CheeseType type) { this.type = type; }

}
