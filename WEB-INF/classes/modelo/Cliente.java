package modelo;

public class Cliente {
    private int cedulaCliente;
    private String direccionCliente;
    private String emailCliente;
    private String nombreCliente;
    private String telefonoCliente;

    public Cliente() {
    }

    public Cliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public Cliente(int cedulaCliente, String direccionCliente, String emailCliente, String nombreCliente, String telefonoCliente) {
        this.cedulaCliente = cedulaCliente;
        this.direccionCliente = direccionCliente;
        this.emailCliente = emailCliente;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cedulaCliente=" + cedulaCliente + ", direccionCliente=" + direccionCliente + ", emailCliente=" + emailCliente + ", nombreCliente=" + nombreCliente + ", telefonoCliente=" + telefonoCliente + '}';
    }
    
    
    
}
