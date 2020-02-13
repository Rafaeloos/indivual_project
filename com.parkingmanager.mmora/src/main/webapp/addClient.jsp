<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Cliente</h5> 
                <button class="close" data-dismiss="modal"> <span>&times;</span> </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/client" method="POST" class="was-validated">
                <input type="hidden" name="action" value="insert">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre Cliente</label>
                        <input type="text" class="form-control" name="name" required />
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Apellidos Cliente</label>
                        <input type="text" class="form-control" name="surname" required />
                    </div>
                  
                    <div class="form-group">
                        <label for="precio">email</label>
                        <input type="text" class="form-control" name="email" required  />
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
    