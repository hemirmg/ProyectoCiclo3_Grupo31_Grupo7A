package modelo;

import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Conexion {

    //String sql2 = "SELECT IF( EXISTS(SELECT * FROM usuario WHERE user =  ? AND password = ?), 1, 0)";
    String sql2 = "SELECT EXISTS(SELECT * FROM usuario WHERE user =  ? AND password = ?)";
    String sql_auth = "SELECT * from usuario WHERE user = ? and password = ?";
    String sql_listar = "SELECT cedula_usuario, email_usuario, nombre_usuario, password, user FROM usuario";
    String sql_buscar_por_cc = "SELECT cedula_usuario, email_usuario, nombre_usuario, password, user FROM usuario WHERE cedula_usuario = ?";
    String sql_nuevo = "INSERT INTO usuario(cedula_usuario, email_usuario, nombre_usuario, password, user) VALUES(?, ?, ?, ?, ?)";
    String sql_modificar = "UPDATE usuario SET email_usuario=?, nombre_usuario=?, password=?, user=? WHERE cedula_usuario=?";
    String sql_eliminar = "DELETE FROM usuario WHERE cedula_usuario = ?";

    PreparedStatement ps = null;
    ResultSet rs = null;
    Usuario usuario = null;
    List<Usuario> usuarios = new ArrayList<>();

    public boolean autenticacion(Usuario usuario) {
        try {
            
            ps = Conectar().prepareStatement(sql_auth);
            ps.setString(1, usuario.getUser());
            ps.setString(2, usuario.getPassword());
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setCedulaUsuario(rs.getInt("cedula_usuario"));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUser(rs.getString("user"));
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error al Autenticar Usuario: " + ex);
        }
        return false;
    }
//

    public Usuario autenticacion2(String user, String password) {
        //Usuario usuario = new Usuario();
        
        try {
            ps = Conectar().prepareStatement(sql_auth);
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setCedulaUsuario(rs.getInt("cedula_usuario"));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUser(rs.getString("user"));

            }
        } catch (SQLException ex) {
            System.out.println("Error al Autenticar Usuario: " + ex);
        }
        return usuario;
    }

    public List<Usuario> listar() {

        try {
            ps = Conectar().prepareStatement(sql_listar);
            rs = ps.executeQuery();
            //recorremos la tabla desde la base de datos
            while (rs.next()) {
                // capturamos la info de la base de datos y la guardamos en unas variables
                int cedulaUsuario = rs.getInt("cedula_usuario");
                String emailUsuario = rs.getString("email_usuario");
                String nombreUsuario = rs.getString("nombre_usuario");
                String password = rs.getString("password");
                String user = rs.getString("user");
                //usando un objeto y el constructor pasamos los datos
                usuario = new Usuario(cedulaUsuario, emailUsuario, nombreUsuario, password, user);
                usuarios.add(usuario); //llenamos la lista
                //System.out.println("######Lista desde el DAO######");
                //System.out.println(usuarios);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return usuarios;
    }

    public List<Usuario> buscarBox(String txtBuscar) {

        String sql_cuadro_busqueda = "SELECT * FROM usuario WHERE nombre_usuario LIKE '%" + txtBuscar + "%' or email_usuario LIKE '%" + txtBuscar + "%' or cedula_usuario LIKE '%" + txtBuscar + "%'";
        try {
            ps = Conectar().prepareStatement(sql_cuadro_busqueda);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCedulaUsuario(rs.getInt("cedula_usuario"));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUser(rs.getString("user"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar en la Barra de Busqueda = " + ex.getMessage());
        }
        return usuarios;
    }

    public Usuario buscarPorCC(Usuario usuario) {

        try {
            ps = Conectar().prepareStatement(sql_buscar_por_cc);
            ps.setInt(1, usuario.getCedulaUsuario());
            rs = ps.executeQuery();
            //rs.absolute(1);//nos posicionamos en el primer registro devuelto
            while (rs.next()) {
                // capturamos la info de la base de datos y la guardamos en unas variables
                int cedula = rs.getInt("cedula_usuario");
                String email = rs.getString("email_usuario");
                String nombre = rs.getString("nombre_usuario");
                String password = rs.getString("password");
                String user = rs.getString("user");

                // agregamos cada atribulo al objeto usuario y devolvemos un usuario completo
                usuario.setCedulaUsuario(cedula);
                usuario.setEmailUsuario(email);
                usuario.setNombreUsuario(nombre);
                usuario.setPassword(password);
                usuario.setUser(user);
            }

        } catch (SQLException ex) {
            System.out.println("Error al Buscar Usuario: " + ex.getMessage());
        }
        return usuario;
    }

    public int nuevo(Usuario usuario) {

        int resultado = 0;
        try {
            ps = Conectar().prepareStatement(sql_nuevo);
            ps.setInt(1, usuario.getCedulaUsuario());
            ps.setString(2, usuario.getEmailUsuario());
            ps.setString(3, usuario.getNombreUsuario());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getUser());

            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    public boolean modificar(Usuario usuario) {
        boolean resultado = false;
        try {
            ps = Conectar().prepareStatement(sql_modificar);
            ps.setString(1, usuario.getEmailUsuario());
            ps.setString(2, usuario.getNombreUsuario());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getUser());
            ps.setInt(5, usuario.getCedulaUsuario());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }

    public boolean eliminar(Usuario usuario) {
        boolean resultado = false;

        try {
            ps = Conectar().prepareStatement(sql_eliminar);
            ps.setInt(1, usuario.getCedulaUsuario());

            resultado = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }
}
