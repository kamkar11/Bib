/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import static DB.DBWypozyczenia.sessionObj;
import hibernate.Gatunki;
import hibernate.Ksiazki;
import hibernate.Wydawnictwa;
import hibernate.Wypozyczenia;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author XYZ
 */
public class DBKsiazki {
    
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    
    private static SessionFactory buildSessionFactory() {
		
		Configuration configObj = new Configuration();
		configObj.configure("cfg/hibernateMySQL.cfg.xml");

		sessionFactoryObj = configObj.buildSessionFactory();
		return sessionFactoryObj;
	}
    
    public static Ksiazki findRecordById(Integer id_ksiazki) {
		Ksiazki findStudentObj = null;

			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			findStudentObj = (Ksiazki) sessionObj.load(Ksiazki.class, id_ksiazki);
		
		return findStudentObj;
	}
    
    public static void deleteRecord(Integer id_ksiazki) {
		
			
			sessionObj = buildSessionFactory().openSession();			
			sessionObj.beginTransaction();
                        
                        Ksiazki studObj = findRecordById(id_ksiazki);
                        
			sessionObj.delete(studObj);
			sessionObj.getTransaction().commit();
			
		
	}
    
    public static void updateRecord(int id_ksiazki, String autor, String tytul,
            float cena, int rok, Wydawnictwa wyd, Gatunki gatunki ) {		
		
			
			sessionObj = buildSessionFactory().openSession();
			
			sessionObj.beginTransaction();

			
			Ksiazki gat = (Ksiazki) sessionObj.get(Ksiazki.class, id_ksiazki);
			gat.setAutor(autor);
                        gat.setTytul(tytul);
                        gat.setCena(cena);
                        gat.setRok_wydania(rok);
                        gat.setWydawnictwa(wyd);
			gat.setGatunek(gatunki);

			
			sessionObj.getTransaction().commit();
			
	}
    
    public static ObservableList<Ksiazki> findKsiazke(Integer id_ksiazki) {
        ObservableList<Ksiazki> ksiazkiList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        Ksiazki ent = findRecordById(id_ksiazki);
                
        
            ksiazkiList.add(ent);
        
        return ksiazkiList;
    }  
    
    public static ObservableList<Ksiazki> getKsiazki() {
        ObservableList<Ksiazki> ksiazkiList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        List<Ksiazki> eList = sessionObj.createCriteria(Ksiazki.class).list();
                
        for (Ksiazki ent : eList) {
            ksiazkiList.add(ent);
        }
        return ksiazkiList;
    }
    
     public static ObservableList<Ksiazki> searchKsiazki(String autor, String tytul, float cena, int rok_wydania){
    
        ObservableList<Ksiazki> ksiazkiList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        //String HQL = "SELECT o FROM wydawnictwa o WHERE o.nazwa_wydawnictwa like :nazwa_wydawnictwa ";
        String hql = "FROM Ksiazki E WHERE "
                + "E.autor = :autor OR "
                + "E.tytul = :tytul OR "
                + "E.cena = :cena OR "
                + "E.rok_wydania = :rok_wydania";
                
                
        
        Query query = sessionObj.createQuery(hql);
        query.setParameter("autor",autor );
        query.setParameter("tytul",tytul );
        query.setParameter("cena",cena );
        query.setParameter("rok_wydania",rok_wydania );
      
        List<Ksiazki> eList = query.list();
          
        
        for (Ksiazki ent : eList) {
            ksiazkiList.add(ent);
        }
        
        return ksiazkiList; 

}
}
