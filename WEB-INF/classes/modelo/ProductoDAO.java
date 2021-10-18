package modelo;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    String sql_listar = "SELECT * FROM producto";
    String sql_buscar_por_ID = "SELECT * FROM producto WHERE codigo_producto = ?";
    String sql_nuevo = "INSERT INTO producto(codigo_producto, iva_compra, nit_proveedor, nombre_producto, precio_compra, precio_venta) VALUES(?, ?, ?, ?, ?, ?)";
    String sql_modificar = "UPDATE producto SET iva_compra=?, nit_proveedor=?, nombre_producto=?, precio_compra=?, precio_venta=? WHERE codigo_producto=?";
    String sql_eliminar = "DELETE FROM producto WHERE codigo_producto = ?";

    Conexion con = new Conexion();
    Connection conn = con.Conectar();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    Producto producto = null;
    List<Producto> productos = new ArrayList<>();

    public List<Producto> listar() {

        try {
            ps = conn.prepareStatement(sql_listar);
            rs = ps.executeQuery();
            while (rs.next()) {
                int codigoProducto = rs.getInt("codigo_producto");
                double ivaCompra = rs.getDouble("iva_compra");
                int nitProveedor = rs.getInt("nit_proveedor");
                String nombreProducto = rs.getString("nombre_producto");
                double precioCompra = rs.getDouble("precio_compra");
                double precioVenta = rs.getDouble("precio_venta");
                producto = new Producto(codigoProducto, ivaCompra, nitProveedor, nombreProducto, precioCompra, precioVenta);
                productos.add(producto);
            }
        } catch (SQLException ex) {
            System.out.println("Error al Listar los Productos = " + ex.getMessage());
        }
        return productos;
    }

    public List buscarBox(String txtBuscar) {

        String sql_cuadro_busqueda = "SELECT * FROM producto WHERE nombre_producto LIKE '%" + txtBuscar + "%' or codigo_producto LIKE '%" + txtBuscar + "%'";
        try {
            ps = conn.prepareStatement(sql_cuadro_busqueda);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto.setCodigoProducto(rs.getInt("codigo_producto"));
                producto.setIvaCompra(rs.getDouble("iva_compra"));
                producto.setNitProveedor(rs.getInt("nit_proveedor"));
                producto.setNombreProducto(rs.getString("nombre_producto"));
                producto.setPrecioCompra(rs.getDouble("precio_compra"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                productos.add(producto);

            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar en la Barra de Busqueda = " + ex.getMessage());
        }
        return productos;
    }

    public Producto buscarPorID(Producto producto) {
        try {
            ps = conn.prepareStatement(sql_buscar_por_ID);
            ps.setInt(1, producto.getCodigoProducto());
            rs = ps.executeQuery();
            while (rs.next()) {
                int codigoProducto = rs.getInt("codigo_producto");
                double ivaCompra = rs.getDouble("iva_compra");
                int nitProveedor = rs.getInt("nit_proveedor");
                String nombreProducto = rs.getString("nombre_producto");
                double precioCompra = rs.getDouble("precio_compra");
                double precioVenta = rs.getDouble("precio_venta");

                producto.setCodigoProducto(codigoProducto);
                producto.setIvaCompra(ivaCompra);
                producto.setNitProveedor(nitProveedor);
                producto.setNombreProducto(nombreProducto);
                producto.setPrecioCompra(precioCompra);
                producto.setPrecioVenta(precioVenta);
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar el Producto = " + ex.getMessage());
        }
        return producto;
    }

    public int nuevo(Producto producto) {

        int resultado = 0;
        try {
            ps = conn.prepareStatement(sql_nuevo);
            ps.setInt(1, producto.getCodigoProducto());
            ps.setDouble(2, producto.getIvaCompra());
            ps.setInt(3, producto.getNitProveedor());
            ps.setString(4, producto.getNombreProducto());
            ps.setDouble(5, producto.getPrecioCompra());
            ps.setDouble(6, producto.getPrecioVenta());

            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al Guardar el Producto = " + ex.getMessage());
        }
        return resultado;
    }

    public boolean modificar(Producto producto) {

        boolean resultado = false;
        try {
            ps = conn.prepareStatement(sql_modificar);
            ps.setDouble(1, producto.getIvaCompra());
            ps.setInt(2, producto.getNitProveedor());
            ps.setString(3, producto.getNombreProducto());
            ps.setDouble(4, producto.getPrecioCompra());
            ps.setDouble(5, producto.getPrecioVenta());
            ps.setInt(6, producto.getCodigoProducto());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error al Modificar el Producto = " + ex.getMessage());
        }
        return resultado;
    }

    public boolean eliminar(Producto producto) {

        boolean resultado = false;

        try {
            ps = conn.prepareStatement(sql_eliminar);
            ps.setInt(1, producto.getCodigoProducto());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error al Eliminar el Producto = " + ex.getMessage());
        }
        return resultado;
    }

    public boolean cargarArchivo(String url) {
        boolean resultado = false;
        try {
            String sql = "load data infile '" + url + "' into table producto fields terminated by ',' lines terminated by '\r\n'";

            ps = conn.prepareStatement(sql);
            //ps.setString(1, url);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error desde ProductoDAO.java = " + ex.getMessage());
        }
        return resultado;
    }
}
