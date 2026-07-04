/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorLogin;

import ModeloVendedor.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ControladorLogin.ConexionMySql;

/**
 *
 * @author ASUS
 */
public class Control_Vendedor {

    public Vendedor validarLogin(String nombre, String password) {
        // Consulta corregida con la tabla y columnas reales de tu base de datos
        String sql = "SELECT idVendedor, nombreVendedor FROM table_vendedor WHERE nombreVendedor = ? AND password = ?";
        ConexionMySql mysql = new ConexionMySql();

        Connection conn = mysql.conectar();

        if (conn == null) {
            System.out.println("No se puede iniciar sesión: La conexión a la base de datos falló.");
            return null;
        }

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nombre);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Vendedor v = new Vendedor();
                    // Se mapean los nombres de columna correctos a tu objeto Vendedor
                    v.setId(rs.getInt("idVendedor"));
                    v.setNombre(rs.getString("nombreVendedor"));

                    // Nota: Eliminé v.setRol() porque la tabla 'table_vendedor' no tiene columna de rol en la imagen.
                    // ASIGNA UN ROL POR DEFECTO AQUÍ PARA EVITAR EL NULL
                    v.setRol("Administrador");
                    return v;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta de login: " + e.getMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return null;

    }
}
