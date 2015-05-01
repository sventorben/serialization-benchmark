package de.sven_torben.serialization_benchmark.testdata.java;

import java.io.Serializable;

public final class CatalogItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int quantityAvailable;
	private double price;
	private long inStockSince;

	public void setName(final String name) {
		this.name = name;
	}

	public void setQuantityAvailable(final int quantity) {
		this.quantityAvailable = quantity;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public void setInStockSince(final long since) {
		this.inStockSince = since;
	}

	public String getName() {
		return name;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public double getPrice() {
		return price;
	}

	public long getInStockSince() {
		return inStockSince;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
