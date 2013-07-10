package qsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import qsoft.dao.BankAccountDao;
import qsoft.entity.BankAccount;
import qsoft.service.BankAccountService;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 7/10/13
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountDao bankAccountDao;

    public BankAccount openAccount(String string) {
        BankAccount obj = new BankAccount();
        obj.setAccountNumber(string);
        obj.setDescription("OPEN ACCOUNT " + string);
        obj.setBalance(1000);
        bankAccountDao.save(obj);
        return obj;
    }
}
