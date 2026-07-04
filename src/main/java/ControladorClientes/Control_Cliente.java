/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorClientes;

import ControladorLogin.ConexionMySql;
import ModeloClientes.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Control_Cliente {

    public List<Cliente> obtenerListaClientes() {
        List<Cliente> lista = new ArrayList<>();
        // CORREGIDO: Tabla y columnas según tu phpMyAdmin
        String sql = "SELECT idCliente, Nombre_Cliente, Apellido_Cliente FROM table_cliente";

        ConexionMySql mysql = new ConexionMySql();

        try (Connection conn = mysql.conectar(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                // CORREGIDO: Mapeo con las etiquetas exactas de la BD
                c.setId(rs.getInt("idCliente"));
                c.setNombre(rs.getString("Nombre_Cliente"));
                c.setApellido(rs.getString("Apellido_Cliente"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
            e.printStackTrace(); 
        }
        return lista;
    }

    public boolean registrarCliente(ModeloClientes.Cliente c) {
        // CORREGIDO: Estructura de inserción para table_cliente
        String sql = "INSERT INTO table_cliente (Nombre_Cliente, Apellido_Cliente) VALUES (?, ?)";
        ControladorLogin.ConexionMySql mysql = new ControladorLogin.ConexionMySql();
        try (java.sql.Connection conn = mysql.conectar(); java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, c.getNombre());
            pst.setString(2, c.getApellido());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO PARA ACTUALIZAR
    public boolean actualizarCliente(ModeloClientes.Cliente c) {
        // CORREGIDO: Estructura de actualización para table_cliente
        String sql = "UPDATE table_cliente SET Nombre_Cliente = ?, Apellido_Cliente = ? WHERE idCliente = ?";
        ControladorLogin.ConexionMySql mysql = new ControladorLogin.ConexionMySql();
        try (java.sql.Connection conn = mysql.conectar(); java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, c.getNombre());
            pst.setString(2, c.getApellido());
            pst.setInt(3, c.getId());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO PARA ELIMINAR
    public boolean eliminarCliente(int id) {
        // CORREGIDO: Estructura de eliminación para table_cliente
        String sql = "DELETE FROM table_cliente WHERE idCliente = ?";
        ControladorLogin.ConexionMySql mysql = new ControladorLogin.ConexionMySql();
        try (java.sql.Connection conn = mysql.conectar(); java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
