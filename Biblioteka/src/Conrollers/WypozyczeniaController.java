/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conrollers;

import java.sql.Date;
import DB.DBConnection;
import DB.DBWypozyczenia;
import hibernate.Czytelnicy;
import hibernate.Gatunki;
import hibernate.Ksiazki;
import hibernate.Wydawnictwa;
import hibernate.Wypozyczenia;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
//import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author XYZ
 */
public class WypozyczeniaController implements Initializable {

    @FXML
    private TableView<Wypozyczenia> tabWypozyczenia;
    @FXML
    private TableColumn<Czytelnicy, Integer> colCzytelnik;
    @FXML
    private TableColumn<Wypozyczenia, String> colDataWyp;
    @FXML
    private TableColumn<Wypozyczenia, String> colDataZwr;
    @FXML
    private TableColumn<Wypozyczenia, Integer> colIdWypozyczenia;
    @FXML
    private TableColumn<Ksiazki, String> colKsiazka;
    @FXML
    private DatePicker dateWyp;
    @FXML
    private DatePicker dateZwr;
    @FXML
    private ComboBox<Integer> combCzytelnik;
    @FXML
    private ComboBox<Integer> combKsiazka;
    //private List<Wypozyczenia> 

    /**
     * Initializes the controller class.
     */
    ObservableList<Integer> czytelnicyList = FXCollections.observableArrayList();
    ObservableList<Integer> ksiazkilist = FXCollections.observableArrayList();
    
    ObservableList<Wypozyczenia> data = FXCollections.observableArrayList();
    @FXML
    private TextField tfUsun;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillComboBoxKsiazki();
        fillComboBoxCzytelnicy();
        combCzytelnik.setItems(czytelnicyList);
        combKsiazka.setItems(ksiazkilist);
        
        
            colIdWypozyczenia.setCellValueFactory(new PropertyValueFactory<Wypozyczenia, Integer>("id_wypozyczenia"));
            colCzytelnik.setCellValueFactory(new PropertyValueFactory<Czytelnicy, Integer>("Czytelnicyy"));
            colKsiazka.setCellValueFactory(new PropertyValueFactory<Ksiazki, String>("Ksiazkii"));
            colDataWyp.setCellValueFactory(new PropertyValueFactory<Wypozyczenia, String>("data_wyp"));
            colDataZwr.setCellValueFactory(new PropertyValueFactory<Wypozyczenia, String>("data_zwr"));

            tabWypozyczenia.setItems(DBWypozyczenia.getWypozyczenia());
        
        
        
        
    }    
    
     

     private void fillComboBoxCzytelnicy(){
        try {
            Connection con = DBConnection.getConnection();

            ResultSet rs = con.createStatement().executeQuery("select id_czytelnika from czytelnik");

            while (rs.next()) {
                czytelnicyList.add(rs.getInt("id_czytelnika"));  
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(KsiazkiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void fillComboBoxKsiazki(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select id_ksiazki from ksiazki");
            
            while (rs.next()) {   
                ksiazkilist.add(rs.getInt("id_ksiazki"));
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(KsiazkiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void dodajWypozyczenia(ActionEvent event) {
        
        //Date  DPCurrentDate =  Date.valueOf(dateWyp);

        String wyp = dateWyp.getEditor().getText();
        String zwr = dateZwr.getEditor().getText();
        
        Integer  id_czytelnika = combCzytelnik.getSelectionModel().getSelectedItem();
        Integer id_ksiazki = combKsiazka.getSelectionModel().getSelectedItem();
        
       // Ksiazki ksiazka = new Ksiazki(autor, tytul, rok_wydania, cena, id_wydawnictwa,id_gatunku);
        Wypozyczenia wypozyczenie = new Wypozyczenia(new Czytelnicy(id_czytelnika), new Ksiazki(id_ksiazki), wyp, zwr);
        
        tabWypozyczenia.getItems().add(wypozyczenie);
        
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        session.save(wypozyczenie);
        session.getTransaction().commit();
        
        
        session.close();
        sf.close();
        tabWypozyczenia.setItems(DBWypozyczenia.getWypozyczenia());
        
    }

    @FXML
    private void usun(ActionEvent event) {
        ObservableList<Wypozyczenia> selectedRows, allCzytelnicy = FXCollections.observableArrayList();
        allCzytelnicy = tabWypozyczenia.getItems();
        
        selectedRows = tabWypozyczenia.getSelectionModel().getSelectedItems();
       
        String us = tfUsun.getText();
        int delValue = Integer.parseInt(us);

        for (Wypozyczenia wypozyczenie : selectedRows) {
            allCzytelnicy.remove(wypozyczenie);

        }
        
        DBWypozyczenia.deleteRecord(delValue);
        tabWypozyczenia.setItems(DBWypozyczenia.getWypozyczenia());
        clear();
    }

    @FXML
    private void szukaj(ActionEvent event) {
        String us = tfUsun.getText();
        int delValue = Integer.parseInt(us);
        
        tabWypozyczenia.setItems(DBWypozyczenia.findWypozyczenia(delValue));
        clear();
        
    }
    
    private void clear(){
          
        tfUsun.setText("");
    }

    @FXML
    private void wysTabele(ActionEvent event) {
        tabWypozyczenia.setItems(DBWypozyczenia.getWypozyczenia());
    }
    
}
