package ControladorProductos;

import ControladorLogin.ConexionMySql;
import ModeloProductos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Control_Producto {

    // 1. LEER (Con Stock real de la BD)
    public List<Producto> obtenerListaProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT idProductos, nombreProductos, preciosProductos, stock FROM table_productos";
        ConexionMySql mysql = new ConexionMySql();
        try (Connection conn = mysql.conectar(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("idProductos"));
                p.setNombre(rs.getString("nombreProductos"));
                p.setPrecio(rs.getDouble("preciosProductos"));
                p.setStock(rs.getInt("stock")); // Lee el stock real
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // 2. CREAR NUEVO (Inserta el Stock)
    public boolean registrarProducto(Producto p) {
        String sql = "INSERT INTO table_productos (nombreProductos, preciosProductos, stock) VALUES (?, ?, ?)";
        ConexionMySql mysql = new ConexionMySql();
        try (Connection conn = mysql.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, p.getNombre());
            pst.setDouble(2, p.getPrecio());
            pst.setInt(3, p.getStock()); // Envía el stock
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    // 3. ACTUALIZAR (Actualiza el Stock)
    public boolean actualizarProducto(Producto p) {
        String sql = "UPDATE table_productos SET nombreProductos = ?, preciosProductos = ?, stock = ? WHERE idProductos = ?";
        ConexionMySql mysql = new ConexionMySql();
        try (Connection conn = mysql.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, p.getNombre());
            pst.setDouble(2, p.getPrecio());
            pst.setInt(3, p.getStock()); // Actualiza el stock
            pst.setInt(4, p.getId());
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // 4. ELIMINAR (Se mantiene igual)
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM table_productos WHERE idProductos = ?";
        ConexionMySql mysql = new ConexionMySql();
        try (Connection conn = mysql.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}
