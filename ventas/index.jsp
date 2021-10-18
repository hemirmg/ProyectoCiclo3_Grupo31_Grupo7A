<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--Importado de la librería JSTL 1.2.1 para evirar incrustar tantos scriptles -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <!--<script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <script src="../js/newjavascript.js" type="text/javascript"></script>-->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/css/f90d3bf50d.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/principalEstilos.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Módulo Ventas | MiFactura</title>
    </head>
    <body>
        <jsp:include page="../WEB-INF/paginas/comunes/cabecera5.jsp"></jsp:include>
            <header id="main-header" class="py-2 mb-2 bg-info text-dark centrado" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <h1 class="text-white">
                                <i class="fas fa-store"></i> Módulo Ventas</h1>
                        </div>
                    </div>
                </div>
            </header>

            <!-- Barra Nueva Factura y Buscar -->

            <br>
            <br>
            <div class="container d-flex centrado" style="justify-content:space-between ">
                <div class="col-md-2" id="actions">
                    <a href="${pageContext.request.contextPath}/Ventas?accion=Nuevo" class="btn btn-info btn-block "><i class="fas fa-cash-register"></i> Nueva Factura</a>
            </div>
            <form  action="${pageContext.request.contextPath}/Usuarios?accion=buscar" method="POST" class="form d-flex col-md-3">
                <input class="form-control" type="search" name="txtBuscar" placeholder="Buscar Factura" style="margin-right: 10px" autocomplete="off" required>
                <button type="submit" class="btn btn-info btn-block"><i class="fa fa-search"></i></button>
            </form>
        </div>
        <br>

        <!-- Resultado Consulta Usuarios -->

        <div class="container centrado">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="col-md-3">FACT No.</th>
                        <th class="col-md-3">CLIENTE</th>
                        <th class="col-md-3">VALOR $</th>
                        <th class="col-md-3">ACCIONES</th>

                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="factura" items="${facturas}">

                        <tr>
                            <td class="text-center" id="codigoVenta"><c:out value="${factura.consecutivo}"/></td>
                            <td><c:out value="${factura.nombreCliente}"/></td>
                            <td><c:out value="$ ${factura.totalVenta}"/></td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/Ventas?accion=ver&id=<c:out value="${factura.codigoVenta}"/>&cc=<c:out value="${factura.cedulaCliente}"/>" class="btn btn-success btn-sm"> <i class="far fa-eye"></i> Ver</a>
                                <a href="" class="btn btn-primary btn-sm"> <i class="fa fa-pencil"></i> Editar</a>
                                <a href="${pageContext.request.contextPath}/Ventas?accion=eliminar&id=<c:out value="${factura.codigoVenta}"/>" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Eliminar</a>
                            </td>
                        </tr> 
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
