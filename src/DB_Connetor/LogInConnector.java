/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connetor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Cornelius
 */
public class LogInConnector {
    

    public static void ChangedScences(ActionEvent e, String FXML_FILE, String Tittle, String Nama_Pegawai) {
        Parent root = null;
        if (Nama_Pegawai != null) {
            try {
                FXMLLoader loader = new FXMLLoader(LogInConnector.class.getResource(FXML_FILE));
                root = loader.load();
              } catch (IOException Eror) {
                Eror.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(LogInConnector.class.getResource(FXML_FILE));
            } catch (IOException Eror) {
                Eror.printStackTrace();
            }
        }
        Stage stg = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stg.setTitle(Tittle);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(new Scene(root));
        stg.show();
    
    }
    
    public static void signuppegawai(ActionEvent e, String NIP, String Nama, String Alamat_Pegawai, String Password, String No_Telepon_Pegawai) {
        Connection connect = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckEmailExists = null;
        ResultSet rs = null;

        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/imk_yomart", "root", "");
            psCheckEmailExists = connect.prepareStatement("SELECT * FROM pegawai WHERE NIP =?");
            psCheckEmailExists.setString(1, NIP);
            rs = psCheckEmailExists.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("NIP already used!" + "\n" + "You can't used this NIP");
                alert.show();
            } else {
                psInsert = connect.prepareStatement("INSERT INTO pegawai (NIP, Nama,Password, Alamat_Pegawai,Nomor_Telepon_Pegawai) VALUES (?,?,?,?,?)");
                psInsert.setString(1, NIP);
                psInsert.setString(2, Nama);
                psInsert.setString(3, Password);
                psInsert.setString(4, Alamat_Pegawai);
                psInsert.setString(5, No_Telepon_Pegawai);
                psInsert.executeUpdate();
                ChangedScences(e, "/fxml/FXMLDocument.fxml", "Main Menu", null);
            }
        } catch (SQLException eror) {
            eror.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
            }
            if (psCheckEmailExists != null) {
                try {
                    psCheckEmailExists.close();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
            }
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
            }
        }
    }

    public static void login(ActionEvent e, String NIP, String Password) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imk_yomart", "root", "");
            statement = con.prepareStatement("SELECT Nama,Password FROM pegawai Where NIP=? ");
            statement.setString(1, NIP);
            rs = statement.executeQuery();
            if (!rs.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Access Dennied!!! ⛔" + "\n"
                        + "404 Not Found!" + "\n"  
                        + "NIP Not Found In Data Base!");
                alert.show();
            } else {
                while (rs.next()) {
                    String getPassword = rs.getString("Password");
                    String getName = rs.getString("Nama");
                    if (getPassword.equals(Password)) {
                        ChangedScences(e, "/fxml/FXMLCalculasiJualBarang.fxml", "Jual Barang", getName);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Access Dennied!!! ⛔" + "\n" +
                                "Passwords Did Not Match!");
                        alert.show();
                    }
                }

            }
        } catch (SQLException error) {
            error.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
            }
              if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
            }
                if (con != null) {
                try {
                    con.close();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
            }
        }
    }

    
    
}


