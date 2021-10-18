package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Usuario;
import modelo.UsuarioDAO;

@WebServlet("/Usuarios")
public class UsuarioServlet extends HttpServlet {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario usuario = new Usuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarUsuario(request, response);
                    break;
                case "eliminar":
                    this.eliminarUsuario(request, response);
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
                    this.nuevoUsuario(request, response);
                    break;
                case "modificar":
                    this.modificarUsuario(request, response);
                    break;
                case "buscar":
                    this.buscarUsuario(request, response);
                    break;
                case "login":
                    this.login(request, response);
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

        //el Url contiene las acciones y envia nuevamente la informacion generando duplicidad o errores
//        List<Usuario> usuarios = new UsuarioDAO().listar();
//        System.out.println("######Lista desde el Servlet######");
//        System.out.println("usuarios = " + usuarios);
//        request.setAttribute("usuarios", usuarios);
//        request.getRequestDispatcher("usuarios/usuarios.jsp").forward(request, response);

        /*el usar el metodo forward
        , el cambio de pagina que se hace es interna y el navegador no se entera de que se ha seleccionado otro jsp a mostrar, todo se hace del lado del servidor
        .
        
        ya se visualiza correctamente la url pero no carga la informacion
        , debido a que */
        
        List<Usuario> usuarios = new UsuarioDAO().listar();
        HttpSession sesion = request.getSession();
        System.out.println("######Lista desde el Servlet######");
        System.out.println("usuarios = " + usuarios);
        sesion.setAttribute("usuarios", usuarios);
        response.sendRedirect("usuarios/usuarios.jsp"); //sendRedirect si notifica al navegador
    }

    private void nuevoUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario Nuevo Usuario

        //la cedula es de tipo Int, y no podemos asignarla directamente a una variable de tipo int
        //ya que el método de getParameter nos devuelve un String.
        //en dado caso de que no se haya enviado ningun valor, vamos a suponer que le vamos a asignar el val de CERO
        System.out.println("#########################");
        System.out.println("Capturando nuevo Usuario");
        Integer Cedula = 0;
        //Solicitamos la cedula por medio de cedulaString
        String cedulaString = request.getParameter("txtCedula");
        //Revisamos si esta variable contiene algún valor | cedulaString diferente de null y diferente de la cadena vacía
        if (cedulaString != null && !"".equals(cedulaString)) {
            Cedula = Integer.parseInt(cedulaString);
        }
        String email = request.getParameter("txtEmail");
        String nombre = request.getParameter("txtNombre");
        String password = request.getParameter("txtPassword");
        String user = request.getParameter("txtUser");

        //Creamos un objeto de Usuario(modelo)
        Usuario usuario = new Usuario(Cedula, email, nombre, password, user);
        //Insertamos en nuevo objeto en la base de datos por medio de UsuarioDAO
        int resultadoRegistro = new UsuarioDAO().nuevo(usuario);
        System.out.println("resultadoRegistro = " + resultadoRegistro);

        //Redirigimos a la accion por Default
        this.refrescarInformacion(request, response);
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        //recuperamos cedulaCliente
        int cedulaUsuario = Integer.parseInt(request.getParameter("cedulaUsuario"));

        //Usuario cedula = new Usuario(cedulaUsuario);
        //Usuario usuario = new UsuarioDAO().buscarPorCC(cedula);
        Usuario usuario = new UsuarioDAO().buscarPorCC(new Usuario(cedulaUsuario));

        //recuperado el usuario, lo ponemos en algun alcance, ejem Request
        System.out.println("usuario a Editar = " + usuario);
        sesion.setAttribute("usuario", usuario);
        //request.setAttribute("usuario", usuario);
        //request.getRequestDispatcher("usuarios/eliminar.jsp").forward(request, response);

        //no usamos sendRedirect porque hace una peticion extra al servidor, es la misma peticion
        response.sendRedirect("usuarios/editar.jsp");
        //request.getRequestDispatcher("/usuarios/editar.jsp").forward(request, response);
    }

    private void modificarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarUsuario, de ahí proviene la informacion que editamos de este cliente
        //este cedulaUsuario es el recuperado de la URL en el formulario editar
        int cedulaUsuario = Integer.parseInt(request.getParameter("cedulaUsuario"));

        System.out.println("###################");
        System.out.println("Modificando Usuario");
        Integer Cedula = 0;
        String cedulaString = request.getParameter("txtCedula");
        if (cedulaString != null && !"".equals(cedulaString)) {
            Cedula = Integer.parseInt(cedulaString);
        }
        String email = request.getParameter("txtEmail");
        String nombre = request.getParameter("txtNombre");
        String password = request.getParameter("txtPassword");
        String user = request.getParameter("txtUser");

        //Creamos un objeto de Usuario(modelo)
        Usuario usuario = new Usuario(Cedula, email, nombre, password, user);
        //Modificar el objeto en la base de datos por medio de UsuarioDAO por medio del metodo modificar
        boolean resultadoRegistro = new UsuarioDAO().modificar(usuario);
        System.out.println("resultadoRegistro = " + resultadoRegistro);

        //Redirigimos a la accion por Default
        this.refrescarInformacion(request, response);
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos cedulaCliente
        int cedulaUsuario = Integer.parseInt(request.getParameter("cedulaUsuario"));
        String nombre = request.getParameter("nom");

        Usuario usuario = new Usuario(cedulaUsuario);
        boolean resultadoEliminacion = new UsuarioDAO().eliminar(usuario);
        System.out.println("##########################");
        System.out.println("Eliminado desde Servlet = " + resultadoEliminacion);
        if (resultadoEliminacion) {
            request.setAttribute("configuracion", "alert alert-success");
            request.setAttribute("mensaje", nombre + " ha sido eliminado con exito");
            request.getRequestDispatcher("usuarios/mensaje.jsp").forward(request, response);
        } else {
            request.setAttribute("configuracion", "alert alert-danger");
            request.setAttribute("mensaje", "Error al eliminar, el registro no existe!");
            request.getRequestDispatcher("usuarios/mensaje.jsp").forward(request, response);
        }
    }

    private void buscarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String txtBuscar = request.getParameter("txtBuscar");
        System.err.println("txtBuscar = " + txtBuscar);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.buscarBox(txtBuscar);
        System.err.println(usuarios);

        //request.setAttribute("usuarios", lista);
        //request.getRequestDispatcher("usuarios/usuarios.jsp").forward(request, response);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuarios", usuarios);
        response.sendRedirect("usuarios/usuarios.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("###Presionó el Boton Login###");
        Usuario USUARIO = new Usuario();
        //ServletContext aplicacion = getServletConfig().getServletContext();
        HttpSession sesion = request.getSession();
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        USUARIO = new Usuario(user, password);
        System.out.println("### Datos ### Usuario: " + user + " - Contraseña: " + password);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.autenticacion(USUARIO)) {
            System.out.println("el usuario es = " + USUARIO);
            sesion.setAttribute("usuario", USUARIO);
            response.sendRedirect("principal.jsp");
        } else if (user.equals("admininicial") && password.equals("admin123456")) {
            sesion.setAttribute("usuario", USUARIO);
            response.sendRedirect("principal.jsp");
        } else {
            System.out.println("### Se devuelve al INDEX");
            response.sendRedirect("index.jsp");
        }
//        ServletContext aplicacion = getServletConfig().getServletContext();
//        HttpSession sesion = request.getSession();
//        String user = request.getParameter("user");
//        String password = request.getParameter("password");
//        usuario = usuarioDAO.autenticacion2(user, password);
//        String nombreUsuario=usuario.getNombreUsuario();
//        System.out.println("el usuario es= "+nombreUsuario);
//        if (usuario.getUser() != null) {
//            try{
//            sesion.setAttribute("usuario", usuario);
//            response.sendRedirect("principal.jsp");
//            }catch(Exception ex){
//                System.out.println("Error en el Servlet Login = "+ex.getMessage());
//            }
//        }
    }


}
