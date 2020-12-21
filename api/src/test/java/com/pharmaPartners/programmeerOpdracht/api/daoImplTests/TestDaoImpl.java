package com.pharmaPartners.programmeerOpdracht.api.daoImplTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.Test;

import com.pharmaPartners.programmeerOpdracht.api.dao.JpaBlockChainCurrencyDaoImpl;
import com.pharmaPartners.programmeerOpdracht.api.model.BlockChainCurrency;

public class TestDaoImpl {

	// TODO fix detection of resources this test can't see the required persistenceUnit
	@PersistenceUnit(name = "CurrencyEntityManager")
	private EntityManagerFactory factory;

	private JpaBlockChainCurrencyDaoImpl currencyDao = null;
	private final String name = "TestCurrency";
	private final String tickerCode = "TST";
	private final String marketCap = "100";
	private BlockChainCurrency blockChainCurrency;

	@Test
	public void test() {
		// prepare the data and anything else that needs to be done before testing
		// TODO implement a "private" database for each test so we're not dependant upon existing database's or other
		// tests

		// prepare Dao
		EntityManager manager = factory.createEntityManager();
		currencyDao = new JpaBlockChainCurrencyDaoImpl(manager);

		// prepare BlockChainCurrency for testing
		BlockChainCurrency bcc = new BlockChainCurrency();
		blockChainCurrency.setName(name);
		blockChainCurrency.setTickerCode(tickerCode);
		blockChainCurrency.setMarketCap(marketCap);

		// save currency
		currencyDao.save(blockChainCurrency);
		// check if after persisting the currency the database has created
		Long id = blockChainCurrency.getId();
		// retrieve saved currency
		BlockChainCurrency sbc = currencyDao.findById(id);
		assertNotNull(blockChainCurrency);
		assertEquals(blockChainCurrency.getId(), sbc.getId());
		assertEquals(blockChainCurrency.getName(), sbc.getName());
		assertEquals(blockChainCurrency.getTickerCode(), sbc.getTickerCode());
		assertEquals(blockChainCurrency.getMarketCap(), sbc.getMarketCap());

		List<BlockChainCurrency> CurrenciesBeforeDelete = currencyDao.getCurrencies();
		assertTrue(!CurrenciesBeforeDelete.isEmpty());
		assertTrue(CurrenciesBeforeDelete.contains(sbc));

		currencyDao.delete(sbc.getId());
		List<BlockChainCurrency> CurrenciesAfterDelete = currencyDao.getCurrencies();
		assertTrue(!CurrenciesAfterDelete.isEmpty());
		assertTrue(!CurrenciesAfterDelete.contains(sbc));

	}

}
