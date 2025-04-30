/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author joao.pedro.pereira
 */
public class Incidence {

    private int id; //Identificador da incidencia
    private String description; //Descripción do problema
    private String computer; //Nome do equipo relacionado coa incidencia
    private String resolution; //Descripción da solución da incidencia
    private int status; //Estado da incidencia
    
    public static final int STATUS_UNSOLVED = 0;
    public static final int STATUS_PENDING = 1;
    public static final int STATUS_SOLVED = 2;
    
    private User sender; //Usuario que xenerou a incidencia

    public Incidence(int id, String description, String computer, String resolution, int status, User sender) {
        this.id = id;
        this.description = description;
        this.computer = computer;
        this.resolution = resolution;
        this.status = status;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
    
    
}
