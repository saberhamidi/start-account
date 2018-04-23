package com.qa.repository;

import com.qa.domain.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional(Transactional.TxType.SUPPORTS)
public class AccountRepository {

    @PersistenceContext(name="primary")
    private EntityManager em;

    public Account find(Long id){
        return em.find(Account.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Account create(Account account){
        em.persist(account);
        return account;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public String delete(Long id){
        em.remove(em.find(Account.class, id));
        return "Account deleted successfully!";
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
