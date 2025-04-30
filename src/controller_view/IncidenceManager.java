/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller_view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Incidence;
import model.User;
import static model.User.USER;
import static model.User.ADMIN;
import persistencia.IncidenceDB;
import persistencia.UserDB;

/**
 *
 * @author joao.pedro.pereira
 */
public class IncidenceManager {

    Scanner scan = new Scanner(System.in);

    /**
     * Pide o login e o contrasinal do usuario para iniciar sesión. Cando exista
     * un usuario chama a outro metodo "showIncidencesMenu()" Para rematar,
     * preguntarase ao usuario se quere pechar a aplicación; en caso de que
     * indique que non repetirase todo o proceso, pedindo de novo as credenciais
     * para poder iniciar sesión con outro usuario
     */
    public void showInitMenu() {

        boolean trylogin = true;
        boolean aplicationopen = true;
        System.out.println("Benvid@ a aplicacion de notificacion de incidencias");
        while (aplicationopen) {
            while (trylogin) {

                System.out.println("Nome de usuario");
                String loginname = scan.nextLine();
                System.out.println("Contrasinal");
                String loginpass = scan.nextLine();
                User myuser = UserDB.findByName(loginname);
                if (myuser != null && myuser.getPassword().equals(loginpass)) {
                    if (myuser.getType() == USER) {
                        showIncidencesMenu(UserDB.findByName(loginname));
                        trylogin = false;
                    } else if (myuser.getType() == ADMIN) {
                        IncidenceAdmin myIncidenceAdmin = new IncidenceAdmin();
                        myIncidenceAdmin.setVisible(true);
                        trylogin = false;
                    }

                } else {
                    System.out.println("Nome de usuario ou contrasinal son incorrectos");
                }
            }

            System.out.println("Queres pechar a aplicación?? s/n");
            String c = scan.nextLine();
            if ("S".equals(c) || "s".equals(c)) {
                aplicationopen = false;
                System.out.println("Pechando a aplicación");
            } else if ("N".equals(c) || "n".equals(c)) {
                trylogin = true;
            } else {
                System.out.println("Ocion non valida");
            }

        }
    }

    private void showIncidencesMenu(User user) {
        boolean open = true;
        while (open) {
            System.out.println("Benvid@, " + user.getName());
            System.out.println("As tuas incidencias");
            ArrayList<Incidence> myIncidences = IncidenceDB.findByUser(user.getUsername());
            for (int i = 0; i < myIncidences.size(); i++) {
                if (myIncidences.get(i).getStatus() == 0) {
                    System.out.println(i + ". (sen resolver): " + myIncidences.get(i).getDescription());
                } else if (myIncidences.get(i).getStatus() == 1) {
                    System.out.println(i + ". (en proceso): " + myIncidences.get(i).getDescription());
                } else if (myIncidences.get(i).getStatus() == 2) {
                    System.out.println(i + ". (resolto): " + myIncidences.get(i).getDescription());
                }
            }
            System.out.println("Escolle unha opción:");
            System.out.println("1. Crear unha incidencia");
            System.out.println("2. Pechar a sesión");
            String option = scan.nextLine();
            if ("1".equals(option)) {
                System.out.println("Introduce a descrición da incidencia:");
                String incidencedesc = scan.nextLine();
                System.out.println("Indica o equipo sobre o que trata a incidencia:");
                String incidencedev = scan.nextLine();
                //Creo un obxeto incidencia
                Incidence incidence = new Incidence(0, incidencedesc, incidencedev, "", 0, user);
                //Añado o obxeto a DB
                IncidenceDB.save(incidence);
            } else {
                open = false;
            }
        }
    }

    public static void main(String[] args) {
        IncidenceManager i = new IncidenceManager();
        i.showInitMenu();
    }
}
