package com.edutecno.procesaconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;

public class AdministradorConexion {
    protected Connection conn = null;

    protected Connection generaConexion() {
        String usr = "postgres";
        String pwd = "admin1234";  // Cambia esto por tu contraseña
        String url = "jdbc:postgresql://localhost:5432/horoscopo_chino";

        try {
            // Cargar el driver explícitamente
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url, usr, pwd);
            System.out.println("Conexión establecida exitosamente");
        } catch (Exception ex) {
            System.out.println("Error en la conexión: " + ex.getMessage());
            ex.printStackTrace();
        }
        return conn;
    }

    // Para depuración, cambia temporalmente a usar conexión directa en lugar del pool
    protected Connection generaPoolConexion() {
        return generaConexion();  // Usar conexión directa por ahora
    }
}