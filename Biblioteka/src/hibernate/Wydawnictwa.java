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
@Table(name="wydawnictwa")
public class Wydawnictwa implements Serializable {
    
    
    @Column(name="id_wydawnictwa", unique=true)
    @Id
    @GeneratedValue
    private int id_wydawnictwa;
    
    @Column(name="nazwa_wydawnictwa")
    private String nazwa_wydawnictwa;
    
    @OneToMany(mappedBy = "wydawnictwa")
    private List<Ksiazki> ksiazki;

    public Wydawnictwa(int id_wydawnictwa, String nazwa_wydawnictwa) {
        this.id_wydawnictwa = id_wydawnictwa;
        this.nazwa_wydawnictwa = nazwa_wydawnictwa;
    }

    public Wydawnictwa(String nazwa_wydawnictwa) {
        this.nazwa_wydawnictwa = nazwa_wydawnictwa;
    }
    public Wydawnictwa(int id_wydawnictwa) {
        this.id_wydawnictwa = id_wydawnictwa;
    }

    public Wydawnictwa() {
    }
    
    
    

    public int getId_wydawnictwa() {
        return id_wydawnictwa;
    }

    public void setId_wydawnictwa(int id_wydawnictwa) {
        this.id_wydawnictwa = id_wydawnictwa;
    }

    public String getNazwa_wydawnictwa() {
        return nazwa_wydawnictwa;
    }

    public void setNazwa_wydawnictwa(String nazwa_wydawnictwa) {
        this.nazwa_wydawnictwa = nazwa_wydawnictwa;
    }

    public List<Ksiazki> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<Ksiazki> ksiazki) {
        this.ksiazki = ksiazki;
    }

    @Override
    public String toString() {
        return  nazwa_wydawnictwa;
    }
    
    
    
}
