/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import model.Incidence;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import model.User;

/**
 *
 * @author joao.pedro.pereira
 */
public class IncidenceDB {

    private static ArrayList<Incidence> incidences = new ArrayList<>();

    /**
     * Devolve un ArrayList coas incidencias xeneradas polo usuario indicado no
     * parametro
     *
     * @param username nome do usuario que se quere encontrar
     * @return
     */
    public static ArrayList<Incidence> findByUser(String username) {
//        //ArrayList para almacenar as incidencias do usuario que queremos encontrar
//        ArrayList<Incidence> userIncidences = new ArrayList<>();
//        //Añadimos todas as incidencias do usuario no arraylist xerado
//        for (int i = 0; i < incidences.size(); i++) {
//            if (incidences.get(i).getSender().getUsername().equals(username)) {
//                userIncidences.add(incidences.get(i));
//            }
//        }
//
//        return userIncidences;

        ArrayList<Incidence> userIncidences = new ArrayList<>();

        Connection c = UserDB.getConnection();
        try {
            String sql = "SELECT * FROM Incidence WHERE sender = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String computer = rs.getString("computer");
                String resolution = rs.getString("resolution");
                int status = rs.getInt("status");
                User sender = UserDB.findByName(rs.getString("sender"));

                Incidence i = new Incidence(id, description, computer, resolution, status, sender);
                userIncidences.add(i);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return userIncidences;
    }

    /**
     * Almacena a incidencia no ArrayList de incidencias. Antes de facelo,
     * establece o identificador da incidencia, usando o tamaño do ArrayList
     * para asignalo
     *
     * @param incidence incidencia que se quere almacenar
     */
    public static void save(Incidence incidence) {
        Connection c = UserDB.getConnection();

        String sqlinsert = "INSERT INTO Incidence (description, computer, resolution, status, sender) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = c.prepareStatement(sqlinsert);
            pst.setString(1, incidence.getDescription());
            pst.setString(2, incidence.getComputer());
            pst.setString(3, incidence.getResolution());
            pst.setInt(4, incidence.getStatus());
            pst.setString(5, incidence.getSender().getUsername());

            pst.executeUpdate();
            System.out.println("Incidencia gardada");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Devolve un ArrayList das incidencias que teñen o estado selecionado
     *
     * @param status
     * @return
     */
    public static ArrayList<Incidence> findByStatus(int status) {
        ArrayList<Incidence> myIncidences = new ArrayList<>();
        Connection c = UserDB.getConnection();

//        for (int i = 0; i < incidences.size(); i++) {
//            if (incidences.get(i).getStatus() == status) {
//                myIncidences.add(incidences.get(i));
//            }
//        }
        try {
            String sql = "SELECT * FROM Incidence LEFT JOIN User ON sender = username WHERE status = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, status);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String computer = rs.getString("computer");
                String resolution = rs.getString("resolution");
                User sender = UserDB.findByName(rs.getString("sender"));

                Incidence i = new Incidence(id, description, computer, resolution, status, sender);
                myIncidences.add(i);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return myIncidences;
    }

    /**
     * Actualiza a incidencia na base de datos
     *
     * @param incidence
     */
    public static void update(Incidence incidence) {
        Connection c = UserDB.getConnection();

        String sql = "UPDATE Incidence SET status = ?, resolution = ? WHERE id = ?";

        try {
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, incidence.getStatus());
            pst.setString(2, incidence.getResolution());
            pst.setInt(3, incidence.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ArrayList<Incidence> getIncidences() {
        return incidences;
    }

}
