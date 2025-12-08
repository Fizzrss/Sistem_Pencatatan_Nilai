/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author MyBook Hype AMD
 */
import java.sql.*;
public class DBHelper {
    private static String url = "jdbc:mysql://localhost:3306/sistem_pencatatan_nilai";
        private static String user = "root";
        private static String pass = "";
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, user, pass);
        }
}
