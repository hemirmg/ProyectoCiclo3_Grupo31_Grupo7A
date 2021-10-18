package modelo;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    String sql_buscar_producto = "SELECT * FROM producto WHERE codigo_producto = ?";

    String sql_max_num = "SELECT MAX(consecutivo) FROM ventas";
    String sql_max_codVentas = "SELECT MAX(codigo_venta) FROM ventas";
    String sql_guardar_V = "INSERT INTO ventas(cedula_cliente, cedula_usuario, sub_total, iva_venta, total_venta, consecutivo)values(?,?,?,?,?,?)";
    String sql_guardar_DV = "INSERT INTO detalle_ventas(cantidad_producto, codigo_producto, codigo_venta, valor_total, valor_iva)values(?,?,?,?,?)";
    String sql_eliminar = " DELETE ventas,detalle_ventas FROM ventas JOIN detalle_ventas ON ventas.codigo_venta=detalle_ventas.codigo_venta WHERE ventas.codigo_venta=?";
    String sql_listar = "SELECT ventas.codigo_venta, ventas.cedula_cliente, ventas.total_venta, ventas.consecutivo, cliente.nombre_cliente, cliente.direccion_cliente, cliente.telefono_cliente FROM ventas LEFT JOIN cliente ON ventas.cedula_cliente = cliente.cedula_cliente";
    String sql_buscar_V = "SELECT * FROM ventas INNER JOIN detalle_ventas ON ventas.codigo_venta = detalle_ventas.codigo_venta WHERE ventas.codigo_venta=?";
    String sql_buscar_V_cliente = "SELECT * FROM ventas INNER JOIN cliente ON ventas.cedula_cliente = cliente.cedula_cliente WHERE ventas.cedula_cliente=?";
    String sql_consultar_factura = "SELECT ventas.codigo_venta, ventas.cedula_cliente, ventas.cedula_usuario, ventas.sub_total, ventas.iva_venta, ventas.total_venta, ventas.consecutivo, detalle_ventas.cantidad_producto, detalle_ventas.codigo_producto, detalle_ventas.codigo_venta, detalle_ventas.valor_total, detalle_ventas.valor_iva, producto.codigo_producto, producto.nombre_producto, producto.precio_venta FROM detalle_ventas inner JOIN ventas ON detalle_ventas.codigo_venta = ventas.codigo_venta inner JOIN producto ON detalle_ventas.codigo_producto = producto.codigo_producto";

    Conexion con = new Conexion();
    Connection conn = con.Conectar();
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    PreparedStatement ps2 = null;
    ResultSet rs2 = null;
    Venta venta = null;
    List<Venta> ventas = new ArrayList<>();
    int num, Resultado;
    String numero;

    //Primero Consultamos el El Código Máximo que esta en la Base de Datos
    public String numeroMaximo() {
        String numMax = "";
        try {
            ps = conn.prepareStatement(sql_max_num);
            rs = ps.executeQuery();
            while (rs.next()) {
                numMax = rs.getString(1);
                System.out.println("encuentra el consecutivo Máximo = " + numMax);
            }
        } catch (SQLException ex) {
            System.out.println("Error al Buscar el consecutivo Máximo = " + ex.getMessage());
        }
        return numMax;
    }

    public String numConsecutivo(int num) {
        this.num = num + 1;
        if (this.num < 10) {
            numero = "0000000" + this.num;
        }
        if ((this.num >= 10) && (this.num <= 100)) {
            numero = "000000" + this.num;
        }
        if ((this.num >= 100) && (this.num <= 1000)) {
            numero = "00000" + this.num;
        }
        if ((this.num >= 1000) && (this.num <= 10000)) {
            numero = "0000" + this.num;
        }
        if ((this.num >= 10000) && (this.num <= 100000)) {
            numero = "000" + this.num;
        }
        if ((this.num >= 100000) && (this.num <= 1000000)) {
            numero = "00" + this.num;
        }
        if ((this.num >= 1000000) && (this.num <= 10000000)) {
            numero = "0" + this.num;
        }
        if ((this.num >= 10000000) && (this.num <= 100000000)) {
            numero = "" + this.num;
        }
        return numero;
    }

    public String codigoVenta() {
        String codVenta = "";
        try {
            ps = conn.prepareStatement(sql_max_codVentas);
            rs = ps.executeQuery();
            while (rs.next()) {
                codVenta = rs.getString(1);
                System.out.println("encuentra el Código Venta Máximo = " + codVenta);
            }
        } catch (SQLException ex) {
            System.out.println("Error al Buscar Código Venta Máximo = " + ex.getMessage());
        }
        return codVenta;
    }

    public int guardarVenta(Venta venta) {
        try {
            ps = conn.prepareStatement(sql_guardar_V);
            ps.setInt(1, venta.getCedulaCliente());
            ps.setInt(2, venta.getCedulaUsuario());
            ps.setDouble(3, venta.getSubTotal());
            ps.setDouble(4, venta.getIvaVenta());
            ps.setDouble(5, venta.getTotalVenta());
            ps.setString(6, venta.getConsecutivo());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al Guardar VENTA = " + ex.getMessage());
        }
        return Resultado;
    }

    public int guardarDetalleVenta(Venta venta) {
        try {
            ps = conn.prepareStatement(sql_guardar_DV);
            ps.setInt(1, venta.getCantidad());
            ps.setInt(2, venta.getCodigo());
            ps.setInt(3, venta.getCodigoVenta());
            ps.setDouble(4, venta.getTotalVenta());
            ps.setDouble(5, venta.getIvaVenta());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al Guardar DETALLE-VENTA = " + ex.getMessage());
        }
        return Resultado;
    }

    public List<Venta> listar() {
        try {
            ps = conn.prepareStatement(sql_listar);
            rs = ps.executeQuery();
            //recorremos la tabla desde la base de datos
            while (rs.next()) {
                // capturamos la info de la base de datos y la guardamos en unas variables
                int codigoVenta = rs.getInt("codigo_venta");
                int cedulaCliente = rs.getInt("cedula_cliente");
                double totalVenta = rs.getDouble("total_venta");
                String consecutivo = rs.getString("consecutivo");
                String nombreCliente = rs.getString("nombre_cliente");
                String direccionCliente = rs.getString("direccion_cliente");
                String telefonoCliente = rs.getString("telefono_cliente");
                //usando un objeto y el constructor pasamos los datos
                venta = new Venta(codigoVenta, cedulaCliente, totalVenta, consecutivo, nombreCliente, direccionCliente, telefonoCliente);
                ventas.add(venta); //llenamos la lista
            }
        } catch (SQLException ex) {
            System.out.println("Error al Listar Facturas = " + ex.getMessage());
        }
        return ventas;
    }

    public Venta buscarVenta(Venta venta) {

        try {
            ps = conn.prepareStatement(sql_buscar_V);
            ps.setInt(1, venta.getCodigoVenta());
            rs = ps.executeQuery();
            while (rs.next()) {

                //venta
                int codigoVenta = rs.getInt("codigo_venta");
                int cedulaCliente = rs.getInt("cedula_cliente");
                double subTotal = rs.getDouble("sub_total");
                double ivaVenta = rs.getDouble("iva_venta");
                double totalVenta = rs.getDouble("total_venta");
                String consecutivo = rs.getString("consecutivo");

                venta.setCodigoVenta(codigoVenta);
                venta.setCedulaCliente(cedulaCliente);
                venta.setSubTotal(subTotal);
                venta.setIvaVenta(ivaVenta);
                venta.setTotalVenta(totalVenta);
                venta.setConsecutivo(consecutivo);

                System.out.println("Venta DAO = " + venta);

            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar Factura x ID = " + ex.getMessage());
        }
        return venta;
    }

    public List<Venta> buscarDetalleVenta(int Cod) {
        int item = 0;
        Producto producto = new Producto();
        try {
            ps = conn.prepareStatement(sql_buscar_V);
            ps.setInt(1, Cod);
            rs = ps.executeQuery();
            while (rs.next()) {
                item = item + 1;
                int cantidadProducto = rs.getInt("cantidad_producto");
                int codigoProducto = rs.getInt("codigo_producto");

                //consultamos la información de el producto con codigoProducto
                try {
                    ps2 = conn.prepareStatement(sql_buscar_producto);
                    ps2.setInt(1, codigoProducto);
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        int cod = rs2.getInt("codigo_producto");
                        String descripcion = rs2.getString("nombre_producto");
                        double precio = rs2.getDouble("precio_venta");
                        producto.setCodigoProducto(codigoProducto);
                        producto.setNombreProducto(descripcion);
                        producto.setPrecioVenta(precio);
                    }
                } catch (SQLException e) {
                    System.out.println("No se encontró el producto  = " + e.getMessage());
                }

                String descripcion = producto.getNombreProducto();
                double precio = producto.getPrecioVenta();

                System.out.println("Descripcion= " + descripcion + " precio= " + precio);
                int codigoVenta = rs.getInt("codigo_venta");
                double valorTotal = rs.getDouble("valor_total");
                System.out.println(valorTotal);
                double valorIva = rs.getDouble("valor_iva");

                venta = new Venta(item, cantidadProducto, codigoProducto, descripcion, precio, codigoVenta, valorTotal, valorIva);
                ventas.add(venta);
            }
        } catch (SQLException ex) {
            System.out.println("Error al Listar Facturas = " + ex.getMessage());
        }
        return ventas;
    }

    public boolean eliminar(Venta venta) {

        boolean resultado = false;

        try {
            ps = conn.prepareStatement(sql_eliminar);
            ps.setInt(1, venta.getCodigoVenta());
            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar la Factura = " + ex.getMessage());
        }
        return resultado;
    }

    public List<Venta> buscarFacturasCliente(int cedula) {
        int item = 0;
        Producto producto = new Producto();
        try {
            ps = conn.prepareStatement(sql_buscar_V_cliente);
            ps.setInt(1, cedula);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cedulaCliente = rs.getInt("cedula_cliente");
                String nombreCliente = rs.getString("nombre_cliente");
                double totalVenta = rs.getDouble("total_venta");

                venta = new Venta(cedulaCliente, nombreCliente, totalVenta);
                ventas.add(venta);
            }
        } catch (SQLException ex) {
            System.out.println("Error al Listar Factura Cliente = " + ex.getMessage());
        }
        return ventas;
    }
}
