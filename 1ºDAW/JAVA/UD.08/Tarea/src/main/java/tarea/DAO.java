package tarea;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Objeto de Acceso a Datos (DAO) para la gestión de entidades Producto en la
 * base de datos. Utiliza Jakarta Persistence API (JPA) para realizar
 * operaciones CRUD completas.
 *
 * * @author BenjaminDTS
 * @version 1.0
 */
public class DAO {

    /**
     * Fábrica de gestores de entidades configurada mediante persistence.xml
     * ("tarea")
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("tarea");

    /**
     * Persiste un nuevo producto en la base de datos.
     *
     * * @param producto La entidad Producto que se desea guardar.
     */
    public void save(Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(producto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Busca y recupera un producto específico utilizando su código
     * identificador.
     *
     * * @param id El código único (Primary Key) del producto a buscar.
     * @return El objeto Producto si se encuentra, de lo contrario null.
     */
    public Producto findById(String id) {
        EntityManager em = emf.createEntityManager();
        Producto producto = null;
        try {
            producto = em.find(Producto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return producto;
    }

    /**
     * Actualiza la información de un producto existente en la base de datos.
     *
     * * @param producto La entidad Producto con los datos modificados.
     */
    public void update(Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(producto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Elimina permanentemente un producto de la base de datos.
     *
     * * @param id El código único del producto a eliminar.
     */
    public void delete(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Producto p = em.find(Producto.class, id);
            if (p != null) {
                em.remove(p);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Recupera todos los productos registrados en la base de datos mediante una
     * consulta JPQL.
     *
     * * @return Una lista (List) que contiene todos los productos.
     */
    public List<Producto> mostrarProductos() {
        String jpql = "SELECT p FROM Producto p";
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(jpql, Producto.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
