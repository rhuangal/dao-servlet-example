package org.app.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * @author roberto huangal diaz
 * @web https://github.com/rhuangal/
 * @version 2.0
 */
public class DbConn {

    private static Logger log = Logger.getLogger(DbConn.class);

    private static Connection con;

    public static Connection getConnection() {
        Properties props = new Properties();
        InputStream input = null;
        con = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
            //Carga las propiedades de la carpeta resources
            props.load(input);
            // Cargamos la libreria de la base de datos
            Class.forName(props.getProperty("jdbc.driver"));
            // Creamos la conexion a la base de datos
            con = DriverManager.getConnection(props.getProperty("jdbc.url"),
                    props.getProperty("jdbc.username"),
                    props.getProperty("jdbc.password"));
            log.info("Se conecto correctamente.");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            // Manejo de errores
            log.error(e);
        }
        return con;
    }

    /*
    * Cerramos conexion con la base de datos.
    */
    public static void closeConn(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException ex) {
            log.error(ex);
            ex.printStackTrace();
        }

        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException ex) {
            log.error(ex);
            ex.printStackTrace();
        }

        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException ex) {
            log.error(ex);
            ex.printStackTrace();
        }

    }

    /*
    * Para prevenir errores al momento de realizar trasacciones en la base de datos,
      colocamos autocommit false. De esta maneras prevenimos que nuestros cambios se inserten de manera directa.
    */
    public static void beginTransaction() {
        if (con != null) {
            try {
                con.setAutoCommit(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
    * Nos permite mandar la confirmacion de procesamiento de las transacciones que estamos enviando.
    */
    public static void commit() {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /*
    * En caso de errores, podemos revertir las transacciones realizadas y simplemente no se ejecutan en la base de datos.
      Es bueno sobre todo en el caso que se tuviera que insertar en varias tablas simultaneamente.
    */
    public static void rollback() {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
