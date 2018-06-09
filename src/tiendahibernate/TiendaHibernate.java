/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendahibernate;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;
import tiendahibernate.hibernatefiles.Articulo;
import tiendahibernate.hibernatefiles.Comenta;
import tiendahibernate.hibernatefiles.Compra;
import tiendahibernate.hibernatefiles.Usuario;

/**
 *
 * @author pradosmac
 */
public class TiendaHibernate {
    
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    static Session session = sessionFactory.openSession();
    static Scanner teclado = new Scanner(System.in);
    
    public static void leerUsuarios(){
        Query sql = session.createQuery("from Usuario");
        
        Iterator it = sql.iterate();
        
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            System.out.println(u.getId() + ". \bNombre: " + u.getNombre());  
        }
    }
    
    public static void leerArticulos(){
        Query sql = session.createQuery("from Articulo");
        
        Iterator it = sql.iterate();
        
        while(it.hasNext()){
            Articulo a = (Articulo) it.next();
            System.out.println(a.getId() + ". \bNombre: " + a.getNombre()
                                            + " \bPrecio: " + a.getPrecio());
        }
    }
    
    public static void leerCompras(){
        Query sql = session.createQuery("from Compra");
        
        Iterator it = sql.iterate();
        
        while(it.hasNext()){
            Compra c = (Compra) it.next();
            System.out.println(c.getId() + ". \bFecha: " + c.getFecha() 
                                        + " \bUsuario: " + c.getUsuario().getNombre()
                                        + " \bArticulo: " + c.getArticulo().getNombre());  
        }
    }
    
    public static void leerComentarios(){
        Query sql = session.createQuery("from Comenta");
        
        Iterator it = sql.iterate();
        
        while(it.hasNext()){
            Comenta c = (Comenta) it.next();
            System.out.println(c.getId() + ". \bFecha: " + c.getFecha() 
                                        + " \bUsuario: " + c.getUsuario().getNombre()
                                        + " \bArticulo: " + c.getArticulo().getNombre()
                                        + " \bComentario: " + c.getComentario());
        }
    }
    
    public static void crearUsuario(){
        Usuario u = new Usuario();
        
        System.out.println("Introduzca el nombre: ");
        u.setNombre(teclado.nextLine());
        
        System.out.println("Introduzca el login");
        u.setLogin(teclado.nextLine());
        
        System.out.println("Introduzca la contraseña");
        u.setPass(teclado.nextLine());
        
        
	session.beginTransaction();

	session.save(u);

	session.getTransaction().commit();
    }
    
    public static void crearArticulo(){
        Articulo a = new Articulo();
        
        System.out.println("Introduzca el nombre: ");
        a.setNombre(teclado.nextLine());
        
        System.out.println("Introduzca el precio");
        a.setPrecio(Integer.parseInt(teclado.nextLine()));
        
        
	session.beginTransaction();

	session.save(a);

	session.getTransaction().commit();
    }
    
    public static void crearCompra(){
        Compra c = new Compra();
        
        leerUsuarios();
        System.out.println("Introduzca el usuario: ");
        c.setUsuario((Usuario) session.load(Usuario.class, (int) Integer.parseInt(teclado.nextLine())));
        
        leerArticulos();
        System.out.println("Introduzca el articulo");
        c.setArticulo((Articulo) session.load(Articulo.class, (int) Integer.parseInt(teclado.nextLine())));
        
        c.setFecha(new Date());
        
	session.beginTransaction();

	session.save(c);

	session.getTransaction().commit();
    }
    
    public static void crearComentario(){
        Comenta c = new Comenta();
        
        leerUsuarios();
        System.out.println("Introduzca el usuario: ");
        c.setUsuario((Usuario) session.load(Usuario.class, (int) Integer.parseInt(teclado.nextLine())));
        
        leerArticulos();
        System.out.println("Introduzca el articulo");
        c.setArticulo((Articulo) session.load(Articulo.class, (int) Integer.parseInt(teclado.nextLine())));
        
        System.out.println("Introduzca el comentario");
        c.setComentario(teclado.nextLine());
        
        c.setFecha(new Date());
        
	session.beginTransaction();

	session.save(c);

	session.getTransaction().commit();
    }
    
    public static void modificarUsuario(){   
        leerUsuarios();
        Usuario u = new Usuario();
        System.out.println("Indique el usuario que quiere modificar");
        u = (Usuario) session.load(Usuario.class, (int) Integer.parseInt(teclado.nextLine()));
        
        System.out.println("Introduzca el nombre: ");
        u.setNombre(teclado.nextLine());
        
        System.out.println("Introduzca el login");
        u.setLogin(teclado.nextLine());
        
        System.out.println("Introduzca la contraseña");
        u.setPass(teclado.nextLine());
        
	session.beginTransaction();

	session.update(u);

	session.getTransaction().commit();
        
    }
    
    public static void modificarArticulo(){
        leerArticulos();
        Articulo a = new Articulo();
        System.out.println("Indique el articulo que quiere modificar");
        a = (Articulo) session.load(Articulo.class, (int) Integer.parseInt(teclado.nextLine()));
        
        System.out.println("Introduzca el nombre: ");
        a.setNombre(teclado.nextLine());
        
        System.out.println("Introduzca el precio");
        a.setPrecio(Integer.parseInt(teclado.nextLine()));
	session.beginTransaction();

	session.update(a);

	session.getTransaction().commit();
	
        
    }
    
    public static void modificarCompra(){
        leerCompras();
        Compra c = new Compra();
        System.out.println("Indique la compra que quiere modificar");
        c = (Compra) session.load(Compra.class, (int) Integer.parseInt(teclado.nextLine()));
        
        leerUsuarios();
        System.out.println("Indique el usuario que quiere asignar");
        c.setUsuario((Usuario) session.load(Usuario.class, (int) Integer.parseInt(teclado.nextLine())));
        
        leerArticulos();
        System.out.println("Indique el articulo que quiere asignar");
        c.setArticulo((Articulo) session.load(Articulo.class, (int) Integer.parseInt(teclado.nextLine())));
        
        
	session.beginTransaction();

	session.update(c);

	session.getTransaction().commit();
        
    }
    
    public static void modificarComentario(){
        leerComentarios();
        Comenta c = new Comenta();
        System.out.println("Indique el comentario que quiere modificar");
        c = (Comenta) session.load(Comenta.class, (int) Integer.parseInt(teclado.nextLine()));
        
        System.out.println("Introduzca el comentario");
        c.setComentario(teclado.nextLine());
        
	session.beginTransaction();

	session.update(c);

	session.getTransaction().commit();
        
    }
    
    static void eliminarUsuario(){
        leerUsuarios();
        Usuario u = new Usuario();
        System.out.println("Indique el articulo que quiere eliminar");
        u = (Usuario) session.load(Usuario.class, (int) Integer.parseInt(teclado.nextLine()));
        
        session.beginTransaction();

	session.delete(u);

	session.getTransaction().commit();
    }
    
    static void eliminarArticulo(){
        leerArticulos();
        Articulo a = new Articulo();
        System.out.println("Indique el articulo que quiere eliminar");
        a = (Articulo) session.load(Articulo.class, (int) Integer.parseInt(teclado.nextLine()));
        
        session.beginTransaction();

	session.delete(a);

	session.getTransaction().commit();
    }

    static void eliminarCompra(){
        leerCompras();
        Compra c = new Compra();
        System.out.println("Indique la compra que quiere eliminar");
        c = (Compra) session.load(Compra.class, (int) Integer.parseInt(teclado.nextLine()));
        
        session.beginTransaction();

	session.delete(c);

	session.getTransaction().commit();
    }
    
    static void eliminarComentario(){
        leerComentarios();
        Comenta c = new Comenta();
        System.out.println("Indique el comentario que quiere eliminar");
        c = (Comenta) session.load(Comenta.class, (int) Integer.parseInt(teclado.nextLine()));
        
        session.beginTransaction();

	session.delete(c);

	session.getTransaction().commit();
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ///////////SWITCH
        
        int opcion = 0;
        boolean repite = true;
        
        do{
            System.out.println("1.Leer Usuarios");
            System.out.println("2.Leer Articulos");
            System.out.println("3.Leer Compras");
            System.out.println("4.Leer Comentarios");
            System.out.println("5.Crear Usuario");
            System.out.println("6.Crear Articulo");
            System.out.println("7.Crear Compra");
            System.out.println("8.Crear Comentario");
            System.out.println("9.Eliminar Usuario");
            System.out.println("10.Eliminar Articulo");
            System.out.println("11.Eliminar Compra");
            System.out.println("12.Eliminar Comentario");
            System.out.println("13.Modificar Usuarios");
            System.out.println("14.Modificar Articulo");
            System.out.println("15.Modificar Compra");
            System.out.println("16.Modificar Comentario");
            System.out.println("17.Salir");

            //LEER OPCION
            
            opcion = Integer.parseInt(teclado.nextLine());
            
            //ALTERNATIVAS
            switch(opcion){
                
		case 1:
		{
                    leerUsuarios();
		}
		break;			
		case 2:
		{
                    leerArticulos();
		}
		break;
                case 3:
                {
                    leerCompras();
                }
                break;
                case 4:
		{
                    leerComentarios();
		}
		break;			
		case 5:
		{
                    crearUsuario();
		}
		break;
                case 6:
                {
                    crearArticulo();
                }
                break;
                case 7:
		{
                    crearCompra();
		}
		break;			
		case 8:
		{
                    crearComentario();
		}
		break;
                case 9:
                {
                    eliminarUsuario();
                }
                break;
                case 10:
		{
                   eliminarArticulo();
		}
		break;			
		case 11:
		{
                    eliminarCompra();
		}
		break;
                case 12:
                {
                    eliminarComentario();
                }
                break;
                case 13:
                {
                    modificarUsuario();
                }
                break;
                case 14:
                {
                    modificarArticulo();
                }
                break;
                case 15:
		{
                   modificarCompra();
		}
		break;			
		case 16:
		{
                    modificarComentario();
		}
		break;
		case 17:
                {
                    repite = false;
                }
                break;
			
            }
            /*
            if(repite == true){
                System.out.println("Presiona ENTER para continuar");
                Scanner a = new Scanner(System.in);
                a.nextLine();
            }
            */
	}while(repite);
        //////////////////////
        session.close();
        sessionFactory.close();
    }
    
}
