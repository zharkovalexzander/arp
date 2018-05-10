package springapp.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz;

    public BaseDao() {
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        this.clazz = (Class<T>)type.getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public T findById(int id) {
        return entityManager.find(clazz, id);
    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public List<T> findAll() {
        String baseQuery = "select e from " + clazz.getName() + " e";
        return entityManager.createQuery(baseQuery, clazz).getResultList();
    }

    public List<T> findByIds(List<Integer> ids) {
        String query = "select e from " + clazz.getName() + " e where e.id =: ids";

        return entityManager.createQuery(query, clazz)
                .setParameter("ids", ids)
                .getResultList();
    }

    public void merge(T entity) {
        entityManager.merge(entity);
    }
}
