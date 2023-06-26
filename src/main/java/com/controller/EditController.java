package com.controller;

import com.model.Cloth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EditController {
    @FXML
    private ChoiceBox<String> materialChoiceBox;
    @FXML
    private TextField idInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField colorInput;
    @FXML
    private TextField brandInput;
    @FXML
    private RadioButton menSexRadio;
    @FXML
    private RadioButton womenSexRadio;
    @FXML
    private RadioButton unisexSexRadio;
    @FXML
    private RadioButton noChangeRadio;
    @FXML
    private ToggleGroup sexGroup;
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
    private Cloth cloth;

    @FXML
    public Controller controller;
    @FXML
    private ListView sizesListView;

    @FXML
    protected void init(Controller controller, Cloth cloth) {
        this.controller = controller;
        this.cloth = cloth;
        setMaterialChoiceBox();
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

    protected void setMaterialChoiceBox() {
        Map<Integer, String> materials = Cloth.getMaterials();
        ArrayList<String> aList = new ArrayList<String>(materials.values());
        ObservableList<String> list = FXCollections.observableArrayList(aList);
        materialChoiceBox.setItems(list);
    }

    @FXML
    protected void acceptButton() {
        Boolean edited = false;
        String material;
        String sex;
        if (sexGroup.getSelectedToggle() != null && !noChangeRadio.isSelected()) {
            if (menSexRadio.isSelected()) {
                sex = "m";
            } else if (womenSexRadio.isSelected()) {
                sex = "w";
            } else {
                sex = "u";
            }
            cloth.setSex(sex);
            edited = true;
        }
        if (materialChoiceBox.getSelectionModel().getSelectedItem() != null) {
            material = materialChoiceBox.getSelectionModel().getSelectedItem();
            cloth.setMaterialId(Cloth.getMaterialIdByName(material));
            edited = true;
        }
        if (!idInput.getText().isEmpty()) {
            cloth.setId(Integer.parseInt(idInput.getText()));
            edited = true;
        }
        if (!nameInput.getText().isEmpty()) {
            cloth.setName(nameInput.getText());
            edited = true;
        }
        if (!colorInput.getText().isEmpty()) {
            cloth.setColor(colorInput.getText());
            edited = true;
        }
        if (!brandInput.getText().isEmpty()) {
            cloth.setBrand(brandInput.getText());
            edited = true;
        }
        if (edited) {
            System.out.println("Cloth edited.");
            controller.setCloth(cloth);
            controller.setLogTextArea("Cloth edited.");
        } else {
            System.out.println("Cloth was not edited.");
            controller.setLogTextArea("Cloth was not edited.");
        }
    }
    @FXML
    protected void onCancelButton() throws IOException {
        controller.cancel();
    }
}
