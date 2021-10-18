<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--Importado de la librería JSTL 1.2.1 para evirar incrustar tantos scriptles -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <script src="../js/newjavascript.js" type="text/javascript"></script>
        <link href="../css/principalEstilos.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Módulo Productos | Mi Factura</title>

    </head>
    <body>
        <jsp:include page="../WEB-INF/paginas/comunes/cabecera4.jsp"></jsp:include>
            <!--Header / Cabecero -->
            <header id="main-header" class="py-2 bg-danger text-white centrado" >
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <h1>
                                <i class="fab fa-product-hunt"></i> Productos</h1>
                        </div>
                    </div>
                </div>
            </header>

            <!-- Barra Agregar Productos -->
            <div class=" centrado">
                <div>
                    <br>
                    <div>
                        <h5 style="text-align: left">Crear Producto:</h5>
                    </div>
                    <div class="card">
                        <form action="${pageContext.request.contextPath}/Productos?accion=nuevo" method="POST">
                        <div class="d-flex align-items-center">
                            <div class="form-group col-sm-2 px-2">
                            </div>
                        </div>
                        <div class="card-body">

                            <div class="form-group d-flex">

                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-1 px-2">
                                    <label>CÓDIGO:</label>
                                    <input type="text" name="txtCodigoProducto" placeholder="Codigo Hidden" value="${producto.codigoProducto}" class="form-control" required>
                                </div>
                                <div class="col-sm-3 px-2">
                                    <label>PRODUCTO:</label>
                                    <input type="text" name="txtNombreProducto" placeholder="Descripción" value="${producto.nombreProducto}" class="form-control" required>
                                </div>
                                <div class="col-sm-1 px-2">
                                    <label>PROVEE:</label>
                                    <input type="number" name="txtNitProveedor" placeholder="Nit" value="${producto.nitProveedor}" class="form-control" required>
                                </div>
                                <div class="col-sm-2 px-2">
                                    <label>P. COMPRA:</label>
                                    <input type="number" name="txtPrecioCompra" placeholder="$ 0.000" value="${producto.precioCompra}" class="form-control" style="text-align: right" required>
                                </div>
                                <div class="col-sm-1 px-2">
                                    <label>IVA:</label>
                                    <input type="number" name="txtIvaCompra"  placeholder="%" value="${producto.ivaCompra}" class="form-control" style="text-align: right" required>
                                </div>
                                <div class="col-sm-2 px-2">
                                    <label>P. VENTA:</label>
                                    <input type="number" name="txtPrecioVenta" placeholder="$ 0.000" value="${producto.precioVenta}" class="form-control" style="text-align: right" required>
                                </div>
                                <div class="col-sm-2 center">
                                    <br>
                                    <a class="btn btn-outline-danger">
                                        <i class="fas fa-file-csv" onclick="mostrarCargar()"></i>
                                    </a>
                                    <button type="submit" class="btn btn-outline-success">
                                        <i class="fas fa-check"></i> Guardar
                                    </button>
                                </div>

                            </div>
                        </div>
                    </form>
                </div>
                <br>
                <h5 id="lbResultado" style="text-align: center; color:${color}; ${display}"><i class="${icono}"></i> ${mensaje}</h5>

                <div class="card" id="cargarArchivo" style="display: none">
                    <br>
                    <div class="form-group centrado d-flex align-self-center">
                        <h5><i class="fas fa-file-csv"></i> Cargar archivo .csv</h5>
                    </div>
                    <form action="${pageContext.request.contextPath}/Productos?accion=csv" method="POST" enctype="multipart/form-data">
                        <div class="card-body">

                            <div class="form-group d-flex justify-content-center">
                                <div class="px-2">
                                    <input type="file" accept=".csv,.txt" name="archivo" class="form-control" required>
                                </div>
                                <button type="submit" class="btn btn-outline-success">
                                    <i class="fas fa-upload"></i> Subir
                                </button>
                                <a class="btn btn-light">
                                    <i class="far fa-eye-slash" onclick="ocultarCargar()"></i>
                                </a>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <br>
            <div class="form-group">
                <h5 style="text-align: left">Lista Productos:</h5>
            </div>
            <div>
                <div class="card">
                    <div class="card-body">

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>DESCRIPCIÓN</th>
                                    <th>PROVEEDOR</th>
                                    <th>P. COMPRA</th>
                                    <th>IVA</th>
                                    <th>P. VENTA</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="producto" items="${productos}">
                                    <tr>
                                        <td><c:out value="${producto.codigoProducto}"/></td>
                                        <td><c:out value="${producto.nombreProducto}"/></td>
                                        <td><c:out value="${producto.nitProveedor}"/></td>
                                        <td><c:out value="${producto.precioCompra}"/></td>
                                        <td><c:out value="${producto.ivaCompra}"/></td>
                                        <td><c:out value="${producto.precioVenta}"/></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/Productos?accion=editar&id=<c:out value="${producto.codigoProducto}"/>" class="btn btn-primary btn-sm"> <i class="fa fa-pencil"></i> Editar</a>
                                            <a href="${pageContext.request.contextPath}/Productos?accion=eliminar&id=<c:out value="${producto.codigoProducto}"/>" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div>

            </div>

        </div>

        <script type="text/javascript">
            function mostrarCargar() {
                document.getElementById("cargarArchivo").style.display = "";
                document.getElementById("lbResultado").style.display = "none";
            }

            function ocultarCargar() {
                document.getElementById("cargarArchivo").style.display = "none";
                document.getElementById("lbResultado").style.display = "none";
            }
        </script>

        <!-- Scripts para JS-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



    </body>
</html>