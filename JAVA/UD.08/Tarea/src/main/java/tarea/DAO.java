package tarea;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("tarea");

    public void save (Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(producto);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

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

    public void update(Producto producto) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(producto);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(em.find(Producto.class, id));
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> mostrarProductos(){
        String jpql = "SELECT p FROM Producto p";
        EntityManager em = emf.createEntityManager();
        return em.createQuery(jpql, Producto.class).getResultList();
        
    }

    
}
