package mx.samedgo.productos;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModeloProductos {

    /* constructor que recibe como parametro el DataSource*/
    public ModeloProductos(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    public void updateProductos(Productos updateProduct) throws SQLException {
        try {
            conn = origenDatos.getConnection();
            cs = conn.prepareCall(sqlUpdateProducto);

            cs.setInt(1,updateProduct.getProducto_id());
            cs.setString(2,updateProduct.getSeccion());
            cs.setString(3,updateProduct.getNombre_articulo());
            cs.setDouble(4,updateProduct.getPrecio());
            cs.setString(5,updateProduct.getImportado());
            cs.setString(6,updateProduct.getPais_origen());

            cs.execute();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            cs.close();
            conn.close();
        }
    }

    public Productos getProducto(int producto_id) throws SQLException {
        pv = new Productos();
        Productos producto_buscar = null;

        try {
            /* establecer la conexion a la DB */
            conn = origenDatos.getConnection(); // utilizar el pool de conexiones para conectar a la BD

            /* crear la consulta mediante procedimeinto almacenado */
            cs = conn.prepareCall(sqlReadProducto);

            /* establecer los parametros */
            cs.setInt(1,producto_id);

            /* ejecutar consulta */
            rs = cs.executeQuery();

            /* obtener datos de respuesta */
            if(rs.next()){

                pv.setProducto_id(rs.getInt("producto_id"));
                pv.setSeccion(rs.getString("seccion"));
                pv.setNombre_articulo(rs.getString("nombre_articulo"));
                pv.setPrecio(rs.getDouble("precio"));
                Timestamp fecha = rs.getTimestamp("fecha");
                pv.setFecha(fecha.toLocalDateTime());
//                LocalDateTime fechaConvertida = fecha.toLocalDateTime(); // convierte TimeStamp a LocalDateTime
                pv.setImportado(rs.getString("importado"));
                pv.setPais_origen(rs.getString("pais_origen"));

                /* crear un producto */
                producto_buscar = new Productos(pv.getProducto_id(),pv.getSeccion(),pv.getNombre_articulo(),
                        pv.getPrecio(),pv.getFecha(),pv.getImportado(),pv.getPais_origen());;
            }else {
                throw new Exception("No se encontró registro con el id " + producto_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            cs.close();
            conn.close();
        }

        return producto_buscar;
    }

    public void addProductos(Productos newProduct) {

        try {
            /* obtener la conexion */
            conn = origenDatos.getConnection(); // utilizar el pool de conexiones para conectar a la BD

            /* crear la consulta mediante procedimeinto almacenado */
            cs = conn.prepareCall(sqlCreateProducto);

            /* establecer parametro de producto */
            cs.setString(1,newProduct.getSeccion());
            cs.setString(2,newProduct.getNombre_articulo());
            cs.setDouble(3,newProduct.getPrecio());
            cs.setString(4,newProduct.getImportado());
            cs.setString(5,newProduct.getPais_origen());

            /* ejecutar sql */
            cs.execute();

        }catch (Exception e) {

        }
    }

    public List<Productos> getProductos() throws Exception {
        pv = new Productos();

        List<Productos> productosList = new ArrayList<>();

        try {
            /* establecer la conexion */
            conn = origenDatos.getConnection(); // utilizar el pool de conexiones para conectar a la BD

            /* crear sentencia sql */
            cs = conn.prepareCall(sqlListProductos); // recibe como parametro un procedimiento almacenado

            /* ejecutar sql */
            rs = cs.executeQuery();

            /* recorrer el resulset obtenido y asignarla a cada atributo */
            while (rs.next()){

                pv.setProducto_id(rs.getInt("producto_id"));
                pv.setSeccion(rs.getString("seccion"));
                pv.setNombre_articulo(rs.getString("nombre_articulo"));
                pv.setPrecio(rs.getDouble("precio"));
                Timestamp fecha = rs.getTimestamp("fecha");
                pv.setFecha(fecha.toLocalDateTime());
//                LocalDateTime fechaConvertida = fecha.toLocalDateTime(); // convierte TimeStamp a LocalDateTime
                pv.setImportado(rs.getString("importado"));
                pv.setPais_origen(rs.getString("pais_origen"));

                /* crear un producto */
                Productos temProductos = new Productos(pv.getProducto_id(),pv.getSeccion(),pv.getNombre_articulo(),
                        pv.getPrecio(),pv.getFecha(),pv.getImportado(),pv.getPais_origen());

                /* llenar la lista productos con el producto creado */
                productosList.add(temProductos);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            rs.close();
            cs.close();
            conn.close();
        }

        return productosList; // retorna la lista producto
    }

    private Productos pv;
    private DataSource origenDatos;
    private Connection conn = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;
    private final String sqlListProductos = "{CALL PRODUCTOS_SPL()}";
    private final String sqlCreateProducto = "{CALL PRODUCTO_SPC(?,?,?,?,?)}";
    private final String sqlReadProducto = "{CALL PRODUCTO_SPR(?)}";
    private final String sqlUpdateProducto = "{CALL PRODUCTO_SPU(?,?,?,?,?,?)}";
}