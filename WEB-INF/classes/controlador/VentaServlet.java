package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.Venta;
import modelo.VentaDAO;

@WebServlet("/Ventas")
public class VentaServlet extends HttpServlet {

    Producto producto = new Producto();
    Cliente cliente = new Cliente();

    Venta articulo = new Venta();
    Venta venta = new Venta();
    List<Venta> articulos = new ArrayList<>();

    //Número Consucutivo
    String consecutivo;
    VentaDAO ventaDAO = new VentaDAO();

    //Venta
    int cedulaCliente;
    double iva;
    double subTotal;
    double totalIva;
    double totalVenta;

    //Detalle Venta
    int item;
    int codigo;
    String descripcion;
    double precio;
    int cantidad;
    double subtotal;
    double subtotalIva;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "Nuevo":
                    this.limpiarSesion(request, response);
                    response.sendRedirect("ventas/ventas.jsp");
                    //request.getRequestDispatcher("ventas/ventas.jsp").forward(request, response);
                    break;
                case "Cancelar":
                    this.limpiarSesion(request, response);
                    response.sendRedirect("Ventas");
                    //request.getRequestDispatcher("ventas/index.jsp").forward(request, response);
                    break;
                case "Atras":
                    this.limpiarSesion(request, response);
                    response.sendRedirect("Ventas");
                    break;
                case "ver":
                    this.ver(request, response);
                    System.out.println("Entra al caso Ver");
                    break;
                case "eliminar":
                    this.eliminarFactura(request, response);
                    break;
                default:
                    this.cargarFacturas(request, response);
            }
        } else {
            this.cargarFacturas(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    this.buscarCliente(request, response);
                    this.consecutivo(request, response);
                    break;
                case "BuscarProducto":
                    this.buscarProducto(request, response);
                    break;
                case "Agregar":
                    this.agregarProducto(request, response);
                    break;
                case "Pagar":
                    this.consecutivo(request, response);
                    this.guardarVenta(request, response);
                    break;
                case "Cancelar":
                    this.limpiarSesion(request, response);
                    break;
                default:
                    //response.sendRedirect("ventas/ventas.jsp");
                    request.getRequestDispatcher("ventas/ventas.jsp").forward(request, response);
            }
//            response.sendRedirect("ventas/ventas.jsp");
            request.getRequestDispatcher("ventas/ventas.jsp").forward(request, response);
        }
    }

    private void cargarFacturas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Venta> facturas = new VentaDAO().listar();
        HttpSession sesion = request.getSession();
        System.out.println("######Lista desde el Servlet######");
        System.out.println("facturas = " + facturas);
        sesion.setAttribute("facturas", facturas);
        response.sendRedirect("ventas"); //sendRedirect si notifica al navegador
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //HttpSession sesion = request.getSession();
        int cedulaCliente = Integer.parseInt(request.getParameter("txtCedulaCliente"));
        cliente = new ClienteDAO().buscarPorCC(new Cliente(cedulaCliente));

        if (cliente.getNombreCliente() != null) {
            System.out.println("el cliente es = " + cliente);
            request.setAttribute("display", "display:none");
            request.setAttribute("cliente", cliente);
        } else {
            System.out.println("el cliente es no encontrado desde VENTAS");
            request.setAttribute("mensaje", "Cliente no encontrado");
            request.setAttribute("color", "#FE0101");
            request.setAttribute("icono", "fas fa-times");
            request.setAttribute("display", "display = \"\"");
        }
        request.setAttribute("subTotal", String.format("%.1f", subTotal));
        request.setAttribute("totalIva", String.format("%.1f", totalIva));
        request.setAttribute("totalVenta", String.format("%.1f", totalVenta));
        request.setAttribute("totalVenta2", String.format("%.0f", totalVenta));
        request.setAttribute("articulos", articulos);

    }

    private void buscarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int codigoProducto = Integer.parseInt(request.getParameter("txtCodigoProducto"));
        producto = new ProductoDAO().buscarPorID(new Producto(codigoProducto));

        if (producto.getNombreProducto() != null) {
            System.out.println("el producto es = " + producto);
            request.setAttribute("display", "display:none");
            request.setAttribute("producto1", producto);
            request.setAttribute("cliente", cliente);
        } else {
            System.out.println("el producto es no encontrado desde VENTAS");
            request.setAttribute("mensaje", "Producto no encontrado");
            request.setAttribute("color", "#FE0101");
            request.setAttribute("icono", "fas fa-times");
            request.setAttribute("display", "display = \"\"");
        }

        request.setAttribute("subTotal", String.format("%.1f", subTotal));
        request.setAttribute("totalIva", String.format("%.1f", totalIva));
        request.setAttribute("totalVenta", String.format("%.1f", totalVenta));
        request.setAttribute("totalVenta2", String.format("%.0f", totalVenta));
        request.setAttribute("consecutivo", consecutivo);
        request.setAttribute("articulos", articulos);

    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        item = item + 1;
        codigo = producto.getCodigoProducto();
        descripcion = request.getParameter("txtNombreProducto");
        precio = Double.parseDouble(request.getParameter("txtPrecio"));
        cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
        subTotal = precio * cantidad;
        totalVenta = 0.0;

        //Calculo del TotalIva
        iva = (producto.getIvaCompra() / 100) + 1;
        subtotalIva = (precio - (precio / iva)) * cantidad;
        totalIva = totalIva + subtotalIva;

        articulo = new Venta();

        articulo.setItem(item);
        articulo.setCodigo(codigo);
        articulo.setDescripcion(descripcion);
        articulo.setPrecio(precio);
        articulo.setCantidad(cantidad);
        articulo.setSubtotal(subTotal);
        articulo.setSubtotalIva(subtotalIva);
        articulos.add(articulo);

        if (articulo.getDescripcion() != null) {

            for (int i = 0; i < articulos.size(); i++) {
                totalVenta = totalVenta + articulos.get(i).getSubtotal();
                subTotal = totalVenta - totalIva;

                articulos.get(0).getItem();

                request.setAttribute("subTotal", String.format("%.1f", subTotal));
                request.setAttribute("totalIva", String.format("%.1f", totalIva));
                request.setAttribute("totalVenta", String.format("%.1f", totalVenta));
                request.setAttribute("totalVenta2", String.format("%.0f", totalVenta));
                request.setAttribute("consecutivo", consecutivo);
                request.setAttribute("articulos", articulos);
                request.setAttribute("cliente", cliente);
                //request.setAttribute("control", "disabled=\"false\"");

            }
        }
    }

    private void consecutivo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        consecutivo = ventaDAO.numeroMaximo();
        if (consecutivo == null) {
            consecutivo = "00000001";
            request.setAttribute("consecutivo", consecutivo);
        } else {
            int aumentar = Integer.parseInt(consecutivo);
            VentaDAO nuevo = new VentaDAO();
            consecutivo = nuevo.numConsecutivo(aumentar);
            request.setAttribute("consecutivo", consecutivo);
        }

    }

    private void guardarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Guardar Venta
        articulo.setCedulaCliente(cliente.getCedulaCliente());
        articulo.setSubTotal(subTotal);
        articulo.setIvaVenta(totalIva);
        articulo.setTotalVenta(totalVenta);
        articulo.setConsecutivo(consecutivo);
        ventaDAO.guardarVenta(articulo);

        //Guardar DetalleVenta
//        int codigoVenta = Integer.parseInt(ventaDAO.numeroMaximo());
        int codigoVenta = Integer.parseInt(ventaDAO.codigoVenta());
        for (int i = 0; i < articulos.size(); i++) {
            venta = new Venta(); //iniciamos en vacío la entidad venta
            venta.setCantidad(articulos.get(i).getCantidad());
            venta.setCodigo(articulos.get(i).getCodigo());
            venta.setCodigoVenta(codigoVenta);
            venta.setTotalVenta(articulos.get(i).getSubtotal());
            venta.setIvaVenta(articulos.get(i).getSubtotalIva());
            ventaDAO.guardarDetalleVenta(venta);

        }
        this.limpiarSesion(request, response);
    }

    private void limpiarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        articulos.clear();
        cliente = null;
        producto = null;
        item = 0;
        double ceros = 0.0;

        subTotal = 0.0;
        totalIva = 0.0;
        totalVenta = 0.0;
        totalVenta = 0.0;
        //request.setAttribute("control", "disabled=\"\"");
        request.setAttribute("articulos", null);
        request.setAttribute("subTotal", ceros);
        request.setAttribute("totalIva", ceros);
        request.setAttribute("totalVenta", ceros);
        request.setAttribute("totalVenta2", ceros);
        request.setAttribute("articulos", null);
        //request.setAttribute("consecutivo", null);

        request.setAttribute("cliente", null);
        sesion.setAttribute("cliente", null);
        request.setAttribute("subTotal1", ceros);
        request.setAttribute("totalIva1", ceros);
        request.setAttribute("totalVenta1", ceros);
        request.setAttribute("totalVenta3", ceros);
        request.setAttribute("venta", null);
        request.setAttribute("producto1", null);
        request.setAttribute("mensaje", null);
        //request.getSession().invalidate(); //Esto obtiene las variables de sesión creadas y las elimina.
        //response.sendRedirect("ventas");
    }

    private void ver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        //Traer Cliente
        int cedulaCliente = Integer.parseInt(request.getParameter("cc"));
        cliente = new ClienteDAO().buscarPorCC(new Cliente(cedulaCliente));
        sesion.setAttribute("cliente", cliente);

        //venta
        int codigoVenta = Integer.parseInt(request.getParameter("id"));
        Venta venta = new VentaDAO().buscarVenta(new Venta(codigoVenta));
        System.out.println("Resultado Consulta Buscar Venta = " + venta);
        sesion.setAttribute("subTotal1", String.format("%.1f", venta.getSubTotal()));
        sesion.setAttribute("totalIva1", String.format("%.1f", venta.getIvaVenta()));
        sesion.setAttribute("totalVenta1", String.format("%.1f", venta.getTotalVenta()));
        sesion.setAttribute("totalVenta3", String.format("%.0f", venta.getTotalVenta()));
        sesion.setAttribute("venta", venta);

        //Detalle de Venta
        List<Venta> detalleVenta = new VentaDAO().buscarDetalleVenta(codigoVenta);
        System.out.println("detalleVenta = " + detalleVenta);
//        sesion.setAttribute("subTotal1", String.format("%.1f", venta.getSubTotal()));
        sesion.setAttribute("detalleVenta", detalleVenta);

        response.sendRedirect("ventas/ver.jsp"); //sendRedirect si notifica al navegador
    }

    private void eliminarFactura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigoVenta = Integer.parseInt(request.getParameter("id"));

        Venta venta = new Venta(codigoVenta);
        boolean resultadoEliminacion = new VentaDAO().eliminar(venta);
        System.out.println("##########################");
        System.out.println("Eliminado desde Servlet = " + resultadoEliminacion);

        this.cargarFacturas(request, response);
    }
}
