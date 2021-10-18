package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Venta;
import modelo.VentaDAO;

@WebServlet("/Reportes")
public class ReporteServlet extends HttpServlet {

    List<Venta> clienteFacturas = new ArrayList<>();
    VentaDAO ventaDAO = new VentaDAO();
    double totalFactura;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.limpiar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "buscar":
                    this.limpiar(request, response);
                    this.buscarCliente(request, response);
                    break;
                default:
            }
        } else {
        }
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        //Cliente cliente = new Cliente();
        Venta venta = new Venta();
        double totalFactura = 0.0;
        int cedulaCliente = Integer.parseInt(request.getParameter("txtBuscar"));
//        List<Venta> clienteFacturas = new VentaDAO().buscarFacturasCliente(cedulaCliente);

        clienteFacturas = ventaDAO.buscarFacturasCliente(cedulaCliente);

        for (int i = 0; i < clienteFacturas.size(); i++) {
            totalFactura = totalFactura + clienteFacturas.get(i).getTotalVenta();
        }

        System.out.println("######Lista desde el Servlet######");
        System.out.println("clienteFacturas = " + clienteFacturas);
        sesion.setAttribute("clienteFacturas", clienteFacturas);
        sesion.setAttribute("totalFactura", totalFactura);
        response.sendRedirect("reportes");

    }

    private void limpiar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        clienteFacturas.clear();
        sesion.setAttribute("totalFactura", null);
    }

}
