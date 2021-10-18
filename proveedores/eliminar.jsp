<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header bg-danger text-white">
            <h5 class="modal-title">Desea eliminar el proveedor?</h5>
            <button class="close" data-dismiss="modal">
                <span>&times;</span>
            </button>
        </div>
        <br>
        <form action="${pageContext.request.contextPath}/Proveedor?accion=eliminar&cedulaUsuario=${param.cedulaUsuario}" method="POST" class="was-validated  centrado">

            <!-- Campos de Formulario-->
            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <h4 class="text-center"><i class="fas fa-exclamation-triangle"></i> ${param.nom}</h4>
                        </div>
                    </div>
            </section>
            <br>
            <div style="display: flex; justify-content: center">
                <a href="${pageContext.request.contextPath}/Usuarios" class="btn btn-outline-primary" style="margin-right: 10px"> <i class="fas fa-arrow-left"></i> Cancelar</a>
                <a href="${pageContext.request.contextPath}/Usuarios?accion=eliminar&cedulaUsuario=${param.cedulaUsuario}&nom=${param.nom}" class="btn btn-outline-danger"><i class="fa fa-trash-o"></i> Eliminar</a>
            </div>

        </form>
    </div>
</div>