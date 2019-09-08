/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import hibernate.Wydawnictwa;
import hibernate.Wypozyczenia;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author XYZ
 */
public class DBOperations {
    
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    
    private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("cfg/hibernateMySQL.cfg.xml");
/*
		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
*/
		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory();
		return sessionFactoryObj;
	}
    
    public static Wydawnictwa findRecordById(Integer id_wydawnictwa) {
		Wydawnictwa findStudentObj = null;
		
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			findStudentObj = (Wydawnictwa) sessionObj.load(Wydawnictwa.class, id_wydawnictwa);
		
		return findStudentObj;
	}
    
    public static void deleteRecord(Integer id_wydawnictwa) {
		
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

                        Wydawnictwa studObj = findRecordById(id_wydawnictwa);
			sessionObj.delete(studObj);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			
		
	}
    
    
    
}
