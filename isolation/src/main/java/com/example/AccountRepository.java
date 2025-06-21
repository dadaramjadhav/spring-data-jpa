package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // for serializable, create below range query
    @Query("select a from Account a where balance > 200")
    public List<Account> getAccountGreaterThan200Balance();
}
