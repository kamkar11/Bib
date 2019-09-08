/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import hibernate.Czytelnicy;
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
public class DBCzytelnicy {
    
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    
    private static SessionFactory buildSessionFactory() {
		
		Configuration configObj = new Configuration();
		configObj.configure("cfg/hibernateMySQL.cfg.xml");

		sessionFactoryObj = configObj.buildSessionFactory();
		return sessionFactoryObj;
	}
    
    public static Czytelnicy findRecordById(Integer id_czytelnika) {
		Czytelnicy findStudentObj = null;

			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			findStudentObj = (Czytelnicy) sessionObj.load(Czytelnicy.class, id_czytelnika);
		
		return findStudentObj;
	}
    
    public static void deleteRecord(Integer id_czytelnika) {
		
			
			sessionObj = buildSessionFactory().openSession();			
			sessionObj.beginTransaction();
                        
                        Czytelnicy studObj = findRecordById(id_czytelnika);
                        
			sessionObj.delete(studObj);
			sessionObj.getTransaction().commit();
			
		
	}
    
    public static void updateRecord(int id_czytelnika, String imie, String nazwisko, String miasto, String ulica, int nr_domu ) {		
		
			
			sessionObj = buildSessionFactory().openSession();
			
			sessionObj.beginTransaction();

			
			Czytelnicy gat = (Czytelnicy) sessionObj.get(Czytelnicy.class, id_czytelnika);
			gat.setImie(imie);
                        gat.setNazwisko(nazwisko);
                        gat.setMiasto(miasto);
                        gat.setUlica(ulica);
                        gat.setNr_domu(nr_domu);
			

			
			sessionObj.getTransaction().commit();
			
	}
    
  public static ObservableList<Czytelnicy> findCzytelnik(Integer id_czytelnika) {
        ObservableList<Czytelnicy> czytelnicyList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        Czytelnicy ent = findRecordById(id_czytelnika);
                
        
            czytelnicyList.add(ent);
        
        return czytelnicyList;
    }  
    
    public static ObservableList<Czytelnicy> getCzytelnicy() {
        ObservableList<Czytelnicy> czytelnicyList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        List<Czytelnicy> eList = sessionObj.createCriteria(Czytelnicy.class).list();
                
        for (Czytelnicy ent : eList) {
            czytelnicyList.add(ent);
        }
        return czytelnicyList;
    }
    
    public static ObservableList<Czytelnicy> searchCzytelnicy(String imie, String nazwisko, String miasto, String ulica,int nrDomu){
    
        ObservableList<Czytelnicy> czytelnicyList = FXCollections.observableArrayList();
        sessionObj = buildSessionFactory().openSession();			
	sessionObj.beginTransaction();
        //String HQL = "SELECT o FROM wydawnictwa o WHERE o.nazwa_wydawnictwa like :nazwa_wydawnictwa ";
        String hql = "FROM Czytelnicy E WHERE "
                + "E.imie = :imie_czytelnika OR "
                + "E.nazwisko = :nazwisko_czytelnika OR "
                + "E.miasto = :miasto OR "
                + "E.ulica = :ulica OR "
                + "E.nr_domu = :nr_domu";
        
        Query query = sessionObj.createQuery(hql);
        query.setParameter("imie_czytelnika",imie );
        query.setParameter("nazwisko_czytelnika",nazwisko );
        query.setParameter("miasto",miasto );
        query.setParameter("ulica",ulica );
        query.setParameter("nr_domu",nrDomu );
        List<Czytelnicy> eList = query.list();
          
        
        for (Czytelnicy ent : eList) {
            czytelnicyList.add(ent);
        }
        
        return czytelnicyList; 
      /*  
      String hql = "FROM Employee E WHERE E.id = :employee_id";
      Query query = session.createQuery(hql);
      query.setParameter("employee_id", 10);
      List results = query.list();
*/
}
}
