package com.fujitsu.accountmgmt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fujitsu.accountmgmt.model.Account;

public class AccountServiceTest {
	AccountService accService = new AccountServiceImpl();

	@BeforeAll
	public static void init() {
		// Logic to initialize test data goes here
		System.out.println("Test data initialization at class level..");
	}

	@AfterAll
	public static void tearDown() {
		// Logic to clean up test data goes here
		System.out.println("Test data clean up at class level..");
	}

	@BeforeEach
	public void setup() {
		System.out.println("Test data initialization at each test case level..");
		// Initialize Test data
		Account account = new Account();
		account.setAccountId(1);
		account.setBalance(2434.00);
		account.setBranch("Pune");
		account.setName("Pankaj");
		account.setType("saving");
		accService.create(account);

		Account account1 = new Account();
		account1.setAccountId(2);
		account1.setBalance(26434.00);
		account1.setBranch("Mum");
		account1.setName("Pan");
		account1.setType("fixed");
		accService.create(account1);
	}

	@AfterEach
	public void cleanup() {
		System.out.println("Test data clean up at each test case level..");
		accService.clear();
	}

	@Test
	public void shouldCreateAccountWhenPassingMandatoryDetails() {
		Account account = new Account();
		account.setAccountId(3);
		account.setName("Dada");
		accService.create(account);

		assertNotNull(accService.get(3));
		assertEquals(3, accService.get(3).getAccountId());
	}

    @Test
    public void shouldShowErrorWhenNotPassingMandatoryDetails() {
        Account account = new Account();
        try {
            accService.create(account);
        }
        catch(Exception e) {
            assertEquals("AccountId mandatory", e.getMessage());
        }    
        
    }

    @Test
    public void shouldUpdateProductForGivenAccountId() {
    	Account account = new Account();
    	account.setName("Rahul");
    	account.setBranch("Pune");
    	account.setType("fixed");

        accService.update(account);

        assertNotNull(accService.get(2));
        assertEquals("fixed",accService.get(2).getType());
    }

    @Test
    public void shouldDeleteAccountWhenPassingValidAccountId() {
        accService.delete(2);
        assertNull(accService.get(2));
        assertEquals(1, accService.getAll().size());
    }

    @Test
    public void shouldReturnAccountForGivenAccountId() {
        assertNotNull(accService.get(2));
        assertEquals(2,accService.get(2).getAccountId());
    }

    @Test
    public void shouldReturnAllAccountsWhenDontSpecifyAccountId() {
          assertEquals(123,accService.getAll().size());
    }
}