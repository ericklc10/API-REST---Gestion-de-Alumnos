/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package appGestionNotas;

//JHOEL LIMACHI 6°4
//EJERCICIO 8 CREACON DE API
//LA API SE DEBE EJECUTAR DESDE LA TERMINAL Y EN ESTE ARCHIVO ESTA LA APLICACION DE ESCRITORIO EL CUAL CONSUME LA API CREADA (apiEj8)


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.lang.reflect.Type;




public class Interfaz extends javax.swing.JFrame {

   private final String API_URL = "http://localhost:8080/api/calificaciones";
    
    
    public Interfaz() {
        initComponents();
        fetchCalificaciones();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Cuadro = new javax.swing.JTable();
        nombreApellido = new javax.swing.JTextField();
        materia = new javax.swing.JTextField();
        nota = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cuadro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N°", "Nombre", "Materia", "Nota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Cuadro);
        if (Cuadro.getColumnModel().getColumnCount() > 0) {
            Cuadro.getColumnModel().getColumn(0).setResizable(false);
            Cuadro.getColumnModel().getColumn(1).setResizable(false);
            Cuadro.getColumnModel().getColumn(2).setResizable(false);
            Cuadro.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 390, 320));

        nombreApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreApellidoActionPerformed(evt);
            }
        });
        jPanel1.add(nombreApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 140, 40));

        materia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiaActionPerformed(evt);
            }
        });
        jPanel1.add(materia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 170, 40));

        nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaActionPerformed(evt);
            }
        });
        jPanel1.add(nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 170, 40));

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        jPanel1.add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, -1, 40));

        jLabel1.setText("Nombre del alumno");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jLabel2.setText("Materia");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

        jLabel3.setText("Nota");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fetchCalificaciones() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                java.lang.reflect.Type listType = new TypeToken<List<AlumnoCalificacion>>() {}.getType();
List<AlumnoCalificacion> alumnos = new Gson().fromJson(response.toString(), listType);

                cargarTabla(alumnos);
            }
            con.disconnect();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la API: " + e.getMessage());
        }
    }

    private void cargarTabla(List<AlumnoCalificacion> alumnos) {
        DefaultTableModel model = (DefaultTableModel) Cuadro.getModel();
        model.setRowCount(0); // Limpiar tabla
        for (AlumnoCalificacion alumno : alumnos) {
            model.addRow(new Object[]{
                    alumno.getId(),
                    alumno.getNombreCompleto(),
                    alumno.getMateria(),
                    alumno.getCalificacion()
            });
        }
    }
    
    
    
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
       
        
    String nombre = nombreApellido.getText();
    String materia = this.materia.getText();
    double notaValor;

    try {
        notaValor = Double.parseDouble(nota.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Nota inválida.");
        return;
    }

    AlumnoCalificacion nuevo = new AlumnoCalificacion();
    nuevo.setNombreCompleto(nombre);
    nuevo.setMateria(materia);
    nuevo.setCalificacion(notaValor);

    try {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        String jsonInputString = new Gson().toJson(nuevo);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int status = con.getResponseCode();
        if (status == 200 || status == 201) {
            fetchCalificaciones();
            nombreApellido.setText("");
            this.materia.setText("");
            nota.setText("");
            JOptionPane.showMessageDialog(this, "Alumno agregado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + status);
        }
        con.disconnect();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al conectar con la API: " + e.getMessage());
    }
        
        
    }//GEN-LAST:event_agregarActionPerformed
    // TODO add your handling code here:

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void nombreApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreApellidoActionPerformed

    private void notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notaActionPerformed

    
    // Clase auxiliar para JSON
    public static class AlumnoCalificacion {
        private Long id;
        private String nombreCompleto;
        private String materia;
        private double calificacion;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getNombreCompleto() { return nombreCompleto; }
        public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

        public String getMateria() { return materia; }
        public void setMateria(String materia) { this.materia = materia; }

        public double getCalificacion() { return calificacion; }
        public void setCalificacion(double calificacion) { this.calificacion = calificacion; }
    }

    
    
   
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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Cuadro;
    private javax.swing.JButton agregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField materia;
    private javax.swing.JTextField nombreApellido;
    private javax.swing.JTextField nota;
    // End of variables declaration//GEN-END:variables
}
