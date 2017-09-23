package org.app.example.bean;

/**
 *
 * @author rober
 */
public class Editorial {
    
    private int cod_editorial;
    private String nom_editorial;
    private String dir_editorial;
    private Pais pais;

    public Editorial() {
    }

    public int getCod_editorial() {
        return cod_editorial;
    }

    public void setCod_editorial(int cod_editorial) {
        this.cod_editorial = cod_editorial;
    }

    public String getNom_editorial() {
        return nom_editorial;
    }

    public void setNom_editorial(String nom_editorial) {
        this.nom_editorial = nom_editorial;
    }

    public String getDir_editorial() {
        return dir_editorial;
    }

    public void setDir_editorial(String dir_editorial) {
        this.dir_editorial = dir_editorial;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
}
