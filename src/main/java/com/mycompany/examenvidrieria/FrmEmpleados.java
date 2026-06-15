package com.mycompany.examenvidrieria;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmEmpleados extends JFrame {

    private JTextField txtIdentidad;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtSalarioBase;
    private JComboBox<String> cmbRol;
    private JComboBox<String> cmbEstado;
    private JTable tblEmpleados;
    private int idSeleccionado = -1;

    public FrmEmpleados() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Glasscore - Empleados");
        cargarRoles();
        cargarTabla();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 620);

        JPanel header = new JPanel();
        header.setBackground(new java.awt.Color(19, 74, 158));
        JLabel titulo = new JLabel("GLASSCORE - EMPLEADOS");
        titulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        titulo.setForeground(java.awt.Color.WHITE);
        header.add(titulo);

        JPanel form = new JPanel(null);
        form.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del empleado"));
        form.setBounds(0, 0, 880, 200);

        form.add(new JLabel("Identidad:")).setBounds(20, 30, 80, 22);
        txtIdentidad = new JTextField();
        form.add(txtIdentidad).setBounds(110, 30, 150, 22);

        form.add(new JLabel("Nombre:")).setBounds(280, 30, 60, 22);
        txtNombre = new JTextField();
        form.add(txtNombre).setBounds(350, 30, 150, 22);

        form.add(new JLabel("Apellido:")).setBounds(520, 30, 60, 22);
        txtApellido = new JTextField();
        form.add(txtApellido).setBounds(590, 30, 150, 22);

        form.add(new JLabel("Telefono:")).setBounds(20, 70, 80, 22);
        txtTelefono = new JTextField();
        form.add(txtTelefono).setBounds(110, 70, 150, 22);

        form.add(new JLabel("Correo:")).setBounds(280, 70, 60, 22);
        txtCorreo = new JTextField();
        form.add(txtCorreo).setBounds(350, 70, 200, 22);

        form.add(new JLabel("Salario:")).setBounds(570, 70, 60, 22);
        txtSalarioBase = new JTextField();
        form.add(txtSalarioBase).setBounds(630, 70, 110, 22);

        form.add(new JLabel("Rol:")).setBounds(20, 110, 80, 22);
        cmbRol = new JComboBox<>();
        form.add(cmbRol).setBounds(110, 110, 150, 22);

        form.add(new JLabel("Estado:")).setBounds(280, 110, 60, 22);
        cmbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        form.add(cmbEstado).setBounds(350, 110, 150, 22);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 150, 100, 30);
        btnGuardar.addActionListener(e -> btnGuardarActionPerformed());
        form.add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(130, 150, 100, 30);
        btnActualizar.addActionListener(e -> btnActualizarActionPerformed());
        form.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(240, 150, 100, 30);
        btnEliminar.addActionListener(e -> btnEliminarActionPerformed());
        form.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(350, 150, 100, 30);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        form.add(btnLimpiar);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(460, 150, 100, 30);
        btnRegresar.addActionListener(e -> {
            new PanelPrincipal().setVisible(true);
            dispose();
        });
        form.add(btnRegresar);

        tblEmpleados = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "Identidad", "Nombre", "Apellido", "Telefono", "Correo", "Salario", "Estado", "Rol"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargarFilaSeleccionada();
            }
        });
        JScrollPane scroll = new JScrollPane(tblEmpleados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addComponent(form, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(form, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE));
    }

    private boolean validarCampos() {
        if (txtIdentidad.getText().trim().isEmpty()
                || txtNombre.getText().trim().isEmpty()
                || txtApellido.getText().trim().isEmpty()
                || txtCorreo.getText().trim().isEmpty()
                || txtSalarioBase.getText().trim().isEmpty()
                || cmbRol.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios");
            return false;
        }
        if (contieneNumeros(txtNombre.getText()) || contieneNumeros(txtApellido.getText())) {
            JOptionPane.showMessageDialog(this, "Nombre y apellido no pueden contener numeros");
            return false;
        }
        try {
            double salario = Double.parseDouble(txtSalarioBase.getText().trim());
            if (salario < 0) {
                JOptionPane.showMessageDialog(this, "El salario no puede ser negativo");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un salario valido");
            return false;
        }
        return true;
    }

    private boolean contieneNumeros(String texto) {
        return texto.matches(".*\\d.*");
    }

    private int obtenerIdRol() {
        String item = cmbRol.getSelectedItem().toString();
        return Integer.parseInt(item.split("-")[0].trim());
    }

    private void btnGuardarActionPerformed() {
        if (!validarCampos()) {
            return;
        }
        try {
            Connection con = ConexionDB.getConexion();
            String sql = "INSERT INTO empleados "
                    + "(id_rol, identidad, nombre, apellido, telefono, correo, salario_base, estado) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obtenerIdRol());
            ps.setString(2, txtIdentidad.getText().trim());
            ps.setString(3, txtNombre.getText().trim());
            ps.setString(4, txtApellido.getText().trim());
            ps.setString(5, txtTelefono.getText().trim());
            ps.setString(6, txtCorreo.getText().trim());
            ps.setDouble(7, Double.parseDouble(txtSalarioBase.getText().trim()));
            ps.setString(8, cmbEstado.getSelectedItem().toString());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Empleado guardado");
            limpiarCampos();
            cargarTabla();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnActualizarActionPerformed() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla");
            return;
        }
        if (!validarCampos()) {
            return;
        }
        try {
            Connection con = ConexionDB.getConexion();
            String sql = "UPDATE empleados SET id_rol=?, identidad=?, nombre=?, apellido=?, "
                    + "telefono=?, correo=?, salario_base=?, estado=? WHERE id_empleado=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obtenerIdRol());
            ps.setString(2, txtIdentidad.getText().trim());
            ps.setString(3, txtNombre.getText().trim());
            ps.setString(4, txtApellido.getText().trim());
            ps.setString(5, txtTelefono.getText().trim());
            ps.setString(6, txtCorreo.getText().trim());
            ps.setDouble(7, Double.parseDouble(txtSalarioBase.getText().trim()));
            ps.setString(8, cmbEstado.getSelectedItem().toString());
            ps.setInt(9, idSeleccionado);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Empleado actualizado");
            limpiarCampos();
            cargarTabla();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnEliminarActionPerformed() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla");
            return;
        }
        try {
            Connection con = ConexionDB.getConexion();
            String sql = "DELETE FROM empleados WHERE id_empleado=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSeleccionado);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Empleado eliminado");
            limpiarCampos();
            cargarTabla();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void limpiarCampos() {
        idSeleccionado = -1;
        txtIdentidad.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtSalarioBase.setText("");
        if (cmbRol.getItemCount() > 0) {
            cmbRol.setSelectedIndex(0);
        }
        cmbEstado.setSelectedIndex(0);
    }

    private void cargarFilaSeleccionada() {
        int fila = tblEmpleados.getSelectedRow();
        if (fila == -1) {
            return;
        }
        idSeleccionado = Integer.parseInt(tblEmpleados.getValueAt(fila, 0).toString());
        txtIdentidad.setText(tblEmpleados.getValueAt(fila, 1).toString());
        txtNombre.setText(tblEmpleados.getValueAt(fila, 2).toString());
        txtApellido.setText(tblEmpleados.getValueAt(fila, 3).toString());
        txtTelefono.setText(tblEmpleados.getValueAt(fila, 4) != null
                ? tblEmpleados.getValueAt(fila, 4).toString() : "");
        txtCorreo.setText(tblEmpleados.getValueAt(fila, 5).toString());
        txtSalarioBase.setText(tblEmpleados.getValueAt(fila, 6).toString());
        cmbEstado.setSelectedItem(tblEmpleados.getValueAt(fila, 7).toString());
        String rolNombre = tblEmpleados.getValueAt(fila, 8).toString();
        for (int i = 0; i < cmbRol.getItemCount(); i++) {
            if (cmbRol.getItemAt(i).contains("- " + rolNombre)) {
                cmbRol.setSelectedIndex(i);
                break;
            }
        }
    }

    private void cargarRoles() {
        try {
            Connection con = ConexionDB.getConexion();
            if (con == null) {
                return;
            }
            cmbRol.removeAllItems();
            PreparedStatement ps = con.prepareStatement("SELECT id_rol, nombre_rol FROM roles");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbRol.addItem(rs.getInt("id_rol") + " - " + rs.getString("nombre_rol"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarTabla() {
        try {
            Connection con = ConexionDB.getConexion();
            if (con == null) {
                return;
            }
            DefaultTableModel modelo = (DefaultTableModel) tblEmpleados.getModel();
            modelo.setRowCount(0);
            String sql = "SELECT e.id_empleado, e.identidad, e.nombre, e.apellido, e.telefono, "
                    + "e.correo, e.salario_base, e.estado, r.nombre_rol "
                    + "FROM empleados e INNER JOIN roles r ON e.id_rol = r.id_rol";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_empleado"),
                    rs.getString("identidad"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getDouble("salario_base"),
                    rs.getString("estado"),
                    rs.getString("nombre_rol")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
