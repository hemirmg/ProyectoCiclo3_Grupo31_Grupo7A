<div class="modal fade" id="nuevoUsuarioModal"> <!-- id viene desde atributo data-target="#nuevoUsuarioModal" en el boton nuevo-->
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title"><i class="fas fa-users"></i> Agregar Usuario</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <br>
            <!-- Formulario Nuevo-->
            <form action="${pageContext.request.contextPath}/Usuarios?accion=nuevo" method="POST" class="was-validated">
                <section id="details">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <label for="txtCedula">CÉDULA:</label>
                                        <input type="number" name="txtCedula" class="form-control" autocomplete="off" required>
                                        <label for="txtNombre">NOMBRE:</label>
                                        <input type="text" name="txtNombre" class="form-control" autocomplete="off" required>
                                        <label for="txtEmail">CORREO ELECTRÓNICO:</label>
                                        <input type="email" name="txtEmail" class="form-control" autocomplete="off" required>
                                        <label for="txtUser">USUARIO:</label>
                                        <input type="text" name="txtUser" class="form-control" autocomplete="off" required>
                                        <label for="txtPassword">CONTRASEÑA:</label>
                                        <input type="password" name="txtPassword" class="form-control" autocomplete="off" required>
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
                            <div class="col-sm-3">
                                <div class="card-body">
                                    <a class="btn btn-light btn-block close" data-dismiss="modal">
                                        <i class="fas fa-arrow-left"></i> Regresar
                                    </a>
                                </div>
                            </div>
                            <div class="col-sm-3">
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
        </div>
    </div>
</div>