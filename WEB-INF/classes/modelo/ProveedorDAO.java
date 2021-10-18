package modelo;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    String sql_listar = "SELECT nit_proveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor FROM proveedores";
    String sql_buscar_por_cc = "SELECT nit_proveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor FROM proveedores WHERE nit_proveedor = ?";
    String sql_nuevo = "INSERT INTO proveedores(nit_proveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor) VALUES(?, ?, ?, ?, ?)";
    String sql_modificar = "UPDATE proveedores SET ciudad_proveedor=?, direccion_proveedor=?, nombre_proveedor=?, telefono_proveedor=? WHERE nit_proveedor=?";
    String sql_eliminar = "DELETE FROM proveedores WHERE nit_proveedor = ?";

    Conexion con = new Conexion();
    Connection conn = con.Conectar();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    Proveedor proveedor = null;
    List<Proveedor> proveedores = new ArrayList<>();

    public List<Proveedor> listar() {

        try {
            ps = conn.prepareStatement(sql_listar);
            rs = ps.executeQuery();
            while (rs.next()) {
                int nitProveedor = rs.getInt("nit_proveedor");
                String ciudadProveedor = rs.getString("ciudad_proveedor");
                String direccionProveedor = rs.getString("direccion_proveedor");
                String nombreProveedor = rs.getString("nombre_proveedor");
                String telefonoProveedor = rs.getString("telefono_proveedor");
                proveedor = new Proveedor(nitProveedor, ciudadProveedor, direccionProveedor, nombreProveedor, telefonoProveedor);
                proveedores.add(proveedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return proveedores;
    }

    public List buscarBox(String txtBuscar) {

        String sql_cuadro_busqueda = "SELECT * FROM proveedores WHERE nit_proveedor LIKE '%" + txtBuscar + "%' or nombre_proveedor LIKE '%" + txtBuscar + "%' or telefono_proveedor LIKE '%" + txtBuscar + "%'";
        try {
            ps = conn.prepareStatement(sql_cuadro_busqueda);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setNitProveedor(rs.getInt("nit_proveedor"));
                proveedor.setCiudadProveedor(rs.getString("ciudad_proveedor"));
                proveedor.setDireccionProveedor(rs.getString("direccion_proveedor"));
                proveedor.setNombreProveedor(rs.getString("nombre_proveedor"));
                proveedor.setTelefonoProveedor(rs.getString("telefono_proveedor"));
                proveedores.add(proveedor);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar en la Barra de Busqueda = " + ex.getMessage());
        }
        return proveedores;
    }

    public Proveedor buscarPorCC(Proveedor proveedor) {

        try {
            ps = conn.prepareStatement(sql_buscar_por_cc);
            ps.setInt(1, proveedor.getNitProveedor());
            rs = ps.executeQuery();
            while (rs.next()) {
                int nit = rs.getInt("nit_proveedor");
                String ciudad = rs.getString("ciudad_proveedor");
                String direccion = rs.getString("direccion_proveedor");
                String nombre = rs.getString("nombre_proveedor");
                String telefono = rs.getString("telefono_proveedor");

                proveedor.setNitProveedor(nit);
                proveedor.setCiudadProveedor(ciudad);
                proveedor.setDireccionProveedor(direccion);
                proveedor.setNombreProveedor(nombre);
                proveedor.setTelefonoProveedor(telefono);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return proveedor;
    }

    public int nuevo(Proveedor proveedor) {

        int resultado = 0;
        try {
            ps = conn.prepareStatement(sql_nuevo);
            ps.setInt(1, proveedor.getNitProveedor());
            ps.setString(2, proveedor.getCiudadProveedor());
            ps.setString(3, proveedor.getDireccionProveedor());
            ps.setString(4, proveedor.getNombreProveedor());
            ps.setString(5, proveedor.getTelefonoProveedor());

            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    public boolean modificar(Proveedor proveedor) {

        boolean resultado = false;
        try {
            ps = conn.prepareStatement(sql_modificar);
            ps.setString(1, proveedor.getCiudadProveedor());
            ps.setString(2, proveedor.getDireccionProveedor());
            ps.setString(3, proveedor.getNombreProveedor());
            ps.setString(4, proveedor.getTelefonoProveedor());
            ps.setInt(5, proveedor.getNitProveedor());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    public boolean eliminar(Proveedor proveedor) {

        boolean resultado = false;

        try {
            ps = conn.prepareStatement(sql_eliminar);
            ps.setInt(1, proveedor.getNitProveedor());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }
}
