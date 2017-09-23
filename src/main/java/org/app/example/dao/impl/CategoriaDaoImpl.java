package org.app.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.app.example.bean.Categoria;
import org.app.example.dao.CategoriaDao;
import org.app.example.util.DbConn;

/**
 * @author roberto huangal diaz
 * @web https://github.com/rhuangal/
 * @version 2.0
 */
public class CategoriaDaoImpl implements CategoriaDao {

    private static Logger log = Logger.getLogger(CategoriaDaoImpl.class);

    @Override
    public int create(Categoria c) {
        int result = 0;
        try {
            String sql = "INSERT INTO TB_CATEGORIA(nom_categoria) VALUES(?)";
            Connection con = DbConn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, c.getNom_categoria());
            DbConn.beginTransaction();
            result = pstmt.executeUpdate();
            if (result != 0) {
                DbConn.commit();
            } else {
                DbConn.rollback();
            }
            DbConn.closeConn(null, pstmt, con);
        } catch (SQLException ex) {
            log.error(ex);
        }
        return result;
    }

    @Override
    public Categoria read(int cod) {
        Categoria bean = null;
        String sql = "SELECT * FROM TB_CATEGORIA WHERE cod_categoria = ?";
        try {
            Connection con = DbConn.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cod);
            ResultSet rs = stmt.executeQuery();
            DbConn.beginTransaction();
            if (rs.next()) {
                bean = new Categoria();
                bean.setCod_categoria(rs.getInt(1));
                bean.setNom_categoria(rs.getString(2));
                DbConn.commit();
            } else {
                DbConn.rollback();
            }
            DbConn.closeConn(rs, stmt, con);
            return bean;
        } catch (SQLException ex) {
            log.error(ex);
        }
        return bean;
    }

    @Override
    public int update(Categoria c) {
        int result = 0;
        try {
            String sql = "UPDATE TB_CATEGORIA SET nom_categoria = ? WHERE cod_categoria = ?";
            Connection con = DbConn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, c.getNom_categoria());
            pstmt.setInt(2, c.getCod_categoria());
            DbConn.beginTransaction();
            result = pstmt.executeUpdate();
            if (result != 0) {
                DbConn.commit();
            } else {
                DbConn.rollback();
            }
            DbConn.closeConn(null, pstmt, con);
            return result;
        } catch (SQLException ex) {
            log.error(ex);
        }
        return result;
    }

    @Override
    public void delete(int cod) {
        int result = 0;
        try {
            String sql = "DELETE FROM tb_categoria WHERE cod_categoria = ?";
            Connection con = DbConn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, cod);
            DbConn.beginTransaction();
            result = pstmt.executeUpdate();
            if (result != 0) {
                DbConn.commit();
            } else {
                DbConn.rollback();
            }
            DbConn.closeConn(null, pstmt, con);
        } catch (SQLException ex) {
            log.error(ex);
        }
    }

    @Override
    public List<Categoria> readAll() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM TB_CATEGORIA";
        try {
            Connection con = DbConn.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            DbConn.beginTransaction();
            Categoria bean;
            while (rs.next()) {
                bean = new Categoria();
                bean.setCod_categoria(rs.getInt(1));
                bean.setNom_categoria(rs.getString(2));
                lista.add(bean);
            }
            DbConn.closeConn(rs, stmt, con);
        } catch (SQLException ex) {
            log.error(ex);
        }
        return lista;
    }

}
