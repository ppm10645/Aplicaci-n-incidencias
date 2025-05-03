/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import model.Incidence;
import model.User;

/**
 *
 * @author joao.pedro.pereira
 */
public class IncidenceDB {

    private static ArrayList<Incidence> incidences = new ArrayList<>();

    static {
        // Obter os usuarios dende UserDB
        User user1 = UserDB.findByName("pedro");
        User user2 = UserDB.findByName("raquel");

        // Engadir incidencias usando os usuarios
        incidences.add(new Incidence(0, "Pantalla azul al encender", "PC01", "", Incidence.STATUS_UNSOLVED, user1));
        incidences.add(new Incidence(0, "No funciona el ratón", "PC02", "", Incidence.STATUS_PENDING, user2));
        incidences.add(new Incidence(0, "Error de red", "PC03", "", Incidence.STATUS_UNSOLVED, user1));
        incidences.add(new Incidence(0, "No se inicia el sistema", "PC04", "", Incidence.STATUS_SOLVED, user2));

        // Establecer os IDs correctos
        for (int i = 0; i < incidences.size(); i++) {
            incidences.get(i).setId(i);
        }
    }

    /**
     * Devolve un ArrayList coas incidencias xeneradas polo usuario indicado no
     * parametro
     *
     * @param username nome do usuario que se quere encontrar
     * @return
     */
    public static ArrayList<Incidence> findByUser(String username) {
        //ArrayList para almacenar as incidencias do usuario que queremos encontrar
        ArrayList<Incidence> userIncidences = new ArrayList<>();
        //Añadimos todas as incidencias do usuario no arraylist xerado
        for (int i = 0; i < incidences.size(); i++) {
            if (incidences.get(i).getSender().getUsername().equals(username)) {
                userIncidences.add(incidences.get(i));
            }
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
        //Establece o identificador da incidencia
        incidence.setId(incidences.size());
        //Almacena a incidencia
        incidences.add(incidence);
    }

    /**
     * Devolve un ArrayList das incidencias que teñen o estado selecionado
     *
     * @param status
     * @return
     */
    public static ArrayList<Incidence> findByStatus(int status) {
        ArrayList<Incidence> myIncidences = new ArrayList<>();

        for (int i = 0; i < incidences.size(); i++) {
            if (incidences.get(i).getStatus() == status) {
                myIncidences.add(incidences.get(i));
            }
        }

        return myIncidences;
    }

    public static ArrayList<Incidence> getIncidences() {
        return incidences;
    }

}
