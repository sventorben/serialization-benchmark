package de.sven_torben.serialization_benchmark.testdata.avro;

import java.util.List;

import org.joda.time.DateTime;

import de.sven_torben.serialization_benchmark.testdata.avro.Catalog;
import de.sven_torben.serialization_benchmark.testdata.avro.CatalogItem;
import de.sven_torben.serialization_benchmark.testdata.AbstractCatalogCreator;

public final class AvroCatalogCreator extends
		AbstractCatalogCreator<Catalog, CatalogItem> {

	@Override
	protected Catalog createCatalog(final List<CatalogItem> items) {
		return new Catalog(items);
	}

	@Override
	protected CatalogItem createItem(final int id, final String name,
			final int quantity, final double price, final DateTime inStockSince) {
		return new CatalogItem(id, name, quantity, price,
				inStockSince.getMillis());
	}

}
