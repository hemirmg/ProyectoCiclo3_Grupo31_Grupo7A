package modelo;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    String sql_listar = "SELECT cedula_cliente, direccion_cliente, email_cliente, nombre_cliente, telefono_cliente FROM cliente";
    String sql_listaCC = "SELECT * from cliente WHERE cedula_cliente LIKE =? or nombre_cliente LIKE =? or email_cliente =?";
    String sql_buscar_por_cc = "SELECT cedula_cliente, direccion_cliente, email_cliente, nombre_cliente, telefono_cliente FROM cliente WHERE cedula_cliente = ?";
    String sql_nuevo = "INSERT INTO cliente(cedula_cliente, direccion_cliente, email_cliente, nombre_cliente, telefono_cliente) VALUES(?, ?, ?, ?, ?)";
    String sql_modificar = "UPDATE cliente SET direccion_cliente=?, email_cliente=?, nombre_cliente=?, telefono_cliente=? WHERE cedula_cliente=?";
    String sql_eliminar = "DELETE FROM cliente WHERE cedula_cliente = ?";

    Conexion con = new Conexion();
    Connection conn = con.Conectar();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    Cliente cliente = null;
    List<Cliente> clientes = new ArrayList<>();

//    public Cliente buscar(String cedula) {
//        String sql = "select * from cliente where cedula_cliente=" + cedula;
//        try {
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                cliente.setCedulaCliente(rs.getInt(1));
//                cliente.setDireccionCliente(rs.getString(2));
//                cliente.setEmailCliente(rs.getString(3));
//                cliente.setNombreCliente(rs.getString(4));
//                cliente.setTelefonoCliente(rs.getString(5));
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error al buscar Cliente = " + ex.getMessage());
//        }
//        return cliente;
//    }

    public List<Cliente> listar() {

        try {
            ps = conn.prepareStatement(sql_listar);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cedulaCliente = rs.getInt("cedula_cliente");
                String direccionCliente = rs.getString("direccion_cliente");
                String emailCliente = rs.getString("email_cliente");
                String nombreCliente = rs.getString("nombre_cliente");
                String telefonoCliente = rs.getString("telefono_cliente");
                cliente = new Cliente(cedulaCliente, direccionCliente, emailCliente, nombreCliente, telefonoCliente);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return clientes;
    }

    public List buscarBox(String txtBuscar) {

        String sql_cuadro_busqueda = "SELECT * FROM cliente WHERE nombre_cliente LIKE '%" + txtBuscar + "%' or telefono_cliente LIKE '%" + txtBuscar + "%' or cedula_cliente LIKE '%" + txtBuscar + "%'";
        try {
            ps = conn.prepareStatement(sql_cuadro_busqueda);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCedulaCliente(rs.getInt("cedula_cliente"));
                cliente.setDireccionCliente(rs.getString("direccion_cliente"));
                cliente.setEmailCliente(rs.getString("email_cliente"));
                cliente.setNombreCliente(rs.getString("nombre_cliente"));
                cliente.setTelefonoCliente(rs.getString("telefono_cliente"));
                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar en la Barra de Busqueda = " + ex.getMessage());
        }
        return clientes;
    }

    public Cliente buscarPorCC(Cliente cliente) {

        try {
            ps = conn.prepareStatement(sql_buscar_por_cc);
            ps.setInt(1, cliente.getCedulaCliente());
            rs = ps.executeQuery();
            while (rs.next()) {
                int cedula = rs.getInt("cedula_cliente");
                String direccion = rs.getString("direccion_cliente");
                String email = rs.getString("email_cliente");
                String nombre = rs.getString("nombre_cliente");
                String telefono = rs.getString("telefono_cliente");

                cliente.setCedulaCliente(cedula);
                cliente.setDireccionCliente(direccion);
                cliente.setEmailCliente(email);
                cliente.setNombreCliente(nombre);
                cliente.setTelefonoCliente(telefono);
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar Cliente x CC = " + ex.getMessage());
        }
        return cliente;
    }

    public int nuevo(Cliente cliente) {

        int resultado = 0;
        try {
            ps = conn.prepareStatement(sql_nuevo);
            ps.setInt(1, cliente.getCedulaCliente());
            ps.setString(2, cliente.getDireccionCliente());
            ps.setString(3, cliente.getEmailCliente());
            ps.setString(4, cliente.getNombreCliente());
            ps.setString(5, cliente.getTelefonoCliente());

            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    public boolean modificar(Cliente cliente) {

        boolean resultado = false;
        try {
            ps = conn.prepareStatement(sql_modificar);
            ps.setString(1, cliente.getDireccionCliente());
            ps.setString(2, cliente.getEmailCliente());
            ps.setString(3, cliente.getNombreCliente());
            ps.setString(4, cliente.getTelefonoCliente());
            ps.setInt(5, cliente.getCedulaCliente());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    public boolean eliminar(Cliente cliente) {

        boolean resultado = false;

        try {
            ps = conn.prepareStatement(sql_eliminar);
            ps.setInt(1, cliente.getCedulaCliente());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }
}
