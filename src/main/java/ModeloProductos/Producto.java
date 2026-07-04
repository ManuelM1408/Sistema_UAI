package ModeloProductos;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock; // <--- Asegúrate de tener esta variable

    // Métodos Getter y Setter para Stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        // Corrección del error común en la asignación
        this.stock = stock; 
    }

    // (Mantén tus getters y setters anteriores de id, nombre y precio aquí abajo)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
