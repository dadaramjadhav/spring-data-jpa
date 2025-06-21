package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OuterService {

    @Autowired
    AccountRepository ar;

    @Autowired
    InnerService is;

    // // ///////////////////REQUIRED================================
    // @Transactional(propagation = Propagation.REQUIRED)
    // public void outerMethod() {
    // ar.save(new Account("dm", 123));
    // is.innerMethod();
    // throw new RuntimeException("something went wrong in outermethod");
    // }

    // // // ///////////////////REQUIRES_NEW================================
    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    // public void outerMethod() {
    // ar.save(new Account("dm1", 123));
    // try {
    // is.innerMethod();
    // } catch (RuntimeException re) {
    // System.out.println("caught exception in inner method" + re.getMessage());
    // }
    // }

    // // ///////////////////exception ================================
    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor =  MyException.class)
    public void outerMethod() {
        ar.save(new Account("dm1", 123));

        is.innerMethod();

    }
}
