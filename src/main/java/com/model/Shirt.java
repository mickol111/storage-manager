package com.model;

public class Shirt extends Cloth {
    protected int collar;
    protected int chest;
    protected int[][] sizes;

    public Shirt(String name, int id, String sex, int materialId, String color, String brand) {
        super(name, id, sex, materialId, color, brand);
        type = "shirt";
        this.sizes = new int[][]{{15, 40}, {16, 42}, {17, 44}, {18, 46}};
    }

    public int getChest() {
        return chest;
    }

    public int getCollar() {
        return collar;
    }

    public String[] getSizes() {
        String[] arr = new String[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            arr[i] = sizes[i][0] + "/" + sizes[i][1];
        }
        return arr;
    }

    public int[][] getSizesInt() {
        return sizes;
    }

    public int[] getSize() {
        int[] arr = new int[]{collar, chest};
        return arr;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public void setCollar(int collar) {
        this.collar = collar;
    }

    public void setSizes(int[][] sizes) {
        this.sizes = sizes;
    }

    public void setSizes(String[] sizes) {
        int[][] newSizes = new int[sizes.length][2];
        String wStr = null;
        String lStr = null;
        String size = null;
        System.out.println("sizes.length: " + sizes.length);
        for (int i = 0; i < sizes.length; i++) {

            size = sizes[i];

            wStr = size.substring(0, size.indexOf("/"));
            lStr = size.substring(size.indexOf("/") + 1);
            newSizes[i][0] = Integer.parseInt(wStr);
            newSizes[i][1] = Integer.parseInt(lStr);
        }
        this.sizes = newSizes;
    }

    public void setSize(int @org.jetbrains.annotations.NotNull [] size) {
        this.collar = size[0];
        this.chest = size[1];
    }
}
