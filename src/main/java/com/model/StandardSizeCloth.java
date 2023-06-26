package com.model;

class StandardSizeCloth extends Cloth {
    protected String[] sizes;
    protected int size;

    public StandardSizeCloth(String name, int id, String sex, int materialId, String color, String brand) {
        super(name, id, sex, materialId, color, brand);
        sizes = new String[]{"S", "M", "L", "XL"};
    }

    public String[] getSizes() {
        return sizes;
    }

    public final int getSize() {
        return size;
    }

    public void setSizes(String[] sizes) {
        this.sizes = sizes;
    }

    public final void setSize(int size) {
        this.size = size;
    }
}
