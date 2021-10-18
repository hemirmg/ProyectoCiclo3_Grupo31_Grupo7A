package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Cliente;
import modelo.ClienteDAO;

@WebServlet("/Clientes")
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
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
                    this.nuevoCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                case "buscar":
                    this.buscarCliente(request, response);
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

        List<Cliente> clientes = new ClienteDAO().listar();
        HttpSession sesion = request.getSession();
        System.out.println("######Lista desde el Servlet######");
        System.out.println("clientes = " + clientes);
        sesion.setAttribute("clientes", clientes);
        response.sendRedirect("clientes/clientes.jsp");
    }

    private void nuevoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("#########################");
        System.out.println("Capturando nuevo Cliente");
        Integer Cedula = 0;

        String cedulaString = request.getParameter("txtCedula");

        if (cedulaString != null && !"".equals(cedulaString)) {
            Cedula = Integer.parseInt(cedulaString);
        }
        String direccion = request.getParameter("txtDireccion");
        String email = request.getParameter("txtEmail");
        String nombre = request.getParameter("txtNombre");
        String telefono = request.getParameter("txtTelefono");

        Cliente cliente = new Cliente(Cedula, direccion, email, nombre, telefono);

        int resultadoRegistro = new ClienteDAO().nuevo(cliente);
        System.out.println("resultadoRegistro = " + resultadoRegistro);

        this.refrescarInformacion(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int cedulaCliente = Integer.parseInt(request.getParameter("cedulaCliente"));

        Cliente cliente = new ClienteDAO().buscarPorCC(new Cliente(cedulaCliente));

        System.out.println("cliente a Editar = " + cliente);
        sesion.setAttribute("cliente", cliente);
        //String jspEditar = "/clientes/editar.jsp";
        response.sendRedirect("clientes/editar.jsp");
        //request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cedulaCliente = Integer.parseInt(request.getParameter("cedulaCliente"));

        System.out.println("###################");
        System.out.println("Modificando Cliente");
        Integer Cedula = 0;
        String cedulaString = request.getParameter("txtCedula");
        if (cedulaString != null && !"".equals(cedulaString)) {
            Cedula = Integer.parseInt(cedulaString);
        }
        String direccion = request.getParameter("txtDireccion");
        String email = request.getParameter("txtEmail");
        String nombre = request.getParameter("txtNombre");
        String telefono = request.getParameter("txtTelefono");

        Cliente cliente = new Cliente(Cedula, direccion, email, nombre, telefono);

        boolean resultadoRegistro = new ClienteDAO().modificar(cliente);
        System.out.println("resultadoRegistro = " + resultadoRegistro);

        this.refrescarInformacion(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int cedulaCliente = Integer.parseInt(request.getParameter("cedulaCliente"));

        Cliente cliente = new Cliente(cedulaCliente);
        boolean resultadoEliminacion = new ClienteDAO().eliminar(cliente);
        System.out.println("##########################");
        System.out.println("Eliminado desde Servlet = " + resultadoEliminacion);

        this.refrescarInformacion(request, response);

    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String txtBuscar = request.getParameter("txtBuscar");
        System.err.println("txtBuscar = " + txtBuscar);
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.buscarBox(txtBuscar);
        System.err.println(clientes);

        //request.setAttribute("clientes", clientes);
        //request.getRequestDispatcher("clientes/clientes.jsp").forward(request, response);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        response.sendRedirect("clientes/clientes.jsp");

    }

}
