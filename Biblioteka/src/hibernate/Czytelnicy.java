/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 *
 * @author XYZ
 */
@Entity
@Table(name="czytelnik")
public class Czytelnicy implements Serializable {
    
    @Column(name="id_czytelnika", unique=true, nullable = false)
    @Id
    @GeneratedValue
    private int id_czyt;
    
    @Column(name="imie_czytelnika")
    private String imie;
    
    @Column(name="nazwisko_czytelnika")
    private String nazwisko;
    
    @Column(name="miasto")
    private String miasto;
    
    @Column(name="ulica")
    private String ulica;
    
    @Column(name="nr_domu")
    private int nr_domu;
    
    @OneToMany(mappedBy = "czytelnik")
    private List<Wypozyczenia> wypozyczenia;
           
    public Czytelnicy( String imie, String nazwisko, String miasto, String ulica, int nr_domu) {
        
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_domu = nr_domu;
    }

    public Czytelnicy(int id_czyt, String imie, String nazwisko, String miasto, String ulica, int nr_domu) {
        this.id_czyt = id_czyt;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nr_domu = nr_domu;
    }
    
     public Czytelnicy(String nazwisko) { 
        this.nazwisko = nazwisko;
    }

    public Czytelnicy(int id_czyt) {
        this.id_czyt = id_czyt;
    }

    public Czytelnicy() {
    }
    
    
     
     

    public List<Wypozyczenia> getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(List<Wypozyczenia> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }
    
    

    public int getId_czyt() {
        return id_czyt;
    }

    public void setId_czyt(int id_czyt) {
        this.id_czyt = id_czyt;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNr_domu() {
        return nr_domu;
    }

    public void setNr_domu(int nr_domu) {
        this.nr_domu = nr_domu;
    }
    
    
    
    
}
