/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.DBCzytelnicy.sessionObj;
import static DB.DBWydawnictwa.sessionObj;
import hibernate.Czytelnicy;
import hibernate.Gatunki;
import hibernate.Wydawnictwa;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author XYZ
 */
public class DBGatunki {
    
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    
    private static SessionFactory buildSessionFactory() {
		
		Configuration configObj = new Configuration();
		configObj.configure("cfg/hibernateMySQL.cfg.xml");

		sessionFactoryObj = configObj.buildSessionFactory();
		return sessionFactoryObj;
	}
    
    public static Gatunki findRecordById(Integer id_gatunku) {
		Gatunki findStudentObj = null;

			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			findStudentObj = (Gatunki) sessionObj.load(Gatunki.class, id_gatunku);
		
		return findStudentObj;
	}
    
    public static void deleteRecord(Integer id_gatunku) {
		
			
			sessionObj = buildSessionFactory().openSession();			
			sessionObj.beginTransaction();
                        
                        Gatunki studObj = findRecordById(id_gatunku);
                        
			sessionObj.delete(studObj);
			sessionObj.getTransaction().commit();
			
		
	}
    
    public static void updateRecord(int id_gatunku, String nazwa ) {		
		
			
			sessionObj = buildSessionFactory().openSession();
			
			sessionObj.beginTransaction();

			
			Gatunki gat = (Gatunki) sessionObj.get(Gatunki.class, id_gatunku);
			gat.setNazwa_gatunku(nazwa);
			

			
			sessionObj.getTransaction().commit();
			
	}
    
  public static ObservableList<Gatunki> findGatunek(Integer id_gatunku) {
        ObservableList<Gatunki> wydawnictwaList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        Gatunki ent = findRecordById(id_gatunku);
                
        
            wydawnictwaList.add(ent);
        
        return wydawnictwaList;
    }  
    
    public static ObservableList<Gatunki> getGatunki() {
        ObservableList<Gatunki> gatunkiList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        List<Gatunki> eList = sessionObj.createCriteria(Gatunki.class).list();
                
        for (Gatunki ent : eList) {
            gatunkiList.add(ent);
        }
        return gatunkiList;
    }
    
    public static ObservableList<Gatunki> searchGatunki(String keyword){
    
        ObservableList<Gatunki> gatunkiList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        //String HQL = "SELECT o FROM wydawnictwa o WHERE o.nazwa_wydawnictwa like :nazwa_wydawnictwa ";
        String hql = "FROM Gatunki E WHERE E.nazwa_gatunku = :nazwa_gatunku";
        Query query = sessionObj.createQuery(hql);
        query.setParameter("nazwa_gatunku",keyword );
        List<Gatunki> eList = query.list();
          
        
        for (Gatunki ent : eList) {
            gatunkiList.add(ent);
        }
        
        return gatunkiList; 
      
}
    
}
