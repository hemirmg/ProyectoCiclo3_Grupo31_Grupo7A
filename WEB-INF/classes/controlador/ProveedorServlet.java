package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Proveedor;
import modelo.ProveedorDAO;

@WebServlet("/Proveedores")
public class ProveedorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //this.refrescarInformacion(request, response);
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarProveedor(request, response);
                    break;
                case "eliminar":
                    this.eliminarProveedor(request, response);
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
                    this.nuevoProveedor(request, response);
                    break;
                case "modificar":
                    this.modificarProveedor(request, response);
                    break;
                case "buscar":
                    this.buscarProveedor(request, response);
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

        List<Proveedor> proveedores = new ProveedorDAO().listar();
        HttpSession sesion = request.getSession();
        System.out.println("######Lista desde el Servlet######");
        System.out.println("proveedores = " + proveedores);
        sesion.setAttribute("proveedores", proveedores);
        response.sendRedirect("proveedores/proveedores.jsp");
    }

    private void nuevoProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("#########################");
        System.out.println("Capturando nuevo Proveedor");
        Integer nit = 0;
        
        String nitString = request.getParameter("txtNit");
        
        if (nitString != null && !"".equals(nitString)) {
            nit = Integer.parseInt(nitString);
        }
        String ciudad = request.getParameter("txtCiudad");
        String direccion = request.getParameter("txtDireccion");
        String nombre = request.getParameter("txtNombre");
        String telefono = request.getParameter("txtTelefono");

        Proveedor proveedor = new Proveedor(nit, ciudad, direccion, nombre, telefono);

        int resultadoRegistro = new ProveedorDAO().nuevo(proveedor);
        System.out.println("resultadoRegistro = " + resultadoRegistro);

        this.refrescarInformacion(request, response);
    }

    private void editarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int nitProveedor = Integer.parseInt(request.getParameter("nitProveedor"));

        Proveedor proveedor = new ProveedorDAO().buscarPorCC(new Proveedor(nitProveedor));

        System.out.println("proveedor a Editar = " + proveedor);
        sesion.setAttribute("proveedor", proveedor);
        //String jspEditar = "/proveedores/editar.jsp";
        response.sendRedirect("proveedores/editar.jsp");
        //request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    private void modificarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int nitProveedor = Integer.parseInt(request.getParameter("nitProveedor"));

        System.out.println("###################");
        System.out.println("Modificando Proveedor");
        Integer nit = 0;
        String nitString = request.getParameter("txtNit");
        if (nitString != null && !"".equals(nitString)) {
            nit = Integer.parseInt(nitString);
        }
        String ciudad = request.getParameter("txtCiudad");
        String direccion = request.getParameter("txtDireccion");
        String nombre = request.getParameter("txtNombre");
        String telefono = request.getParameter("txtTelefono");

        Proveedor proveedor = new Proveedor(nit, ciudad, direccion, nombre, telefono);
        
        boolean resultadoRegistro = new ProveedorDAO().modificar(proveedor);
        System.out.println("resultadoRegistro = " + resultadoRegistro);

        this.refrescarInformacion(request, response);
    }

    private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int nitProveedor = Integer.parseInt(request.getParameter("nitProveedor"));

        Proveedor proveedor = new Proveedor(nitProveedor);
        boolean resultadoEliminacion = new ProveedorDAO().eliminar(proveedor);
        System.out.println("##########################");
        System.out.println("Eliminado desde Servlet = " + resultadoEliminacion);

        this.refrescarInformacion(request, response);

    }

    private void buscarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String txtBuscar = request.getParameter("txtBuscar");
        System.err.println("txtBuscar = " + txtBuscar);
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        List<Proveedor> proveedores = proveedorDAO.buscarBox(txtBuscar);
        System.err.println(proveedores);

        //request.setAttribute("usuarios", lista);
        //request.getRequestDispatcher("usuarios/usuarios.jsp").forward(request, response);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("proveedores", proveedores);
        response.sendRedirect("proveedores/proveedores.jsp");

    }

}
