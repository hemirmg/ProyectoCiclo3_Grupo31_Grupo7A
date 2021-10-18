<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--Importado de la librería JSTL 1.2.1 para etiquetas -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <link href="../css/principalEstilos.css" rel="stylesheet" type="text/css"/>
        <link href="/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Modificar | Mi Factura</title>

    </head>
    <body>
        <jsp:include page="../WEB-INF/paginas/comunes/cabecera1.jsp"></jsp:include>
            <!--Header / Cabecera -->
            <header id="main-header" class="py-2 bg-primary text-white centrado" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <h1><i class="fas fa-users"></i> Control de Usuarios</h1>
                        </div>
                    </div>
                </div>
            </header>
            <br>
            <form action="${pageContext.request.contextPath}/Usuarios?accion=modificar&cedulaUsuario=${usuario.cedulaUsuario}" method="POST" class="was-validated mx-auto col-md-7">

            <!-- Campos de Formulario-->
            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Modificar Usuario</h4>
                                </div>
                                <div class="card-body">
                                    <label for="txtCedula">CÉDULA:</label>
                                    <input type="number" name="txtCedula" class="form-control form-control-plaintext" autocomplete="off" required value="${usuario.cedulaUsuario}" readonly> 
                                    <label for="txtNombre">NOMBRE:</label>
                                    <input type="text" name="txtNombre" class="form-control" autocomplete="off" required value="${usuario.nombreUsuario}">
                                    <label for="txtEmail">CORREO ELECTRÓNICO:</label>
                                    <input type="email" name="txtEmail" class="form-control" autocomplete="off" required value="${usuario.emailUsuario}">
                                    <label for="txtUser">USUARIO:</label>
                                    <input type="text" name="txtUser" class="form-control" autocomplete="off" required value="${usuario.user}">
                                    <label for="txtPassword">CONTRASEÑA:</label>
                                    <input type="password" name="txtPassword" class="form-control" autocomplete="off" required value="${usuario.password}">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Botones Navegación-->
            <section id="actions" class="py-4 mb-4 bg-light">
                <div class="container">
                    <div class="d-flex">
                        <div class="col-sm-2">
                            <div class="card-body">
                                <a href="${pageContext.request.contextPath}/Usuarios" class="btn btn-light btn-block">
                                    <i class="fas fa-arrow-left"></i> Regresar
                                </a>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="card-body">
                                <button type="submit" class="btn btn-success btn-block">
                                    <i class="fas fa-check"></i> Guardar
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>
        <!-- Scripts para JS-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


    </body>
</html>