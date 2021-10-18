<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container mt-4">
            <div class="${configuracion}" role="alert">
                ${mensaje}!
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/Usuarios" class="btn btn-light btn-block">
                    <i class="fas fa-arrow-left"></i> Regresar
                </a>

            </div>
        </div>
    </body>
</html>
