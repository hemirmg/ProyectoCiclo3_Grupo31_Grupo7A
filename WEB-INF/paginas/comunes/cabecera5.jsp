<header class="header">
    <div class="header-contenedor d-flex justify-content-center align-items-center">
        <div>
            <h1><a href="${pageContext.request.contextPath}/principal.jsp" style="font-size: 40px"><i class="fas fa-file-invoice-dollar"></i> Mi <span>Factura</span></a></h1>
        </div>
        <nav style="font-size: 20px" class="d-flex justify-content-between align-items-center">
            <a href="${pageContext.request.contextPath}/Usuarios">Usuarios</a>
            <a href="${pageContext.request.contextPath}/Clientes">Clientes</a>
            <a href="${pageContext.request.contextPath}/Proveedores">Proveedores</a>
            <a href="${pageContext.request.contextPath}/Productos">Productos</a>
            <a href="${pageContext.request.contextPath}/Ventas" class="selected">Ventas</a>
            <a href="${pageContext.request.contextPath}/reportes">Reportes</a>
        </nav>
        <div class="dropdown" style="padding-left: 20px">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-user"></i> ${usuario.nombreUsuario}
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                <a style="color: black" class="dropdown-item" href="${pageContext.request.contextPath}">Salir <i class="fas fa-sign-out-alt"></i></a>
                <a style="color: black" class="dropdown-item" href="#">Configurar</a>
            </div>
        </div>
    </div>
</header>
