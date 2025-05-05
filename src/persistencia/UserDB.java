/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joao.pedro.pereira
 */
public class UserDB {

    private static Connection c = null;

//    /**
//     * Devolve un usuario a partir do nome do usuario
//     *
//     * @param username Nome do usuario que se quere buscar
//     * @return
//     */
//    public static User findByName(String username) {
//        for (User user : users) {
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
//        }
//        return null;
//    }
    /**
     * Devolve un usuario a partir do nome do usuario
     *
     * @param username Nome do usuario que se quere buscar
     * @return
     */
    public static User findByName(String username) {
        User u = null;
        String sqluser = "SELECT * FROM User WHERE username = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement pst = conn.prepareStatement(sqluser);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();
            //Si existe un resultado con ese nombre de usuario crea un usuario 
            if (rs.next()) {
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int role = rs.getInt("type");

                u = new User(username, password, name, surname, role);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return u;
    }

    public static Connection getConnection() {
        if (c == null) {
            try {
                // Establece a conexión
                c = DriverManager.getConnection("jdbc:sqlite:/home/joao.pedro.pereira/NetBeansProjects/Aplicaci-n-incidencias/incidences.db");
                System.out.println("Conexión establecida con SQLite");
            } catch (SQLException e) {
                System.out.println("Error ao conectar: " + e.getMessage());
            }
        }
        return c;
    }
}
