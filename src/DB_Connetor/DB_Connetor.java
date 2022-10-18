/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connetor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Cornelius
 */
public class DB_Connetor {
    public Connection DB_Connector;
    public Statement statement;
    public PreparedStatement preparedStatement;
    public DB_Connetor() {        this.DB_Connector = null;    }
    public void bukaKoneksi() {
        try {    Class.forName("com.mysql.jdbc.Driver");
            DB_Connector = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/imk_yomart?user=root&password=");
        } catch (Exception e) {
            e.printStackTrace();        }    }
    public void tutupKoneksi() {
        try { if (statement != null) {    statement.close();           }
            if (preparedStatement != null) {     preparedStatement.close();            }
            if (DB_Connector != null) {            DB_Connector.close();            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());        }    }
    
}
