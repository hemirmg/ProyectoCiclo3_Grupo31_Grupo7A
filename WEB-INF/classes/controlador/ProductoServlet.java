package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Producto;
import modelo.ProductoDAO;

@WebServlet("/Productos")
@MultipartConfig

public class ProductoServlet extends HttpServlet {

    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarProducto(request, response);
                    break;
                case "eliminar":
                    this.eliminarProducto(request, response);
                    break;

                default:
                    this.refrescarInformacion(request, response);
            }
        } else {
            this.refrescarInformacion(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "nuevo":
                    this.nuevoProducto(request, response);
                    break;
                case "modificar":
                    this.modificarProducto(request, response);
                    break;
                case "buscar":
                    this.buscarProducto(request, response);
                    break;
                case "csv":
                    this.cargarArchivo(request, response);
                    break;
                default:
                    this.refrescarInformacion(request, response);
            }
        } else {
            this.refrescarInformacion(request, response);
        }
    }

    private void refrescarInformacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Producto> productos = new ProductoDAO().listar();
        HttpSession sesion = request.getSession();
        System.out.println("######Lista desde el Servlet######");
        System.out.println("productos = " + productos);
        sesion.setAttribute("productos", productos);
        response.sendRedirect("productos/productos.jsp");
    }

    private void nuevoProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("#########################");
        System.out.println("Capturando nuevo Producto");

        int codigoProducto = Integer.parseInt(request.getParameter("txtCodigoProducto"));
        String nombreProducto = request.getParameter("txtNombreProducto");
        int nitProveedor = Integer.parseInt(request.getParameter("txtNitProveedor"));
        double precioCompra = Double.parseDouble(request.getParameter("txtPrecioCompra"));
        double ivaCompra = Double.parseDouble(request.getParameter("txtIvaCompra"));
        double precioVenta = Double.parseDouble(request.getParameter("txtPrecioVenta"));

        Producto producto = new Producto(codigoProducto, ivaCompra, nitProveedor, nombreProducto, precioCompra, precioVenta);

        int resultadoRegistro = new ProductoDAO().nuevo(producto);
        System.out.println("resultadoRegistro = " + resultadoRegistro);

        this.refrescarInformacion(request, response);
    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int codigoProducto = Integer.parseInt(request.getParameter("id"));
        Producto producto = new ProductoDAO().buscarPorID(new Producto(codigoProducto));
        System.out.println("Producto a Editar = " + producto);
        sesion.setAttribute("producto", producto);
        response.sendRedirect("productos/productos.jsp");

    }

    private void modificarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("###################");
        System.out.println("Modificando Producto");
        int codigoProducto = Integer.parseInt(request.getParameter("id"));
        String nombreProducto = request.getParameter("txtNombreProducto");
        int nitProveedor = Integer.parseInt(request.getParameter("txtNitProveedor"));
        double precioCompra = Double.parseDouble(request.getParameter("txtPrecioCompra"));
        double ivaCompra = Double.parseDouble(request.getParameter("txtIvaCompra"));
        double precioVenta = Double.parseDouble(request.getParameter("txtPrecioVenta"));

        Producto producto = new Producto(codigoProducto, ivaCompra, nitProveedor, nombreProducto, precioCompra, precioVenta);

        boolean resultadoRegistro = new ProductoDAO().modificar(producto);
        System.out.println("resultadoRegistro = " + resultadoRegistro);

        this.refrescarInformacion(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Producto producto = new Producto(id);
        boolean resultadoEliminacion = new ProductoDAO().eliminar(producto);
        System.out.println("##########################");
        System.out.println("Eliminado desde Servlet = " + resultadoEliminacion);

        this.refrescarInformacion(request, response);
    }

    private void buscarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String txtBuscar = request.getParameter("txtBuscar");
        System.err.println("txtBuscar = " + txtBuscar);
        List<Producto> productos = productoDAO.buscarBox(txtBuscar);
        System.err.println(productos);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("productos", productos);
        response.sendRedirect("productos/productos.jsp");
    }

    private void cargarArchivo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        ProductoDAO prodDAO = new ProductoDAO();
        Part archivo = request.getPart("archivo");
        String nombre = archivo.getSubmittedFileName();
        String url = "C:/mifactura/uploads/"; //--funciona
        //String url = getServletContext().getRealPath("productos/uploads/");
        //String url = getServletContext().getRealPath("WEB-INF/uploads/");
        //url = url.replace("build\\", "");
        //System.out.println("La ruta url1 =" + url + "\\" + nombre);
        //####
        //String url2 = url + "\\" + nombre;
        //System.out.println("La ruta url2 = " + url2);

        try {
            InputStream file = archivo.getInputStream();
            File copia = new File(url + nombre);
            //File copia = new File(url2);
            FileOutputStream escribir = new FileOutputStream(copia);
            int num = file.read();
            while (num != -1) {
                escribir.write(num);
                num = file.read();
            }
            escribir.close();
            file.close();
            System.out.println("** Archivo " + nombre + " Copiado Correctamente");
            if (prodDAO.cargarArchivo(url + nombre)) {

                System.out.println(url);
                //if (prodDAO.cargarArchivo(url2)) {
                System.out.println("Carga Exitosa!");
                sesion.setAttribute("mensaje", "<b>" + nombre + "</b>" + " Cargado con Exito!");
                sesion.setAttribute("color", "#198754");
                sesion.setAttribute("icono", "fas fa-check");
                sesion.setAttribute("display", "display = \"\"");
            } else {
                System.out.println("Error, no se registraron los Productos en Servlet");
                sesion.setAttribute("mensaje", "No se pudo cargar " + nombre);
                sesion.setAttribute("color", "#FE0101");
                sesion.setAttribute("icono", "fas fa-times");
                sesion.setAttribute("display", "display = \"\"");
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar el Archivo al Servlet = " + ex.getMessage());
        }
        this.refrescarInformacion(request, response);
    }
}
