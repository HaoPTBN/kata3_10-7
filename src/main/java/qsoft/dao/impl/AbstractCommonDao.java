package qsoft.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 7/10/13
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public abstract class AbstractCommonDao<K, E> extends JpaDaoSupport {

    protected Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractCommonDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        super.setEntityManagerFactory(entityManagerFactory);
    }

    public E findById(K id) {
        return getJpaTemplate().find(entityClass, id);
    }

    public void persist(E entity) {
        getJpaTemplate().persist(entity);
    }

    public void refresh(E entity) {
        getJpaTemplate().refresh(entity);
    }

    public E flush(E entity) {
        getJpaTemplate().flush();
        return entity;
    }

    public void remove(E entity) {
        getJpaTemplate().remove(entity);
    }

    public E merge(E entity) {
        return getJpaTemplate().merge(entity);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<E> findAll() {
        Object res = getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query q = em.createQuery("SELECT h FROM " + entityClass.getName() + " h");
                return q.getResultList();
            }
        });
        return (List<E>) res;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Integer removeAll() {
        return (Integer) getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query q = em.createQuery("DELETE FROM " + entityClass.getName() + " h");
                return q.executeUpdate();
            }
        });
    }
}
