package tiendahibernate.hibernatefiles;
// Generated 04-mar-2018 12:18:25 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Articulo generated by hbm2java
 */
public class Articulo  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private int precio;
     private Set compras = new HashSet(0);
     private Set comentas = new HashSet(0);

    public Articulo() {
    }

	
    public Articulo(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public Articulo(String nombre, int precio, Set compras, Set comentas) {
       this.nombre = nombre;
       this.precio = precio;
       this.compras = compras;
       this.comentas = comentas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public Set getCompras() {
        return this.compras;
    }
    
    public void setCompras(Set compras) {
        this.compras = compras;
    }
    public Set getComentas() {
        return this.comentas;
    }
    
    public void setComentas(Set comentas) {
        this.comentas = comentas;
    }




}


