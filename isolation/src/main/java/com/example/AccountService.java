package com.example;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AccountService {
    @Autowired
    AccountRepository ar;

    @PersistenceContext
    private EntityManager em;
    // read uncommited example==========================
    // Writer method: updates balance but delays commit
    // @Transactional
    // public void updateBalance() {
    // Account account = ar.findById(2L).orElseThrow();
    // account.setBalance(1234);

    // ar.flush();
    // System.out.println("updated balance but not committed 1234");
    // try {
    // Thread.sleep(5000);
    // } catch (InterruptedException e) {
    // }
    // }

    // // Reader method with READ_UNCOMMITTED isolation
    // @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    // public void readBalance() {
    // Account account = ar.findById(2L).orElseThrow();
    // System.out.println("Read balance: " + account.getBalance());
    // }

    // ///// read commited example======================================
    // @Transactional
    // public void updateBalance() {
    // Account account = ar.findById(2L).orElseThrow();

    // account.setBalance(1234);
    // ar.flush();
    // System.out.println("updated balance but not committed 1234");
    // try {
    // Thread.sleep(6000);
    // } catch (InterruptedException e) {
    // }
    // }

    // @Transactional(isolation = Isolation.READ_COMMITTED)
    // public void readBalance() {
    // Account account = ar.findById(2L).orElseThrow();
    // System.out.println("Read balance: " + account.getBalance());
    // }

    // ///// repeatable read example======================================
    // @Transactional(isolation = Isolation.REPEATABLE_READ)
    // public void readTwice() {
    // Account account = ar.findById(2L).orElseThrow();

    // System.out.println("first read: " + account.getBalance());
    // try {
    // Thread.sleep(6000);
    // } catch (InterruptedException e) {
    // }
    // em.clear();
    // Account acc2 = ar.findById(2L).orElseThrow();
    // System.out.println("TxA - Second Read: " + acc2.getBalance());

    // }

    // @Transactional
    // public void updateBalance() {
    // Account account = ar.findById(2L).orElseThrow();
    // account.setBalance(2000);
    // ar.save(account);
    // System.out.println("Updated balance to: " + account.getBalance());
    // }

    ///// serializable example ======================================
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void readAccounts() {
        List<Account> accounts = ar.getAccountGreaterThan200Balance();
        System.out.println("first Read account: " + accounts.size() + "         " + new Date());
        try {
            Thread.sleep(8000); // simulate long read
        } catch (InterruptedException e) {
        }

        em.clear();
        accounts = ar.getAccountGreaterThan200Balance();
        System.out.println("second  Read : " + accounts.size() + "    " + new Date());
    }

    @Transactional
    public void addAccount() {
        ar.save(new Account("dm105", 600));
        ar.save(new Account("dm106", 700));
        System.out.println("two account inserted " + new Date());
    }
}
