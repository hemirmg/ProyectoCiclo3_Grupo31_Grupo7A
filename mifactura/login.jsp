<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
        <link href="${pageContext.request.contextPath}/image/favicon.ico" rel="icon" type="image/x-icon" />
        <title>Iniciar Sesión</title>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="contenedor-todo">
            <div class="ctn-form">
                <div class="ctn-LogoTexto">
                    
                    <h1 class="texto" style="text-align: left"><i class="fas fa-file-invoice-dollar"></i> Mi Factura</h1>
                </div>
                <h1 class="titulo">Iniciar Sesión</h1>
                <form action = "${pageContext.request.contextPath}/Usuarios?accion=login" method = "POST">
                    <label for="user">Usuario</label>
                    <input type="text" id="user" name="user" autocomplete="off" required="true"/>
                    <label for="password">Contraseña</label>
                    <input type="password" id="password" name="password" autocomplete="off" required="true"/>
                    <input type="submit" value="Iniciar">
                    <!--<input type="submit" value="Iniciar" name="Iniciar"/>-->
                </form>
                <!--
                <span class="texto-footer"
                      >¿Aún no te has registrado? <a href="registro.jsp">Regístrate</a></span>-->
            </div>
            <!--<div class="ctn-texto">
                <div class="capa"></div>
                <h1 class="titulo-descripcion">Minuto 85 venta y cambios</h1>
                <p class="texto-descripcion">
                    Venta, compra y cambios de artículos deportivos , para mayor información puedes escribirnos al 3227118624
                </p>
            </div>-->
            <div class="ctn-texto">
                <div class="capa"></div>
                <h1 class="titulo-descripcion">Mi Factura</h1>
                <p class="texto-descripcion">
                    Sistema administrativo para la gestion de Usuarios, cliente, productos... con Punto de Venta
                </p>
            </div>
        </div>
    </body>
</html>
