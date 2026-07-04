package ControladorClientes;

import ControladorLogin.ConexionMySql;
import ModeloVentas.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class Control_Venta {

    public boolean registrarVenta(Venta venta) {
        boolean respuesta = false;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.conectar();

        try {
            cn.setAutoCommit(false); // Transacción segura

            // 1. CORREGIDO: Insertar la cabecera en 'table_detalle' usando las columnas reales
            // Se usa CURDATE() para asignar la fecha del día de hoy de forma automática
            String sqlVenta = "INSERT INTO table_detalle (cliente, fecha, vendedor, totals) VALUES (?, CURDATE(), ?, ?)";
            PreparedStatement pstVenta = cn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            pstVenta.setInt(1, venta.getIdCliente());
            pstVenta.setInt(2, venta.getIdVendedor());
            pstVenta.setDouble(3, venta.getTotal());
            pstVenta.executeUpdate();

            // Capturamos el id_detalle generado
            ResultSet rs = pstVenta.getGeneratedKeys();
            int idVentaGenerada = 0;
            if (rs.next()) {
                idVentaGenerada = rs.getInt(1);
            }

            // 2. CORREGIDO: El detalle real se guarda en la tabla 'table_ventas'
            String sqlDetalle = "INSERT INTO table_ventas (id_detalle, Productos, cantidad, importe) VALUES (?, ?, ?, ?)";
            PreparedStatement pstDetalle = cn.prepareStatement(sqlDetalle);

            // Descuento de stock en 'table_productos'
            String sqlStock = "UPDATE table_productos SET stock = stock - ? WHERE idProductos = ?";
            PreparedStatement pstStock = cn.prepareStatement(sqlStock);

            // 3. Recorremos el carrito
            for (Object[] fila : venta.getDetalles()) {
                // A. Guardamos en table_ventas
                pstDetalle.setInt(1, idVentaGenerada);
                pstDetalle.setInt(2, Integer.parseInt(fila[0].toString()));    // Productos (id)
                pstDetalle.setInt(3, Integer.parseInt(fila[3].toString()));    // cantidad
                pstDetalle.setDouble(4, Double.parseDouble(fila[4].toString())); // importe (subtotal)
                pstDetalle.executeUpdate();

                // B. Restar el stock del inventario
                pstStock.setInt(1, Integer.parseInt(fila[3].toString())); // Cantidad a restar
                pstStock.setInt(2, Integer.parseInt(fila[0].toString())); // idProductos
                pstStock.executeUpdate();
            }

            // 4. Confirmamos la transacción si no hubo fallos
            cn.commit();
            respuesta = true;

        } catch (SQLException e) {
            System.out.println("Error al registrar la venta: " + e.getMessage());
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error en el rollback: " + ex.getMessage());
            }
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }

        return respuesta;
    }
}
