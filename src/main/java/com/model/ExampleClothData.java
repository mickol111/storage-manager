package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ExampleClothData {
    private Cloth[] clothes;
    private List<ClothSupply> clothSupply = new ArrayList<ClothSupply>();

    public ExampleClothData() {
        clothes = new Cloth[6];
        String[] sizes = new String[]{"S", "M", "L", "XL"};
        int randomId = ThreadLocalRandom.current().nextInt(100000, 990000 + 1);
        int randomSex = ThreadLocalRandom.current().nextInt(0, 2);
        clothes[0] = new Underwear("Socks 3-PACK", randomId, Cloth.getSexes()[randomSex], 1001, "yellow", "Puma");
        clothes[1] = new Underwear("Socks 3-PACK", randomId + 1, Cloth.getSexes()[randomSex], 1001, "white", "Puma");
        clothes[2] = new Underwear("Socks 6-PACK", randomId + 10, Cloth.getSexes()[randomSex], 1001, "black", "Puma");
        randomId = ThreadLocalRandom.current().nextInt(100000, 990000 + 1);
        randomSex = ThreadLocalRandom.current().nextInt(0, 2);
        clothes[3] = new TShirt("TShirt with print", randomId, Cloth.getSexes()[randomSex], 1001, "black", "Fruit of the Loom");
        randomId = ThreadLocalRandom.current().nextInt(100000, 990000 + 1);
        randomSex = ThreadLocalRandom.current().nextInt(0, 2);
        clothes[4] = new Shirt("Plain shirt", randomId, Cloth.getSexes()[randomSex], 1001, "black", "H&M");
        randomId = ThreadLocalRandom.current().nextInt(100000, 990000 + 1);
        randomSex = ThreadLocalRandom.current().nextInt(0, 2);
        clothes[5] = new Jeans("Straight-fit jeans", randomId, Cloth.getSexes()[randomSex], 1001, "blue", "H&M");

        for (int i = 0; i < 4; i++) {
            clothSupply.add(new ClothSupply(clothes[3], sizes[i], 30));
            for (int j = 0; j < 3; j++) {
                clothSupply.add(new ClothSupply(clothes[j], sizes[i], 50));
            }
        }
        for (int i = 0; i < clothes[4].getSizes().length; i++) {
            clothSupply.add(new ClothSupply(clothes[4], clothes[4].getSizes()[i], 40));
        }
        for (int i = 0; i < clothes[5].getSizes().length; i++) {
            clothSupply.add(new ClothSupply(clothes[4], clothes[4].getSizes()[i], 20));
        }
    }

    public List<ClothSupply> getClothSupply() {
        return clothSupply;
    }
}
