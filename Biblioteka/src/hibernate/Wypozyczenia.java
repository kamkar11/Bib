/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author XYZ
 */
@Entity
@Table(name="wypozyczenia")
public class Wypozyczenia implements Serializable {
    
    @Column(name="id_wypozyczenia", unique=true)
    @Id
    @GeneratedValue
    private int id_wypozyczenia;
    
    @ManyToOne
    @JoinColumn(name = "id_czytelnika")
    private Czytelnicy czytelnik;
    
    @ManyToOne
    @JoinColumn(name = "id_ksiazki")
    private Ksiazki ksiazki;

    
    
    @Column(name="data_wyp")
    private String data_wyp;
    
    @Column(name="data_zwr")
    private String data_zwr;

    public Wypozyczenia(int id_wypozyczenia, Czytelnicy czytelnik, Ksiazki ksiazki, String data_wyp, String data_zwr) {
        this.id_wypozyczenia = id_wypozyczenia;
        this.czytelnik = czytelnik;
        this.ksiazki = ksiazki;
        this.data_wyp = data_wyp;
        this.data_zwr = data_zwr;
    }
    
     public Wypozyczenia( Czytelnicy czytelnik, Ksiazki ksiazki, String data_wyp, String data_zwr) {
        
        this.czytelnik = czytelnik;
        this.ksiazki = ksiazki;
        this.data_wyp = data_wyp;
        this.data_zwr = data_zwr;
    }

    public Wypozyczenia() {
    }
    
     
    

    public int getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public void setId_wypozyczenia(int id_wypozyczenia) {
        this.id_wypozyczenia = id_wypozyczenia;
    }

    public Czytelnicy getCzytelnicy() {
        return czytelnik;
    }
    
     public int getCzytelnicyy() {
        return czytelnik.getId_czyt();
    }

    public void setCzytelnicy(Czytelnicy czytelnicy) {
        this.czytelnik = czytelnicy;
    }

    public String getData_wyp() {
        return data_wyp;
    }

    public void setData_wyp(String data_wyp) {
        this.data_wyp = data_wyp;
    }

    public String getData_zwr() {
        return data_zwr;
    }

    public void setData_zwr(String data_zwr) {
        this.data_zwr = data_zwr;
    }

    public Ksiazki getKsiazki() {
        return ksiazki;
    }
    
    public String getKsiazkii() {
        return ksiazki.getTytul();
    }

    public void setKsiazki(Ksiazki ksiazki) {
        this.ksiazki = ksiazki;
    }

   
    
    
    
    
}
