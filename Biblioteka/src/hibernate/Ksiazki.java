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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author XYZ
 */

@Entity
@Table(name="ksiazki")
public class Ksiazki implements Serializable {
    
    @Column(name="id_ksiazki", unique=true)
    @Id
    @GeneratedValue
    private int id_ksiazki;
    
    @Column(name="autor")
    private String autor;
    
    @Column(name="tytul")
    private String tytul;
    
    @Column(name="rok_wydania")
    private int rok_wydania;
    
    @Column(name="cena")
    private float cena;

    public Ksiazki(int id_ksiazki, String autor, String tytul, int rok_wydania, float cena ,Wydawnictwa wydawnictwa ,Gatunki gatunek ) {
        this.id_ksiazki = id_ksiazki;
        this.autor = autor;
        this.tytul = tytul;
        this.rok_wydania = rok_wydania;
        this.cena = cena;
        this.gatunek = gatunek;
        this.wydawnictwa = wydawnictwa;
    }

    
    
    public Ksiazki(String autor, String tytul, int rok_wydania, float cena, Wydawnictwa wydawnictwa  ,Gatunki gatunek ) {
        this.autor = autor;
        this.tytul = tytul;
        this.rok_wydania = rok_wydania;
        this.cena = cena;
        this.gatunek = gatunek;
        this.wydawnictwa = wydawnictwa;
    }
     public Ksiazki( String tytul ) {
        
        this.tytul = tytul;
        
    }

    public Ksiazki(int id_ksiazki) {
        this.id_ksiazki = id_ksiazki;
    }

    public Ksiazki() {
    }
     

    @OneToMany(mappedBy = "ksiazki")
    private List<Wypozyczenia> wypozyczenia;
    
    @ManyToOne
    @JoinColumn(name = "id_gatunku")
    private Gatunki gatunek;
    
    @ManyToOne
    @JoinColumn(name = "id_wydawnictwa")
    private Wydawnictwa wydawnictwa;

    public int getId_ksiazki() {
        return id_ksiazki;
    }

    public void setId_ksiazki(int id_ksiazki) {
        this.id_ksiazki = id_ksiazki;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public int getRok_wydania() {
        return rok_wydania;
    }

    public void setRok_wydania(int rok_wydania) {
        this.rok_wydania = rok_wydania;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public List<Wypozyczenia> getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(List<Wypozyczenia> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }

    public Gatunki getGatunek() {
        return gatunek;
    }
    
    public String getGatunekk() {
        return gatunek.getNazwa_gatunku();
    }

    public void setGatunek(Gatunki gatunek) {
        this.gatunek = gatunek;
    }

    public Wydawnictwa getWydawnictwa() {
        return wydawnictwa;
    }
    
    public String getWydawnictwaa() {
        return wydawnictwa.getNazwa_wydawnictwa();
    }

    public void setWydawnictwa(Wydawnictwa wydawnictwa) {
        this.wydawnictwa = wydawnictwa;
    }
    
    
}
