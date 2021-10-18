package controlador;

import java.sql.*;

public class Conexion {
//    String url = "jdbc:mysql://185.156.219.149:3306/pastobur_loginjsp?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=false";
//    String url = "jdbc:mysql://localhost:3306/dbminuto85?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//    private static final String url = "jdbc:mysql://localhost:3306/dbminuto85?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=false";
//    private static final String user = "root";
//    private static final String password = "";

    public String url = "jdbc:mysql://localhost:3306/grupo31_equipo_7?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=false";

    //public String url = "jdbc:mysql://prestamosvf.czo3ixoe3xoe.us-east-1.rds.amazonaws.com/Grupo31_Equipo_7?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=false";
    public String user = "root";
    public String password = "";
    Connection con = null;

    public Connection Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("org.mariadb.jdbc.Driver");
            //con = DriverManager.getConnection(url, user, password);
            con = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa.");
        } catch (Exception e) {
            System.out.println("No se puedo conectar..." + e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Conexion con = new Conexion();
        con.Conectar();
    }

}
