package com.pharmaPartners.programmeerOpdracht.api.modelTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pharmaPartners.programmeerOpdracht.api.model.BlockChainCurrency;

@SpringBootTest
public class TestBlockChainCurrency {

	@Test
	public void testConstructor() {
		BlockChainCurrency blockChainCurrency = new BlockChainCurrency();
		assertNotNull(blockChainCurrency);
	}

	@Test
	public void testSettersAndGetters() {
		BlockChainCurrency blockChainCurrency = new BlockChainCurrency();

		String name = "TestCurrency";
		String tickerCode = "TSC";
		String marketCap = "100";

		blockChainCurrency.setName(name);
		blockChainCurrency.setTickerCode(tickerCode);
		blockChainCurrency.setMarketCap(marketCap);

		// id is still null since we haven't persisted this yet in the db which should generate the id
		assertNull(blockChainCurrency.getId());
		assertEquals(name, blockChainCurrency.getName());
		assertEquals(tickerCode, blockChainCurrency.getTickerCode());
		assertEquals(marketCap, blockChainCurrency.getMarketCap());
	}

}
