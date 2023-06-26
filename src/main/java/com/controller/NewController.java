package com.controller;

import com.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class NewController {
    private String type;
    @FXML
    private Label titleText;
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
    private ToggleGroup sexGroup;

    private Cloth cloth;
    private String sex;
    private String material;
    @FXML
    public Controller controller;

    @FXML
    protected void init(Controller controller) {
        this.controller = controller;
    }

    protected void setMaterialChoiceBox() {
        Map<Integer, String> materials = Cloth.getMaterials();
        ArrayList<String> aList = new ArrayList<String>(materials.values());
        ObservableList<String> list = FXCollections.observableArrayList(aList);
        materialChoiceBox.setItems(list);
    }

    protected void setTitleText(String s) {
        titleText.setText(s);
    }

    protected void setType(String s) {
        type = s;
    }

    @FXML
    protected void acceptButton() {
        boolean creationSuccesful = true;
        String logStr = "";
        if (sexGroup.getSelectedToggle() != null) {
            if (menSexRadio.isSelected()) {
                sex = "m";
            } else if (womenSexRadio.isSelected()) {
                sex = "w";
            } else {
                sex = "u";
            }
        } else {
            creationSuccesful = false;
            logStr += "You must select sex.\n";
        }
        if (materialChoiceBox.getSelectionModel().getSelectedItem() != null) {
            material = materialChoiceBox.getSelectionModel().getSelectedItem();
        } else {
            creationSuccesful = false;
            logStr += "You must select material.\n";
        }
        if (nameInput.getText().isEmpty()) {
            creationSuccesful = false;
            logStr += "You must provide name.\n";
        }
        if (idInput.getText().isEmpty()) {
            creationSuccesful = false;
            logStr += "You must provide ID.\n";
        }
        if (brandInput.getText().isEmpty()) {
            creationSuccesful = false;
            logStr += "You must provide brand.\n";
        }
        if (colorInput.getText().isEmpty()) {
            creationSuccesful = false;
            logStr += "You must provide color.\n";
        }
        if (creationSuccesful) {
            try {
                switch (type) {
                    case "underwear":
                        cloth = new Underwear(nameInput.getText(), Integer.parseInt(idInput.getText()), sex, Cloth.getMaterialIdByName(material), colorInput.getText(), brandInput.getText());
                        break;
                    case "t-shirt":
                        cloth = new TShirt(nameInput.getText(), Integer.parseInt(idInput.getText()), sex, Cloth.getMaterialIdByName(material), colorInput.getText(), brandInput.getText());
                        break;
                    case "shirt":
                        cloth = new Shirt(nameInput.getText(), Integer.parseInt(idInput.getText()), sex, Cloth.getMaterialIdByName(material), colorInput.getText(), brandInput.getText());
                        break;
                    case "jeans":
                        cloth = new Jeans(nameInput.getText(), Integer.parseInt(idInput.getText()), sex, Cloth.getMaterialIdByName(material), colorInput.getText(), brandInput.getText());
                        break;
                }
                controller.setCloth(cloth);
                System.out.println("New cloth created: " + type + ".");
                controller.setLogTextArea("New cloth created: " + type + ".");
            } catch (Exception e) {
                System.out.println("Cloth creation failed.\n" + e);
                controller.setLogTextArea("Cloth creation failed.\n" + e);
            }
        } else {
            System.out.println("Cloth creation failed.\n" + logStr);
            controller.setLogTextArea("Cloth creation failed.\n" + logStr);
        }
    }

    @FXML
    protected void onCancelButton() throws IOException {
        controller.cancel();
    }
}
