package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InnerService {
    @Autowired
    AccountRepository ar;

    // // ///////////////////REQUIRED===============================
    // @Transactional(propagation = Propagation.REQUIRED)
    // public void innerMethod() {
    // ar.save(new Account("dm1", 456));
    // }

    // // ///////////////////REQUIRES_NEW================================
    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    // public void innerMethod() {
    // ar.save(new Account("dm2", 456));
    // throw new RuntimeException("something goes wrong in inner method");
    // }

    // /////////////////// exception ================================
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerMethod() {
        ar.save(new Account("dm2", 456));
        throw new MyException("something goes wrong in inner method");
    }
}
