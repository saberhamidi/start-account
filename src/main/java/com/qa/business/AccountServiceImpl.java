package com.qa.business;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import javax.enterprise.inject.Default;
import javax.inject.Inject;


@Default
public class AccountServiceImpl implements AccountService {

    @Inject
    private JSONUtil jsonUtil;

    @Override
    public String create(Account ac) {

        if (ac.getAccountNumber().equals("99")){

            return "{\"message\": \"This account is blocked\"}";
        }

        else{
            return "ok";
        }
    }
}
