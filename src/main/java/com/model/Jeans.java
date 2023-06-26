package com.model;

public class Jeans extends Cloth {
    protected int width;
    protected int length;
    protected int[][] sizes;

    public Jeans(String name, int id, String sex, int materialId, String color, String brand) {
        super(name, id, sex, materialId, color, brand);
        type = "jeans";
        this.sizes = new int[][]{{32, 32}, {32, 34}, {34, 32}, {34, 34}};
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int[] getSize() {
        int[] arr = new int[]{width, length};
        return arr;
    }

    public String[] getSizes() {
        String[] arr = new String[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            arr[i] = "W" + sizes[i][0] + "/L" + sizes[i][0];
        }
        return arr;
    }

    public int[][] getSizesInt() {
        return sizes;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSizes(int[][] sizes) {
        this.sizes = sizes;
    }

    public void setSizes(String[] sizes) {
        int[][] newSizes = new int[sizes.length][2];
        String wStr = null;
        String lStr = null;
        String size = null;
        for (int i = 0; i < sizes.length; i++) {
            size = sizes[i];
            wStr = size.substring(size.indexOf("W") + 1);
            wStr = wStr.substring(0, wStr.indexOf("/"));
            lStr = size.substring(size.indexOf("L") + 1);
            newSizes[i][0] = Integer.parseInt(wStr);
            newSizes[i][1] = Integer.parseInt(lStr);
        }
        this.sizes = newSizes;
    }

    public void setSize(int @org.jetbrains.annotations.NotNull [] size) {
        this.width = size[0];
        this.length = size[1];
    }
}
