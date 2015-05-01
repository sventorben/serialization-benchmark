package de.sven_torben.serialization_benchmark.testdata.protobuf;

import java.util.List;

import org.joda.time.DateTime;

import de.sven_torben.serialization_benchmark.testdata.protobuf.CatalogProtos;
import de.sven_torben.serialization_benchmark.testdata.AbstractCatalogCreator;

public class ProtoBufCatalogCreator
		extends
		AbstractCatalogCreator<CatalogProtos.Catalog, CatalogProtos.CatalogItem> {

	@Override
	protected CatalogProtos.Catalog createCatalog(
			final List<CatalogProtos.CatalogItem> items) {
		final CatalogProtos.Catalog.Builder builder = CatalogProtos.Catalog
				.newBuilder();
		for (final CatalogProtos.CatalogItem item : items) {
			builder.addItem(item);
		}
		return builder.build();
	}

	@Override
	protected CatalogProtos.CatalogItem createItem(final int id,
			final String name, final int quantity, final double price,
			final DateTime inStockSince) {
		final CatalogProtos.CatalogItem.Builder builder = CatalogProtos.CatalogItem
				.newBuilder();
		builder.setId(id);
		builder.setInStockSince(inStockSince.getMillis());
		builder.setName(name);
		builder.setPrice(price);
		builder.setQuantityAvailable(quantity);
		return builder.build();
	}

}
