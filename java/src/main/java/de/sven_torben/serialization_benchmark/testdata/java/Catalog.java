package de.sven_torben.serialization_benchmark.testdata.java;

import java.io.Serializable;


public final class Catalog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatalogItem[] items;
	
	public Catalog(){
		this(new CatalogItem[0]);
	}
	
	public Catalog(final CatalogItem[] items) {
		this.items = items;
	}
	
	public void setItems(final CatalogItem[] items)
	{
		this.items = items;
	}
	
	public CatalogItem[] getItems()
	{
		return items;
	}

}
