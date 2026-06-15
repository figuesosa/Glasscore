package com.mycompany.examenvidrieria;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

class ConexionDB {

    private static final Properties CONFIG = cargarConfiguracion();
    private static Connection conexion = null;

    private ConexionDB() {
    }

    private static Properties cargarConfiguracion() {
        Properties props = new Properties();
        props.setProperty("servidor", "127.0.0.1");
        props.setProperty("puerto", "1433");
        props.setProperty("base_datos", "db_glasscore_management");
        props.setProperty("usuario", "glasscore_user");
        props.setProperty("contrasena", "Glasscore123");

        try (InputStream interno =
                ConexionDB.class.getResourceAsStream("/config.properties")) {
            if (interno != null) {
                props.load(interno);
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer config interno: " + e.getMessage());
        }

        try (FileInputStream externo = new FileInputStream("config.properties")) {
            props.load(externo);
        } catch (IOException e) {
            // Usa valores por defecto o los del JAR
        }

        return props;
    }

    private static String construirUrl() {
        String servidor = CONFIG.getProperty("servidor", "127.0.0.1").trim();
        String puerto = CONFIG.getProperty("puerto", "").trim();
        String baseDatos = CONFIG.getProperty("base_datos", "db_glasscore_management").trim();

        if (servidor.contains("\\")) {
            return "jdbc:sqlserver://" + servidor + ";"
                    + "databaseName=" + baseDatos + ";"
                    + "encrypt=true;trustServerCertificate=true;loginTimeout=10;";
        }

        if (puerto.isEmpty()) {
            return "jdbc:sqlserver://" + servidor + ";"
                    + "databaseName=" + baseDatos + ";"
                    + "encrypt=true;trustServerCertificate=true;loginTimeout=10;";
        }

        return "jdbc:sqlserver://" + servidor + ":" + puerto + ";"
                + "databaseName=" + baseDatos + ";"
                + "encrypt=true;trustServerCertificate=true;loginTimeout=10;";
    }

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexion = DriverManager.getConnection(
                        construirUrl(),
                        CONFIG.getProperty("usuario"),
                        CONFIG.getProperty("contrasena"));
                System.out.println("Conexion exitosa a GlassCore");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontro el Driver SQL Server: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error SQL al conectar: " + e.getMessage());
            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo conectar a la base de datos.\n"
                    + e.getMessage() + "\n\n"
                    + "Revise INSTALACION.txt y el archivo config.properties");
            conexion = null;
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexion = null;
                System.out.println("Conexion cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexion: " + e.getMessage());
        }
    }
}
