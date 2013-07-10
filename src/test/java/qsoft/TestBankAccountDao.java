package qsoft;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import qsoft.dao.BankAccountDao;
import qsoft.dao.impl.BankAccountDaoExtendAbstract;
import qsoft.entity.BankAccount;
import qsoft.service.BankAccountService;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 7/10/13
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:bankAccountContext.xml"})
public class TestBankAccountDao {

    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @BeforeClass
    public static void createSchema() throws Exception {
        String schemaFileName = System.class.getResource("/table_bank_account.sql").toString().substring(6);
        String transactionTable = System.class.getResource("/table_transaction.sql").toString().substring(6);

        RunScript.execute(JDBC_URL, USER, PASSWORD, schemaFileName, Charset.forName("UTF8"), false);
        RunScript.execute(JDBC_URL, USER, PASSWORD, transactionTable, Charset.forName("UTF8"), false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();  // read data from xml file
        cleanlyInsert(dataSet);  // empty the db and insert data
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset_bank_account.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Autowired
    private BankAccountDao bankAccountDao;
    private Long id;
    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    BankAccountDaoExtendAbstract bankAccountDaoExtendAbstract;

    @Before
    public void init() {
        id = 1L;
    }

    @Test
    public void listBankAccountTest() {
        List<BankAccount> bankAccounts = bankAccountDao.findAll();
        Assert.assertNotNull(bankAccounts);
        Assert.assertEquals(1, bankAccounts.size());

//        List<BankAccount> bankAccounts1 = bankAccountDaoExtendAbstract.findAll();
//        Assert.assertNotNull(bankAccounts1);
//        Assert.assertNotNull(bankAccountDaoExtendAbstract.findById(1));

    }

    @Test
    public void getAccountByAccountNumber() {
        String numberAccount = "123";
        BankAccount bankAccount = bankAccountDao.findByAccountNumber(numberAccount);
        Assert.assertNotNull(bankAccount);
        Assert.assertEquals(numberAccount, bankAccount.getAccountNumber());
    }

    @Test
    public void testSaveAccount() {

        BankAccount obj = new BankAccount();
        obj.setAccountNumber("222");
        obj.setDescription("OPEN ACCOUNT 222");
        obj.setBalance(1000);
        bankAccountDao.save(obj);

        BankAccount bankAccountSave =  bankAccountDao.findByAccountNumber("222");
        Assert.assertNotNull(bankAccountSave);
        Assert.assertEquals("222", bankAccountSave.getAccountNumber());

    }
}
