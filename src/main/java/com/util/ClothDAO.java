package com.util;


import com.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClothDAO {
    public static String toText(Cloth cloth) {
        String s = cloth.getId() + "\n" + cloth.getName() + "\n" + cloth.getSex() + "\n" + cloth.getMaterialId()
                + "\n" + cloth.getColor() + "\n" + cloth.getType() + "\n" + cloth.getBrand();
        return s;
    }

    public static String sizesToText(Cloth cloth) {
        String s = null;
        String[] sizes = cloth.getSizes();
        for (int i = 0; i < sizes.length; i++) {
            s += "\n" + sizes[i];
        }
        return s;
    }

    public static void insertCloth(Cloth cloth, int count, String size) throws SQLException, ClassNotFoundException {
        int checkResult = checkDuplicate(cloth, size);
        if (checkResult == 0) {
            String updateStmt = "INSERT INTO storage\n" + "(`ID`, `name`, `sex`, `materialID`, `color`, `type`, `brand`, `size`, `cnt`)\n"
                    + "VALUES\n" + "(" + cloth.getId() + ", '" + cloth.getName() + "', '" + cloth.getSex() + "', "
                    + cloth.getMaterialId() + ", '" + cloth.getColor() + "', '" + cloth.getType() + "', '"
                    + cloth.getBrand() + "', '" + size + "', '" + count + "');";

            try {
                DBUtil.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                System.out.print("Error occurred: " + e);
                throw e;
            }
        } else {
            updateCnt(cloth.getId(), size, count + checkResult);
            System.out.println("Duplicate item. Item count changed.");
        }
    }

    public static ObservableList<ClothSupply> searchAll() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM storage";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<ClothSupply> list = getClothList(rs);
            return list;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<ClothSupply> getClothList(ResultSet rs) throws SQLException {
        ObservableList<ClothSupply> list = FXCollections.observableArrayList();
        while (rs.next()) {
            String type = rs.getString("type");
            Cloth cloth;
            switch (type) {
                case "underwear":
                    cloth = new Underwear(rs.getString("name"), rs.getInt("ID"), rs.getString("sex"), rs.getInt("materialID"), rs.getString("color"), rs.getString("brand"));
                    break;
                case "t-shirt":
                    cloth = new TShirt(rs.getString("name"), rs.getInt("ID"), rs.getString("sex"), rs.getInt("materialID"), rs.getString("color"), rs.getString("brand"));
                    break;
                case "shirt":
                    cloth = new Shirt(rs.getString("name"), rs.getInt("ID"), rs.getString("sex"), rs.getInt("materialID"), rs.getString("color"), rs.getString("brand"));
                    break;
                case "jeans":
                    cloth = new Jeans(rs.getString("name"), rs.getInt("ID"), rs.getString("sex"), rs.getInt("materialID"), rs.getString("color"), rs.getString("brand"));
                    break;
                default:
                    cloth = new Cloth();
            }

            ClothSupply clothSupply = new ClothSupply(cloth, rs.getString("size"), rs.getInt("cnt"));
            list.add(clothSupply);
        }
        return list;
    }

    public static void updateCnt(int id, String size, int cnt) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE storage\n" + "      SET cnt=" + cnt + "\n" + "    WHERE ID=" + id + " AND size='" + size + "';";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public static int getCnt(int id, String size) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        int cnt = 0;
        String selectStmt = "SELECT cnt FROM storage\n" + "    WHERE ID=" + id + " AND size='" + size + "';";
        try {
            rs = DBUtil.dbExecuteQuery(selectStmt);
            if (rs == null) {
                System.out.println("rs is null");
            }
            while (rs.next()) {
                cnt = rs.getInt(1);
            }
            System.out.println(rs.getMetaData().getColumnName(1));

        } catch (SQLException e) {
            System.out.print("Error occurred while SELECT Operation: " + e);
            throw e;
        }
        return cnt;
    }

    public static void removeClothBySize(int id, String size) throws SQLException, ClassNotFoundException {
        String updateStmt = "   DELETE FROM storage\n" + "    WHERE ID=" + id + " AND size='" + size + "';";
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void removeClothById(int id) throws SQLException, ClassNotFoundException {
        String updateStmt = "   DELETE FROM storage\n" + "    WHERE ID=" + id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static int checkDuplicate(Cloth cloth, String size) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT cnt FROM storage \n" + "WHERE ID=" + cloth.getId() + " AND size='" + size + "';";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            while (rs.next()) {
                if (rs == null) {
                    return 0;
                } else {
                    return Integer.parseInt(rs.getString("cnt"));
                }
            }
            return 0;
        } catch (SQLException e) {
            System.out.println("SQL select operation failed: " + e);
            throw e;
        }
    }
}