package com.mycompany.examenvidrieria;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmPlanillas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPlanillas.class.getName());
    public FrmPlanillas() {
    initComponents();
    setLocationRelativeTo(null);
    setTitle("Glasscore - Planillas");

    cargarEmpleados();
    cargarTabla();
    }
    public void cargarEmpleados() {

          if(cmbEmpleado == null){
        return;
    }
    try {

        Connection con = ConexionDB.getConexion();

        String sql =
                "SELECT id_empleado, nombre, apellido " +
                "FROM empleados " +
                "WHERE estado='Activo'";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        cmbEmpleado.removeAllItems();

        while(rs.next()){

            cmbEmpleado.addItem(
                    rs.getInt("id_empleado")
                    + " - "
                    + rs.getString("nombre")
                    + " "
                    + rs.getString("apellido"));
        }

    } catch(Exception e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbEmpleado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtSalarioBase = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHorasExtras = new javax.swing.JTextField();
        txtViaticos = new javax.swing.JTextField();
        txtTotalNeto = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTotal = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnRegresar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(54, 54, 196));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GLASSCORE - PLANILLAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1)
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 630, 70);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Empleado:");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(80, 40, 84, 25);

        cmbEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEmpleado.addActionListener(this::cmbEmpleadoActionPerformed);
        jPanel3.add(cmbEmpleado);
        cmbEmpleado.setBounds(220, 40, 265, 25);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Horas Extras:");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(80, 130, 102, 25);
        jPanel3.add(txtSalarioBase);
        txtSalarioBase.setBounds(220, 80, 265, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Salario Base:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(80, 90, 98, 25);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Viaticos:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(80, 180, 65, 25);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Total Neto:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(80, 230, 87, 25);

        txtHorasExtras.addActionListener(this::txtHorasExtrasActionPerformed);
        jPanel3.add(txtHorasExtras);
        txtHorasExtras.setBounds(220, 130, 265, 30);

        txtViaticos.addActionListener(this::txtViaticosActionPerformed);
        jPanel3.add(txtViaticos);
        txtViaticos.setBounds(220, 180, 265, 30);

        txtTotalNeto.addActionListener(this::txtTotalNetoActionPerformed);
        jPanel3.add(txtTotalNeto);
        txtTotalNeto.setBounds(220, 230, 265, 30);

        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(this::btnCalcularActionPerformed);
        jPanel3.add(btnCalcular);
        btnCalcular.setBounds(80, 290, 113, 43);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        jPanel3.add(btnGuardar);
        btnGuardar.setBounds(220, 290, 118, 44);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        jPanel3.add(btnLimpiar);
        btnLimpiar.setBounds(370, 290, 120, 44);

        tblTotal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "id_Empleado", "Nombre Empleado", "Fecha_Pago", "Salario_Base", "Monto_HorasExtras", "Monto_Viaticos", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblTotal);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(10, 360, 590, 239);
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(10, 360, 590, 10);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        jPanel3.add(btnRegresar);
        btnRegresar.setBounds(40, 10, 520, 23);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 70, 610, 610);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHorasExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHorasExtrasActionPerformed
    }//GEN-LAST:event_txtHorasExtrasActionPerformed

    private void txtViaticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtViaticosActionPerformed
    }//GEN-LAST:event_txtViaticosActionPerformed

    private void txtTotalNetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalNetoActionPerformed
    }//GEN-LAST:event_txtTotalNetoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    try{
    if (cmbEmpleado.getSelectedItem() == null
            || txtSalarioBase.getText().trim().isEmpty()
            || txtHorasExtras.getText().trim().isEmpty()
            || txtViaticos.getText().trim().isEmpty()
            || txtTotalNeto.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Complete todos los campos y calcule el total");
        return;
    }

    String dato =
            cmbEmpleado
            .getSelectedItem()
            .toString();

    int idEmpleado =
            Integer.parseInt(
                    dato.split("-")[0].trim());

    double salario =
            Double.parseDouble(
                    txtSalarioBase.getText());

    double extras =
            Double.parseDouble(
                    txtHorasExtras.getText());

    double viaticos =
            Double.parseDouble(
                    txtViaticos.getText());

    double total =
            Double.parseDouble(
                    txtTotalNeto.getText());

    Connection con =
            ConexionDB.getConexion();

    String sql =
            "INSERT INTO planillas "
            + "(id_empleado,"
            + "fecha_pago,"
            + "salario_base,"
            + "monto_horas_extras,"
            + "monto_viaticos,"
            + "total_neto)"
            + "VALUES"
            + "(?,GETDATE(),?,?,?,?)";

    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setInt(1,idEmpleado);
    ps.setDouble(2,salario);
    ps.setDouble(3,extras);
    ps.setDouble(4,viaticos);
    ps.setDouble(5,total);

    ps.executeUpdate();

    JOptionPane.showMessageDialog(
        this,
        "Planilla guardada");

    cargarTabla();

    txtHorasExtras.setText("");
    txtViaticos.setText("");
    txtTotalNeto.setText("");

}
catch(HeadlessException | NumberFormatException | SQLException e){

    JOptionPane.showMessageDialog(
            this,
            e.getMessage());
}
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEmpleadoActionPerformed
   if(cmbEmpleado == null ||
       cmbEmpleado.getSelectedItem() == null){
        return;
    }

    try {

        String dato =
                cmbEmpleado.getSelectedItem().toString();

        int idEmpleado =
                Integer.parseInt(
                        dato.split("-")[0].trim());

        Connection con =
                ConexionDB.getConexion();

        String sql =
                "SELECT salario_base "
                + "FROM empleados "
                + "WHERE id_empleado=?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1,idEmpleado);

        ResultSet rs =
                ps.executeQuery();

        if(rs.next()){

            txtSalarioBase.setText(
                    rs.getString(
                            "salario_base"));
        }

    } catch(NumberFormatException | SQLException e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }

    }//GEN-LAST:event_cmbEmpleadoActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
    try{
    if (txtSalarioBase.getText().trim().isEmpty()
            || txtHorasExtras.getText().trim().isEmpty()
            || txtViaticos.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese salario, horas extras y viaticos");
        return;
    }

    double salario =
            Double.parseDouble(
                    txtSalarioBase.getText());

    double extras =
            Double.parseDouble(
                    txtHorasExtras.getText());

    double viaticos =
            Double.parseDouble(
                    txtViaticos.getText());

    double total =
            salario +
            extras +
            viaticos;

    txtTotalNeto.setText(
            String.format("%.2f", total));

}
catch(NumberFormatException e){

    JOptionPane.showMessageDialog(
            this,
            "Datos incorrectos");
}
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    cmbEmpleado.setSelectedItem(null);

    txtSalarioBase.setText("");
    txtHorasExtras.setText("");
    txtViaticos.setText("");
    txtTotalNeto.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
    PanelPrincipal dashboard =
        new PanelPrincipal();

    dashboard.setVisible(true);

    this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FrmPlanillas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbEmpleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblTotal;
    private javax.swing.JTextField txtHorasExtras;
    private javax.swing.JTextField txtSalarioBase;
    private javax.swing.JTextField txtTotalNeto;
    private javax.swing.JTextField txtViaticos;
    // End of variables declaration//GEN-END:variables

    private void cargarTabla() {
         try{

        DefaultTableModel modelo =
                (DefaultTableModel)
                tblTotal.getModel();

        modelo.setRowCount(0);

        Connection con =
                ConexionDB.getConexion();

        String sql =
                "SELECT * FROM vw_planillas_empleados";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()){

            modelo.addRow(new Object[]{
                rs.getInt("id_planilla"),
                rs.getInt("id_empleado"),
                rs.getString("empleado"),
                rs.getDate("fecha_pago"),
                rs.getDouble("salario_base"),
                rs.getDouble("monto_horas_extras"),
                rs.getDouble("monto_viaticos"),
                rs.getDouble("total_neto")

            });
        }

    }
    catch(Exception e){

        JOptionPane.showMessageDialog(
                this,
                e.getMessage());
    }
    }
}
