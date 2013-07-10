package qsoft.dao.impl;

import org.springframework.orm.jpa.JpaCallback;
import qsoft.entity.BankAccount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 7/10/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDaoExtendAbstract extends AbstractCommonDao<Integer, BankAccount> {
    public BankAccount findByAccountNumber(String string) {
        EntityManager em = getJpaTemplate().getEntityManager();
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
}
