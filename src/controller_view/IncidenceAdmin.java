/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package controller_view;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import model.Incidence;
import static model.Incidence.STATUS_PENDING;
import static model.Incidence.STATUS_SOLVED;
import static model.Incidence.STATUS_UNSOLVED;
import persistencia.IncidenceDB;

/**
 *
 * @author joao.pedro.pereira
 */
public class IncidenceAdmin extends javax.swing.JFrame {

    private ArrayList<Incidence> incidences;

    public ArrayList<Incidence> getIncidences() {
        return incidences;
    }

    public void setIncidences(ArrayList<Incidence> incidences) {
        this.incidences = incidences;
    }

    /**
     * Creates new form IncidenceAdmin
     */
    public IncidenceAdmin() {
        initComponents();
        jComboBoxIncidences.removeAllItems();

        for (int i = 0; i < 3; i++) {
            switch (i) {
                case STATUS_UNSOLVED:
                    jComboBoxIncidences.addItem("Sen resolver");
                    break;
                case STATUS_PENDING:
                    jComboBoxIncidences.addItem("En proceso");
                    break;
                case STATUS_SOLVED:
                    jComboBoxIncidences.addItem("Resolto");
                    break;
                default:
                    break;
            }
        }

        this.incidences = IncidenceDB.findByStatus(jComboBoxIncidences.getSelectedIndex());

        loadIncidences();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxIncidences = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListIncidences = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Listado de incidencias:");

        jComboBoxIncidences.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxIncidences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIncidencesActionPerformed(evt);
            }
        });

        jListIncidences.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListIncidencesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListIncidences);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxIncidences, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxIncidences, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxIncidencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIncidencesActionPerformed
        //Actualiza la lista de incidencias para luego volver a cargarlas en la lista
        this.incidences = IncidenceDB.findByStatus(jComboBoxIncidences.getSelectedIndex());

        loadIncidences();
    }//GEN-LAST:event_jComboBoxIncidencesActionPerformed

    private void jListIncidencesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListIncidencesMouseClicked
        //Doble click
        if (evt.getClickCount() == 2) {

            int index = jListIncidences.getSelectedIndex();
            if (index != -1) {
                Incidence myincidence = incidences.get(index);
                IncidenceDialog myDialog = new IncidenceDialog(this, true, myincidence);
                myDialog.setVisible(true);
                
                //Si se acepta, guarda establece las variables a esa incidencia
                if(myDialog.isAccepted()) {
                    Incidence updated = myDialog.getIncidence();
                    incidences.set(index, updated);
                    
                    //Garda a incidencia actualizada na base de datos
                    IncidenceDB.update(updated);
                }

                // Actualizar cambios después de cerrar el diálogo
                loadIncidences();
            }
        }
    }//GEN-LAST:event_jListIncidencesMouseClicked

    /**
     * Carga la lista de incidencias
     */
    private void loadIncidences() {
        DefaultListModel<String> modelo = new DefaultListModel<>();

        for (Incidence inc : incidences) {
            String texto = inc.getSender().getName() + " " + inc.getSender().getSurname() + ": " + inc.getDescription();
            modelo.addElement(texto);
        }

        jListIncidences.setModel(modelo);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IncidenceAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IncidenceAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IncidenceAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IncidenceAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IncidenceAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxIncidences;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListIncidences;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
