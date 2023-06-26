package com.controller;

import com.model.ClothSupply;
import com.util.ClothDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class StorageController {
    @FXML
    private TextField idInput;
    @FXML
    private TextField sizeInput;
    @FXML
    private TextField countInput;
    @FXML
    private TableView storageTable;
    @FXML
    private TableColumn<ClothSupply, Integer> csIdColumn;
    @FXML
    private TableColumn<ClothSupply, String> csNameColumn;
    @FXML
    private TableColumn<ClothSupply, String> csBrandColumn;
    @FXML
    private TableColumn<ClothSupply, Integer> csMaterialIDColumn;
    @FXML
    private TableColumn<ClothSupply, String> csSizeColumn;
    @FXML
    private TableColumn<ClothSupply, String> csSexColumn;
    @FXML
    private TableColumn<ClothSupply, Integer> csCountColumn;
    @FXML
    private TableColumn<ClothSupply, String> csColorColumn;
    @FXML
    private TableColumn<ClothSupply, String> csTypeColumn;
    @FXML
    public Controller controller;

    protected void loadTable() throws SQLException, ClassNotFoundException {
        ObservableList<ClothSupply> list = ClothDAO.searchAll();
        storageTable.setItems(list);
        csIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCloth().getId()).asObject());
        csNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCloth().getName()));
        csBrandColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCloth().getBrand()));
        csMaterialIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCloth().getMaterialId()).asObject());
        csSexColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCloth().getSex()));
        csColorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCloth().getColor()));
        csTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCloth().getType()));
        csSizeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSize()));
        csCountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCount()).asObject());
    }

    @FXML
    protected void init(Controller controller) throws SQLException, ClassNotFoundException {
        this.controller = controller;
        loadTable();
    }

    @FXML
    protected void remove() throws SQLException, ClassNotFoundException {
        controller.setLogTextArea("Remove");
        int cnt = ClothDAO.getCnt(Integer.parseInt(idInput.getText()), sizeInput.getText());
        ClothDAO.updateCnt(Integer.parseInt(idInput.getText()), sizeInput.getText(), cnt - Integer.parseInt(countInput.getText()));
        loadTable();
    }

    @FXML
    protected void removeSize() throws SQLException, ClassNotFoundException {
        controller.setLogTextArea("RemoveSize");
        ClothDAO.removeClothBySize(Integer.parseInt(idInput.getText()), sizeInput.getText());
        controller.setLogTextArea("Size: " + sizeInput.getText() + " of item ID: " + idInput.getText() + " removed.");
        loadTable();
    }

    @FXML
    protected void removeId() throws SQLException, ClassNotFoundException {
        controller.setLogTextArea("RemoveID");
        ClothDAO.removeClothById(Integer.parseInt(idInput.getText()));
        controller.setLogTextArea("All items of ID: " + idInput.getText() + " removed.");
        loadTable();
    }
}
