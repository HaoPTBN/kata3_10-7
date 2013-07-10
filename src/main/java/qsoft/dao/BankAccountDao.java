package qsoft.dao;

import qsoft.entity.BankAccount;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 7/10/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */

public interface BankAccountDao {
    public BankAccount findById(Integer integer);

    public BankAccount findByAccountNumber(String string);

    public void save(BankAccount integer);

    public void update(BankAccount integer);

    public List<BankAccount> findAll();
}
