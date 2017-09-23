
package org.app.example.dao;

import java.util.List;
import org.app.example.bean.Categoria;

/**
 * @author roberto huangal diaz
 * @web https://github.com/rhuangal/
 * @version 2.0
 */
public interface CategoriaDao {
    
    public int create(Categoria c);
    public Categoria read(int cod);
    public int update(Categoria c);
    public void delete(int cod);
    
    public List<Categoria> readAll();
    
}
