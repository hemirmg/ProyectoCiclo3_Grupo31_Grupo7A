package modelo;

public class Usuario {
    private int cedulaUsuario;
    private String emailUsuario;
    private String nombreUsuario;
    private String password;
    private String user;

    public Usuario() {
    }

    public Usuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    
    public Usuario(String user, String password) {
        this.password = password;
        this.user = user;
    }

    public Usuario(int cedulaUsuario, String emailUsuario, String nombreUsuario, String password, String user) {
        this.cedulaUsuario = cedulaUsuario;
        this.emailUsuario = emailUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.user = user;
    }

    public int getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedulaUsuario=" + cedulaUsuario + ", emailUsuario=" + emailUsuario + ", nombreUsuario=" + nombreUsuario + ", password=" + password + ", user=" + user + '}';
    }

        
    
}
