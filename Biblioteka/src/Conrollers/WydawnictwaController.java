/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conrollers;

import DB.DBConnection;
import DB.DBOperations;
import DB.DBWydawnictwa;
import hibernate.Gatunki;
import hibernate.Wydawnictwa;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author XYZ
 */
public class WydawnictwaController implements Initializable {

    @FXML
    private TextField tfNazwaWydawnictwa;
    /*
    @FXML
    private TableColumn<Wydawnictwa, Integer> colIdWydawnictwa;
    */
    @FXML
    private TableColumn<Wydawnictwa, String> colNazwaWydawnictwa;
    @FXML
    private TableView<Wydawnictwa> tabWydawnictwa;

    ObservableList<Wydawnictwa> data = FXCollections.observableArrayList();
    private TextField tfUsuwanie;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            //colIdWydawnictwa.setCellValueFactory(new PropertyValueFactory<Wydawnictwa, Integer>("id_wydawnictwa"));
            colNazwaWydawnictwa.setCellValueFactory(new PropertyValueFactory<Wydawnictwa, String>("nazwa_wydawnictwa"));

            tabWydawnictwa.setItems(DBWydawnictwa.getWydawnictwa());
            
            
            
            
    }    

    @FXML
    private void dodajWydawnictwo(ActionEvent event) {
        String nazwaWydawnictwa = tfNazwaWydawnictwa.getText();
        
        

        Wydawnictwa wydawnictwo = new Wydawnictwa(nazwaWydawnictwa);

        tabWydawnictwa.getItems().add(wydawnictwo);
     
    
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        session.save(wydawnictwo);
        session.getTransaction().commit();
        
        
        session.close();
        sf.close();
        clear();
        tabWydawnictwa.setItems(DBWydawnictwa.getWydawnictwa());

    }

    @FXML
    private void usunWydawnictwo(ActionEvent event) throws SQLException {
        /*
        ObservableList<Wydawnictwa> selectedRows, allWydawnictwa = FXCollections.observableArrayList();
        allWydawnictwa = tabWydawnictwa.getItems();
        
        selectedRows = tabWydawnictwa.getSelectionModel().getSelectedItems();
       
        String us = tfUsuwanie.getText();
        int delValue = Integer.parseInt(us);
        
        
       
        
        for (Wydawnictwa wydawnictwo : selectedRows) {
            allWydawnictwa.remove(wydawnictwo);

        }
       
        
        DBWydawnictwa.deleteRecord(delValue);
        
        clear();
        tabWydawnictwa.setItems(DBWydawnictwa.getWydawnictwa());    
        */
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        try{
        
        
        int delete_element = tabWydawnictwa.getSelectionModel().getSelectedItem().getId_wydawnictwa();
        Query query = session.createQuery("delete from Wydawnictwa where id_wydawnictwa=:name");
        query.setParameter("name", delete_element);
        int del = query.executeUpdate();
                
        session.getTransaction().commit();
        session.flush();
        /*
        session.close();
        sf.close();
        */
        } catch(HibernateException ex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Błąd usuwania");
            alert.setContentText("Nie można usunąć ponieważ jest powiązany z innymi tabelami");

            alert.showAndWait();
        }
        finally{
            session.close();
            sf.close();
        }
        tabWydawnictwa.setItems(DBWydawnictwa.getWydawnictwa());
    }

    @FXML
    private void szukaj(ActionEvent event) {
        /*
        String us = tfUsuwanie.getText();
        int delValue = Integer.parseInt(us);
        */
        String nazwaWydawnictwa = tfNazwaWydawnictwa.getText();
        
        tabWydawnictwa.setItems(DBWydawnictwa.searchWydawnictwa(nazwaWydawnictwa));
        clear();
        
    }

    @FXML
    private void edycja(ActionEvent event) {
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        
        try{
        /*
        String nazwaWydawnictwa = tfNazwaWydawnictwa.getText();
        int update_element = tabWydawnictwa.getSelectionModel().getSelectedItem().getId_wydawnictwa();
        
        Query query = session.createQuery("update Wydawnictwa set nazwa_wydawnictwa = :nazwa_wydawnictwa"+
                "where id_wydawnictwa = :name");
        query.setParameter("nazwa_wydawnictwa", nazwaWydawnictwa);
        query.setParameter("name", update_element);
        
        int del = query.executeUpdate();
                
        session.getTransaction().commit();
        session.flush();
            */
        int update_element = tabWydawnictwa.getSelectionModel().getSelectedItem().getId_wydawnictwa();
        String nazwaWydawnictwa = tfNazwaWydawnictwa.getText();
        
        DBWydawnictwa.updateRecord(update_element, nazwaWydawnictwa);
        tabWydawnictwa.setItems(DBWydawnictwa.getWydawnictwa());
        clear();
        /*
        session.close();
        sf.close();
        */
        } catch(HibernateException ex){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Błąd edycji");
            alert.setContentText("Nie można edytować ponieważ jest powiązany z innymi tabelami");

            alert.showAndWait();
        }
        finally{
            session.close();
            sf.close();
        }
        
        
        
        
    }
    
    @FXML
    private void cala(ActionEvent event) {
        
        tabWydawnictwa.setItems(DBWydawnictwa.getWydawnictwa());
    }
    
    
    
    
    private void clear(){
        
        tfNazwaWydawnictwa.setText("");
        tfUsuwanie.setText("");
    }

    @FXML
    private void edit(MouseEvent event) {
        if(event.getClickCount() == 2){
            tfNazwaWydawnictwa.setText(tabWydawnictwa.getSelectionModel().getSelectedItem().getNazwa_wydawnictwa());
        }
    }

    

    
    
    
    
}
