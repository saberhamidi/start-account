package com.qa.repository;

import com.qa.business.AccountService;
import com.qa.domain.Account;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Transactional(Transactional.TxType.SUPPORTS)
@Default
public class AccountDBRepository implements Repository{

    @PersistenceContext(name="primary")
    private EntityManager em;

    @Inject
    private AccountService accountService;

    public Account find(Long id){
        return em.find(Account.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public String create(Account account){
        if (accountService.create(account) == "ok"){
            em.persist(account);
            return "{\"message\": \"Account successfully created!\"}";
        }
        else return accountService.create(account);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public String delete(Long id){
        Account accountFound = em.find(Account.class, id);
        System.out.println(accountFound);
        if(accountFound == null){
            return "{\"message\":\"Account couldn't be found!\"}";
        }
        else{
            em.remove(accountFound);
            return "{\"message\":\"Account deleted successfully!\"}";
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Account update(Account account){
        return em.merge(account);
    }

    public List<Account> findAll(){
        TypedQuery<Account> query = em.createQuery("select ac from Account ac order by ac.firstName", Account.class);
        return query.getResultList();
    }
}
