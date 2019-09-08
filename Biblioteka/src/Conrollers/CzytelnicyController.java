/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conrollers;

import DB.DBCzytelnicy;
import DB.DBWydawnictwa;
import hibernate.Czytelnicy;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
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
public class CzytelnicyController implements Initializable {
    
    //SessionFactory sf = new Configuration().configure("cfg/hibernateMySQL.cfg.xml").buildSessionFactory();
    /*
    
    
    PreparedStatement preparedStatement=null;
    ResultSet rs=null;
    */
    @FXML
    private TextField tfImie;
    @FXML
    private TextField tfNazwisko;
    @FXML
    private TextField tfMiasto;
    @FXML
    private TextField tfUlica;
    @FXML
    private TextField tfNrDomu;
    
    /*
    @FXML
    private TableColumn<Czytelnicy, Integer> colId;
    */
    

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Czytelnicy> data = FXCollections.observableArrayList();
    
    
    @FXML
    private TableColumn<Czytelnicy, String> colImie;
    @FXML
    private TableColumn<Czytelnicy, String> colNazwisko;
    @FXML
    private TableColumn<Czytelnicy, String> colMiasto;
    @FXML
    private TableColumn<Czytelnicy, String> colUlica;
    @FXML
    private TableColumn<Czytelnicy, Integer> colNrDomu;
    @FXML
    private TableView<Czytelnicy> table;
    private TextField tfUsun;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
            colImie.setCellValueFactory(new PropertyValueFactory<Czytelnicy, String>("imie"));
            colNazwisko.setCellValueFactory(new PropertyValueFactory<Czytelnicy, String>("nazwisko"));
            colMiasto.setCellValueFactory(new PropertyValueFactory<Czytelnicy, String>("miasto"));
            colUlica.setCellValueFactory(new PropertyValueFactory<Czytelnicy, String>("ulica"));
            colNrDomu.setCellValueFactory(new PropertyValueFactory<Czytelnicy, Integer>("nr_domu"));
            
            table.setItems(DBCzytelnicy.getCzytelnicy());
            
    }
    
    

    @FXML
    private void dodajDoBazy(ActionEvent event) {

        String imie = tfImie.getText();
        String nazwisko = tfNazwisko.getText();
        String miasto = tfMiasto.getText();
        String ulica = tfUlica.getText();
        int nrDomu = Integer.parseInt(tfNrDomu.getText());
        
        
        Czytelnicy czytelnik = new Czytelnicy(imie, nazwisko, miasto, ulica, nrDomu);

        table.getItems().add(czytelnik);
        
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        session.save(czytelnik);
        session.getTransaction().commit();
        
        
        session.close();
        sf.close();
        table.setItems(DBCzytelnicy.getCzytelnicy());
        clear();
    }

    @FXML
    private void usunCzytelnika(ActionEvent event) {
        
        Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        try{
        
        
        int delete_element = table.getSelectionModel().getSelectedItem().getId_czyt();
        Query query = session.createQuery("delete from Czytelnicy where id_czytelnika=:name");
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
        table.setItems(DBCzytelnicy.getCzytelnicy());
    }

    @FXML
    private void edytuj(ActionEvent event) {
       Configuration configuration = new Configuration().configure("cfg/hibernateMySQL.cfg.xml");
      
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        session.beginTransaction();
        
        try{
    
        int update_element = table.getSelectionModel().getSelectedItem().getId_czyt();
        
        
        String imie = tfImie.getText();
        String nazwisko = tfNazwisko.getText();
        String miasto = tfMiasto.getText();
        String ulica = tfUlica.getText();
        int nrDomu = Integer.parseInt(tfNrDomu.getText());
        
        DBCzytelnicy.updateRecord(update_element, imie, nazwisko, miasto, ulica, nrDomu);
        table.setItems(DBCzytelnicy.getCzytelnicy());
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

    @FXML
    private void szukaj(ActionEvent event) {
       
        String imie = tfImie.getText();
        String nazwisko = tfNazwisko.getText();
        String miasto = tfMiasto.getText();
        String ulica = tfUlica.getText();
        int nrDomu = Integer.parseInt(tfNrDomu.getText());
        
        table.setItems(DBCzytelnicy.searchCzytelnicy(imie, nazwisko, miasto, ulica, nrDomu));
        clear();
        
    }
    
    @FXML
    private void wysTabele(ActionEvent event) {
        table.setItems(DBCzytelnicy.getCzytelnicy());
    }

    private void clear(){
        
        tfImie.setText("");
        tfMiasto.setText("");
        tfNazwisko.setText("");
        tfUlica.setText("");
        tfNrDomu.setText("");
        tfUsun.setText("");
    }

    @FXML
    private void edit(MouseEvent event) {
        if(event.getClickCount() == 2){
            tfImie.setText(table.getSelectionModel().getSelectedItem().getImie());
            tfNazwisko.setText(table.getSelectionModel().getSelectedItem().getNazwisko());
            tfMiasto.setText(table.getSelectionModel().getSelectedItem().getMiasto());
            tfUlica.setText(table.getSelectionModel().getSelectedItem().getUlica());
            String nrDomu = Integer.toString(table.getSelectionModel().getSelectedItem().getNr_domu());
            tfNrDomu.setText(nrDomu);
        }
    }

    

}
