package mx.samedgo.productos;

import com.google.gson.Gson;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {

    @Resource(name = "jdbc/productos") // define o establece el DataSource
    private DataSource miPool; // establece el pool de conexiones
    private ModeloProductos modeloProductos;
    private Productos pv;
    private RequestDispatcher dispatcher;
    String page = "";
//    private Gson gson;

    @Override
    public void init() throws ServletException { // equivalente al metodo main en javaSE
        super.init();

        try {
            modeloProductos = new ModeloProductos(miPool); // recibe como parametro el datasource-miPool
        }catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ListarProductos(request, response);

        /* leer el parametro del formulario */
        String recibir = request.getParameter("instruccion");

        /* si no se envia parametro, listar productos */
        if(recibir == null) recibir = "listarProductos";

        /* redirigir el flujo de ejecucion al metodo adecuado */
        switch (recibir){
            case "listarProductos":
                ListarProductos(request, response);
                break;
            case "createProducto":
                CreateProducto(request, response);
                break;
            case "readProducto":
                try {
                    ReadProducto(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "updateProducto":
                try {
                    UpdateProducto(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                default:
                    ListarProductos(request, response);
        }

        dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response); // solo se puede hacer forward una vez

    }

    private void UpdateProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        pv = new Productos();

        pv.setProducto_id(Integer.valueOf(request.getParameter("inputProducto_ID")));
        pv.setSeccion(request.getParameter("inputSeccion"));
        pv.setNombre_articulo(request.getParameter("inputNombreArticulo"));
        pv.setPrecio(Double.parseDouble(request.getParameter("inputPrecio")));
        pv.setImportado(request.getParameter("inputImportado"));
        pv.setPais_origen(request.getParameter("inputPais"));

        Productos updateProduct = new Productos(pv.getProducto_id(),pv.getSeccion(),pv.getNombre_articulo(),
                pv.getPrecio(),pv.getImportado(), pv.getPais_origen());

        modeloProductos.updateProductos(updateProduct);

        ListarProductos(request, response);
    }

    private void ReadProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        pv = new Productos();
        /* leer el producto_id que viene de la tabla */
        pv.setProducto_id(Integer.valueOf(request.getParameter("producto")));

        /* enviar el producto_id al modelo para obtener un producto*/
        Productos producto_buscar = modeloProductos.getProducto(pv.getProducto_id());

        /* colocar atributo correspondiente al producto devuelto */
        request.setAttribute("producto_buscar",producto_buscar);

        /* enviar el producto al formulario ViewUpdateProducto */
        page = "/ViewUpdateProducto.jsp";
    }

    private void CreateProducto(HttpServletRequest request, HttpServletResponse response) {
        pv = new Productos();
        /* leer la informacion del producto */
        pv.setSeccion(request.getParameter("inputSeccion"));
        pv.setNombre_articulo(request.getParameter("inputNombreArticulo"));
        pv.setPrecio(Double.parseDouble(request.getParameter("inputPrecio")));
        pv.setImportado(request.getParameter("inputImportado"));
        pv.setPais_origen(request.getParameter("inputPais"));

        /* crear un objeto de tipo producto */
        Productos newProduct = new Productos(pv.getSeccion(),pv.getNombre_articulo(),pv.getPrecio(),pv.getImportado(),
                pv.getPais_origen());

        /* enviar el objeto al modelo y despues insertar el objeto producto a la Db */
        modeloProductos.addProductos(newProduct);

        /* volver al listado de productos */
        ListarProductos(request, response);
    }

    private void ListarProductos(HttpServletRequest request, HttpServletResponse response) {
        /* obtener la lista de productos desde el modelo */
        List<Productos> productos;

        try {
            productos = modeloProductos.getProductos();

            /* agregar lista de productos al request */
            request.setAttribute("Lista_Productos",productos);

            /* enviar el reuqest a la pagina JSP */
            page = "/ViewListaproductos.jsp";

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}