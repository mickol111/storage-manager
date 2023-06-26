package com.model;

public class ClothSupply {
    protected int count;
    protected String size;
    protected Cloth cloth;

    public ClothSupply(Cloth cloth, String size, int count) {
        this.cloth = cloth;
        this.count = count;
        this.size = size;
    }

    public ClothSupply(String name, int id, String sex, int materialId, String color, String brand) {
        cloth = new Cloth(name, id, sex, materialId, color, brand);
        this.count = count;
        this.size = size;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
