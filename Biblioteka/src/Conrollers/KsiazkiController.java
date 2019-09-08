/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conrollers;

import DB.DBConnection;
import DB.DBKsiazki;
import hibernate.Czytelnicy;
import hibernate.Gatunki;
import hibernate.Ksiazki;
import hibernate.Wydawnictwa;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author XYZ
 */
public class KsiazkiController implements Initializable {

    private BorderPane Pane;
    /*
    ObservableList<Gatunki> gatunkiList = FXCollections.observableArrayList();
    ObservableList<String> wydawnictwalist = FXCollections.observableArrayList();
    */
    /*
    ObservableList<Gatunki> gatunkiListG = FXCollections.observableArrayList();
    ObservableList<Wydawnictwa> wydawnictwalistW = FXCollections.observableArrayList();
    */
    
    @FXML
    private ComboBox<String> combGatunek;
    @FXML
    private ComboBox<String> combWydawnictwo;
    @FXML
    private TableColumn<Ksiazki, String> colAutor;
    @FXML
    private TableColumn<Ksiazki, String> colTytul;
    @FXML
    private TableColumn<Wydawnictwa, String> colWydawnictwo;
    @FXML
    private TableColumn<Gatunki, String> colGatunek;
    @FXML
    private TableColumn<Ksiazki, Integer> colRokWydania;
    @FXML
    private TableColumn<Ksiazki, Float> colCena;
    
    private TableColumn<Ksiazki, Integer> colIdKsiazki;
    @FXML
    private TextField tfAutor;
    @FXML
    private TextField tfTytul;
    @FXML
    private TextField tfRokWyd;
    @FXML
    private TextField tfCena;
    @FXML
    private TableView<Ksiazki> tabKsiazki;
    private TextField tfUsun;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Ksiazki> data = FXCollections.observableArrayList();
    //combWydawnictwo.getSelectionModel().getSelectedItem();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        fillComboBoxGatunek();
        fillComboBoxWydawnictwo();
        
        
        
        
        
            
            colAutor.setCellValueFactory(new PropertyValueFactory<Ksiazki, String>("autor"));
            colTytul.setCellValueFactory(new PropertyValueFactory<Ksiazki, String>("tytul"));
            colRokWydania.setCellValueFactory(new PropertyValueFactory<Ksiazki, Integer>("rok_wydania"));
            colCena.setCellValueFactory(new PropertyValueFactory<Ksiazki, Float>("cena"));
            colGatunek.setCellValueFactory(new PropertyValueFactory<Gatunki, String>("Gatunekk"));
            colWydawnictwo.setCellValueFactory(new PropertyValueFactory<Wydawnictwa, String>("Wydawnictwaa"));
            /*
            colGatunek.setCellValueFactory(new PropertyValueFactory<Gatunki, String>("id_gatunku"));
            colWydawnictwo.setCellValueFactory(new PropertyValueFactory<Wydawnictwa, String>("id_wydawnictwa"));
            */
            tabKsiazki.setItems(DBKsiazki.getKsiazki());
    }


    private void fillComboBoxWydawnictwo(){
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        
       Criteria c = session.createCriteria(Wydawnictwa.class);
        List<Wydawnictwa> students = c.list();
        for (Wydawnictwa k : students) {
            combWydawnictwo.getItems().addAll(k.toString());
        }
        session.close();
        sf.close();
    }
    
    private void fillComboBoxGatunek(){
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();

        Criteria c = session.createCriteria(Gatunki.class);
        List<Gatunki> students = c.list();
        for (Gatunki k : students) {
            combGatunek.getItems().addAll(k.toString());
        }
        session.close();
        sf.close();
    }


    @FXML
    private void dodajKsiazke(ActionEvent event) {
        
        String autor = tfAutor.getText();
        String tytul = tfTytul.getText();
        int rok_wydania = Integer.parseInt(tfRokWyd.getText());
        float cena = Float.parseFloat(tfCena.getText());
        /*
        String  id_gatunku = combGatunek.getSelectionModel().getSelectedItem();
        String id_wydawnictwa = combWydawnictwo.getSelectionModel().getSelectedItem();
        */
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");

        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        
        String cos = combWydawnictwo.getValue();
        String[] parts = cos.split(" ");
        String part1 = parts[0]; 
        
        String cos1 = combGatunek.getValue();
        String[] parts1 = cos1.split(" ");
        String part11 = parts1[0]; 
        
        Criteria xd = session.createCriteria(Wydawnictwa.class);
        xd.add(Restrictions.like("nazwa_wydawnictwa", part1));
        List<Wydawnictwa> wydawnictwa = xd.list();
        
        Criteria xxd = session.createCriteria(Gatunki.class);
        xxd.add(Restrictions.like("nazwa_gatunku", part11));
        List<Gatunki> gatunki = xxd.list();
        
        //Ksiazki ksiazka = new Ksiazki(autor, tytul, rok_wydania, cena, id_wydawnictwa,id_gatunku);
        Ksiazki ksiazka = new Ksiazki(autor, tytul, rok_wydania, cena, wydawnictwa.get(0),gatunki.get(0));
        
        tabKsiazki.getItems().add(ksiazka);
        
        
        
        session.save(ksiazka);
        session.getTransaction().commit();
        
        
        session.close();
        sf.close();
        tabKsiazki.setItems(DBKsiazki.getKsiazki());
        clear();

    }

    @FXML
    private void usunKsiazke(ActionEvent event) {
        
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        try{
        
        
        int delete_element = tabKsiazki.getSelectionModel().getSelectedItem().getId_ksiazki();
        Query query = session.createQuery("delete from Ksiazki where id_ksiazki=:name");
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
        tabKsiazki.setItems(DBKsiazki.getKsiazki());
    }

    @FXML
    private void szukaj(ActionEvent event) {
        String autor = tfAutor.getText();
        String tytul = tfTytul.getText();
        int rok_wydania = Integer.parseInt(tfRokWyd.getText());
        float cena = Float.parseFloat(tfCena.getText());
        
        tabKsiazki.setItems(DBKsiazki.searchKsiazki(autor, tytul, cena, rok_wydania));
        clear();
    }

    @FXML
    private void wysTabele(ActionEvent event) {
        tabKsiazki.setItems(DBKsiazki.getKsiazki());
    }
    
    private void clear(){
        
       tfAutor.setText("");
       tfCena.setText("");
       tfTytul.setText("");
       tfRokWyd.setText("");
        tfUsun.setText("");
        
    }

    @FXML
    private void edit(MouseEvent event) {
        if(event.getClickCount() == 2){
            
            tfAutor.setText(tabKsiazki.getSelectionModel().getSelectedItem().getAutor());
            String cena = Float.toString(tabKsiazki.getSelectionModel().getSelectedItem().getCena());
            tfCena.setText(cena);
            tfTytul.setText(tabKsiazki.getSelectionModel().getSelectedItem().getTytul());
            String rok = Integer.toString(tabKsiazki.getSelectionModel().getSelectedItem().getRok_wydania());
            tfRokWyd.setText(rok);
            
        }
    }

    @FXML
    private void edycja(ActionEvent event) {
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        
        try{
    
        int update_element = tabKsiazki.getSelectionModel().getSelectedItem().getId_ksiazki();
        
        String autor = tfAutor.getText();
        String tytul = tfTytul.getText();
        int rok_wydania = Integer.parseInt(tfRokWyd.getText());
        float cena = Float.parseFloat(tfCena.getText());
        
        String cos = combWydawnictwo.getValue();
        String[] parts = cos.split(" ");
        String part1 = parts[0]; 
        
        String cos1 = combGatunek.getValue();
        String[] parts1 = cos1.split(" ");
        String part11 = parts1[0]; 
        
        Criteria xd = session.createCriteria(Wydawnictwa.class);
        xd.add(Restrictions.like("nazwa_wydawnictwa", part1));
        List<Wydawnictwa> wydawnictwa = xd.list();
        
        Criteria xxd = session.createCriteria(Gatunki.class);
        xxd.add(Restrictions.like("nazwa_gatunku", part11));
        List<Gatunki> gatunki = xxd.list();
        
        
        DBKsiazki.updateRecord(update_element, autor, tytul, cena, rok_wydania, wydawnictwa.get(0), gatunki.get(0));
        tabKsiazki.setItems(DBKsiazki.getKsiazki());
        clear();
        
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
}

/*




*/