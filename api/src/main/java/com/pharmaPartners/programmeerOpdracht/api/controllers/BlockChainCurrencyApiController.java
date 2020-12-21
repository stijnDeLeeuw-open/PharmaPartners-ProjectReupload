package com.pharmaPartners.programmeerOpdracht.api.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmaPartners.programmeerOpdracht.api.dao.JpaBlockChainCurrencyDaoImpl;
import com.pharmaPartners.programmeerOpdracht.api.model.BlockChainCurrency;

@RestController
public class BlockChainCurrencyApiController {

	@PersistenceUnit(name = "CurrencyEntityManager")
	private EntityManagerFactory factory;

	private JpaBlockChainCurrencyDaoImpl currencyDao;
	private Logger log;

	public BlockChainCurrencyApiController() {
		log = LogManager.getLogger(BlockChainCurrencyApiController.class);
		log.info("Starting BlockChainCurrencyController...");
		log.info("BlockChainCurrencyController started.");
	}

	// TODO remove this it's only for dev purposes to see if the application is running
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		// check if there is already a dao unlikely but if there is no need to make a new one

		return "Hello " + name + "!" + " retrieved: ";
	}

	@PostMapping("/api/currencies")
	public BlockChainCurrency create(@RequestBody BlockChainCurrency bcc) {
		// retrieve a specific record
		// can't log more since we still lack validation so we don't know if we can get a name or something else from
		// the input
		// TODO add validation using @Valid
		log.info("creating currency");
		if (currencyDao == null) {
			EntityManager manager = factory.createEntityManager();
			currencyDao = new JpaBlockChainCurrencyDaoImpl(manager);
		}
		if (bcc.getId() != null) {

		}
		currencyDao.save(bcc);
		return currencyDao.findById(bcc.getId());
	}

	@GetMapping("/api/currencies/{identifier}")
	public BlockChainCurrency read(@PathVariable String identifier) {
		// retrieve a specific record
		log.info("retrieving currency with id=" + identifier);
		if (currencyDao == null) {
			EntityManager manager = factory.createEntityManager();
			currencyDao = new JpaBlockChainCurrencyDaoImpl(manager);
		}
		return currencyDao.findById(Long.valueOf(identifier));
	}

	// TODO add paging and sorting based on requestBody attributes and fix spring detection/mapping
	@GetMapping("/api/currencies")
	public List<BlockChainCurrency> read() {
		// Haalt een alle records op
		log.info("retrieving currencies");
		if (currencyDao == null) {
			EntityManager manager = factory.createEntityManager();
			currencyDao = new JpaBlockChainCurrencyDaoImpl(manager);
		}
		return currencyDao.getCurrencies();
	}

	// TODO consider returning a response message and fix spring detection/mapping
	@PutMapping("/api/currencies/{identifier}")
	public void update(@PathVariable String identifier, @RequestBody BlockChainCurrency bcc) {
		// Update een specific record
		log.info("updating currency with id=" + identifier);
		if (currencyDao == null) {
			EntityManager manager = factory.createEntityManager();
			currencyDao = new JpaBlockChainCurrencyDaoImpl(manager);
		}
		// find the requested currency to update
		BlockChainCurrency bccFromDB = currencyDao.findById(Long.valueOf(identifier));

		// TODO find a smarter way to do this
		// update the existing currency with the currency in the requestbody
		// TODO consider validation of requestBody
		bccFromDB.setName(bcc.getName());
		bccFromDB.setTickerCode(bcc.getTickerCode());
		bccFromDB.setMarketCap(bcc.getMarketCap());

		currencyDao.save(bccFromDB);
	}

	// TODO fix spring detection/mapping
	@DeleteMapping("/api/currencies/{identifier}")
	public void delete(@PathVariable String identifier) {
		// Delete een specifiek record
		log.info("deleting currency with id=" + identifier);
		if (currencyDao == null) {
			EntityManager manager = factory.createEntityManager();
			currencyDao = new JpaBlockChainCurrencyDaoImpl(manager);
		}
		currencyDao.delete(Long.valueOf(identifier));
	}

}
