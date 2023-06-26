package com.model;

public class TShirt extends StandardSizeCloth {
    public TShirt(String name, int id, String sex, int materialId, String color, String brand) {
        super(name, id, sex, materialId, color, brand);
        type = "t-shirt";
    }
}
