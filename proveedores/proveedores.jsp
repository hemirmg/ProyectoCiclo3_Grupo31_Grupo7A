<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--Importado de la librería JSTL 1.2.1 para evirar incrustar tantos scriptles -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <link href="../css/principalEstilos.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Módulo Proveedores | Mi Factura</title>

    </head>
    <body>
        <jsp:include page="../WEB-INF/paginas/comunes/cabecera3.jsp"></jsp:include>
            <!--Header / Cabecero -->
            <header id="main-header" class="py-2 bg-secondary text-white centrado" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <h1>
                                <i class="fas fa-people-carry"></i> Control de Proveedores</h1>
                        </div>
                    </div>
                </div>
            </header>

            <!-- Barra btnNuevo y Buscar -->
            <br>
            <br>
            <div class="container centrado" style="display: flex; justify-content:space-between ">

                <div class="col-md-2" id="actions">
                    <a href="${pageContext.request.contextPath}/proveedores/nuevo.jsp" class="btn btn-secondary btn-block" data-toggle="modal" data-target="#nuevoProveedorModal"> <i class="fas fa-plus"></i> Nuevo Proveedor</a>
            </div>

            <form  action="${pageContext.request.contextPath}/Proveedores?accion=buscar" method="POST" class="form" style="display: flex; width: 300px">
                <input class="form-control" type="search" name="txtBuscar" placeholder="Buscar proveedor" style="margin-right: 10px" autocomplete="off" required>
                <button type="submit" class="btn btn-primary btn-block"><i class="fa fa-search"></i></button>
            </form>

            </form>

        </div>
        <br>

        <!-- Resultado Consulta Proveedores -->
        <div class="container centrado">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">NIT</th>
                        <th>PROVEEDOR</th>
                        <th>TELÉFONO</th>
                        <th class="text-center">ACCIONES</th>

                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="proveedor" items="${proveedores}">

                        <tr>
                            <td class="text-center"><c:out value="${proveedor.nitProveedor}"/></td>
                            <td><c:out value="${proveedor.nombreProveedor}"/></td>
                            <td><c:out value="${proveedor.telefonoProveedor}"/></td>

                            <td class="text-center">
                                <!-- estos no son formularios, son peticiones get por ser links -->
                                <a href="${pageContext.request.contextPath}/Proveedores?accion=editar&nitProveedor=<c:out value="${proveedor.nitProveedor}"/>" class="btn btn-primary btn-sm"> <i class="fa fa-pencil"></i> Editar</a>
                                <a href="${pageContext.request.contextPath}/Proveedores?accion=eliminar&nitProveedor=<c:out value="${proveedor.nitProveedor}"/>" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Eliminar</a>
                            </td>
                        </tr> 

                    </c:forEach>


                </tbody>

            </table>
        </div>

        <!-- Scripts para JS-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


    </body>
</html>

<!--Nuevo Proveedor | MODAL-->
<jsp:include page="nuevo.jsp"/>