<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Agregar Productos</title>
        <link rel="stylesheet" href="/webjars/bootstrap/3.4.1/css/bootstrap.min.css"/>
    </head>
    <body>
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-4">Actualizar Productos</h1>
            <!-- <p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p> -->
        </div>
    </div>
    <div class="container">
        <form name="updateProducto" method="get" action="ControladorProductos">
            <input type="hidden" name="instruccion" value="updateProducto">
            <input type="hidden" name="inputProducto_ID" value="${producto_buscar.producto_id}">

            <div class="form-row">
                <div class="form-group col-md-9" ></div>
                <div class="form-group col-md-1">
                        <label for="inputProducto_id">Id</label>
                        <input type="text" class="form-control" name="inputProducto_id" id="inputProducto_id"
                               value="${producto_buscar.producto_id}" disabled >
                </div>
                <div class="form-group col-md-2">
                    <label for="inputFecha">Fecha de registro</label>
                    <input type="text" class="form-control" name="inputFecha" id="inputFecha"
                           value="${producto_buscar.fecha}" disabled>
                </div>
            </div>
            <div class="form-row">

                <div class="form-group col-md-4">
                    <label for="inputSeccion">Seccion</label>
                    <input type="text" class="form-control" name="inputSeccion" id="inputSeccion"
                           value="${producto_buscar.seccion}" >
                </div>
                <div class="form-group col-md-8">
                    <label for="inputNombreArticulo">Nombre Artículo</label>
                    <input type="text" class="form-control" name="inputNombreArticulo" id="inputNombreArticulo"
                           value="${producto_buscar.nombre_articulo}" >
                </div>

            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="inputPrecio">Precio Artículo</label>
                    <input type="text" class="form-control" name="inputPrecio" id="inputPrecio"
                           value="${producto_buscar.precio}" >
                </div>
                <div class="form-group col-md-4">
                    <label for="inputImportado">¿Es importado?</label>
                    <input type="text" class="form-control" name="inputImportado" id="inputImportado"
                           value="${producto_buscar.importado}" >
                </div>
                <div class="form-group col-md-4">
                    <label for="inputPais">País</label>
                    <input type="text" class="form-control" name="inputPais" id="inputPais"
                           value="${producto_buscar.pais_origen}" >
                </div>
            </div>
            <div class="form-row">
                    <div class="form-group col-md-auto" align="center">
                        <%--<button type="button" value="inputCancelar" class="btn btn-primary">Cancelar</button>--%>
                        <button type="submit" value="inputActualizar" class="btn btn-primary">Actualizar</button>
                    </div>
            </div>
        </form>
    </div>
    </body>
</html>