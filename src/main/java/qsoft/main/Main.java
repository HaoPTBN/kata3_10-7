package qsoft.main;

import org.springframework.transaction.annotation.Transactional;
import qsoft.dao.impl.BankAccountDaoImpl;
import qsoft.entity.BankAccount;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 7/10/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class Main {
    public static void main(String[] args) {
        System.out.println("-----VIET NAM");
        BankAccountDaoImpl bankAccountDao = new BankAccountDaoImpl();
        BankAccount bankAccount = bankAccountDao.findById(2);

        if (bankAccount != null) {
            System.out.println("TTT:" + bankAccount.getId() + "XX:" + bankAccount.getDescription());
            bankAccount.setDescription("TETET");
            bankAccountDao.update(bankAccount);
        }
        BankAccount obj = new BankAccount();
        obj.setId(24);
        obj.setDescription("des 14");
        obj.setAccountNumber("1414");
        obj.setBalance(12212);
        bankAccountDao.save(obj);
    }
}
