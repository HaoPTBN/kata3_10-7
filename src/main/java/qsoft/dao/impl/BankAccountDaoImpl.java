package qsoft.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qsoft.dao.BankAccountDao;
import qsoft.entity.BankAccount;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 7/10/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */

public class BankAccountDaoImpl extends JpaDaoSupport implements BankAccountDao {

    EntityManagerFactory entityManagerFactory;

    public BankAccountDaoImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("JpaPersistenceUnit", System.getProperties());
        super.setEntityManagerFactory(entityManagerFactory);
    }

    public BankAccount findById(Integer integer) {
        return getJpaTemplate().find(BankAccount.class, integer);
    }

    public BankAccount findByAccountNumber(String string) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT h FROM BankAccount h WHERE h.accountNumber = :accountNumber", BankAccount.class);
        query.setParameter("accountNumber", string);
        em.getTransaction().commit();
        List<BankAccount> list = query.getResultList();
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Transactional
    public void save(BankAccount bankAccount) {
//        getJpaTemplate().persist(bankAccount);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(bankAccount);
        em.getTransaction().commit();
    }


    public void update(BankAccount bankAccount) {
//        getJpaTemplate().merge(bankAccount);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(bankAccount);
        em.getTransaction().commit();
    }

    public List<BankAccount> findAll() {
        Object res = getJpaTemplate().execute(new JpaCallback() {
            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query q = em.createQuery("SELECT h FROM " + BankAccount.class.getName() + " h");
                return q.getResultList();
            }
        });
        return (List<BankAccount>) res;
    }
}
