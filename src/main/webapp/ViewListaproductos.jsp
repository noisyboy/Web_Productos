<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--language="java"--%>

<html>
    <head>
        <script src="/webjars/jquery/3.3.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Lista Productos</title>
        <link rel="stylesheet" href="/webjars/bootstrap/3.4.1/css/bootstrap.min.css"/>
    </head>
    <body>
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4">Lista de Productos</h1>
                <!-- <p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p> -->
            </div>
        </div>
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="col-md-auto" align="center">
                    <input class="btn btn-primary" type="button"
                           onclick="window.location.href='ViewCreateProducto.jsp'" value="Insertar">
                    <%--<input class="btn btn-primary" type="button"--%>
                           <%--onclick="window.location.href=''" value="Actualizar">--%>
                </div>
            </div>
        </div>

        <div class="container">
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <%--<th scope="col">#</th>--%>
                        <th scope="col">sección</th>
                        <th scope="col">nombre artículo</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">importado</th>
                        <th scope="col">pais origen</th>
                        <th scope="col">Accion</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="temProd" items="${Lista_Productos}">
                        <%-- link para cada producto con su campo clave --%>
                        <c:url var="linkTemporal" value="ControladorProductos">
                            <c:param name="instruccion" value="readProducto"></c:param>
                            <c:param name="producto" value="${temProd.producto_id}"></c:param>
                        </c:url>

                        <tr>
                            <td hidden> ${temProd.producto_id} </td>
                            <td> ${temProd.seccion} </td>
                            <td> ${temProd.nombre_articulo} </td>
                            <td> ${temProd.precio} </td>
                            <td> ${temProd.fecha} </td>
                            <td> ${temProd.importado} </td>
                            <td> ${temProd.pais_origen} </td>
                            <td> <a class="btn btn-warning" href="${linkTemporal}" role="button">editar</a> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>