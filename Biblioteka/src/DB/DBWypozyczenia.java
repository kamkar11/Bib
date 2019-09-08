/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import hibernate.Wypozyczenia;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author XYZ
 */
public class DBWypozyczenia {
    
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    
    private static SessionFactory buildSessionFactory() {
		
		Configuration configObj = new Configuration();
		configObj.configure("cfg/hibernateMySQL.cfg.xml");

		sessionFactoryObj = configObj.buildSessionFactory();
		return sessionFactoryObj;
	}
    
    public static Wypozyczenia findRecordById(Integer id_wypozyczenia) {
		Wypozyczenia findStudentObj = null;

			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			findStudentObj = (Wypozyczenia) sessionObj.load(Wypozyczenia.class, id_wypozyczenia);
		
		return findStudentObj;
	}
    
    public static void deleteRecord(Integer id_wypozyczenia) {
		
			
			sessionObj = buildSessionFactory().openSession();			
			sessionObj.beginTransaction();
                        
                        Wypozyczenia studObj = findRecordById(id_wypozyczenia);
                        
			sessionObj.delete(studObj);
			sessionObj.getTransaction().commit();
			
		
	}
    
    public static ObservableList<Wypozyczenia> findWypozyczenia(Integer id_wypozyczenia) {
        ObservableList<Wypozyczenia> wypozyczeniaList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        Wypozyczenia ent = findRecordById(id_wypozyczenia);
                
        
            wypozyczeniaList.add(ent);
        
        return wypozyczeniaList;
    }  
   
    public static ObservableList<Wypozyczenia> getWypozyczenia() {
        ObservableList<Wypozyczenia> wypozyczeniaList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        List<Wypozyczenia> eList = sessionObj.createCriteria(Wypozyczenia.class).list();
                
        for (Wypozyczenia ent : eList) {
            wypozyczeniaList.add(ent);
        }
        return wypozyczeniaList;
    }
  
    
    /*
    public static ObservableList<Wypozyczenia> getWypozyczenia() {
        ObservableList<Wypozyczenia> wypozyczeniaList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        List<Wypozyczenia> eList = sessionObj.createQuery("from Wypozyczenia").list();
                
        for (Wypozyczenia ent : eList) {
            wypozyczeniaList.add(ent);
        }
        return wypozyczeniaList;
    }
*/
}
