<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Principal | MiFactura</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <link href="css/principalEstilos.css" rel="stylesheet" type="text/css"/>

    </head>
    <jsp:include page="WEB-INF/paginas/comunes/cabecera.jsp"></jsp:include>
        <br>

        <body class="fondo" style="background: #ccc">
            <br>
            <div style="display: flex; justify-content: center; align-items: center" >
                <div>
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/Usuarios" class="card text-center btn btn-outline-primary " style="padding: 50px; font-size: 30px">
                        <i class="fas fa-users"></i> Usuarios
                    </a>
                </div>
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/Productos" class="card text-center btn btn-outline-danger" style="padding: 50px; font-size: 30px">
                        <i class="fab fa-product-hunt"></i> Productos
                    </a>
                </div>
            </div>

            <div>


                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/Clientes" class="card text-center btn btn-outline-success" style="padding: 50px; font-size: 30px">
                        <i class="fas fa-mug-hot"></i> Clientes
                    </a>
                </div>
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/Ventas" class="card text-center btn btn-outline-info" style="padding: 50px; font-size: 30px">
                        <i class="fas fa-store"></i> Ventas
                    </a>
                </div>
            </div>

            <div>

                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/Proveedores" class="card text-center btn btn-outline-secondary" style="padding: 50px; font-size: 30px">
                        <i class="fas fa-people-carry"></i> Proveedores
                    </a>
                </div>
                <div class="card-body">
                    <a href="${pageContext.request.contextPath}/reportes" class="card text-center btn btn-outline-dark" style="padding: 50px; font-size: 30px">
                        <i class="fas fa-file-invoice"></i> Reportes
                    </a>
                </div>
            </div>

        </div>
        <!-- Scripts para JS-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>

