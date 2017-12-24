/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halil.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author halil
 */
public class Config {

    public static String hostName = "localhost";
    public static String userName = "root";
    public static String password = "";
    public static String dbName = "MediavalWorld";
    public static int port = 3306;
    public static Connection con;

    public static void mysqlConnect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Connection");
        Connection conx = (Connection) DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port + "/" + dbName, userName, password);
        con = conx;
    }
}
