package com.qa.repository;

import com.qa.domain.Account;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Alternative
public class AccountMapRepository implements Repository{

	private Map<Integer, Account> accountMap;

	private int count = 0;

	public AccountMapRepository() {
		accountMap = new HashMap<Integer, Account>();
	}

	public Account find(Long id){
		return (Account) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getAccountNumber().equals(id));
	}
	public Account create(Account userAccount) {
		accountMap.put(count, userAccount);
		count++;
		return userAccount;
	}

	public String delete(Long accountToRemove) {
		boolean countExists = accountMap.containsKey(accountToRemove);
		if (countExists) {
			accountMap.remove(accountToRemove);
		}

		return "Account successfully removed!";
	}

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
		return (int) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
	}

	@Override
	public List<Account> findAll() {
		List<Account> allAcounts = new ArrayList<>();
		return allAcounts;

	}
}
