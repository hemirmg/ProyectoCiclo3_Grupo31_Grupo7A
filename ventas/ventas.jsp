<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--Importado de la librería JSTL 1.2.1 para evirar incrustar tantos scriptles -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>-->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/css/f90d3bf50d.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/principalEstilos.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />

        <title>Modulo de Ventas</title>
    </head>
    <body>
        <jsp:include page="../WEB-INF/paginas/comunes/cabecera5.jsp"></jsp:include>
            <header id="main-header" class="py-2 mb-2 bg-warning text-dark centrado" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 d-flex">
                            <a href="${pageContext.request.contextPath}/Ventas?accion=Atras" class="btn btn-warning btn-circle" style="margin-left: -10px; margin-right:5px "><i class="fas fa-arrow-left"></i></a>
                        <h1><i class="fas fa-cash-register"></i> Punto de Venta</h1>
                    </div>
                </div>
            </div>
        </header>
        <br>
        <div class="d-flex centrado">
            <div class="col-sm-5">
                <div class="card">

                    <!--Datos de Cliente-->

                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/Ventas?menu=NuevaVenta" method="POST">
                            <div class="form-group">
                                <p style="text-align:left" class="h5"><i class="fas fa-user-tie"></i> Datos del Cliente</p>
                            </div>                   
                            <div class="form-group d-flex">
                                <div class="col-sm-5 d-flex">
                                    <input type="number" name="txtCedulaCliente" id="txtCedulaCliente" value="${cliente.cedulaCliente}" oninput="ActivarBoton()" class="form-control" placeholder="Cédula">
                                    <button type="submit" name="accion" value="BuscarCliente" id="btnBuscarCliente"  disabled="true" class="btn btn-warning" style="margin-right: 10px"><i class="fa fa-search"></i></button>
                                </div>

                                <div class="col-sm-7">
                                    <input type="text" name="txtNombreCliente" id="txtNombreCliente" value="${cliente.nombreCliente}" class="form-control" placeholder="Nombre Cliente" readonly>
                                </div>

                            </div>
                            <br>
                        </form>
                                
                        <!--Datos de Producto-->
                        <form action="${pageContext.request.contextPath}/Ventas?menu=NuevaVenta" method="POST">
                            <div class="form-group">
                                <p style="text-align:left" class="h5"><i class="fas fa-shopping-basket"></i> Datos Producto</p>
                            </div>                   
                            <div class="form-group d-flex">
                                <div class="col-sm-5 d-flex">
                                    <input type="text" name="txtCodigoProducto" value="${producto1.codigoProducto}" id="txtCodigoProducto" oninput="ActivarBoton()" class="form-control" placeholder="Código" autocomplete="off">
                                    <button type="submit" name="accion" value="BuscarProducto" id="btnBuscarProducto" class="btn btn-warning" disabled="true" style="margin-right: 10px"><i class="fa fa-search"></i></button>
                                </div>

                                <div class="col-sm-7">
                                    <input type="text" name="txtNombreProducto" id="txtNombreProducto" value="${producto1.nombreProducto}" placeholder="Descripción" class="form-control" readonly>
                                </div>
                            </div>                   
                            <div class="form-group d-flex justify-content-between">
                                <div class="input-group mb-4">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1" style="margin-top: 10px">$</span>
                                    </div>
                                    <input type="number" value="${producto1.precioVenta}" name="txtPrecio" class="form-control" placeholder="0.000" aria-label="Username" aria-describedby="basic-addon1" style="margin-right: 10px;margin-top: 10px" readonly>
                                </div>

                                <div class="input-group mb-4">
                                    <input type="number" value="${producto1.ivaCompra}" name="txtIva" class="form-control" placeholder="0.0" aria-label="Username" aria-describedby="basic-addon1"  style="margin-top: 10px" readonly>
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1" style="margin-right: 5px;margin-top: 10px">%</span>
                                    </div>
                                </div>

                                <div class="col-sm-3">
                                    <input type="number" name="txtCantidad" value="" placeholder="Cantidad" id="txtCantidad" oninput="ActivarBoton()" class="form-control" style="margin-right: 5px;margin-top: 10px">
                                </div>
                                <div class="col-sm-1">
                                    <button type="submit" name="accion" value="Agregar" id="btnAgregar" disabled="true" class="btn btn-warning" onclick="activarPagar()" style="margin-left: 5px;margin-top: 10px"><i class="fas fa-angle-double-right"></i></button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <h5 id="lbResultado" style="text-align: center; color:${color}; ${display}"><i class="${icono}"></i> ${mensaje}</h5>
            </div>
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div>
                                <div class="d-flex justify-content-start" style="margin-top: 5px">
                                    <p class="h5">Cliente</p>
                                    <small class="h5 text-muted">: ${cliente.nombreCliente}</small>
                                </div>
                                <div class="d-flex justify-content-start" style="margin-top: 5px">
                                    <p class="h6">Dirección</p>
                                    <small class="h6 text-muted">: ${cliente.direccionCliente}</small>
                                </div>
                                <div class="d-flex justify-content-start" style="margin-top: 5px">
                                    <p class="h6">Telefono</p>
                                    <small class="h6 text-muted">: ${cliente.telefonoCliente}</small>
                                </div>
                            </div>

                            <div>
                                <div class="d-flex justify-content-end" style="margin-top: 5px">
                                    <p type="number"  class="h1" style="text-align: right">$ ${totalVenta2}</p>
                                </div>
                                <div class="d-flex justify-content-end" style="margin-top: 5px">
                                    <div class="col-sm-8 d-flex">
                                        <p class="h6">Nro:</p>
                                        <input type="text" name="txtConsecutivo" class="form-control" value="${consecutivo}" style="text-align: right" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Item</th>
                                    <th>Código</th>
                                    <th>Descripción</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th>
                                    <th>Total</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="articulo" items="${articulos}">
                                    <tr>
                                        <td>${articulo.item}</td>
                                        <td>${articulo.codigo}</td>
                                        <td>${articulo.descripcion}</td>
                                        <td>${articulo.precio}</td>
                                        <td>${articulo.cantidad}</td>
                                        <td>${articulo.subtotal}</td>
                                        <td>
                                            <a href="" class="btn btn-light"><i class="fa fa-trash-o"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer d-flex align-items-center">
                        <div class="col-sm-5">
                            <form action="${pageContext.request.contextPath}/Ventas?menu=NuevaVenta" method="POST">
                                <a href="${pageContext.request.contextPath}/Ventas?accion=Cancelar" class="btn btn-danger"><i class="fas fa-times"></i> Cancelar</a>
                                <button type="submit" name="accion" value="Pagar" id="btnPagar" ${control} class="btn btn-warning"><i class="fas fa-dollar-sign"></i> Pagar <i class="fas fa-angle-right"></i></button>
                            </form>
                        </div>

                        <div class="col-sm-7">
                            <div class="form-group row">
                                <label class="col-sm-8 col-form-label col-form-label-sm" style="text-align: right; margin-top: 5px">SubTotal</label>
                                <div class="col-sm-4" style="margin-top: 5px">
                                    <input type="email" class="form-control form-control-sm" value="${subTotal}" placeholder="$ 0.000"  style="text-align: right" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-8 col-form-label col-form-label-sm"  style="text-align: right; margin-top: 5px">Total IVA</label>
                                <div class="col-sm-4" style="margin-top: 5px">
                                    <input type="email" class="form-control form-control-sm" value="${totalIva}" placeholder="$ 0.000"  style="text-align: right" readonly>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-8 col-form-label col-form-label-sm"  style="text-align: right; margin-top: 5px">Total Venta</label>
                                <div class="col-sm-4" style="margin-top: 5px">
                                    <input type="email" class="form-control form-control-sm" value="${totalVenta}" placeholder="$ 0.000"  style="text-align: right" readonly>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>






        <!-- Scripts para JS-->
        <Script type="text/javascript">
            function ActivarBoton() {
                var nomCliente = document.getElementById("txtNombreCliente").value;
                var nomProducto = document.getElementById("txtNombreProducto").value;
                var cantidad = document.getElementById("txtCantidad").value;
                if (nomProducto != "" && cantidad != "" && nomCliente != "") {
                    document.getElementById('btnAgregar').disabled = false;
                    document.getElementById('btnBuscarCliente').disabled = true;
                    document.getElementById('btnBuscarProducto').disabled = true;
                } else {
                    document.getElementById('btnAgregar').disabled = true;
                    document.getElementById('btnBuscarCliente').disabled = false;
                    document.getElementById('btnBuscarProducto').disabled = false;
                }

                var codCliente = document.getElementById("txtCedulaCliente").value;
                if (codCliente != "") {
                    document.getElementById('btnBuscarCliente').disabled = false;
                } else {
                    document.getElementById('btnBuscarCliente').disabled = true;
                }
                var codProducto = document.getElementById("txtCodigoProducto").value;
                if (codProducto != "") {
                    document.getElementById('btnBuscarProducto').disabled = false;
                } else {
                    document.getElementById('btnBuscarProducto').disabled = true;
                }
            }
            function activarPagar(){
                document.getElementById('btnPagar').disabled = false;
            }

        </Script>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
