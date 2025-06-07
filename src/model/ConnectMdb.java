/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rpbp
 */
public class ConnectMdb {
    String db;
    String host;
    String password;
    String port;
    String user;

    public ConnectMdb() {
        this.db = "Abuelitas";
        this.host = "127.0.0.1";
        this.password = "abc123.";
        this.port = "3306";
        this.user="rpbp";
    }

    
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mariadb://" + host + ":" + port + "/" + db;
        return DriverManager.getConnection(url, user, password);
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    

    
}
