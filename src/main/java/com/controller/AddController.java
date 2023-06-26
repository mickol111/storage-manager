package com.controller;

import com.model.Cloth;
import com.util.ClothDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AddController {
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
    private TextField sizeInput;
    @FXML
    private TextField cntInput;
    @FXML
    private ListView sizesListView;

    private Cloth cloth;
    private String size;
    private int count;
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
    protected void acceptButton() throws SQLException, ClassNotFoundException {
        boolean creationSuccessful = true;
        String logStr = "";
        if (cntInput.getText().isEmpty()) {
            creationSuccessful = false;
            logStr += "You must provide count.\n";
        } else {
            count = Integer.parseInt(cntInput.getText());
        }

        if (sizeInput.getText().isEmpty()) {
            creationSuccessful = false;
            logStr += "You must provide size.\n";
        } else {
            size = sizeInput.getText();
        }

        if (creationSuccessful) {
            ClothDAO.insertCloth(cloth, count, size);
            System.out.println("Cloth: " + cloth.getId() + " added to database successfully.");
            controller.setLogTextArea("Cloth: " + cloth.getId() + " added to database successfully.");
        } else {
            System.out.println("Cloth creation failed.\n" + logStr);
            controller.setLogTextArea("Cloth creation failed.\n" + logStr);
        }

    }

}
