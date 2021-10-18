<div class="modal fade" id="nuevoProveedorModal"> <!-- id viene desde atributo data-target="#nuevoUsuarioModal" en el boton nuevo-->
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-secondary text-white">
                <h5 class="modal-title"><i class="fas fa-people-carry"></i> Agregar Proveedor</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <br>
            <!-- Formulario Nuevo-->
            <form action="${pageContext.request.contextPath}/Proveedores?accion=nuevo" method="POST" class="was-validated">
                <section id="details">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <label for="txtNit">NIT:</label>
                                        <input type="number" name="txtNit" class="form-control" autocomplete="off" required>
                                        <label for="txtCiudad">CIUDAD:</label>
                                        <input type="text" name="txtCiudad" class="form-control" autocomplete="off" required>
                                        <label for="txtDireccion">DIRECCIÓN:</label>
                                        <input type="text" name="txtDireccion" class="form-control" autocomplete="off" required>
                                        <label for="txtNombre">NOMBRE:</label>
                                        <input type="text" name="txtNombre" class="form-control" autocomplete="off" required>
                                        <label for="txtTelefono">TELÉFONO:</label>
                                        <input type="text" name="txtTelefono" class="form-control" autocomplete="off" required>
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
                                    <button type="submit" class="btn btn-primary btn-block">
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