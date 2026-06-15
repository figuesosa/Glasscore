package com.mycompany.examenvidrieria;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FrmHerramientas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmHerramientas.class.getName());

  
    public FrmHerramientas() {
    initComponents();
    setLocationRelativeTo(null);
    setTitle("Glasscore - Herramientas");

    cargarTabla();

    cmbEstadoConservacion.removeAllItems();
    cmbEstadoConservacion.addItem("Excelente");
    cmbEstadoConservacion.addItem("Regular");
    cmbEstadoConservacion.addItem("En Mantenimiento");

    cmbDisponibilidad.removeAllItems();
    cmbDisponibilidad.addItem("Disponible");
    cmbDisponibilidad.addItem("Asignada");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreHerramienta = new javax.swing.JTextField();
        txtCodigoActivo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cmbEstadoConservacion = new javax.swing.JComboBox<>();
        cmbDisponibilidad = new javax.swing.JComboBox<>();
        btnRegresar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tableHerramientas = new javax.swing.JScrollPane();
        tblHerramientas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(19, 74, 158));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GLASSCORE HERRAMIENTAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel1)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Herramienta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel2.setLayout(null);

        jLabel2.setText("Disponibilidad:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(30, 120, 150, 16);

        jLabel3.setText("Codigo Activo:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(30, 30, 90, 16);

        jLabel4.setText("Nombre de la Herramienta:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(30, 60, 150, 16);

        jLabel5.setText("Estado de Conservacion:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(30, 90, 150, 16);

        txtNombreHerramienta.setText(" ");
        jPanel2.add(txtNombreHerramienta);
        txtNombreHerramienta.setBounds(190, 60, 320, 22);

        txtCodigoActivo.setText(" ");
        jPanel2.add(txtCodigoActivo);
        txtCodigoActivo.setBounds(190, 30, 200, 22);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        jPanel2.add(btnGuardar);
        btnGuardar.setBounds(530, 20, 110, 30);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        jPanel2.add(btnActualizar);
        btnActualizar.setBounds(670, 20, 110, 30);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        jPanel2.add(btnEliminar);
        btnEliminar.setBounds(530, 80, 110, 30);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        jPanel2.add(btnLimpiar);
        btnLimpiar.setBounds(670, 80, 110, 30);

        cmbEstadoConservacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cmbEstadoConservacion);
        cmbEstadoConservacion.setBounds(190, 90, 200, 22);

        cmbDisponibilidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cmbDisponibilidad);
        cmbDisponibilidad.setBounds(190, 120, 200, 22);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        jPanel2.add(btnRegresar);
        btnRegresar.setBounds(670, 130, 110, 30);

        jButton1.setText("Asignacion");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel2.add(jButton1);
        jButton1.setBounds(530, 130, 110, 30);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de herramientas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 255))); // NOI18N

        tblHerramientas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo Activo", "Nombre Herramienta", "Estado de Conversacion", "Disponibilidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHerramientas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHerramientasMouseClicked(evt);
            }
        });
        tableHerramientas.setViewportView(tblHerramientas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tableHerramientas)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
     try{
        int fila = tblHerramientas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una herramienta de la tabla");
            return;
        }

        int id =
                Integer.parseInt(
                        tblHerramientas
                                .getValueAt(
                                        fila,
                                        0)
                                .toString());

        Connection con =
                ConexionDB.getConexion();

        String sql =
                "DELETE FROM herramientas "
                + "WHERE id_herramienta=?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1,id);

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Herramienta eliminada");

        cargarTabla();

    }
    catch(HeadlessException | NumberFormatException | SQLException e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {
        new PanelPrincipal().setVisible(true);
        this.dispose();
    }

    private boolean validarCamposHerramienta() {
        if (txtCodigoActivo.getText().trim().isEmpty()
                || txtNombreHerramienta.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete codigo y nombre de la herramienta");
            return false;
        }
        return true;
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    try{
        if (!validarCamposHerramienta()) {
            return;
        }

        Connection con =
                ConexionDB.getConexion();

        String sql =
                "INSERT INTO herramientas "
                + "(codigo_activo,"
                + "nombre_herramienta,"
                + "estado_conservacion,"
                + "disponibilidad) "
                + "VALUES (?,?,?,?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(
                1,
                txtCodigoActivo.getText());

        ps.setString(
                2,
                txtNombreHerramienta.getText());

        ps.setString(
                3,
                cmbEstadoConservacion
                        .getSelectedItem()
                        .toString());

        ps.setString(
                4,
                cmbDisponibilidad
                        .getSelectedItem()
                        .toString());

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Herramienta guardada");

        cargarTabla();

    }
    catch(HeadlessException | SQLException e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    txtCodigoActivo.setText("");
    txtNombreHerramienta.setText("");

    cmbEstadoConservacion.setSelectedIndex(0);
    cmbDisponibilidad.setSelectedIndex(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblHerramientasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHerramientasMouseClicked
    int fila =
            tblHerramientas.getSelectedRow();

    txtCodigoActivo.setText(
            tblHerramientas.getValueAt(
                    fila,
                    1).toString());

    txtNombreHerramienta.setText(
            tblHerramientas.getValueAt(
                    fila,
                    2).toString());

    cmbEstadoConservacion.setSelectedItem(
            tblHerramientas.getValueAt(
                    fila,
                    3).toString());

    cmbDisponibilidad.setSelectedItem(
            tblHerramientas.getValueAt(
                    fila,
                    4).toString());
    }//GEN-LAST:event_tblHerramientasMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    try{
        int fila = tblHerramientas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una herramienta de la tabla");
            return;
        }
        if (!validarCamposHerramienta()) {
            return;
        }

        int id =
                Integer.parseInt(
                        tblHerramientas
                                .getValueAt(
                                        fila,
                                        0)
                                .toString());

        Connection con =
                ConexionDB.getConexion();

        String sql =
                "UPDATE herramientas "
                + "SET codigo_activo=?,"
                + "nombre_herramienta=?,"
                + "estado_conservacion=?,"
                + "disponibilidad=? "
                + "WHERE id_herramienta=?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(
                1,
                txtCodigoActivo.getText());

        ps.setString(
                2,
                txtNombreHerramienta.getText());

        ps.setString(
                3,
                cmbEstadoConservacion
                        .getSelectedItem()
                        .toString());

        ps.setString(
                4,
                cmbDisponibilidad
                        .getSelectedItem()
                        .toString());

        ps.setInt(5,id);

        ps.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Herramienta actualizada");

        cargarTabla();

    }
    catch(HeadlessException | NumberFormatException | SQLException e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      FrmAsignacionHerramientas frm =
            new FrmAsignacionHerramientas();

    frm.setVisible(true);

    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmHerramientas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbDisponibilidad;
    private javax.swing.JComboBox<String> cmbEstadoConservacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane tableHerramientas;
    private javax.swing.JTable tblHerramientas;
    private javax.swing.JTextField txtCodigoActivo;
    private javax.swing.JTextField txtNombreHerramienta;
    // End of variables declaration//GEN-END:variables

    private void cargarTabla() {
          try{

        DefaultTableModel modelo =
                (DefaultTableModel)
                tblHerramientas.getModel();

        modelo.setRowCount(0);

        Connection con =
                ConexionDB.getConexion();

        String sql =
                "SELECT * FROM herramientas";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()){

            modelo.addRow(new Object[]{

                rs.getInt("id_herramienta"),
                rs.getString("codigo_activo"),
                rs.getString("nombre_herramienta"),
                rs.getString("estado_conservacion"),
                rs.getString("disponibilidad")

            });

        }

    }
    catch(Exception e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
    }}
