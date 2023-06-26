package com.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Cloth {
    protected String name;
    protected int materialId;
    protected String color;
    protected String type;
    protected int id;
    protected String sex;
    protected String brand;
    protected String[] sizes;
    protected final static String[] types = {"cloth", "underwear", "shirt", "t-shirt", "jeans"};
    protected final static String[] sexes = {"m", "w", "u"};
    protected final static Map<Integer, String> materials;

    static {
        materials = new TreeMap<>();
        materials.put(0, "Not specified");
        materials.put(1001, "Cotton");
        materials.put(1002, "Cotton,Elastan");
    }

    public Cloth() {
        color = "";
        type = "cloth";
        sex = "u";
        id = 0;
        materialId = 0;
        name = "";
        brand = "";
    }

    public Cloth(String name, int id, String sex, int materialId, String color, String brand) {
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.materialId = materialId;
        this.color = color;
        this.brand = brand;
    }

    public static int getMaterialIdByName(String material) {
        int id = -1;
        Set<Map.Entry<Integer, String>> entries = materials.entrySet();

        for (Map.Entry<Integer, String> entry : entries) {
            if (entry.getValue().equals(material)) {
                id = entry.getKey();
                break;
            }
        }
        return id;
    }

    public static String getMaterialNameById(int materialId) {
        String materialName = "";
        try {
            materialName = materials.get(materialId);
        } catch (Exception e) {
            System.out.println("Invalid material name");
            System.out.println(e);
        }
        return materialName;
    }

    public static Map<Integer, String> getMaterials() {
        return materials;
    }

    public String[] getSizes() {
        String[] s = new String[]{""};
        return s;
    }

    public void setSizes(String[] sizes) {
        this.sizes = sizes;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getBrand() {
        return brand;
    }

    public static String[] getTypes() {
        return types;
    }

    public static String[] getSexes() {
        return sexes;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

