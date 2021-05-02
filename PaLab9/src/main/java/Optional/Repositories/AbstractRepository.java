package Optional.Repositories;

import Optional.Classes.Movie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AbstractRepository<T>{
    private static EntityManager em = null;
    private final Class<T> type;

    public AbstractRepository(EntityManager em, Class<T> type) {
        this.em = em;
        this.type = type;
    }

    public T findById(long id){
        T element = (T) em.find(type, id);
        return element;
    }

    public void create(T element){
        em.getTransaction().begin();
        em.persist(element);
        em.getTransaction().commit();
    }

    public List<T> findByName(String name){
        List<T> listOfElements = new ArrayList<>();
        Query query = em.createNamedQuery("findByName");
        query.setParameter("substr","%" + name + "%");

        listOfElements = query.getResultList();

        return listOfElements;
    }

}
