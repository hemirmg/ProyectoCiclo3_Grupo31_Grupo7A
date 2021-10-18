package modelo;

public class Proveedor {
    private int nitProveedor;
    private String ciudadProveedor;
    private String direccionProveedor;
    private String nombreProveedor;
    private String telefonoProveedor;

    public Proveedor() {
    }

    public Proveedor(int nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public Proveedor(int nitProveedor, String ciudadProveedor, String direccionProveedor, String nombreProveedor, String telefonoProveedor) {
        this.nitProveedor = nitProveedor;
        this.ciudadProveedor = ciudadProveedor;
        this.direccionProveedor = direccionProveedor;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
    }

    public int getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(int nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getCiudadProveedor() {
        return ciudadProveedor;
    }

    public void setCiudadProveedor(String ciudadProveedor) {
        this.ciudadProveedor = ciudadProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "nitProveedor=" + nitProveedor + ", ciudadProveedor=" + ciudadProveedor + ", direccionProveedor=" + direccionProveedor + ", nombreProveedor=" + nombreProveedor + ", telefonoProveedor=" + telefonoProveedor + '}';
    }
    
    
}
