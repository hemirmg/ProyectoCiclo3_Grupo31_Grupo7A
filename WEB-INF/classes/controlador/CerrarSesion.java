package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CerrarSesion", urlPatterns = {"/CerrarSesion"})
public class CerrarSesion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Entra en doGet1");
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
//                case "logout":
//                    System.out.println("Entra en doGet2");
//                    this.cerrarSesion(request, response);
//                    break;
                default:
                    this.verificarSesion(request, response);
            }
        } else {
            this.verificarSesion(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ServletContext aplicacion = getServletConfig().getServletContext();

        System.out.println("Entra en cerrarSesion");
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuarioSesion", null);

        //sesion.invalidate();
        response.sendRedirect("index.jsp");
    }

    public void verificarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("usuarioSesion") == null) {
            response.sendRedirect("index.jsp");
        }
    }

}
