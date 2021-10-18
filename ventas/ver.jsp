<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--Importado de la librería JSTL 1.2.1 para etiquetas -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <link href="../css/principalEstilos.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Ver detalle de Factura | Mi Factura</title>

    </head>
    <body>
        <jsp:include page="../WEB-INF/paginas/comunes/cabecera5.jsp"></jsp:include>
        
            <header id="main-header" class="py-2 bg-success text-white centrado" >
                <div class="container">
                    <div class="row">
                        <div class="mx-auto col-md-7" >
                            <h1><i class="fas fa-file-invoice-dollar"></i> Detalle de Factura </h1>
                        </div>
                    </div>
                </div>
            </header>
            <br>

            <!--<div class="col-sm-7 align-items-center align-self-center">-->
            <div class="m-0 row justify-content-center">
                <div class="col-md-6 align-items-center align-self-center">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between">
                                <div class="col-md-7">
                                    <div class="card">
                                        <div class="card-body">
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
                                </div>
                            </div>

                            <div class="col-md-5">
                                <div class="d-flex justify-content-end" style="margin-top: 5px">
                                    <p type="number"  class="h1" style="text-align: right">$ ${totalVenta3}</p>
                                </div>
                                <div class="d-flex justify-content-end" style="margin-top: 5px">
                                    <div class="col-sm-8 d-flex">
                                        <p class="h6">Nro:</p>
                                        <input type="text" name="txtConsecutivo" class="form-control" value="${venta.consecutivo}" style="text-align: right" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th class="text-center">Item</th>
                                    <th class="text-center">Código</th>
                                    <th>Descripción</th>
                                    <th>Precio</th>
                                    <th class="text-center">Cantidad</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="detalleVenta1" items="${detalleVenta}">
                                    <tr>
                                        <td class="text-center"><c:out value="${detalleVenta1.item}"/></td>
                                        <td class="text-center"><c:out value="${detalleVenta1.codigo}"/></td>
                                        <td><c:out value="${detalleVenta1.descripcion}"/></td>
                                        <td><c:out value="${detalleVenta1.precio}"/></td>
                                        <td class="text-center"><c:out value="${detalleVenta1.cantidad}"/></td>
                                        <td><c:out value="${detalleVenta1.totalVenta}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer d-flex align-items-center">
                        <div class="col-sm-5">
                            <form>
                                <a href="${pageContext.request.contextPath}/Ventas?accion=Atras" class="btn btn-success"><i class="fas fa-arrow-left"></i> Volver</a>
                                <a href="${pageContext.request.contextPath}/Ventas?accion=eliminar&id=${venta.getCodigoVenta()}" class="btn btn-danger"><i class="far fa-trash-alt"></i> Eliminar</a>
                            </form>
                        </div>

                        <div class="col-sm-7">
                            <div class="form-group row">
                                <label class="col-sm-8 col-form-label col-form-label-sm" style="text-align: right; margin-top: 5px">SubTotal</label>
                                <div class="col-sm-4" style="margin-top: 5px">
                                    <input type="email" class="form-control form-control-sm" value="$ ${subTotal1}" placeholder="$ 0.000"  style="text-align: right" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-8 col-form-label col-form-label-sm"  style="text-align: right; margin-top: 5px">Total IVA</label>
                                <div class="col-sm-4" style="margin-top: 5px">
                                    <input type="email" class="form-control form-control-sm" value="$ ${totalIva1}" placeholder="$ 0.000"  style="text-align: right" readonly>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-8 col-form-label col-form-label-sm"  style="text-align: right; margin-top: 5px">Total Venta</label>
                                <div class="col-sm-4" style="margin-top: 5px">
                                    <input type="email" class="form-control form-control-sm" value="$ ${totalVenta1}" placeholder="$ 0.000"  style="text-align: right" readonly>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- Scripts para JS-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


    </body>
</html>