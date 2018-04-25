package com.qa.repository;

import com.qa.domain.Account;

import java.util.List;

public interface Repository {

    Account find(Long id);
    String create(Account ac);
    String delete(Long id);
    Account update(Account ac);
    List<Account> findAll();
}
