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
            <h1 class="display-4">Agregar Productos</h1>
            <!-- <p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p> -->
        </div>
    </div>
    <div class="container">
        <form name="insertProductos" method="get" action="ControladorProductos">
            <input type="hidden" name="instruccion" value="createProducto">

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="inputSeccion">Seccion</label>
                    <input type="text" class="form-control" name="inputSeccion" id="inputSeccion"
                           placeholder="FERRETERIA DEPORTES etc">
                </div>
                <div class="form-group col-md-4">
                    <label for="inputNombreArticulo">Nombre Artículo</label>
                    <input type="text" class="form-control" name="inputNombreArticulo" id="inputNombreArticulo"
                           placeholder="PALA, BLOCK, or VARILLA">
                </div>
                <div class="form-group col-md-4">
                    <label for="inputPrecio">Precio Artículo</label>
                    <input type="text" class="form-control" name="inputPrecio" id="inputPrecio"
                           placeholder="ejemplo.. 12.00">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputImportado">¿Es importado?</label>
                    <input type="text" class="form-control" name="inputImportado" id="inputImportado"
                           placeholder="VERDADERO o FALSO">
                </div>
                <div class="form-group col-md-6">
                    <label for="inputPais">País</label>
                    <input type="text" class="form-control" name="inputPais" id="inputPais"
                           placeholder="MEXICO CHINA JAPON etc">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-auto" align="center">
                    <%--<button type="button" value="inputCancelar" class="btn btn-primary">Cancelar</button>--%>
                    <button type="submit" value="inputAgregar" class="btn btn-primary">Agregar</button>
                </div>
            </div>
        </form>
    </div>
    </body>
</html>