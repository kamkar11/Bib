/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.DBGatunki.sessionObj;
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
public class DBWydawnictwa {
    
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    
    private static SessionFactory buildSessionFactory() {
		
		Configuration configObj = new Configuration();
		configObj.configure("cfg/hibernateMySQL.cfg.xml");

		sessionFactoryObj = configObj.buildSessionFactory();
		return sessionFactoryObj;
	}
    
    public static Wydawnictwa findRecordById(Integer id_wydawnictwa) {
		Wydawnictwa findStudentObj = null;

			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			findStudentObj = (Wydawnictwa) sessionObj.load(Wydawnictwa.class, id_wydawnictwa);
		
		return findStudentObj;
	}
    
   
    
    public static void deleteRecord(Integer id_wydawnictwa) {
		
			
			sessionObj = buildSessionFactory().openSession();			
			sessionObj.beginTransaction();
                        
                        Wydawnictwa studObj = findRecordById(id_wydawnictwa);
                        
			sessionObj.delete(studObj);
			sessionObj.getTransaction().commit();
			
		
	}
    
    public static void updateRecord(int id_wydawnictwa, String nazwa_wydawnictwa ) {		
		
			
			sessionObj = buildSessionFactory().openSession();
			
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			Wydawnictwa wyd = (Wydawnictwa) sessionObj.get(Wydawnictwa.class, id_wydawnictwa);
			wyd.setNazwa_wydawnictwa(nazwa_wydawnictwa);
			

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			
	}
    
  public static ObservableList<Wydawnictwa> findWydawnictwo(Integer id_wydawnictwa) {
        ObservableList<Wydawnictwa> wydawnictwaList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        Wydawnictwa ent = findRecordById(id_wydawnictwa);
                
        
            wydawnictwaList.add(ent);
        
        return wydawnictwaList;
    }  
  
  public static ObservableList<Wydawnictwa> searchWydawnictwa(String keyword){
    
        ObservableList<Wydawnictwa> wydawnictwaList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        //String HQL = "SELECT o FROM wydawnictwa o WHERE o.nazwa_wydawnictwa like :nazwa_wydawnictwa ";
        String hql = "FROM Wydawnictwa E WHERE E.nazwa_wydawnictwa = :nazwa_wydawnictwa";
        Query query = sessionObj.createQuery(hql);
        query.setParameter("nazwa_wydawnictwa",keyword );
        List<Wydawnictwa> eList = query.list();
          
        
        for (Wydawnictwa ent : eList) {
            wydawnictwaList.add(ent);
        }
        
        return wydawnictwaList; 
      /*  
      String hql = "FROM Employee E WHERE E.id = :employee_id";
      Query query = session.createQuery(hql);
      query.setParameter("employee_id", 10);
      List results = query.list();
*/
}
    
    public static ObservableList<Wydawnictwa> getWydawnictwa() {
        ObservableList<Wydawnictwa> wydawnictwaList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        List<Wydawnictwa> eList = sessionObj.createCriteria(Wydawnictwa.class).list();
                
        for (Wydawnictwa ent : eList) {
            wydawnictwaList.add(ent);
        }
        return wydawnictwaList;
    }
    
}
