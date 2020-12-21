package com.pharmaPartners.programmeerOpdracht.apiInterfaces;

import java.util.List;

import com.pharmaPartners.programmeerOpdracht.api.model.BlockChainCurrency;

//TODO replace this with JpaRepository which should work better
public interface BlockChainCurrencyDao {
	BlockChainCurrency findById(Long id);

	void save(BlockChainCurrency currency);

	List<BlockChainCurrency> getCurrencies();

	void delete(Long id);
}