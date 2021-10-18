package modelo;

public class Venta {

    //Venta
    private int codigoVenta;
    private int cedulaCliente;
    private int cedulaUsuario;
    private double subTotal;
    private double ivaVenta;
    private double totalVenta;
    private String consecutivo;
    private String nombreCliente;
    private String direccionCliente;
    private String telefonoCliente;
    
    double totalIva;

    //detalle venta
    int item;
    int codigo;
    String descripcion;
    double iva;
    double precio;
    int cantidad;
    double subtotalIva;
    double subtotal;

    public Venta() {
    }

    public Venta(int cedulaCliente, String nombreCliente, double totalVenta) {
        this.cedulaCliente = cedulaCliente;
        this.totalVenta = totalVenta;
        this.nombreCliente = nombreCliente;
    }

    
    
    public Venta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Venta(int item, int cantidad, int codigo, String descripcion, double precio, int codigoVenta, double totalVenta, double ivaVenta) {
        this.item=item;
        this.codigoVenta = codigoVenta;
        this.ivaVenta = ivaVenta;
        this.totalVenta = totalVenta;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.descripcion=descripcion;
        this.precio=precio;
    }
    
    

    public Venta(int codigoVenta, int cedulaCliente, double totalVenta, String consecutivo, String nombreCliente, String direccionCliente, String telefonoCliente) {
        this.codigoVenta = codigoVenta;
        this.cedulaCliente = cedulaCliente;
        this.totalVenta = totalVenta;
        this.consecutivo=consecutivo;
        this.nombreCliente=nombreCliente;
        this.direccionCliente=direccionCliente;
        this.telefonoCliente=telefonoCliente;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(double totalIva) {
        this.totalIva = totalIva;
    }



    
    
    //Geter - Setter # detalle
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSubtotalIva() {
        return subtotalIva;
    }

    public void setSubtotalIva(double subtotalIva) {
        this.subtotalIva = subtotalIva;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
    
    

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public int getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIvaVenta() {
        return ivaVenta;
    }

    public void setIvaVenta(double ivaVenta) {
        this.ivaVenta = ivaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String toString() {
        return "Venta{" + "codigoVenta=" + codigoVenta + ", cedulaCliente=" + cedulaCliente + ", cedulaUsuario=" + cedulaUsuario + ", subTotal=" + subTotal + ", ivaVenta=" + ivaVenta + ", totalVenta=" + totalVenta + ", consecutivo=" + consecutivo + ", nombreCliente=" + nombreCliente + ", direccionCliente=" + direccionCliente + ", telefonoCliente=" + telefonoCliente + ", totalIva=" + totalIva + ", item=" + item + ", codigo=" + codigo + ", descripcion=" + descripcion + ", iva=" + iva + ", precio=" + precio + ", cantidad=" + cantidad + ", subtotalIva=" + subtotalIva + ", subtotal=" + subtotal + '}';
    }

    
    
}
