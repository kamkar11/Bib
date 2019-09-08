/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conrollers;

import DB.DBConnection;
import DB.DBGatunki;
import hibernate.Czytelnicy;
import hibernate.Gatunki;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * FXML Controller class
 *
 * @author XYZ
 */
public class GatunkiController implements Initializable {

    @FXML
    private TextField tfNameGatunku;
    @FXML
    private Button buttGatunek;
    @FXML
    private TableColumn<Gatunki, String> colNazwaGatunku;
    
    @FXML
    private TableView<Gatunki> tableGa;
    private TextField tfusunG;
    
    /**
     * Initializes the controller class.
     */
    ObservableList<Gatunki> data = FXCollections.observableArrayList();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           
            colNazwaGatunku.setCellValueFactory(new PropertyValueFactory<Gatunki, String>("nazwa_gatunku"));

            tableGa.setItems(DBGatunki.getGatunki());
            
        }
        

    @FXML
    private void dodajGatunek(ActionEvent event) {
        
        String nazwaGatunku = tfNameGatunku.getText();
        
        

        Gatunki gatunek = new Gatunki(nazwaGatunku);

        tableGa.getItems().add(gatunek);
    
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        session.save(gatunek);
        session.getTransaction().commit();
        
        
        session.close();
        sf.close();
        tableGa.setItems(DBGatunki.getGatunki());
        clear();
    }

    @FXML
    private void usunGatunek(ActionEvent event) {
        
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        try{
        
        
        int delete_element = tableGa.getSelectionModel().getSelectedItem().getId_gatunku();
        Query query = session.createQuery("delete from Gatunki where id_gatunku=:name");
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
        tableGa.setItems(DBGatunki.getGatunki());
    }

    @FXML
    private void edytuj(ActionEvent event) {
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        
        try {

            int update_element = tableGa.getSelectionModel().getSelectedItem().getId_gatunku();
            String nazwaGatunku = tfNameGatunku.getText();

            DBGatunki.updateRecord(update_element, nazwaGatunku);
            tableGa.setItems(DBGatunki.getGatunki());
            clear();
            /*
        session.close();
        sf.close();
             */
        } catch (HibernateException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Błąd usuwania");
            alert.setContentText("Nie można edytować ponieważ jest powiązany z innymi tabelami");

            alert.showAndWait();
        } finally {
            session.close();
            sf.close();
        }
        
    }

    @FXML
    private void szukaj(ActionEvent event) {
        String nazwaGatunku = tfNameGatunku.getText();
        
        tableGa.setItems(DBGatunki.searchGatunki(nazwaGatunku));
        clear();
    }
    
    @FXML
    private void wysTabele(ActionEvent event) {
        tableGa.setItems(DBGatunki.getGatunki());
    }
    
    private void clear(){
        
        tfNameGatunku.setText("");
        tfusunG.setText("");
    }

    @FXML
    private void edit(MouseEvent event) {
        if(event.getClickCount() == 2){
            tfNameGatunku.setText(tableGa.getSelectionModel().getSelectedItem().getNazwa_gatunku());
        }
    }

    
    
}
