/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conrollers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author XYZ
 */
public class FXMLDocumentController implements Initializable {

    /*
    @FXML
    private Biblioteka biblioteka;
    */
    @FXML
    private BorderPane borderpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menu() {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Informacje o programie");
        alert.setHeaderText("Aplikacja Biblioteka --- Kamil Machański");
        alert.setContentText("Umozliwia zarzadzanie biblioteka");
        alert.show();
    }

    @FXML
    private void ksiazki() {
        load("/FXML/Ksiazki");
    }

    @FXML
    private void wypozyczenia(MouseEvent event) {
        load("/FXML/Wypozyczenia");
    }

    @FXML
    private void czytelnicy(MouseEvent event) {
        load("/FXML/Czytelnicy");
    }
    
    @FXML
    private void gatunki(MouseEvent event) {
        load("/FXML/Gatunki");
    }
    
    @FXML
    private void wydawnictwa(MouseEvent event) {
        load("/FXML/Wydawnictwa");
    }

    private void load(String ui){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        }catch(IOException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE,null,ex);
        }
           borderpane.setCenter(root);
    }

    @FXML
    private void zamknij(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    

    
    
}
