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
@Table(name="gatunek")
public class Gatunki implements Serializable{
    
    @Column(name="id_gatunku", unique=true)
    @Id
    @GeneratedValue
    private int id_gatunku;
    
    @Column(name="nazwa_gatunku")
    private String nazwa_gatunku;
    
    @OneToMany(mappedBy = "gatunek")
    private List<Ksiazki> ksiazki;

    public Gatunki(int id_gatunku, String nazwa_gatunku) {
        this.id_gatunku = id_gatunku;
        this.nazwa_gatunku = nazwa_gatunku;
    }
    
    public Gatunki(int id_gatunku) {
        this.id_gatunku = id_gatunku;
       
    }

    public Gatunki(String nazwa_gatunku) {
        this.nazwa_gatunku = nazwa_gatunku;
    }

    public Gatunki() {
    }
    
    

    public int getId_gatunku() {
        return id_gatunku;
    }

    public void setId_gatunku(int id_gatunku) {
        this.id_gatunku = id_gatunku;
    }

    public String getNazwa_gatunku() {
        return nazwa_gatunku;
    }

    public void setNazwa_gatunku(String nazwa_gatunku) {
        this.nazwa_gatunku = nazwa_gatunku;
    }

    public List<Ksiazki> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<Ksiazki> ksiazki) {
        this.ksiazki = ksiazki;
    }

    @Override
    public String toString() {
        return  nazwa_gatunku;
    }
    
    
    
    
}
