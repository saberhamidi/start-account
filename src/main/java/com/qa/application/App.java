package com.qa.application;

import com.qa.domain.Account;
import com.qa.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class App {

	public static void main(String[] args) {
		AccountMapRepository service = new AccountMapRepository();
		JSONUtil util = new JSONUtil();
		Account joeBloggs = new Account("Joe", "Bloggs", "1234");
		Account janeBloggs = new Account("Jane", "Bloggs", "1234");
		service.create(joeBloggs);
		service.create(janeBloggs);
		String mapAsJSON = util.getJSONForObject(service.getAccountMap());
		System.out.println("This is the account map as JSON " + mapAsJSON);

	}

}
