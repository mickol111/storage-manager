package com.controller;

import com.model.Cloth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ViewController {
    @FXML
    private Label titleText;
    @FXML
    private Label colorOut;
    @FXML
    private Label brandOut;
    @FXML
    private Label materialOut;
    @FXML
    private Label nameOut;
    @FXML
    private Label idOut;
    @FXML
    private Label sexOut;
    @FXML
    private Label typeOut;
    @FXML
    private ListView sizesListView;
    private Cloth cloth;
    @FXML
    public Controller controller;
    @FXML
    protected void init(Controller controller, Cloth cloth) {
        this.controller = controller;
        this.cloth = cloth;
        setSizesListView(this.cloth);
        colorOut.setText(cloth.getColor());
        brandOut.setText(cloth.getBrand());
        typeOut.setText(cloth.getType());
        nameOut.setText(cloth.getName());
        idOut.setText(Integer.toString(cloth.getId()));
        typeOut.setText(cloth.getType());
        sexOut.setText(cloth.getSex());
        materialOut.setText(Cloth.getMaterialNameById(cloth.getMaterialId()));
    }
    protected void setSizesListView(Cloth cloth) {
        String[] sizes = cloth.getSizes();
        List<String> list = Arrays.asList(sizes);
        ObservableList<String> observableList = FXCollections.observableArrayList(list);
        sizesListView.setItems(observableList);
    }
    @FXML
    protected void onCancelButton() throws IOException {
        controller.cancel();
    }
    @FXML
    protected void onEditButton() throws IOException {
        controller.edit();
    }
}
