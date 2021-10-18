<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--Importado de la librería JSTL 1.2.1 para evirar incrustar tantos scriptles -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <link href="../css/principalEstilos.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Reportes | Mi Factura</title>

    </head>
    <body>
        <jsp:include page="../WEB-INF/paginas/comunes/cabecera6.jsp"></jsp:include>
            <!--Header / Cabecero -->
            <header id="main-header" class="py-2 bg-dark text-white centrado" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <h1><i class="far fa-file-alt"></i> Reporte de Facturas</h1>
                        </div>
                    </div>
                </div>
            </header>

            <br>
            <br>
            <div class="container d-flex centrado" style="justify-content:initial ">
                <form  action="${pageContext.request.contextPath}/Reportes?accion=buscar" method="POST" class="form d-flex col-md-3">
                <input class="form-control" type="search" name="txtBuscar" placeholder="Buscar cliente" style="margin-right: 10px" autocomplete="off" required>
                <button type="submit" class="btn btn-primary btn-block"><i class="fa fa-search"></i></button>
            </form>
        </div>
        <br>
        <!-- Resultado Consulta Factura Clientes -->
        <div class="container centrado">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">CÉDULA</th>
                        <th class="text-left">CLIENTE</th>
                        <th class="text-right">VALOR FACTURA</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="clienteFactura" items="${clienteFacturas}">

                        <tr>
                            <td class="text-center" id="cedulaUsuario"><c:out value="${clienteFactura.cedulaCliente}"/></td>
                            <td class="text-left"><c:out value="${clienteFactura.nombreCliente}"/></td>
                            <td class="text-right"><c:out value="$ ${clienteFactura.totalVenta}"/></td>
                        </tr> 
                    </c:forEach>
                </tbody>
            </table>
            <div class="d-flex justify-content-end">
                <div class="form-group row">
                    <label class="col-sm-8 col-form-label col-form-label-sm"  style="text-align: right; margin-top: 5px; font-size: 20px">Total Facturado:</label>
                    <div class="col-sm-4" style="margin-top: 5px">
                        <input type="email" class="form-control form-control-sm" value="$ ${totalFactura}" placeholder="$ 0.000"  style="text-align: right; font-size: 20px" readonly>
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
