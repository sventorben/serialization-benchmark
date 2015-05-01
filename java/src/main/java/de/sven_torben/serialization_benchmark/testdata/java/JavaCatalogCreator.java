package de.sven_torben.serialization_benchmark.testdata.java;

import java.util.List;

import org.joda.time.DateTime;

import de.sven_torben.serialization_benchmark.testdata.AbstractCatalogCreator;

public final class JavaCatalogCreator extends
		AbstractCatalogCreator<Catalog, CatalogItem> {

	@Override
	protected Catalog createCatalog(final List<CatalogItem> items) {
		return new Catalog(items.toArray(new CatalogItem[items.size()]));
	}

	@Override
	protected CatalogItem createItem(final int id, final String name,
			final int quantity, final double price, final DateTime inStockSince) {
		final CatalogItem item = new CatalogItem();
		item.setId(id);
		item.setName(name);
		item.setQuantityAvailable(quantity);
		item.setPrice(price);
		item.setInStockSince(inStockSince.getMillis());
		return item;
	}

}
