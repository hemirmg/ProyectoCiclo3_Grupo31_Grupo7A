<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--Importado de la librería JSTL 1.2.1 para evirar incrustar tantos scriptles -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <link href="../css/principalEstilos.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Módulo Usuarios | Mi Factura</title>

    </head>
    <body>
        <jsp:include page="../WEB-INF/paginas/comunes/cabecera1.jsp"></jsp:include>
            <!--Header / Cabecero -->
            <header id="main-header" class="py-2 bg-primary text-white centrado" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <h1>
                                <i class="fas fa-users"></i> Control de Usuarios</h1>
                        </div>
                    </div>
                </div>
            </header>

            <!-- Barra btnNuevo y Buscar -->

            <br>
            <br>
            <div class="container d-flex centrado" style="justify-content:space-between ">
                <div class="col-md-2" id="actions">
                    <a href="${pageContext.request.contextPath}/usuarios/nuevo.jsp" class="btn btn-success btn-block" data-toggle="modal" data-target="#nuevoUsuarioModal"> <i class="fas fa-plus"></i> Nuevo Usuario</a>
            </div>
            <form  action="${pageContext.request.contextPath}/Usuarios?accion=buscar" method="POST" class="form d-flex col-md-3">
                <input class="form-control" type="search" name="txtBuscar" placeholder="Buscar usuario" style="margin-right: 10px" autocomplete="off" required>
                <button type="submit" class="btn btn-primary btn-block" data-toggle="modal" data-target="#buscarUsuario"><i class="fa fa-search"></i></button>
            </form>
        </div>
        <br>
        <!-- Resultado Consulta Usuarios -->
        <div class="container centrado">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">CÉDULA</th>
                        <th>NOMBRE</th>
                        <th>CORREO ELECTRÓNICO</th>
                        <th>USUARIO</th>
                        <th class="text-center">ACCIONES</th>

                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="usuario" items="${usuarios}">

                        <tr>
                            <td class="text-center" id="cedulaUsuario"><c:out value="${usuario.cedulaUsuario}"/></td>
                            <td><c:out value="${usuario.nombreUsuario}"/></td>
                            <td><c:out value="${usuario.emailUsuario}"/></td>
                            <td><c:out value="${usuario.user}"/></td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/Usuarios?accion=editar&cedulaUsuario=<c:out value="${usuario.cedulaUsuario}"/>" class="btn btn-primary btn-sm"> <i class="fa fa-pencil"></i> Editar</a>
                                <a href="${pageContext.request.contextPath}/usuarios/eliminar.jsp?accion=eliminar&cedulaUsuario=<c:out value="${usuario.cedulaUsuario}"/>&nom=<c:out value="${usuario.nombreUsuario}"/>" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Eliminar</a>
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

<!--Nuevo Usuario | MODAL-->
<jsp:include page="nuevo.jsp"/>