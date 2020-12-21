package com.pharmaPartners.programmeerOpdracht.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BlockChainCurrency {

	// id needed to quickly find a specific entry
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	// aangezien we niet weten hoe lang de tickerCode is en of deze meer dan alleen cijfers bevat gebruiken we een
	// String
	@Column
	private String tickerCode;

	// String niet wenselijk TODO veranderen naar BigInt of CLOB
	@Column
	private String marketCap;

	public Long getId() {
		return id;
	}

	// no setId since we don't want anyone to change the id's

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTickerCode() {
		return tickerCode;
	}

	public void setTickerCode(String tickerCode) {
		this.tickerCode = tickerCode;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

}
