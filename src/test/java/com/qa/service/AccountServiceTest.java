package com.qa.service;

import com.qa.repository.AccountMapRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	private AccountMapRepository service;
	private Account joeBloggs;
	private Account janeBloggs;
	private JSONUtil util;

	@Before
	public void init() {
		service = new AccountMapRepository();
		joeBloggs = new Account("Joe", "Bloggs", "1234");
		janeBloggs = new Account("Jane", "Bloggs", "1234");
		util = new JSONUtil();
	}

	@Test
	public void addAndRemoveAccountTest() {
		service.create(joeBloggs);
		Assert.assertEquals(service.getAccountMap().size(), 1);
		service.create(janeBloggs);
		Assert.assertEquals(service.getAccountMap().size(), 2);
	}

	@Test
	public void accountConversionToJSONTest() {
		String emptyMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals("{}", emptyMap);
		String accountAsJSON = "{\"0\":{\"firstName\":\"Joe\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"},\"1\":{\"firstName\":\"Jane\",\"secondName\":\"Bloggs\",\"accountNumber\":\"1234\"}}";
		Assert.assertEquals("{}", emptyMap);
		service.create(joeBloggs);
		service.create(janeBloggs);
		String populatedAccountMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals(accountAsJSON, populatedAccountMap);
	}

	@Test
	public void getCountForFirstNamesInAccount() {
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 0);
		service.create(joeBloggs);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 1);
		Account joeGordon = new Account("Joe", "Gordon", "1234");
		service.create(joeGordon);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 2);
	}

}
