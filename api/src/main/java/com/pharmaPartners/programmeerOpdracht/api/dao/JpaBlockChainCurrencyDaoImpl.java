package com.pharmaPartners.programmeerOpdracht.api.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pharmaPartners.programmeerOpdracht.api.model.BlockChainCurrency;
import com.pharmaPartners.programmeerOpdracht.apiInterfaces.BlockChainCurrencyDao;

public class JpaBlockChainCurrencyDaoImpl implements BlockChainCurrencyDao {
	private EntityManager entityManager;
	private Logger log;

	public JpaBlockChainCurrencyDaoImpl(EntityManager entityManager) {
		log = LogManager.getLogger(JpaBlockChainCurrencyDaoImpl.class);
		this.entityManager = entityManager;
	}

	@Override
	public BlockChainCurrency findById(Long id) {
		log.debug("Finding Currency with id=" + id);
		return entityManager.find(BlockChainCurrency.class, id);
	}

	@Override
	public void save(BlockChainCurrency currency) {
		log.info("Saving currency with name=" + currency.getName() + " tickerCode=" + currency.getTickerCode()
				+ " marketCap=" + currency.getMarketCap());
		entityManager.getTransaction().begin();
		entityManager.persist(currency);
		entityManager.getTransaction().commit();
		log.info("Saving currency was succesfull!");

	}

	@Override
	public List<BlockChainCurrency> getCurrencies() {
		log.debug("Getting all currencies in the database");
		Query query = entityManager.createQuery("SELECT s FROM BlockChainCurrency s", BlockChainCurrency.class);
		return query.getResultList();
	}

	// TODO consider returning a response boolean or currency to know whether the delete was succesfull
	@Override
	public void delete(Long id) {
		log.debug("Deleting currency with id=" + id);
		BlockChainCurrency blockChainCurrency = findById(id);
		entityManager.getTransaction().begin();
		entityManager.remove(blockChainCurrency);
		entityManager.getTransaction().commit();
	}
}
