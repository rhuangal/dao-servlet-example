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
 *
 * @author rober
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
            //load a properties file from class path, inside static method
            props.load(input);
            // load the Driver Class
            Class.forName(props.getProperty("jdbc.driver"));
            // create the connection now
            con = DriverManager.getConnection(props.getProperty("jdbc.url"),
                    props.getProperty("jdbc.username"),
                    props.getProperty("jdbc.password"));
            log.info("Se conecto correctamente.");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            log.error(e);
        }
        return con;
    }

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

    public static void beginTransaction() {
        if (con != null) {
            try {
                con.setAutoCommit(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void commit() {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

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
