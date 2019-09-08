/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author XYZ
 */
public class DBConnection {
    
    public static Connection getConnection() throws SQLException{ 
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka","root","");
        
        return connection;
    }
    
}
