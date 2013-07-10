package qsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 7/4/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "bankaccount")
public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "description")
    private String description;
    @Column(name = "balance")
    private double balance;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, String description, double balance, int id) {
        this.accountNumber = accountNumber;
        this.description = description;
        this.balance = balance;
        this.id = id;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
