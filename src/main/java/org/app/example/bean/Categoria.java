package org.app.example.bean;

/**
 * @author roberto huangal diaz
 * @web https://github.com/rhuangal/
 * @version 2.0
 */
public class Categoria {
    
    private int cod_categoria;
    private String nom_categoria;

    public Categoria() {
    }

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }
    
}
