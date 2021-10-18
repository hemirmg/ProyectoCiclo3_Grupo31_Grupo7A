package modelo;

public class Producto {
    private int codigoProducto;
    private double ivaCompra;
    private int nitProveedor;
    private String nombreProducto;
    private double precioCompra;
    private double precioVenta;

    public Producto() {
    }

    public Producto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Producto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    

    public Producto(double ivaCompra, int nitProveedor, String nombreProducto, double precioCompra, double precioVenta) {
        this.ivaCompra = ivaCompra;
        this.nitProveedor = nitProveedor;
        this.nombreProducto = nombreProducto;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }
    

    public Producto(int codigoProducto, double ivaCompra, int nitProveedor, String nombreProducto, double precioCompra, double precioVenta) {
        this.codigoProducto = codigoProducto;
        this.ivaCompra = ivaCompra;
        this.nitProveedor = nitProveedor;
        this.nombreProducto = nombreProducto;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public double getIvaCompra() {
        return ivaCompra;
    }

    public void setIvaCompra(double ivaCompra) {
        this.ivaCompra = ivaCompra;
    }

    public int getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(int nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigoProducto=" + codigoProducto + ", ivaCompra=" + ivaCompra + ", nitProveedor=" + nitProveedor + ", nombreProducto=" + nombreProducto + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + '}';
    }
    
    
}
