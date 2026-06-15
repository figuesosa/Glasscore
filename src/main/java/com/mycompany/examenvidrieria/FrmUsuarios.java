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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmUsuarios extends JFrame {

    private JComboBox<String> cmbEmpleado;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTable tblUsuarios;
    private int idSeleccionado = -1;

    public FrmUsuarios() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Glasscore - Usuarios");
        cargarEmpleadosSinUsuario();
        cargarTabla();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 550);

        JPanel header = new JPanel();
        header.setBackground(new java.awt.Color(89, 165, 241));
        JLabel titulo = new JLabel("GLASSCORE - USUARIOS");
        titulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        header.add(titulo);

        JPanel form = new JPanel(null);
        form.setBorder(javax.swing.BorderFactory.createTitledBorder("Credenciales de acceso"));
        form.setBounds(0, 0, 830, 160);

        form.add(new JLabel("Empleado:")).setBounds(20, 30, 80, 22);
        cmbEmpleado = new JComboBox<>();
        form.add(cmbEmpleado).setBounds(110, 30, 300, 22);

        form.add(new JLabel("Username:")).setBounds(20, 70, 80, 22);
        txtUsername = new JTextField();
        form.add(txtUsername).setBounds(110, 70, 200, 22);

        form.add(new JLabel("Password:")).setBounds(330, 70, 80, 22);
        txtPassword = new JPasswordField();
        form.add(txtPassword).setBounds(410, 70, 200, 22);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 110, 100, 30);
        btnGuardar.addActionListener(e -> btnGuardarActionPerformed());
        form.add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(130, 110, 100, 30);
        btnActualizar.addActionListener(e -> btnActualizarActionPerformed());
        form.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(240, 110, 100, 30);
        btnEliminar.addActionListener(e -> btnEliminarActionPerformed());
        form.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(350, 110, 100, 30);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        form.add(btnLimpiar);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(460, 110, 100, 30);
        btnRegresar.addActionListener(e -> {
            new PanelPrincipal().setVisible(true);
            dispose();
        });
        form.add(btnRegresar);

        tblUsuarios = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "Username", "Id Empleado", "Nombre", "Apellido", "Correo", "Rol"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargarFilaSeleccionada();
            }
        });
        JScrollPane scroll = new JScrollPane(tblUsuarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addComponent(form, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(form, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE));
    }

    private boolean validarCampos(boolean requierePassword) {
        if (cmbEmpleado.getSelectedItem() == null
                || txtUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione empleado e ingrese username");
            return false;
        }
        if (requierePassword && new String(txtPassword.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la contrasena");
            return false;
        }
        return true;
    }

    private int obtenerIdEmpleado() {
        return Integer.parseInt(cmbEmpleado.getSelectedItem().toString().split("-")[0].trim());
    }

    private void btnGuardarActionPerformed() {
        if (!validarCampos(true)) {
            return;
        }
        try {
            Connection con = ConexionDB.getConexion();
            String sql = "INSERT INTO usuarios (id_empleado, username, password_hash) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obtenerIdEmpleado());
            ps.setString(2, txtUsername.getText().trim());
            ps.setString(3, new String(txtPassword.getPassword()).trim() + "_hash");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuario guardado");
            limpiarCampos();
            cargarEmpleadosSinUsuario();
            cargarTabla();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnActualizarActionPerformed() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla");
            return;
        }
        if (!validarCampos(false)) {
            return;
        }
        try {
            Connection con = ConexionDB.getConexion();
            String password = new String(txtPassword.getPassword()).trim();
            PreparedStatement ps;
            if (password.isEmpty()) {
                String sql = "UPDATE usuarios SET id_empleado=?, username=? WHERE id_usuario=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, obtenerIdEmpleado());
                ps.setString(2, txtUsername.getText().trim());
                ps.setInt(3, idSeleccionado);
            } else {
                String sql = "UPDATE usuarios SET id_empleado=?, username=?, password_hash=? WHERE id_usuario=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, obtenerIdEmpleado());
                ps.setString(2, txtUsername.getText().trim());
                ps.setString(3, password + "_hash");
                ps.setInt(4, idSeleccionado);
            }
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuario actualizado");
            limpiarCampos();
            cargarEmpleadosSinUsuario();
            cargarTabla();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnEliminarActionPerformed() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla");
            return;
        }
        try {
            Connection con = ConexionDB.getConexion();
            String sql = "DELETE FROM usuarios WHERE id_usuario=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSeleccionado);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuario eliminado");
            limpiarCampos();
            cargarEmpleadosSinUsuario();
            cargarTabla();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void limpiarCampos() {
        idSeleccionado = -1;
        txtUsername.setText("");
        txtPassword.setText("");
        cargarEmpleadosSinUsuario();
    }

    private void cargarFilaSeleccionada() {
        int fila = tblUsuarios.getSelectedRow();
        if (fila == -1) {
            return;
        }
        idSeleccionado = Integer.parseInt(tblUsuarios.getValueAt(fila, 0).toString());
        txtUsername.setText(tblUsuarios.getValueAt(fila, 1).toString());
        int idEmpleado = Integer.parseInt(tblUsuarios.getValueAt(fila, 2).toString());
        String nombre = tblUsuarios.getValueAt(fila, 3).toString();
        String apellido = tblUsuarios.getValueAt(fila, 4).toString();
        cargarEmpleadosParaEdicion(idEmpleado, nombre, apellido);
        txtPassword.setText("");
    }

    private void cargarEmpleadosSinUsuario() {
        try {
            cmbEmpleado.removeAllItems();
            Connection con = ConexionDB.getConexion();
            String sql = "SELECT e.id_empleado, e.nombre, e.apellido "
                    + "FROM empleados e "
                    + "LEFT JOIN usuarios u ON e.id_empleado = u.id_empleado "
                    + "WHERE u.id_usuario IS NULL AND e.estado = 'Activo'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbEmpleado.addItem(rs.getInt("id_empleado") + " - "
                        + rs.getString("nombre") + " " + rs.getString("apellido"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarEmpleadosParaEdicion(int idEmpleado, String nombre, String apellido) {
        try {
            cmbEmpleado.removeAllItems();
            cmbEmpleado.addItem(idEmpleado + " - " + nombre + " " + apellido);
            Connection con = ConexionDB.getConexion();
            String sql = "SELECT e.id_empleado, e.nombre, e.apellido "
                    + "FROM empleados e "
                    + "LEFT JOIN usuarios u ON e.id_empleado = u.id_empleado "
                    + "WHERE u.id_usuario IS NULL AND e.estado = 'Activo'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbEmpleado.addItem(rs.getInt("id_empleado") + " - "
                        + rs.getString("nombre") + " " + rs.getString("apellido"));
            }
            cmbEmpleado.setSelectedIndex(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarTabla() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tblUsuarios.getModel();
            modelo.setRowCount(0);
            Connection con = ConexionDB.getConexion();
            String sql = "SELECT u.id_usuario, u.username, e.id_empleado, e.nombre, e.apellido, "
                    + "e.correo, r.nombre_rol "
                    + "FROM usuarios u "
                    + "INNER JOIN empleados e ON u.id_empleado = e.id_empleado "
                    + "INNER JOIN roles r ON e.id_rol = r.id_rol";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_usuario"),
                    rs.getString("username"),
                    rs.getInt("id_empleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getString("nombre_rol")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
