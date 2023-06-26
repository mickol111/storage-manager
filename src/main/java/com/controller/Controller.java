package com.controller;

import com.App;
import com.model.*;
import com.util.ClothDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    private Stage stage;
    private Cloth cloth;
    private Boolean clothIsSet;
    private String logText;
    private BufferedReader reader = null;
    @FXML
    private TextArea logTextArea;
    @FXML
    private MenuItem addToStorageMenuItem;
    @FXML
    private BorderPane rootBorderPane;
    @FXML
    NewController newController;
    @FXML
    AddController addController;
    @FXML
    StorageController storageController;
    @FXML
    private Label loadedModelId;

    protected void setLogTextArea(String s) {
        logText = s;
        setLogTextArea1();
    }
    protected void setCloth(Cloth cloth) {
        this.cloth = cloth;
        clothIsSet = true;
        updateLoadedModelId();
        menuValidate();
    }
    @FXML
    protected void menuValidate()
    {
        addToStorageMenuItem.setDisable(!clothIsSet);
    }
    @FXML
    protected void setLogTextArea1()
    {
        logTextArea.setText(logText);
    }
    @FXML
    protected void close() {
        System.out.println("Exit");
        Platform.exit();
    }
    @FXML
    protected void helpAbout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/about-view.fxml"));
        AnchorPane aboutView = (AnchorPane) loader.load();
        rootBorderPane.setCenter(aboutView);
    }
    @FXML
    protected void addToStorage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/add-view.fxml"));
        AnchorPane view = (AnchorPane) loader.load();
        rootBorderPane.setCenter(view);
        addController = loader.getController();
        addController.init(this,cloth);
    }
    @FXML
    protected void viewStorage() throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/storage-view.fxml"));
        AnchorPane view = (AnchorPane) loader.load();
        rootBorderPane.setCenter(view);
        storageController = loader.getController();
        storageController.init(this);
    }
    @FXML
    protected void newUnderwear() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/new-view.fxml"));
        AnchorPane view = (AnchorPane) loader.load();
        rootBorderPane.setCenter(view);
        newController = loader.getController();
        newController.init(this);
        newController.setMaterialChoiceBox();
        newController.setTitleText("New Underwear");
        newController.setType("underwear");
    }
    @FXML
    protected void newTShirt() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/new-view.fxml"));
        AnchorPane view = (AnchorPane) loader.load();
        rootBorderPane.setCenter(view);
        newController = loader.getController();
        newController.init(this);
        newController.setMaterialChoiceBox();
        newController.setTitleText("New T-Shirt");
        newController.setType("t-shirt");
    }
    @FXML
    protected void newShirt() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/new-view.fxml"));
        AnchorPane view = (AnchorPane) loader.load();
        rootBorderPane.setCenter(view);
        newController = loader.getController();
        newController.init(this);
        newController.setMaterialChoiceBox();
        newController.setTitleText("New Shirt");
        newController.setType("shirt");
    }
    @FXML
    protected void newJeans() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/new-view.fxml"));
        AnchorPane view = (AnchorPane) loader.load();
        rootBorderPane.setCenter(view);
        newController = loader.getController();
        newController.init(this);
        newController.setMaterialChoiceBox();
        newController.setTitleText("New Jeans");
        newController.setType("jeans");
    }
    @FXML
    protected void save(){
        System.out.println("save cloth");
        String sampleText = null;
        if (cloth!=null) {
            sampleText = ClothDAO.toText(cloth);
            sampleText = sampleText + ClothDAO.sizesToText(cloth);
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                saveTextToFile(sampleText, file);
            }
        }
        else {
            System.out.println("Nothing to save.");
            setLogTextArea("Nothing to save.");
        }
    }
    @FXML
    protected void load() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile == null) {
            String fileName = "a1.txt";
            try {
                reader = new BufferedReader(new FileReader("data/" + fileName));
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("IO Exception: " + e.getMessage());
                alert.showAndWait();
                setLogTextArea("IO Exception: " + e.getMessage());
            }
        } else {
            try {
                reader = new BufferedReader(new FileReader(selectedFile));
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("IO Exception: " + e.getMessage());
                alert.showAndWait();
                setLogTextArea("IO Exception: " + e.getMessage());
            }
        }
        int lineCount = 0;
        String line = null;
        List<String> stringList = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            stringList.add(line);
            lineCount++;
        }
        System.out.println(stringList.get(5));
        int stringListLength = stringList.size();
        String[] sizes = new String[stringListLength-7];
        System.out.println(stringListLength);
        for (int i=0;i<(stringListLength-7);i++)
        {

            sizes[i]=stringList.get(i+7);
            System.out.println(sizes[i]);
        }
        switch (stringList.get(5)) {
            case "underwear":
                cloth = new Underwear(
                        stringList.get(1),
                        Integer.parseInt(stringList.get(0)),
                        stringList.get(2),
                        Integer.parseInt(stringList.get(3)),
                        stringList.get(4),
                        stringList.get(6));
                cloth.setSizes(sizes);
                break;
            case "t-shirt":
                cloth = new TShirt(
                        stringList.get(1),
                        Integer.parseInt(stringList.get(0)),
                        stringList.get(2),
                        Integer.parseInt(stringList.get(3)),
                        stringList.get(4),
                        stringList.get(6));
                cloth.setSizes(sizes);
                break;
            case "shirt":
                cloth = new Shirt(
                        stringList.get(1),
                        Integer.parseInt(stringList.get(0)),
                        stringList.get(2),
                        Integer.parseInt(stringList.get(3)),
                        stringList.get(4),
                        stringList.get(6));
                cloth.setSizes(sizes);
                break;
            case "jeans":
                cloth = new Jeans(
                        stringList.get(1),
                        Integer.parseInt(stringList.get(0)),
                        stringList.get(2),
                        Integer.parseInt(stringList.get(3)),
                        stringList.get(4),
                        stringList.get(6));
                cloth.setSizes(sizes);
                break;
            default:
                cloth = new Cloth();
        }
        clothIsSet = true;
        updateLoadedModelId();
        menuValidate();
        setLogTextArea("Model "+cloth.getId() +" loaded");
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateLoadedModelId() {
        if(cloth!=null) {
            loadedModelId.setText("Loaded model ID: " + cloth.getId());
            System.out.println("Loaded model ID: " + cloth.getId());
        }
        else {
            loadedModelId.setText("No model loaded");
            System.out.println("No model loaded");
        }
    }
    @FXML
    protected void viewLoadedCloth() throws IOException {
        if (cloth!=null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/com/view-view.fxml"));
            AnchorPane view = (AnchorPane) loader.load();
            rootBorderPane.setCenter(view);
            ViewController viewController = loader.getController();
            viewController.init(this, cloth);
        }
        else {
            setLogTextArea("No model loaded");
            System.out.println("No model loaded");
        }
    }
    @FXML
    protected void clearLoadedCloth(){
        cloth = null;
        clothIsSet = false;
        menuValidate();
        updateLoadedModelId();
    }
    @FXML
    protected void cancel() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/welcome-view.fxml"));
        AnchorPane view = (AnchorPane) loader.load();
        rootBorderPane.setCenter(view);
    }

    @FXML
    protected void edit() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/com/edit-view.fxml"));
        AnchorPane view = (AnchorPane) loader.load();
        rootBorderPane.setCenter(view);
        EditController editController = loader.getController();
        editController.init(this,cloth);
    }
    @FXML
    protected void addExampleData() throws SQLException, ClassNotFoundException {
        List<ClothSupply> example = new ExampleClothData().getClothSupply();
        for(ClothSupply ex : example) {
            ClothDAO.insertCloth(ex.getCloth(), ex.getCount(), ex.getSize());
        }
        setLogTextArea("Example data added to database. \nRefresh storage window to view results.");
        System.out.println("Example data added to database.");
    }
}