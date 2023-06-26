package com.model;

public class Underwear extends StandardSizeCloth {
    public Underwear(String name, int id, String sex, int materialId, String color, String brand) {
        super(name, id, sex, materialId, color, brand);
        type = "underwear";
    }
}
