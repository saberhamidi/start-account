package com.qa.repository;

import com.qa.domain.Account;

import java.util.List;

public interface Repository {

    Account find(Long id);
    Account create(Account ac);
    String delete(Long id);
    List<Account> findAll();
}
